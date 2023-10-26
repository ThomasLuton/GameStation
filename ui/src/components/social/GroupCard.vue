<script>
export default {
    props: {
        member: {}
    },
    methods: {
        removeToGroup() {

        }
    }
}
</script>

<template>
    <li class="list-group-item list-group-item-secondary"><i class="bi bi-person-fill"></i>{{ member.nickName }}</li>
</template>