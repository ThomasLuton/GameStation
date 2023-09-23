<script>
export default {
    props: {
        player: {}
    },
    data() {
        return {
            listStyle: "list-group-item my-1"
        }
    },
    mounted() {
        if (this.player.role === "user") {
            this.listStyle = this.listStyle + " list-group-item-primary"
        } else if (this.player.role === "group") {
            this.listStyle = this.listStyle + " list-group-item-secondary"
        }
    }
}
</script>

<template>
    <li :class="listStyle">{{ player.name }}</li>
</template>