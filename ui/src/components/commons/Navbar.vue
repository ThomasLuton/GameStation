<script>
import { RouterLink } from 'vue-router';
import SignIn from '../user/SignIn.vue';
import SignUp from '../user/SignUp.vue';
import { mapActions } from 'pinia';
import { mapStores } from 'pinia';
import { useUserStore } from '../../stores/userStore';

export default {
    components: {
        SignIn: SignIn,
        SignUp: SignUp
    },
    data() {
        return {
            isAuthenticated: localStorage.getItem("isAuthenticated") === "true" ? true : false
        }
    },
    computed: {
        ...mapStores(useUserStore),
        ...mapActions(useUserStore, ['reset'])
    },
    methods: {
        switchAuth() {
            if (this.userStore.isAuthenticated) {
                this.disconnect();
            } else {
                this.userStore.isAuthenticated = true;
                this.userStore.name = "Toto le store"
            }
        },
        disconnect() {
            this.userStore.reset();
            this.$router.push("/");
        }
    }
}
</script>

<template>
    <header class="sticky-top bg-dark">
        <nav class="container-xl navbar navbar-dark navbar-expand-lg">
            <div class="container-fluid">
                <RouterLink :to="{ name: 'home' }" class="navbar-brand">Logo</RouterLink>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <button class="btn btn-light" type="button" @click="switchAuth()">Switch
                        auth</button>
                    <ul class="navbar-nav">
                        <li v-if="userStore.isAuthenticated" class="nav-item">
                            <RouterLink :to="{ name: 'history' }" class="nav-link">GameHistory</RouterLink>
                        </li>
                        <li v-if="!userStore.isAuthenticated" class="nav-item" type="button" data-bs-toggle="modal"
                            data-bs-target="#signIn">
                            <a class="nav-link" href="#">Sign In</a>
                        </li>
                        <li v-if="!userStore.isAuthenticated" class="nav-item" type="button" data-bs-toggle="modal"
                            data-bs-target="#signUp">
                            <a class="nav-link" href="#">Sign Up</a>
                        </li>
                        <li v-if="userStore.isAuthenticated" type="button" @click="disconnect()" class="nav-item">
                            <a class="nav-link" href="#">Log Out</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <SignIn></SignIn>
    <SignUp></SignUp>
</template>