<script>
import { useUserStore } from '../stores/userStore.js'
import { mapStores } from 'pinia';
import OptionsNav from '../components/player/OptionsNav.vue'

export default {
    data() {
        return {
            player: {}
        }
    },
    computed: {
        ...mapStores(useUserStore)
    },
    components: {
        OptionsNav: OptionsNav
    },
    methods: {
        async initPlayer() {
            const token = "Bearer " + this.userStore.token;
            const resp = await this.$http.get("/players/options", {
                headers: {
                    Authorization: token
                }
            });
            if (resp.status == 200) {
                this.player = resp.body
            }
        }
    },
    async beforeMount() {
        await this.initPlayer();
    }
}

</script>
<template>
    <section class="container-fluid">
        <h1 class="text-center my-2">Options</h1>
        <div class="d-flex flex-column flex-md-row justify-content-around px-5">
            <div class="">
                <OptionsNav />
            </div>
            <div class="p-2 flex-grow-1">
                <RouterView :player="player" @avatar-updated="initPlayer"></RouterView>
            </div>
        </div>
    </section>
</template>