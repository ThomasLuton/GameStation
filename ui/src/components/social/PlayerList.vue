<script>
import PlayerCard from './PlayerCard.vue';

export default {
    components: {
        PlayerCard: PlayerCard
    },
    data() {
        return {
            connectedPlayers: ["player 1", "player 2", "player 3"]
        }
    }
}
</script>
<template>
    <div class="container-fluid col-3 border border-dark">
        <div v-for="player in connectedPlayers">
            <PlayerCard></PlayerCard>
        </div>
    </div>
</template>