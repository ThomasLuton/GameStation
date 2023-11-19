import { createApp } from 'vue';
import { createPinia } from 'pinia';
import App from './App.vue';
import router from './router';
import axios from './plugins/axios';
import toast from './plugins/toast';
import modal from './plugins/modal';
import webSocket from './plugins/webSocket';
import { useUserStore } from './stores/userStore';

const pinia = createPinia();
const app = createApp(App);

app.use(pinia);
app.use(router);
app.use(axios);
app.use(webSocket);
app.use(toast);
app.use(modal);

app.mount('#app');
