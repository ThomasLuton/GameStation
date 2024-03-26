<script setup>
import Navbar from './components/commons/Navbar.vue';
import Toast from './components/commons/Toast.vue';

</script>
<template>
	<Navbar></Navbar>
	<RouterView />
	<Toast id="toast-global"></Toast>
</template>
