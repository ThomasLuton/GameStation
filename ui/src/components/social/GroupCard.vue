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
    <li class="list-group-item my-1 list-group-item-secondary">{{ member.nickName }}</li>
</template>