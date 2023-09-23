import { defineStore } from "pinia";

export const useUserStore = defineStore('user', {
    state: () => {
        return {
            isAuthenticated: false,
            name: "",
            token: ""
        }
    },
    actions: {
        reset() {
            this.isAuthenticated = false;
            this.name = "";
            this.token = "";
        }
    }
})