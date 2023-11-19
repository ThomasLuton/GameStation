import { defineStore } from "pinia";
import { useLocalStorage } from "@vueuse/core"

export const useUserStore = defineStore('user', {
    state: () => {
        return {
            isAuthenticated: useLocalStorage('isAuthenticated', false),
            name: useLocalStorage('name', ""),
            id: useLocalStorage('id', 0),
            token: useLocalStorage('token', ""),
            connection: useLocalStorage('connection', {}),
            isLeader: useLocalStorage('isLeader', true),
            favorites: useLocalStorage('favorites', [])
        }
    },
    actions: {
        reset() {
            this.isAuthenticated = false;
            this.name = "";
            this.id = 0;
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