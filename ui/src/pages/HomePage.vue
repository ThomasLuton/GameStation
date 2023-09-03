<script>
import GameList from '../components/game/GameList.vue';
import PlayerList from '../components/social/PlayerList.vue';
export default {
    components: {
        GameList: GameList,
        PlayerList: PlayerList
    },
    data() {
        return {
            isAuthenticated: true
        }
    }
}
</script>
<template>
    <div class="container-fluid">
        <h1 class="text-center">Game Station</h1>
        <div class="d-flex">
            <PlayerList v-if=isAuthenticated></PlayerList>
            <GameList></GameList>
        </div>
    </div>
</template>
