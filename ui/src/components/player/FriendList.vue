<script>
import { mapStores } from 'pinia';
import { useUserStore } from '../../stores/userStore';
import FriendCard from './FriendCard.vue';


export default {
    components: {
        FriendCard: FriendCard
    },
    props: ['player'],
    data() {
        return {
            friends: [],
            newFriend: {
                playerName: "",
                suffix: 0
            }
        }
    },
    computed: {
        ...mapStores(useUserStore)
    },
    methods: {
        async getFriendList() {
            const resp = await this.$http.get("/friends", {
                headers: {
                    Authorization: `Bearer ${this.userStore.token}`
                }
            })
            if (resp.status === 200) {
                this.friends = resp.body;
            }
        },
        async request() {
            const resp = await this.$http.post("/friends/request", this.newFriend, {
                headers: {
                    Authorization: `Bearer ${this.userStore.token}`
                }
            })
            if (resp.status === 204) {
                this.$toast.success("toast-global", "Request send!");
                await this.getFriendList();
            } else {
                this.$toast.error("toast-global", resp.body.message);
            }
        }
    },
    async mounted() {
        await this.getFriendList();
    }
}

</script>
<template>
    <h3>Friend List</h3>
    <div class="mb-3">
        <form novalidate @submit.prevent="request">
            <div>
                <h5>Enter the player's gamerTag</h5>
                <div class="d-flex mb-3">
                    <input class="form-control" v-model="newFriend.playerName" type="text" name="playerName"
                        id="playerName">
                    #
                    <input class="form-control" v-model="newFriend.suffix" type="number" name="suffix" id="suffix">
                </div>
            </div>
            <button class="btn btn-primary" type="submit">Add a new friend</button>
        </form>
    </div>
    <div>
        <ul class="list-group border border-primary">
            <FriendCard v-for="friend in friends" :friend="friend"></FriendCard>
        </ul>
    </div>
</template>