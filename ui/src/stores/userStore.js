import { defineStore } from "pinia";

export const useUserStore = defineStore('user', {
    state: () => ({
        isAuthenticated: true,
        name: ""
    }),
    getters: {
        isAuthenticated: (state) => state.isAuthenticated,
        name: (state) => state.name
    },
    actions: {

    }
})