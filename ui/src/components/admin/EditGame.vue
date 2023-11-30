<script>
import { RouterLink } from 'vue-router';

export default {
    data() {
        return {
            games: []
        }
    },
    methods: {
        async initGames() {
            const resp = await this.$http.get('/game');
            this.games = resp.body;
        },
        async remove(id) {
            const resp = await this.$http.delete(`/game/${id}`);
            if (resp.status === 204) {
                this.$toast.success('toast-global', 'Game deleted with success.');
                await this.initGames();
            } else {
                console.error(resp);
                this.$toast.error('toast-global', 'Server error.');
            }
        }
    },
    async beforeMount() {
        await this.initGames();
    }
}
</script>
<template>
    <h1 class="text-center my-3">Edit games</h1>
    <div class="table-responsive container-lg">
        <table class="table table-hover">
            <thead>
                <tr class="text-center align-middle">
                    <th scope="col">Image</th>
                    <th class="w-100" scope="col">Name</th>
                    <th scope="col">Update</th>
                    <th scope="col">Delete</th>
                </tr>
            </thead>
            <tbody class="text-nowrap  align-middle">
                <tr v-for="game in games">
                    <td class="text-center"><img :src="game.thumbnail" class="img-fluid" :alt="game.gameName">
                    </td>
                    <td>{{ game.gameName }}</td>
                    <td class="text-center">
                        <RouterLink :to="{ name: 'game-update', params: { id: game.id } }" title="Update game">
                            <i class="bi bi-pencil-square text-primary"></i>
                        </RouterLink>
                    </td>
                    <td class="text-center"><a href="#" @click="remove(game.id)"><i
                                class="bi bi-trash3 text-danger"></i></a></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>