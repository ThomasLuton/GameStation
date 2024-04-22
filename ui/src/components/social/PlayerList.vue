<script>
import UserCard from './UserCard.vue';
import OtherCard from './OtherCard.vue';
import ConnectedFriendCard from './ConnectedFriendCard.vue'
import { mapStores, mapActions } from 'pinia';
import { useConnectedStore } from '../../stores/connectedStore'

export default {
    components: {
        UserCard: UserCard,
        ConnectedFriendCard: ConnectedFriendCard,
        OtherCard: OtherCard
    },
    data() {
        return {
            friends: []
        }
    },
    computed: {
        ...mapStores(useConnectedStore),
        ...mapActions(useConnectedStore, ['getOthers', 'getSelf', 'getFriends'])
    },
    async mounted() {
        await this.getFriends
    },
    async updated() {
        await this.getFriends
    }
}
</script>
<template>
    <div class="container-fluid my-3 col-10 col-md-3 h-100">
        <div class="p-2 rounded-4 border border-5 border-secondary">
            <h2 class="text-center">Connected Players</h2>
            <ul class="list-group">
                <UserCard v-for="player in getSelf" :player="player"></UserCard>
                <ConnectedFriendCard v-for="player in connectedStore.friends" :player="player"></ConnectedFriendCard>
                <OtherCard v-for="player in getOthers" :key=player.gamerTag :player="player"></OtherCard>
            </ul>
        </div>
    </div>
</template>