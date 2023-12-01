<script setup>
import Navbar from './components/commons/Navbar.vue';
import Toast from './components/commons/Toast.vue';

// import { useUserStore } from './stores/userStore';
// import { mapStores } from 'pinia';

// export default {

// 	components: {
// 		Navbar: Navbar,
// 		Toast: Toast
// 	},
// 	computed: {
// 		...mapStores(useUserStore)
// 	},
// 	mounted() {
// 		if (this.userStore.name !== "") {
// 			const connection = this.$ws.connect(this.userStore.name);
// 			this.this.userStore.createConnection(connection);
// 		}
// 	}

// }

</script>
<template>
	<Navbar></Navbar>
	<RouterView />
	<Toast id="toast-global"></Toast>
</template>
