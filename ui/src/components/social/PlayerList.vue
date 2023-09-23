<script>
import PlayerCard from './PlayerCard.vue';

export default {
    components: {
        PlayerCard: PlayerCard
    },
    data() {
        return {
            user: {
                name: "user",
                role: "user"
            },
            groupedPlayer1: {
                name: "Pote numéro 1",
                role: "group"
            },
            groupedPlayer2: {
                name: "Pote numéro 2",
                role: "group"
            },
            randomPlayer: {
                name: "random pelo",
                role: ""
            },
            connectedPlayers: []
        }
    },
    mounted() {
        this.connectedPlayers.push(this.user);
        this.connectedPlayers.push(this.groupedPlayer1);
        this.connectedPlayers.push(this.groupedPlayer2);
        this.connectedPlayers.push(this.randomPlayer);
        this.connectedPlayers.push(this.randomPlayer);
        this.connectedPlayers.push(this.randomPlayer);
    }

}
</script>
<template>
    <div class="container-fluid col-3">
        <ul class="list-group">
            <PlayerCard v-for="player in connectedPlayers" :player="player"></PlayerCard>
        </ul>
    </div>
</template>