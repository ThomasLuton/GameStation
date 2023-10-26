<script>
import UserCard from './UserCard.vue';
import GroupCard from './GroupCard.vue';
import OtherCard from './OtherCard.vue';
import { mapStores, mapActions } from 'pinia';
import { useConnectedStore } from '../../stores/connectedStore'

export default {
    components: {
        UserCard: UserCard,
        GroupCard: GroupCard,
        OtherCard: OtherCard
    },
    data() {
        return {
            groups: []
        }
    },
    computed: {
        ...mapStores(useConnectedStore),
        ...mapActions(useConnectedStore, ['getOthers'])
    }
}
</script> 
<template>
    <div class="container-fluid col-10 col-md-3 h-100">
        <div class="p-2 rounded-4 border border-5 border-secondary bg-secondary bg-opacity-25 ">
            <h2 class="text-center">Connected Players</h2>
            <ul class="list-group">
                <UserCard></UserCard>
                <GroupCard v-for="member in connectedStore.members" :key=member.nickName :member="member"></GroupCard>
                <OtherCard v-for="player in getOthers" :key=player.nickName :player="player"></OtherCard>
            </ul>
        </div>
    </div>
</template>