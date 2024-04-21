<script setup>

const player = defineProps(['player'])

</script>
<template>
    <h3>Player identity options</h3>
    <form novalidate>
        <label for="name">Name</label>
        <input type="text" name="name" id="name">
        <button type="submit">Update name</button>
    </form>
    <form>
        <label for="avatar">Avatar</label>
        <input type="text" name="avatar" id="avatar">
        <button type="submit">Update avatar</button>
    </form>
</template>