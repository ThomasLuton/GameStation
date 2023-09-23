<script>
import { useUserStore } from '../../stores/userStore';
import { mapStores } from 'pinia';

export default {
    data() {
        return {
            credentials: {
                email: "",
                password: ""
            }
        }
    },
    computed: {
        ...mapStores(useUserStore)
    },
    methods: {
        async submit() {
            const resp = await this.$http.post("/sign-in", this.credentials);
            if (resp.status === 200) {
                this.$toast.success('toast-global', `Welcome back ${resp.body.subject}`);
                const signInModal = document.getElementById('signIn');
                signInModal.classList.remove("show");
                const modalFade = document.querySelector("div.modal-backdrop.fade.show");
                modalFade.remove();

                this.setUserStore(resp.body);
            } else {
                this.$toast.error('toast-global', "Wrong credentials");
            }
        },
        setUserStore(tokenInfo) {
            this.userStore.isAuthenticated = true;
            this.userStore.name = tokenInfo.subject;
            this.userStore.token = tokenInfo.token;
        }
    }
}

</script>

<template>
    <div class="modal fade" id="signIn" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Sign in</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form novalidate @submit.prevent="submit">
                        <div class="mb-3">
                            <label for="email" class="form-label required ">Email</label>
                            <input type="email" class="form-control" name="email" id="email" v-model="credentials.email">
                            <div class=" form-text">e.g. john.doe@mail.com</div>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label required">Password</label>
                            <input type="password" name="password" class="form-control" id="password"
                                v-model="credentials.password">
                            <div class="form-text">e.g. Garfield2022!</div>
                        </div>
                        <button type="submit" class="btn btn-outline-dark col-12 col-md-3">Sign up</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>