<script>
import { mapStores } from 'pinia';
import { useUserStore } from '../../stores/userStore';

export default {
    data() {
        return {
            notifications: [],
            openNotifications: new Map()
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
                this.notifications.forEach((notification) => {
                    notification.sendAt = notification.sendAt.slice(0, 10)
                })
            }
        },
        async openNotification(id) {
            const resp = await this.$http.get(`/notifications/${id}`, {
                headers: {
                    Authorization: `Bearer ${this.userStore.token}`
                }
            });

            if (resp.status === 200) {
                this.openNotifications.set(id, resp.body)
                this.notifications.filter((notification) => notification.id == id)[0].read = true;
                this.$emit('reloadUnread');
            }
        },
        closeNotification(id) {
            this.openNotifications.delete(id)
        },
        async markAsUnread(id) {
            const resp = await this.$http.patch(`/notifications/unread/${id}`, null, {
                headers: {
                    Authorization: `Bearer ${this.userStore.token}`
                }
            })
            if (resp.status === 204) {
                this.openNotifications.delete(id);
                this.notifications.filter((notification) => notification.id === id)[0].read = false;
                this.$emit('reloadUnread')
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
                    <div v-for="notification in  notifications ">
                        <div class="d-flex mt-2 mb-1 border border-primary rounded bg-primary bg-opacity-10">
                            <p class="mx-1">{{ notification.title }}</p>
                            <p class="mx-1">Envoyé le {{ notification.sendAt }}</p>
                            <i v-if="notification.read" class="bi bi-envelope ms-auto p-2"></i>
                            <i v-else class="bi bi-envelope-exclamation-fill ms-auto p-2"></i>
                            <button class="btn btn-primary m-1"
                                @click="openNotification(notification.id)">Ouvrir</button>
                        </div>
                        <div v-if="openNotifications.get(notification.id)" :id="notification.id"
                            class="d-flex flex-column border border-secondary rounded bg-secondary bg-opacity-10">
                            <p class="mx-1">{{ openNotifications.get(notification.id).content }}</p>
                            <p v-if="openNotifications.get(notification.id).redirectLink" class="mx-1">{{
                        openNotifications.get(notification.id).redirectLink }}</p>
                            <div class="d-flex justify-content-between">
                                <button class="btn btn-secondary m-1" @click="markAsUnread(notification.id)">Marquer
                                    comme non
                                    lu</button>
                                <button @click="closeNotification(notification.id)"
                                    class="btn btn-secondary m-1">Fermer</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>