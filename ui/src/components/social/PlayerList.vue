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
    <div class="container-fluid col-3">
        <ul class="list-group">
            <UserCard></UserCard>
            <GroupCard v-for="member in connectedStore.members" :key=member.nickName :member="member"></GroupCard>
            <OtherCard v-for="player in getOthers" :key=player.nickName :player="player"></OtherCard>
        </ul>
    </div>
</template>