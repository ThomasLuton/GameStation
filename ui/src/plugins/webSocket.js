import SockJS from "sockjs-client";
import Stomp from "stompjs";
import { useConnectedStore } from "../stores/connectedStore";
import { useUserStore } from "../stores/userStore";

export default {
    install: (app) => {
        const plugin = {
            connect: () => {
                const token = useUserStore().token;
                const sock = new SockJS("http://localhost:8080/ws");
                const connection = Stomp.over(sock);
                connection.connect({
                    authorization: `Bearer ${token}`
                }, () => {
                    connection.debug("Connected to API via websocket");
                });
                return connection;
            },
            disconnect: (connection) => {
                const store = useConnectedStore();
                store.selfRemove();
                connection.disconnect();
            },
            connectToHub: (connection, gamerTag) => {
                connection.subscribe('/topic/users', (payload) => {
                    const input = JSON.parse(payload.body);
                    const store = useConnectedStore();
                    store.updateUsers(input);
                }, {
                    id: 1
                })
                connection.send("/app/connect", {},
                    JSON.stringify(gamerTag)
                )
            },
            disconnectToHub: (connection) => {
                connection.unsubscribe(1, {})
            }
        }
        app.config.globalProperties.$ws = plugin;
        app.provide("ws", plugin);
    }
};