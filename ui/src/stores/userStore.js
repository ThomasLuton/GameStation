import { defineStore } from "pinia";

export const useUserStore = defineStore('user', {
    state: () => {
        return {
            isAuthenticated: false,
            name: "",
            token: "",
            connection: {}
        }
    },
    actions: {
        reset() {
            this.isAuthenticated = false;
            this.name = "";
            this.token = "";
            this.connection = null;
        },
        createConnection(connection) {
            this.connection = connection;
        }
    }
})