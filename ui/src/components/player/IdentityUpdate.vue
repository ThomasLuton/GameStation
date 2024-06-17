<script>
import { useVuelidate } from '@vuelidate/core'
import { mapStores } from 'pinia'
import { useUserStore } from '../../stores/userStore'
import { required } from '@vuelidate/validators'

export default {
    setup() {
        return {
            validator: useVuelidate({ $autoDirty: true })
        }
    },
    props: ['player'],
    data() {
        return {
            inputs: {
                file: null
            }
        }
    },
    computed: {
        ...mapStores(useUserStore)
    },
    validations() {
        return {
            inputs: {
                file: {
                    required,
                    maxValue: (file) => {
                        return file === null || file.size < 5000512000
                    }
                }
            }
        }
    },
    methods: {
        async updatePlayerName() {
            const input = {
                playerName: this.player.gamerTag.playerName
            }
            const resp = await this.$http.patch("/players/update/player-name", input, {
                headers: {
                    Authorization: `Bearer ${this.userStore.token}`
                }
            })
            if (resp.status === 204) {
                this.$toast.success('toast-global', "Player name updated");
                this.userStore.changeName(input.playerName);
            } else {
                this.$toast.error('toast-global', "The name should have 20 characters or less")
            }
        },
        async updateAvatar() {
            const valid = await this.validator.$validate()
            if (valid) {
                const formData = new FormData();
                formData.append("avatar", this.inputs.file);
                const resp = await this.$http.patch("/players/update/avatar", formData, {
                    headers: {
                        Authorization: `Bearer ${this.userStore.token}`
                    }
                });
                if (resp.status === 204) {
                    this.$toast.success('toast-global', 'Avatar updated')
                    this.$emit("avatarUpdated")
                } else {
                    this.$toast.error('toast-global', 'Error with this file')
                }
            }
        },
        handleFileUpload(event) {
            this.inputs.file = event.target.files[0]
        }
    }
}

</script>
<template>
    <h3>Player identity options</h3>
    <form class="border-bottom border-primary" @submit.prevent="updatePlayerName">
        <div class="mb-3">
            <label class="form-label" for="name">Name</label>
            <input class="form-control" v-model="player.gamerTag.playerName" type="text" name="name" id="name"
                maxlength="20">
        </div>
        <button class="mb-3 btn btn-primary" type="submit">Update name</button>
    </form>
    <form novalidate @submit.prevent="updateAvatar">
        <div class="mb-3 d-flex justify-content-between">
            <div>
                <label class="form-label" for="avatar">Avatar</label>
                <input :class="{ 'is-invalid': validator.inputs.file.$error }" name="formFile" id="formFile"
                    class="form-control" type="file" accept="image/png,image/gif,image/jpeg"
                    @change="handleFileUpload" />
                <div class="form-text text-danger" v-if="validator.inputs.file.$error">
                    {{ validator.inputs.file.$error[0] }}
                </div>
                <div class="form-text mb-3" v-else>Photo, avatar or any image.</div>
            </div>
            <div>
                <img v-if="player.avatar" :src="'/images/' + player.avatar" style="height: 100px; widows: 100px;"
                    alt="avatar">
                <img v-else src="/images/noAvatar.png" style="height: 100px; widows: 100px;" alt="avatar">
            </div>
        </div>
        <button class=" mb-3 btn btn-primary" type="submit">Update avatar</button>
    </form>
</template>