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
            //temporaire pour les tests 
            //supprimer la boucle puis enlever le commentaire
            //this.games = resp.body;
            for (let i = 0; i < 5; i++) {
                this.games.push(resp.body[0]);
                this.games.push(resp.body[1]);
            }
        }
    },
    async mounted() {
        await this.getAllGames();
    }
}

</script>
<template>
    <div class="container-fluid col-9 border border-dark rounded">
        <div class="row d-flex justify-content-around">
            <GameCard v-for="game in games" :game="game"></GameCard>
        </div>
    </div>
</template>