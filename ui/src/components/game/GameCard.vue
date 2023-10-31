<script>
import { useUserStore } from '../../stores/userStore';
import GameDetail from './GameDetail.vue';
import { mapStores, mapActions } from 'pinia';

export default {
    components: {
        GameDetail: GameDetail
    },
    props: {
        game: {}
    },
    data() {
        return {
            gameID: "",
            favoriteClass: ""
        }
    },
    computed: {
        ...mapStores(useUserStore),
        ...mapActions(useUserStore, ['addFavorite', 'removeFavorite'])
    },
    methods: {
        createID() {
            this.gameID = `#game${this.game.id}`;
        },
        isFavorite() {
            const favorites = this.userStore.favorites;
            for (let i = 0; i < favorites.length; i++) {
                if (favorites[i].gameName === this.game.gameName) {
                    return true;
                }
            }
            return false;
        },
        async switchFavorite() {
            const token = this.userStore.token;
            if (this.isFavorite()) {
                const favorites = this.userStore.favorites;
                let favorite = {};
                for (let i = 0; i < favorites.length; i++) {
                    if (favorites[i].gameName === this.game.gameName) {
                        favorite = favorites[i];
                    }
                }
                const resp = await this.$http.delete(`favorites/${favorite.id}`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                if (resp.status == 204) {
                    console.log("Remove " + favorite.id);
                }
            } else {
                const input = {
                    userID: this.userStore.id,
                    gameID: this.game.id
                }
                const resp = await this.$http.post(`favorites/${input.userID}/${input.gameID}`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                if (resp.status == 204) {
                    console.log("Add game " + input.gameID + " to user " + input.userID);
                }
            }
        }
    },
    mounted() {
        this.createID();
    },
    updated() {
        this.favoriteClass = this.isFavorite() ? "bi bi-star-fill text-secondary" : "bi bi-star";
    }
}
</script>
<template>
    <div class="card mx-1 my-3 col-12 col-md-5 col-lg-3 border-primary border-5 bg-primary bg-opacity-10 rounded-4">
        <div class="card-body position-relative">
            <h5 class="card-title text-center">{{ game.gameName }}</h5>
            <button v-if="userStore.isAuthenticated" class="btn position-absolute top-0 end-0"><i :class="favoriteClass"
                    @click="switchFavorite"></i></button>

            <button type="button" class="btn" data-bs-toggle="modal" :data-bs-target="gameID">
                <img :src="game.thumbnail" class="card-img-top" :alt=game.gameName>
            </button>
        </div>
    </div>
    <GameDetail :id="game.id"></GameDetail>
</template>