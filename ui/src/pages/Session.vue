<script>
import { useUserStore } from '../stores/userStore';
import { mapStores } from 'pinia';

export default {
    data() {
        return {
            sessionCode: this.$route.params.sessionCode
        }
    },
    computed: {
        ...mapStores(useUserStore)
    },
    methods: {

    }
}

</script>
<template>
    {{ sessionCode }}
</template>