<script>
import { mapStores } from 'pinia';
import { useUserStore } from '../../stores/userStore';

export default {
    props: ['friend'],
    computed: {
        ...mapStores(useUserStore)
    },
    methods: {
        addBackgroudIfpending() {
            if (this.friend.pending) {
                document.getElementById(this.friend.gamerTag.playerName).classList.add("bg-secondary");
            }
        },
        async accept() {
            const resp = await this.$http.post("/friends/accept", this.friend.gamerTag, {
                headers: {
                    Authorization: `Bearer ${this.userStore.token}`
                }
            })
            if (resp.status === 204) {
                window.location.reload();
            } else {
                this.$toast.error("toast-global", resp.body.message);
            }
        },
        async refuse() {
            const resp = await this.$http.post("/friends/refuse", this.friend.gamerTag, {
                headers: {
                    Authorization: `Bearer ${this.userStore.token}`
                }
            })
            if (resp.status === 204) {
                window.location.reload();
            } else {
                this.$toast.error("toast-global", resp.body.message);
            }
        },
        async remove() {
            const resp = await this.$http.post("/friends/delete", this.friend.gamerTag, {
                headers: {
                    Authorization: `Bearer ${this.userStore.token}`
                }
            })
            if (resp.status === 204) {
                window.location.reload();
            } else {
                this.$toast.error("toast-global", resp.body.message);
            }
        }
    },
    mounted() {
        this.addBackgroudIfpending();
    }
}
</script>

<template>
    <li :id="friend.gamerTag.playerName" class="list-group-item d-flex justify-content-around bg-opacity-10">
        <div class="col-3">
            <img v-if="friend.avatar" :src="'/images/' + friend.avatar" style="height: 100px; widows: 100px;"
                alt="avatar">
            <img v-else src="/images/noAvatar.png" style="height: 100px; widows: 100px;" alt="avatar">
            <p>{{ friend.gamerTag.playerName }}#{{ friend.gamerTag.suffix }}</p>
        </div>
        <div class="col-8 py-5 d-flex">
            <button v-if="friend.pending" class="btn btn-primary mx-2" @click="accept">Accept</button>
            <button v-if="friend.pending" class="btn btn-primary mx-2" @click="refuse">Reject</button>
            <button class="btn btn-secondary ms-auto" @click="remove">Remove</button>
        </div>
    </li>
</template>