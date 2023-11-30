<script>
import { useRoute } from 'vue-router';
import { mapStores } from 'pinia';
import { useVuelidate } from '@vuelidate/core';
import { required, maxLength, minValue, between } from '@vuelidate/validators';
import { useUserStore } from '../../stores/userStore';

export default {
    setup() {
        return {
            route: useRoute(),
            validator: useVuelidate({ $autoDirty: true })
        }
    },
    computed: {
        ...mapStores(useUserStore)
    },
    data() {
        return {
            id: this.route.params.id,
            inputs: {
                gameName: null,
                gameDetail: null,
                minPlayer: 0,
                maxPlayer: 0,
                victoryPoint: 0,
                drawPoint: 0,
                losePoint: 0,
                available: null
            }
        }
    },
    validations() {
        return {
            inputs: {
                gameName: { required, maxLength: maxLength(255) },
                gameDetail: { required, maxLength: maxLength(1000) },
                minPlayer: { required },
                maxPlayer: { required },
                victoryPoint: { required },
                drawPoint: { required },
                losePoint: { required }
            }
        }
    },
    methods: {
        async submit() {
            const token = this.userStore.token;
            const resp = await this.$http.patch(`/game/${this.id}`, this.inputs, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            if (resp.status === 204) {
                this.$toast.success('toast-global', 'Game updated with success.');
                this.$router.push({ name: 'admin' });
            } else {
                console.error(resp);
                this.$toast.error('toast-global', 'Server conversion or validation error.');
            }
        },
        async initInputs() {
            const resp = await this.$http.get(`/game/${this.id}`);
            this.inputs = resp.body;
            console.log(this.inputs);
        }
    },
    beforeMount() {
        this.initInputs();
    }
}
</script>

<template>
    <h1 class="text-center my-3">Update sticker</h1>
    <form @submit.prevent="submit" novalidate class="container-xl">
        <div class="row mb-3">
            <div class="col-12">
                <label for="gameName" class="form-label required">Game name</label>
                <input v-model.trim="inputs.gameName" id="gameName" name="gameName" type="text" class="form-control"
                    :class="{ 'is-invalid': validator.inputs.gameName.$error }">
                <div class="form-text">Must be a unique gameName and not exceed 255 characters.</div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-12">
                <label for="gameDetail" class="form-label required">Game Detail</label>
                <textarea v-model.trim="inputs.gameDetail" id="gameDetail" name="gameDetail" rows="5" class="form-control"
                    :class="{ 'is-invalid': validator.inputs.gameDetail.$error }"></textarea>
                <div class="form-text">Must not exceed 1000 characters.</div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="minPlayer" class="form-label required">Minimun player</label>
                <div class="input-group">
                    <input v-model.number="inputs.minPlayer" id="minPlayer" name="minPlayer" type="number"
                        class="form-control" :class="{ 'is-invalid': validator.inputs.minPlayer.$error }">
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label for="maxPlayer" class="form-label required">Maximun player</label>
                <div class="input-group">
                    <input v-model.number="inputs.maxPlayer" id="maxPlayer" name="maxPlayer" type="number"
                        class="form-control" :class="{ 'is-invalid': validator.inputs.maxPlayer.$error }">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 mb-3">
                <label for="victoryPoint" class="form-label required">Victory Point</label>
                <div class="input-group">
                    <input v-model.number="inputs.victoryPoint" id="victoryPoint" name="victoryPoint" type="number"
                        class="form-control" :class="{ 'is-invalid': validator.inputs.victoryPoint.$error }">
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <label for="drawPoint" class="form-label required">Draw Point</label>
                <div class="input-group">
                    <input v-model.number="inputs.drawPoint" id="drawPoint" name="drawPoint" type="number"
                        class="form-control" :class="{ 'is-invalid': validator.inputs.drawPoint.$error }">
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <label for="losePoint" class="form-label required">Lose Point</label>
                <div class="input-group">
                    <input v-model.number="inputs.losePoint" id="losePoint" name="losePoint" type="number"
                        class="form-control" :class="{ 'is-invalid': validator.inputs.losePoint.$error }">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 mb-3">
                <label for="isAvailable" class="form-label required">Available</label>
                <div class="input-group">
                    <input v-model.checkbox="inputs.available" id="isAvailable" name="isAvailable" type="checkbox"
                        class="form-check-input">
                </div>
            </div>
            <div class="d-grid d-md-flex justify-content-md-end mb-3">
                <button type="submit" class="btn btn-dark" :disabled="validator.$invalid">Update Game</button>
            </div>
        </div>
    </form>
</template>