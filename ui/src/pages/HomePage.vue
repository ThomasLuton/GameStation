<script>
import GameList from '../components/game/GameList.vue';
import PlayerList from '../components/social/PlayerList.vue';
import { mapStores } from 'pinia';
import { useUserStore } from '../stores/userStore';

export default {
    components: {
        GameList: GameList,
        PlayerList: PlayerList
    },
    data() {
        return {
        }
    },
    computed: {
        ...mapStores(useUserStore)
    },
    async mounted() {
        if (this.userStore.name !== "") {
            const connection = await this.$ws.connect({
                nickname: this.userStore.name
            });
            this.userStore.createConnection(connection);
        }
    }
}
</script>
<template>
    <div class="container-fluid">
        <h1 class="text-center my-2">{{ $t('labels.home.welcome') }} {{ userStore.name }}</h1>

        <div class="d-flex flex-column flex-md-row">
            <PlayerList v-if="userStore.isAuthenticated"></PlayerList>
            <GameList></GameList>
        </div>
    </div>
</template>
