<script>

// selon l'url afficher le bon lien en actif

</script>
<template>
    <ul class="list-group">
        <li class="list-group-item" aria-current="true">
            <RouterLink :to="{ name: 'notificationsSetup' }" class="nav-link">notificationsSetup
            </RouterLink>
        </li>
        <li class="list-group-item">
            <RouterLink :to="{ name: 'Identity' }" class="nav-link">Identity
            </RouterLink>
        </li>
        <li class="list-group-item">
            <RouterLink :to="{ name: 'Identity' }" class="nav-link">Friend
            </RouterLink>
        </li>
        <li class="list-group-item">
            <RouterLink :to="{ name: 'Identity' }" class="nav-link">Historique
            </RouterLink>
        </li>
    </ul>
</template>