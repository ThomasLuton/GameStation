<script>
import PlayerCard from './PlayerCard.vue';
import { mapStores } from 'pinia';
import { useConnectedStore } from '../../stores/connectedStore'

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
    computed: {
        ...mapStores(useConnectedStore)
    },
    mounted() {
        this.connectedPlayers = this.connectedStore.users;
        console.log("connected player from list = " + this.connectedPlayers);
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