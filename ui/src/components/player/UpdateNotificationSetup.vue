<script>
import { mapStores } from 'pinia';
import { useUserStore } from '../../stores/userStore';

export default {
    props: ['player'],
    computed: {
        ...mapStores(useUserStore)
    },
    methods: {
        async update() {
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
                window.location.reload();
            } else {
                console.log(resp.body)
            }
        }
    }
}

</script>
<template>
    <h3>Gestion des notifications</h3>
    <form>
        <div>
            <label for="enable">Email de rappel activé</label>
            <input v-model="player.emailNotificationEnable" type="checkbox" name="enable" id="enable">
        </div>
        <div>
            <label for="days">Nombre de jours avant l'envoie d'un email</label>
            <input :disabled="!player.emailNotificationEnable" v-model="player.dayBeforeNotification" type="number"
                name="days" id="days">
        </div>
        <button @click="update">Mettre à jour</button>
    </form>
</template>