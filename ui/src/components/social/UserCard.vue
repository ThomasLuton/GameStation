<script>

export default {
    props: ['player'],
    methods: {
    }
}
</script>

<template>
    <li class="list-group-item list-group-item-primary d-flex py-0" style="max-height: 70px;">
        <div class="mx-2">
            <img v-if="player.avatar" :src="'/images/' + player.avatar" style="height: 70px; width: 70px;" alt="avatar">
            <img v-else src="/images/noAvatar.png" style="height: 70px; width: 70px;" alt="avatar">
        </div>
        <div class="py-4">
            {{ player.gamerTag.playerName }}#{{ player.gamerTag.suffix }}
        </div>
    </li>
</template>