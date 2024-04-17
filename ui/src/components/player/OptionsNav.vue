<script>

export default {
    mounted() {
        const splitedPathName = window.location.pathname.split("/");
        console.log(splitedPathName);
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
            target.classList.add("active");
        },
        resetActive() {
            const targets = document.getElementsByClassName("list-group-item");
            for (let i = 0; i < targets.length; i++) {
                targets[i].classList.remove("active");
            }
        }
    }
}

</script>
<template>
    <ul class="list-group">
        <li @click="goTo('notificationsSetup')" id="notificationsSetup" class="list-group-item" aria-current="true">
            <button class="nav-link">notificationsSetup
            </button>
        </li>
        <li @click="goTo('identity')" id="identity" class="list-group-item">
            <button class="nav-link">identity
            </button>
        </li>
        <li @click="goTo('friendList')" id="friendList" class="list-group-item">
            <button class="nav-link">friendList
            </button>
        </li>
        <li @click="goTo('gameHistory')" id="gameHistory" class="list-group-item">
            <button class="nav-link">gameHistory
            </button>
        </li>
    </ul>
</template>