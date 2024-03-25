<script setup>
import Navbar from './components/commons/Navbar.vue';
import Toast from './components/commons/Toast.vue';
import { inject, onBeforeMount } from 'vue';
import { useUserStore } from './stores/userStore';
import { jwtDecode } from "jwt-decode";

const userStore = useUserStore();
const ws = inject("ws");

onBeforeMount(() => {
	if (userStore.token !== "") {
		const token = userStore.token;
		const decoded = jwtDecode(token);
		const now = Math.floor(Date.now() / 1000);
		if (decoded.exp > now) {
			const connection = ws.connect();
			userStore.createConnection(connection);
		} else {
			userStore.reset();
		}
	}
})

</script>
<template>
	<Navbar></Navbar>
	<RouterView />
	<Toast id="toast-global"></Toast>
</template>
