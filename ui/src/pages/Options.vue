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
        <div class="row">
            <div class="col-1"></div>
            <div class="col-2">
                <OptionsNav />
            </div>
            <div class="border border-primary rounded p-2 col-8">
                <RouterView :player="player"></RouterView>
            </div>
        </div>
    </section>
</template>