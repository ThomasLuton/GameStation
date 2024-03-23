<script>
import { useVuelidate } from '@vuelidate/core';
import { required, helpers, email, minLength, maxLength } from '@vuelidate/validators'

const passwordValidator = helpers.regex(/(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[%||!||*])(?!.* ).{8,42}/);
const passwordInvalid = helpers.withMessage("Your password does'nt respect the pattern", passwordValidator);

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
                playerName: ""
            }
        }
    },
    validations() {
        return {
            inputs: {
                email: { required, email },
                password: { required, passwordInvalid },
                playerName: { required, minLength: minLength(4), maxLength: maxLength(20) }
            }
        }
    },
    methods: {
        async submit() {
            const valid = await this.validator.$validate();
            if (valid) {
                const resp = await this.$http.post("/players/sign-up", this.inputs);
                if (resp.status === 204) {
                    Object.assign(this.inputs, this.$options.data().inputs);
                    this.validator.$reset();
                    this.$toast.success('toast-global', 'Account created with success.');
                    this.$modal.remove('signUp');
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
                <div class="modal-header bg-primary bg-opacity-10">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">{{ $t('labels.auth.signUp') }}</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form novalidate @submit.prevent="submit">
                        <div class="mb-3">
                            <label for="email" class="form-label required ">{{ $t('labels.auth.email') }}</label>
                            <input type="email" class="form-control" name="email" id="email" v-model="inputs.email"
                                :class="{ 'is-invalid': validator.inputs.email.$error }">
                            <div class=" form-text">{{ $t('labels.auth.helpEmail') }}</div>
                            <span v-if="validator.inputs.email.$error">
                                {{ validator.inputs.email.$errors[0].$message }}
                            </span>
                        </div>
                        <div class="mb-3">
                            <label for="playerName" class="form-label required ">{{ $t('labels.auth.playerName')
                                }}</label>
                            <input type="playerName" class="form-control" name="playerName" id="playerName"
                                v-model="inputs.playerName"
                                :class="{ 'is-invalid': validator.inputs.playerName.$error }">
                            <div class=" form-text">{{ $t('labels.auth.helpPlayerName') }}</div>
                            <span v-if="validator.inputs.playerName.$error">
                                {{ validator.inputs.playerName.$errors[0].$message }}
                            </span>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label required">{{ $t('labels.auth.password') }}</label>
                            <input type="password" name="password" class="form-control" id="password"
                                v-model="inputs.password" :class="{ 'is-invalid': validator.inputs.password.$error }">
                            <div class="form-text">{{ $t('labels.auth.helpPassword') }}</div>
                            <span v-if="validator.inputs.password.$error">
                                {{ validator.inputs.password.$errors[0].$message }}
                            </span>
                        </div>
                        <button type="submit" class="btn btn-primary col-12 col-md-3">{{ $t('labels.auth.signUp')
                            }}</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>