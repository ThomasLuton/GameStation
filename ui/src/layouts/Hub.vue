<script setup>
import { inject } from 'vue';
import { useUserStore } from '../stores/userStore';
import { onMounted, onUnmounted } from 'vue';

const ws = inject('ws');
const store = useUserStore();
onMounted(() => {
    if (store.isAuthenticated) {
        ws.connectToHub(store.connection, {
            gamerTag: store.gamerTag.playerName + "#" + store.gamerTag.suffix
        })
    }
})

onUnmounted(() => {
    if (store.isAuthenticated) {
        ws.disconnectToHub(store.connection);
    }
})

</script>

<template>
    <h1>Hub</h1>
    <p>Hub de l'application</p>
    <RouterLink :to="{ name: 'fake' }"><button>test</button></RouterLink>
    <RouterView></RouterView>
    <!-- notifications -->
</template>