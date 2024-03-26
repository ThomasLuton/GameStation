import SockJS from "sockjs-client";
import Stomp from "stompjs";
import { useConnectedStore } from "../stores/connectedStore";
import { useUserStore } from "../stores/userStore";

export default {
    install: (app) => {
        const plugin = {
            connectToHub: (gamerTag) => {
                const token = useUserStore().token;
                const sock = new SockJS("http://localhost:8080/hub", null, {
                    transports: ['xhr-streaming'],
                    headers: { 'Authorization': `Bearer ${token}` }
                });
                const stompClient = Stomp.over(sock);
                stompClient.connect({
                    authorization: `Bearer ${token}`
                }, () => {
                    stompClient.debug("Connected to Hub via websocket");
                    stompClient.subscribe('/topic/users', (payload) => {
                        const input = JSON.parse(payload.body);
                        const store = useConnectedStore();
                        store.updateUsers(input);
                    })
                    stompClient.send("/app/connect", {},
                        JSON.stringify(gamerTag)
                    )
                })
            },
            connectToGame: (gamerTag) => {
                const token = useUserStore().token;
                const sock = new SockJS("http://localhost:8080/game", null, {
                    transports: ['xhr-streaming'],
                    headers: { 'Authorization': `Bearer ${token}` }
                });
                const stompClient = Stomp.over(sock);
                stompClient.connect({
                    authorization: `Bearer ${token}`
                }, () => {
                    stompClient.debug("Connected to Game via websocket " + gamerTag.playerName + "#" + gamerTag.suffix);
                })
            }
            //     connect: async (storeConnection) => {
            //         let connection;
            //         if (storeConnection == null) {
            //             const sock = new SockJS("http://localhost:8080/ws");
            //             connection = Stomp.over(sock);
            //         } else {
            //             connection = storeConnection;
            //         }
            //         const token = useUserStore().token;
            //         await connection.connect({
            //             authorization: `Bearer ${token}`
            //         }, () => {
            //             connection.debug("Connected to API via websocket");
            //         });
            //         return connection;
            //     },
            //     disconnect: (connection) => {
            //         useConnectedStore().selfRemove;
            //         useUserStore().deleteConnection();
            //         connection.disconnect();
            //     },
            //     connectToHub: async (connection, gamerTag) => {
            //         await connection.subscribe('/topic/users', (payload) => {
            //             const input = JSON.parse(payload.body);
            //             const store = useConnectedStore();
            //             store.updateUsers(input);
            //         }, {
            //             id: 1
            //         })
            //         await connection.send("/app/connect", {},
            //             JSON.stringify(gamerTag)
            //         )
            //     },
            //     disconnectToHub: async (connection) => {
            //         await connection.unsubscribe(1, {})
            //     }
        }
        app.config.globalProperties.$ws = plugin;
        app.provide("ws", plugin);
    }
};