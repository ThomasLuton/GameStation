<script>
import { useUserStore } from '../../stores/userStore';
import { mapStores, mapActions } from 'pinia';

export default {
    data() {
        return {
            credentials: {
                email: "",
                password: ""
            },
            connectInput: {
                nickname: ""
            }
        }
    },
    computed: {
        ...mapStores(useUserStore),
        ...mapActions(useUserStore, ['createConnection'])
    },
    methods: {
        async submit() {
            const resp = await this.$http.post("/sign-in", this.credentials);
            if (resp.status === 200) {
                this.$toast.success('toast-global', `Welcome back ${resp.body.subject}`);
                this.$modal.remove('signIn');
                this.connectInput.nickname = resp.body.subject;
                const connection = this.$ws.connect(this.connectInput);
                this.userStore.createConnection(connection);
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
                <div class="modal-header bg-primary bg-opacity-10">
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
                        <button type="submit" class="btn btn-primary col-12 col-md-3">Sign up</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>