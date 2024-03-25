import { defineStore } from "pinia";
import { useLocalStorage } from "@vueuse/core"

export const useUserStore = defineStore('user', {
    state: () => {
        return {
            isAuthenticated: useLocalStorage('isAuthenticated', false),
            gamerTag: useLocalStorage('gamerTag', {}),
            token: useLocalStorage('token', ""),
            role: useLocalStorage('role', ""),
            connection: useLocalStorage('connection', {})
        }
    },
    actions: {
        reset() {
            this.isAuthenticated = false;
            this.gamerTag = {};
            this.role = "";
            this.token = "";
            this.connection = null;
        },
        createConnection(connection) {
            this.connection = connection;
        }
    }
})