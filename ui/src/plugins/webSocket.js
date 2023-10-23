import SockJS from "sockjs-client";
import Stomp from "stompjs";
import { useConnectedStore } from "../stores/connectedStore";


export default {
    install: (app) => {
        app.config.globalProperties.$ws = {
            connect: (connectInput) => {
                const sock = new SockJS("http://localhost:8080/ws");
                const connection = Stomp.over(sock);
                connection.connect({}, () => {
                    connection.subscribe('/topic/users', (payload) => {
                        const input = JSON.parse(payload.body);
                        const store = useConnectedStore();
                        store.updateUsers(input);
                    })
                    connection.send("/app/connect", {},
                        JSON.stringify(connectInput)
                    )

                }, () => {
                    console.log("Connexion impossible");
                })
                return connection;
            },
            disconnect: (connection) => {
                const store = useConnectedStore();
                store.selfRemove()
                connection.disconnect();
            }
        }
    }
};