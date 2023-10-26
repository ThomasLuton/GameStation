<script>
import GameDetail from './GameDetail.vue';

export default {
    components: {
        GameDetail: GameDetail
    },
    props: {
        game: {}
    },
    data() {
        return {
            gameID: ""
        }
    },
    methods: {
        createID() {
            this.gameID = `#game${this.game.id}`;
        }
    },
    mounted() {
        this.createID();
    }
}
</script>
<template>
    <div class="card mx-1 my-3 col-12 col-md-5 col-lg-3 border-primary border-5 bg-primary bg-opacity-10 rounded-4">
        <div class="card-body">
            <h5 class="card-title text-center">{{ game.gameName }}</h5>
            <button type="button" class="btn" data-bs-toggle="modal" :data-bs-target="gameID">
                <img :src="game.thumbnail" class="card-img-top" :alt=game.gameName>
            </button>
        </div>
    </div>
    <GameDetail :id="game.id"></GameDetail>
</template>