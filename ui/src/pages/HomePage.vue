<script>
import GameList from '../components/game/GameList.vue';
import PlayerList from '../components/social/PlayerList.vue';
import { mapStores } from 'pinia';
import { useUserStore } from '../stores/userStore';
import { jwtDecode } from "jwt-decode";

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
        if (this.userStore.token !== "") {
            const token = this.userStore.token;
            const decoded = jwtDecode(token);
            const now = Math.floor(Date.now() / 1000);
            if (decoded.exp > now) {
                console.log("expire in " + (decoded.exp - now) + "sec")
                const connection = await this.$ws.connect({
                    nickname: this.userStore.name
                });
                this.userStore.createConnection(connection);
            } else {
                this.userStore.reset();
            }
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
