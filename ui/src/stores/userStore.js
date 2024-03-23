import { defineStore } from "pinia";
import { useLocalStorage } from "@vueuse/core"

export const useUserStore = defineStore('user', {
    state: () => {
        return {
            isAuthenticated: useLocalStorage('isAuthenticated', false),
            name: useLocalStorage('name', ""),
            token: useLocalStorage('token', ""),
            role: useLocalStorage('role', ""),
            connection: useLocalStorage('connection', {})
        }
    },
    actions: {
        reset() {
            this.isAuthenticated = false;
            this.name = "";
            this.role = "";
            this.token = "";
            this.connection = null;
        },
        createConnection(connection) {
            this.connection = connection;
        }
    }
})