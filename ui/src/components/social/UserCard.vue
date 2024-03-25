<script>
import { useUserStore } from '../../stores/userStore';
import { mapStores } from 'pinia';

export default {
    computed: {
        ...mapStores(useUserStore)
    },
    methods: {
        leaveGroup() {

        }
    }
}
</script>

<template>
    <li class="list-group-item list-group-item-primary"><i class="bi bi-person-fill"></i>{{ userStore.gamerTag }}</li>
</template>