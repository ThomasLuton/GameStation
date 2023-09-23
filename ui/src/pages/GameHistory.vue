<script>
import { mapStores } from 'pinia';
import { useUserStore } from '../stores/userStore';
export default {
    computed: {
        ...mapStores(useUserStore)
    },
    methods: {
        async test() {
            const token = this.userStore.token;
            const resp = await this.$http.get("/test", {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            console.log(resp.body);
        }
    }
}
</script>
<template>
    <p>Game history</p>
    <button class="btn" @click="test()">Test</button>
</template>