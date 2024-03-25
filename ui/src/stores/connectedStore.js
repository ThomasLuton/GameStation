import { defineStore } from "pinia";
import { useUserStore } from "./userStore";

export const useConnectedStore = defineStore('connected', {
    state: () => {
        return {
            users: [],
            members: []
        }
    },
    actions: {
        updateUsers(users) {
            this.users = users;
            console.log("users = " + this.users)
        },
        selfRemove() {
            const userStore = useUserStore();
            this.users = this.users.filter((user) => user.gamerTag !== userStore.gamerTag);
        },
        getOthers() {
            // filtrer les membres du groupes !!!
            const userStore = useUserStore();
            return this.users.filter((user) => user.gamerTag.suffix !== userStore.gamerTag.suffix);
        }
    }
})