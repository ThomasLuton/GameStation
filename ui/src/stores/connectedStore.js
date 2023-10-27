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
        },
        selfRemove() {
            const userStore = useUserStore();
            this.users = this.users.filter((user) => user.nickName !== userStore.name);
        },
        getOthers() {
            // filtrer les membres du groupes !!!
            const userStore = useUserStore();
            return this.users.filter((user) => user.nickName !== userStore.name);
        }
    }
})