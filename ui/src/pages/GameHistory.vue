<script>
export default {
    methods: {
        async test() {
            const token = localStorage.getItem("token");
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