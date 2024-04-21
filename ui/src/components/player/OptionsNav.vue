<script>

export default {
    mounted() {
        const splitedPathName = window.location.pathname.split("/");
        if (splitedPathName.length === 3) {
            this.setActive(splitedPathName[2]);
        }
    },
    methods: {
        goTo(name) {
            this.$router.push({ name: name })
            this.setActive(name);
        },
        setActive(name) {
            const target = document.getElementById(name);
            this.resetActive();
            target?.classList?.add("active");
        },
        resetActive() {
            const targets = document.getElementsByClassName("list-group-item");
            for (let i = 0; i < targets.length; i++) {
                targets[i]?.classList?.remove("active");
            }
        }
    }
}

</script>
<template>
    <ul class="list-group border border-primary">
        <li @click="goTo('identity')" id="identity" class="list-group-item">
            <button class="nav-link">Player identity options
            </button>
        </li>
        <li @click="goTo('notificationsSetup')" id="notificationsSetup" class="list-group-item" aria-current="true">
            <button class="nav-link">Notifications options
            </button>
        </li>
        <li @click="goTo('friendList')" id="friendList" class="list-group-item">
            <button class="nav-link">Friends List
            </button>
        </li>
        <li @click="goTo('gameHistory')" id="gameHistory" class="list-group-item">
            <button class="nav-link">Game history
            </button>
        </li>
    </ul>
</template>