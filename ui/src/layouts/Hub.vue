<script setup>
import { inject } from 'vue';
import { useUserStore } from '../stores/userStore';
import { onMounted } from 'vue';
import { jwtDecode } from "jwt-decode";

const ws = inject('ws');
const store = useUserStore();
onMounted(() => {
    if (store.token !== "") {
        const token = store.token;
        const decoded = jwtDecode(token);
        const now = Math.floor(Date.now() / 1000);
        if (decoded.exp > now) {
            ws.connectToHub(store.gamerTag);
        } else {
            store.reset();
        }
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