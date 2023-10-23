import { defineStore } from "pinia";

export const useConnectedStore = defineStore('connected', {
    state: () => {
        return {
            users: null
        }
    },
    actions: {
        updateUsers(users) {
            this.users = users;
        }
    }
})