<script>
export default {
    props: {
        player: {}
    },
    methods: {
        addToGroup() {

        }
    }
}
</script>

<template>
    <li class="list-group-item"><i class="bi bi-person-fill"></i>{{ player.nickName }}</li>
</template>