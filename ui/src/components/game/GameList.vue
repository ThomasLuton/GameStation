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
            favorites: []
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
            // const resp = await this.$http.get(`/favorites/${this.userStore.id}`, {
            //     headers: {
            //         Authorization: `Bearer ${token}`
            //     }
            // });
            // if (resp.status == 200) {
            //     if (resp.body != null) {
            //         this.favorites = resp.body;
            //     } else {
            //         this.favorites = [];
            //     }
            // }
        },
        isFavorite(game) {
            if (this.favorites.length > 0) {
                for (let i = 0; i < this.favorites.length; i++) {
                    if (this.favorites[i].gameName === game.gameName) {
                        return this.favorites[i];
                    }
                }
            }
            return null;
        }
    },
    async beforeMount() {
        if (this.userStore.isAuthenticated) {
            await this.getAllFavorites();
        }
        await this.getAllGames();
    }
    //un endpoint pour les jeux et si le user est connecté ramene ces favoris => ecoconception
}

</script>
<template>
    <div class="container-fluid col-9">
        <div class="row d-flex justify-content-around">
            <GameCard v-for="game in games" :game="game" :favorite="isFavorite(game)"></GameCard>
        </div>
    </div>
</template>