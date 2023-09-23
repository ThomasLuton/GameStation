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
            isAuthenticated: localStorage.getItem("isAuthenticated") === "true" ? true : false,
            userName: localStorage.getItem("subject") ? localStorage.getItem("subject") : ""
        }
    }
}
</script>
<template>
    <div class="container-fluid">
        <h1 class="text-center">Welcome at Game Station {{ userName }}</h1>
        <div class="d-flex">
            <PlayerList v-if=isAuthenticated class="d-none d-md-block"></PlayerList>
            <GameList></GameList>
        </div>
    </div>
</template>
