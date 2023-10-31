<script>
import GameCard from './GameCard.vue';

export default {
    components: {
        GameCard: GameCard
    },
    data() {
        return {
            games: []
        }
    },
    methods: {
        async getAllGames() {
            const resp = await this.$http.get("/game");
            this.games = resp.body;
        }
    },
    async mounted() {
        await this.getAllGames();
    }
}

</script>
<template>
    <div class="container-fluid col-9">
        <div class="row d-flex justify-content-around">
            <GameCard v-for="game in games" :game="game"></GameCard>
        </div>
    </div>
</template>