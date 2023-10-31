import { defineStore } from "pinia";

export const useUserStore = defineStore('user', {
    state: () => {
        return {
            isAuthenticated: false,
            name: "",
            token: "",
            connection: {},
            isLeader: true,
            favorites: []
        }
    },
    actions: {
        reset() {
            this.isAuthenticated = false;
            this.name = "";
            this.token = "";
            this.connection = null;
            this.isLeader = true;
            this.favorites = [];
        },
        createConnection(connection) {
            this.connection = connection;
        },
        addFavorite(favorite) {
            this.favorites.push(favorite);
        },
        removeFavorite(favorite) {
            const newfavorites = this.favorites.filter((candidate) => favorite !== candidate);
            this.favorites = newfavorites;
        }
    }
})