<script>
import { RouterLink } from 'vue-router';
import SignIn from '../player/SignIn.vue';
import SignUp from '../player/SignUp.vue';
import { mapActions } from 'pinia';
import { mapStores } from 'pinia';
import { useUserStore } from '../../stores/userStore';
import NotificationModal from '../notifications/NotificationModal.vue';

export default {
    components: {
        SignIn: SignIn,
        SignUp: SignUp,
        NotificationModal: NotificationModal
    },
    data() {
        return {
            isAuthenticated: false,
            unread: 0
        }
    },
    computed: {
        ...mapStores(useUserStore),
        ...mapActions(useUserStore, ['reset'])
    },
    methods: {
        async disconnect() {
            const resp = await this.$http.post("/players/log-out", null, {
                headers: {
                    Authorization: `Bearer ${this.userStore.token}`
                }
            });
            if (resp.status === 204) {
                this.userStore.reset();
                window.location.reload();
            }
        },
        async getUnreadNotification() {
            const resp = await this.$http.get("notifications/unread", {
                headers: {
                    Authorization: `Bearer ${this.userStore.token}`
                }
            });
            if (resp.status === 200) {
                this.unread = resp.body ? resp.body : 0;
            }
        }
    },
    async beforeMount() {
        this.isAuthenticated = this.userStore.isAuthenticated;
        if (this.isAuthenticated) {
            await this.getUnreadNotification();
        }
    },
}
</script>

<template>
    <header class="sticky-top bg-primary">
        <nav class="container-xl navbar navbar-expand-lg">
            <div class="container-fluid">
                <RouterLink :to="{ name: 'home' }" class="navbar-brand"><img src="../../../public/images/logo_ecrit.png"
                        alt=" logo" width="160" height="40">
                </RouterLink>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li v-if="userStore.isAuthenticated" class="nav-item">
                            <RouterLink :to="{ name: 'history' }" class="nav-link">{{ $t('labels.navigation.history') }}
                            </RouterLink>
                        </li>
                        <li v-if="userStore.isAuthenticated" class="nav-item">
                            <RouterLink :to="{ name: 'admin' }" class="nav-link">{{ $t('labels.navigation.admin') }}
                            </RouterLink>
                        </li>
                        <li v-if="userStore.isAuthenticated" class="nav-item">
                            <RouterLink :to="{ name: 'options' }" class="nav-link">{{ $t('labels.navigation.options') }}
                            </RouterLink>
                        </li>
                        <li v-if="!userStore.isAuthenticated" class="nav-item" type="button" data-bs-toggle="modal"
                            data-bs-target="#signIn">
                            <a class="nav-link" href="#">{{ $t('labels.navigation.signIn') }}</a>
                        </li>
                        <li v-if="!userStore.isAuthenticated" class="nav-item" type="button" data-bs-toggle="modal"
                            data-bs-target="#signUp">
                            <a class="nav-link" href="#">{{ $t('labels.navigation.signUp') }}</a>
                        </li>
                        <li v-if="userStore.isAuthenticated" type="button" @click="disconnect()" class="nav-item">
                            <a class="nav-link" href="#">{{ $t('labels.navigation.logOut') }}</a>
                        </li>
                    </ul>
                </div>
                <button v-if="userStore.isAuthenticated" class="btn ms-auto p-2" type="button" data-bs-toggle="modal"
                    data-bs-target="#notificationsModal">
                    <i v-if="unread === 0" class="bi bi-envelope"></i>
                    <i v-else class="bi bi-envelope-exclamation-fill">{{ unread }}</i>
                </button>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNavDropdown">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
        </nav>
    </header>
    <NotificationModal @reload-unread="getUnreadNotification"></NotificationModal>
    <SignIn></SignIn>
    <SignUp></SignUp>
</template>