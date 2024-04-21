<script>
import { mapStores } from 'pinia';
import { useUserStore } from '../../stores/userStore';
import { useVuelidate } from '@vuelidate/core';
import { required, minValue } from '@vuelidate/validators'

export default {
    setup() {
        return {
            validator: useVuelidate({ $autoDirty: true })
        }
    },
    props: ['player'],
    computed: {
        ...mapStores(useUserStore)
    },
    validations() {
        return {
            player: {
                emailNotificationEnable: { required },
                dayBeforeNotification: { minValue: minValue(1) },
            }
        }
    },
    methods: {
        async update() {
            const valid = await this.validator.$validate();
            if (valid) {
                const formData = {
                    activate: this.player.emailNotificationEnable,
                    daysBefore: this.player.dayBeforeNotification
                }
                const resp = await this.$http.patch("/players/update/notification", formData, {
                    headers: {
                        Authorization: `Bearer ${this.userStore.token}`
                    }
                })
                if (resp.status === 204) {
                    this.$toast.success('toast-global', "Notification setup updated")
                } else {
                    this.$toast.error('toast-global', resp.body)
                }
            }
        }
    }
}

</script>
<template>
    <h3>Notifications Options</h3>
    <form novalidate @submit.prevent="update">
        <div class=" mb-3 form-check">
            <input class="form-check-input" v-model="player.emailNotificationEnable" type="checkbox" name="enable"
                id="enable">
            <label class="form-check-label" for="enable">Reminder email enabled</label>
            <span v-if="validator.player.emailNotificationEnable.$error">
                {{ validator.player.emailNotificationEnable.$errors[0].$message }}
            </span>
        </div>
        <div class="mb-3">
            <label class="form-label" for="days">
                Number of days before sending an email: </label>
            <input class="form-control" :class="{ 'is-invalid': validator.player.dayBeforeNotification.$error }"
                :disabled="!player.emailNotificationEnable" v-model="player.dayBeforeNotification" type="number"
                name="days" id="days">
            <span v-if="validator.player.dayBeforeNotification.$error">
                {{ validator.player.dayBeforeNotification.$errors[0].$message }}
            </span>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</template>