<script>
import { mapStores } from 'pinia';
import { useUserStore } from '../stores/userStore';
import SockJS from "sockjs-client";
import Stomp from "stompjs";
export default {
    computed: {
        ...mapStores(useUserStore)
    },
    data() {
        return {
            connection: null,
            connectInput: {
                nickname: ""
            }
        }
    },
    methods: {
        async test() {
            const token = this.userStore.token;
            const resp = await this.$http.get("/test", {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            console.log(resp.body);
        },
        connect() {
            this.connectInput.nickname = this.userStore.name;
            const token = this.userStore.token;
            const sock = new SockJS("http://localhost:8080/ws");
            this.connection = Stomp.over(sock);
            this.connection.connect({}, () => {
                this.connection.subscribe('/topic/users', (payload) => {
                    const input = payload.body;
                    console.log("J'ai reçu => " + input);
                })
                this.connection.send("/app/connect", {},
                    JSON.stringify(this.connectInput)
                )

            }, () => {
                console.log("Connexion impossible");
            })
        },
        sendMessage() {
            if (this.connection) {
                const tmp = JSON.stringify({ message: "Superjecraque", sender: "TOTOTO" });
                this.connection.send("/app/chat-send", {}, tmp);
            }
        }
    }
}
</script>
<template>
    <p>Game history</p>
    <button class="btn btn-dark" @click="test()">Test</button>
    <button class="btn" @click="connect()">Test connection</button>
</template>