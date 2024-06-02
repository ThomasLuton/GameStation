<script>
import GameCard from './GameCard.vue';
import { useUserStore } from '../../stores/userStore';
import { mapStores } from 'pinia';

export default {
    components: {
        GameCard: GameCard
    },
    data() {
        return {
            games: [],
            favorites: [],
            sessionCode: null
        }
    },
    computed: {
        ...mapStores(useUserStore)
    },
    methods: {
        async getAllGames() {
            const resp = await this.$http.get("/games");
            this.games = resp.body;
        },
        async getAllFavorites() {
            const token = this.userStore.token;
            const resp = await this.$http.get(`/favorites`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            if (resp.status == 200) {
                if (resp.body != null) {
                    this.favorites = resp.body;
                } else {
                    this.favorites = [];
                }
            }
        },
        isFavorite(game) {
            if (this.favorites.length > 0) {
                for (let i = 0; i < this.favorites.length; i++) {
                    if (this.favorites[i].game.gameName === game.gameName) {
                        return this.favorites[i];
                    }
                }
            }
            return null;
        },
        async getCurrentSession() {
            const token = this.userStore.token;
            const resp = await this.$http.get("/sessions/current", {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
            if (resp.status == 200 && resp.body != null) {
                this.sessionCode = resp.body.sessionCode;
            }
        },
        goToCurrentSession() {
            this.$router.push({
                name: 'session',
                params: {
                    sessionCode: this.sessionCode
                }
            })
        },
        noCurrentSession() {
            return this.sessionCode == null;
        }
    },
    async beforeMount() {
        if (this.userStore.isAuthenticated) {
            await this.getAllFavorites();
        }
        await this.getAllGames();
        await this.getCurrentSession();
    }
}

</script>
<template>
    <div class="container-fluid col-9">
        <div class="d-flex justify-content-between">
            <button type="button" class="btn btn-danger" @click="goToCurrentSession" :hidden="noCurrentSession()">Join
                current party</button>
            <form v-if="userStore.isAuthenticated" novalidate @submit.prevent="" class="d-flex justify-content-end">
                <div class="d-flex p-2 border rounded border-secondary">
                    <div class="mx-4">
                        <label class="form-label" for="joinGame">Type a game code</label>
                        <input class="form-control" type="text" name="joinGame" id="joinGame"
                            placeholder="Not available">
                    </div>
                    <div class="pt-3">
                        <button class="btn btn-primary" type="submit">Play</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="row d-flex justify-content-around">
            <GameCard v-for="game in games" :game="game" :favorite="isFavorite(game)"></GameCard>
        </div>
    </div>
</template>