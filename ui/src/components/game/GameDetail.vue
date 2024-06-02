<script>
import { useConnectedStore } from '../../stores/connectedStore';
import { mapStores } from 'pinia';
import { useUserStore } from '../../stores/userStore';

export default {
    props: {
        id: Number
    },
    data() {
        return {
            game: {},
            gameID: ""
        }
    },
    computed: {
        ...mapStores(useConnectedStore),
        ...mapStores(useUserStore)
    },
    methods: {
        async getGameById(id) {
            const resp = await this.$http.get(`/games/${id}`);
            this.game = resp.body;
        },
        createID() {
            this.gameID = `game${this.game.id}`;
        },
        isAvailable() {
            return this.userStore.isAuthenticated;
        },
        async startSession() {
            const resp = await this.$http.post(`/sessions/create/${this.id}`, null, {
                headers: {
                    Authorization: `Bearer ${this.userStore.token}`
                }
            })
            if (resp.status == 200) {
                this.$modal.remove(this.gameID)
                this.$router.push({
                    name: 'session',
                    params: {
                        sessionCode: resp.body.sessionCode
                    }
                })
            } else {
                this.$modal.remove(this.gameID)
                this.$toast.error('toast-global', resp.body.message)
            }
        }
    },
    async mounted() {
        await this.getGameById(this.id);
        this.createID();
    }
}
</script>

<template>
    <div class="modal fade" :id="gameID" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-primary bg-opacity-10">
                    <h1 class="modal-title fs-5">{{ game.gameName }}</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <img :src="game.thumbnail" class="card-img-top" :alt=game.gameName>
                    <p>{{ $t('labels.game.rules') }}</p>
                    <p>{{ game.gameDetail }}</p>
                    <p>{{ $t('labels.game.playerNumber') }}</p>
                    <p>{{ game.minPlayer }} {{ $t('labels.game.to') }} {{ game.maxPlayer }} {{ $t('labels.game.players')
                        }}
                    </p>
                </div>
                <div class="modal-footer bg-primary bg-opacity-10">
                    <button type="button" class="btn btn-primary" :disabled="!isAvailable()" @click="startSession">{{
        $t('labels.game.play') }}</button>
                </div>
            </div>
        </div>
    </div>
</template>