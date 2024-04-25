import { defineStore } from "pinia";
import { useLocalStorage } from "@vueuse/core"

export const useUserStore = defineStore('user', {
    state: () => {
        return {
            isAuthenticated: useLocalStorage('isAuthenticated', false),
            gamerTag: useLocalStorage('gamerTag', {}),
            token: useLocalStorage('token', ""),
            role: useLocalStorage('role', "")
        }
    },
    actions: {
        reset() {
            this.isAuthenticated = false;
            this.gamerTag = {};
            this.role = "";
            this.token = "";
        }
    }
})