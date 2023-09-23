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
    <div class="card m-1 col-12 col-md-5 col-lg-3">
        <div class="card-body">
            <h5 class="card-title text-center">{{ game.gameName }}</h5>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" :data-bs-target="gameID">
                <img :src="game.thumbnail" class="card-img-top" :alt=game.gameName>
            </button>
        </div>
    </div>
    <GameDetail :id="game.id"></GameDetail>
</template>