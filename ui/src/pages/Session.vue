<script>

/*
    TODO: rejoindre la session en cours pour le joueur
    Quitter la session 
    Jouer 
*/

export default {
    data() {
        return {
            sessionCode: this.$route.params.sessionCode
        }
    }
}

</script>
<template>
    {{ sessionCode }}
</template>