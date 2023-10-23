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
    <li class="list-group-item my-1 list-group-item-primary">{{ userStore.name }}</li>
</template>