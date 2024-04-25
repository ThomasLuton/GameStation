<script>
import { mapStores } from 'pinia';
import { useUserStore } from '../../stores/userStore';

export default {
    props: ['player'],
    computed: {
        ...mapStores(useUserStore)
    },
    methods: {
        async addFriend() {
            const resp = await this.$http.post("/friends/request", this.player.gamerTag, {
                headers: {
                    Authorization: `Bearer ${this.userStore.token}`
                }
            })
            if (resp.status === 204) {
                this.$toast.success("toast-global", "Request sent");
            } else {
                this.$toast.error("toast-global", resp.body.message);
            }
        }
    }
}
</script>

<template>
    <li class="list-group-item d-flex py-0" style="max-height: 70px;">
        <div class="mx-2">
            <img v-if="player.avatar" :src="'/images/' + player.avatar" style="height: 70px; width: 70px;" alt="avatar">
            <img v-else src="/images/noAvatar.png" style="height: 70px; width: 70px;" alt="avatar">
        </div>
        <div class="py-1">
            {{ player.gamerTag.playerName }}#{{ player.gamerTag.suffix }}
            <button type="button" @click="addFriend" class="btn btn-primary">Add a friend</button>
        </div>

    </li>
</template>