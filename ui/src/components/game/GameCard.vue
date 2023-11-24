<script>
import { useUserStore } from '../../stores/userStore';
import GameDetail from './GameDetail.vue';
import { mapStores } from 'pinia';

export default {
    components: {
        GameDetail: GameDetail
    },
    props: {
        game: {},
        favorite: {}
    },
    data() {
        return {
            gameID: "",
            favoriteClass: ""
        }
    },
    computed: {
        ...mapStores(useUserStore)
    },
    methods: {
        createID() {
            this.gameID = `#game${this.game.id}`;
        },
        createFavoriteClass() {
            this.favoriteClass = this.favorite ? "bi bi-star-fill text-secondary" : "bi bi-star";
        },
        async switchFavorite() {
            const token = this.userStore.token;
            const resp = await this.$http.patch(`favorites/${this.game.id}`, null, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            if (resp.body == null) {
                this.favoriteClass = "bi bi-star";
            } else {
                this.favoriteClass = "bi bi-star-fill text-secondary";
            }
        }
    },
    mounted() {
        this.createID();
        this.createFavoriteClass();
    }
}
</script>
<template>
    <div class="card mx-1 my-3 col-12 col-md-5 col-lg-3 border-primary border-5 bg-primary bg-opacity-10 rounded-4">
        <div class="card-body position-relative">
            <h5 class="card-title text-center">{{ game.gameName }}</h5>
            <button v-if="userStore.isAuthenticated" class="btn position-absolute top-0 end-0" @click="switchFavorite"><i
                    :class="favoriteClass"></i></button>
            <button type="button" class="btn" data-bs-toggle="modal" :data-bs-target="gameID">
                <img :src="game.thumbnail" class="card-img-top" :alt=game.gameName>
            </button>
        </div>
    </div>
    <GameDetail :id="game.id"></GameDetail>
</template>