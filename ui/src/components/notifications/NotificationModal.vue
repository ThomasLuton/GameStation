<script>
import { mapStores } from 'pinia';
import { useUserStore } from '../../stores/userStore';

export default {
    data() {
        return {
            notifications: []
        }
    },
    computed: {
        ...mapStores(useUserStore)
    },
    methods: {
        async getPlayerNotifications() {
            const resp = await this.$http.get("/notifications", {
                headers: {
                    Authorization: `Bearer ${this.userStore.token}`
                }
            });
            if (resp.status === 200) {
                this.notifications = resp.body
            }
        }
    },
    async mounted() {
        if (this.userStore.isAuthenticated) {
            await this.getPlayerNotifications();
        }
    }
}

</script>
<template>
    <div class="modal fade" id="notificationsModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-primary bg-opacity-10">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Notifications</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div v-for="notification in notifications">{{ notification }}</div>
                </div>
            </div>
        </div>
    </div>
</template>