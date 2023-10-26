<script>

export default {
    props: {
        id: Number
    },
    data() {
        return {
            game: {},
            gameID: ""
        }
    },
    methods: {
        async getGameById(id) {
            const resp = await this.$http.get(`/game/${id}`);
            this.game = resp.body;
        },
        createID() {
            this.gameID = `game${this.game.id}`;
        }
    },
    async mounted() {
        await this.getGameById(this.id);
        this.createID();
    }
}
</script>

<template>
    <div class="modal fade" :id="gameID" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-primary bg-opacity-10">
                    <h1 class="modal-title fs-5">{{ game.gameName }}</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <img :src="game.thumbnail" class="card-img-top" :alt=game.gameName>
                    <p>Règles: </p>
                    <p>{{ game.gameDetail }}</p>
                    <p>Nombre de joueurs : </p>
                    <p>{{ game.minPlayer }} à {{ game.maxPlayer }} joueur(s)</p>
                    <p>Victoire : {{ game.victoryPoint }} point(s), Egalité : {{ game.drawPoint }} point(s), Défaite : {{
                        game.losePoint }} point(s)
                    </p>
                </div>
                <div class="modal-footer bg-primary bg-opacity-10">
                    <button type="button" class="btn btn-primary" :disabled="!game.available">Jouer</button>
                </div>
            </div>
        </div>
    </div>
</template>