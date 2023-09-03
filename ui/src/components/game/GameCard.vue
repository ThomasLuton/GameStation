<script>
import GameDetail from './GameDetail.vue';

export default {
    components: {
        GameDetail: GameDetail
    }
}
</script>
<template>
    <div class="container-fluid border border-dark">
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
            Launch demo modal
        </button>

        <!-- Modal -->
        <GameDetail id="exampleModal" message="Bonjour"></GameDetail>
    </div>
</template>