<script>
import { useVuelidate } from '@vuelidate/core';
import { required, helpers, email, minLength, maxLength } from '@vuelidate/validators'

const passwordValidator = helpers.regex(/(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[%||!||*])(?!.* ).{8,42}/);

export default {
    setup() {
        return {
            validator: useVuelidate({ $autoDirty: true })
        }
    },
    data() {
        return {
            inputs: {
                email: "",
                password: "",
                nickname: ""
            }
        }
    },
    validations() {
        return {
            inputs: {
                email: { required, email },
                password: { required, passwordValidator },
                nickname: { required, minLength: minLength(4), maxLength: maxLength(20) }
            }
        }
    },
    methods: {
        async submit() {
            const valid = await this.validator.$validate();
            if (valid) {
                const resp = await this.$http.post("/sign-up", this.inputs);
                if (resp.status === 204) {
                    Object.assign(this.inputs, this.$options.data().inputs);
                    this.validator.$reset();
                    this.$toast.success('toast-global', 'Account created with success.');
                    const signUpModal = document.getElementById('signUp');
                    signUpModal.classList.remove("show");
                    const modalFade = document.querySelector("div.modal-backdrop.fade.show");
                    modalFade.remove();
                } else {
                    this.$toast.error('toast-global', resp.body);
                }
            }
        }
    }
}
</script>

<template>
    <div class="modal fade" id="signUp" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Sign up</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form novalidate @submit.prevent="submit">
                        <div class="mb-3">
                            <label for="email" class="form-label required ">Email</label>
                            <input type="email" class="form-control" name="email" id="email" v-model="inputs.email"
                                :class="{ 'is-invalid': validator.inputs.email.$error }">
                            <div class=" form-text">e.g. john.doe@mail.com</div>
                            <span v-if="validator.inputs.email.$error">
                                {{ validator.inputs.email.$errors[0].$message }}
                            </span>
                        </div>
                        <div class="mb-3">
                            <label for="nickname" class="form-label required ">Nickname</label>
                            <input type="nickname" class="form-control" name="nickname" id="nickname"
                                v-model="inputs.nickname" :class="{ 'is-invalid': validator.inputs.nickname.$error }">
                            <div class=" form-text">e.g. Kevindu67</div>
                            <span v-if="validator.inputs.nickname.$error">
                                {{ validator.inputs.nickname.$errors[0].$message }}
                            </span>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label required">Password</label>
                            <input type="password" name="password" class="form-control" id="password"
                                v-model="inputs.password" :class="{ 'is-invalid': validator.inputs.password.$error }">
                            <div class="form-text">e.g. Garfield2022!</div>
                            <span v-if="validator.inputs.password.$error">
                                {{ validator.inputs.password.$errors[0].$message }}
                            </span>
                        </div>
                        <button type="submit" class="btn btn-outline-dark col-12 col-md-3">Sign
                            up</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>