import { defineStore } from "pinia";
import { useUserStore } from "./userStore";
import { inject } from "vue";

export const useConnectedStore = defineStore('connected', {
    state: () => {
        return {
            users: [],
            members: [],
            friends: []
        }
    },
    actions: {
        updateUsers(users) {
            this.users = users;
        },
        selfRemove() {
            const userStore = useUserStore();
            this.users = this.users.filter((user) => user.gamerTag !== userStore.gamerTag);
        },
        getSelf() {
            const userStore = useUserStore();
            return this.users.filter((user) => user.gamerTag.suffix === userStore.gamerTag.suffix);
        },
        getOthers() {
            const userStore = useUserStore();
            const others = this.users.filter((user) => {
                return this.friends.find((friend) => friend.gamerTag.suffix === user.gamerTag.suffix) === undefined || this.friends.find((friend) => friend.gamerTag.suffix === user.gamerTag.suffix).pending === true;
            })
            return others.filter((user) => user.gamerTag.suffix !== userStore.gamerTag.suffix);
        },
        async getFriends() {
            const http = inject('http');
            const userStore = useUserStore();
            const friendRequest = await http.get("/friends", {
                headers: {
                    Authorization: `Bearer ${userStore.token}`
                }
            });
            if (friendRequest.status === 200) {
                const friends = friendRequest.body;
                this.friends = friends.filter((friend) => !friend.pending)
            }
        }
    }
})