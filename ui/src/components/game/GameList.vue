<script>
import GameCard from './GameCard.vue';

export default {
    components: {
        GameCard: GameCard
    },
    data() {
        return {
            games: [1, 2, 3, 4, 5, 6]
        }
    }
}

</script>
<template>
    <div class="container-fluid col-9 border border-dark">
        <div class="container" v-for="game in games">
            <GameCard></GameCard>
        </div>
    </div>
</template>