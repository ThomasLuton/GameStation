<script setup>
import { inject } from 'vue';
import { useUserStore } from '../stores/userStore';

const ws = inject('ws');
const store = useUserStore();

function makeConnection() {
    ws.connectToHub(store.connection, {
        gamerTag: store.gamerTag.playerName + "#" + store.gamerTag.suffix
    })
}

function removeConnection() {
    ws.disconnectToHub(connection);
}

</script>

<template>
    <h1>Hub</h1>
    <p>Hub de l'application</p>
    <button @click="makeConnection">Test</button>
    <button @click="removeConnection">Test</button>
    <RouterView></RouterView>
    <!-- notifications -->
</template>