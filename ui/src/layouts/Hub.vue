<script setup>
import { useUserStore } from '../stores/userStore';
import { useConnectedStore } from '../stores/connectedStore';
import { onMounted, inject } from 'vue';
import { jwtDecode } from "jwt-decode";
import { Client } from '@stomp/stompjs';

const http = inject("http");
const store = useUserStore();
let ws;
onMounted(async () => {
    if (store.token !== "") {
        const token = store.token;
        const decoded = jwtDecode(token);
        const now = Math.floor(Date.now() / 1000);
        if (decoded.exp > now) {
            const body = {
                gamerTag: store.gamerTag.playerName + '#' + store.gamerTag.suffix
            }
            const client = new Client({
                connectHeaders: {
                    Authorization: `Bearer ${token}`
                },
                brokerURL: 'ws://localhost:8080/hub',
                onConnect: () => {
                    client.subscribe('/topic/users', message => {
                        const body = JSON.parse(message.body);
                        useConnectedStore().updateUsers(body)
                    }
                    );
                    client.publish({
                        destination: '/app/connect',
                        body: JSON.stringify(body)
                    })
                }
            })
            client.activate();
        } else {
            store.reset();
        }
    }
})

</script>

<template>
    <RouterView></RouterView>
</template>