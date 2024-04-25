import { createApp } from 'vue';
import { createPinia } from 'pinia';
import App from './App.vue';
import router from './router';
import i18n from './plugins/i18n';
import axios from './plugins/axios';
import toast from './plugins/toast';
import modal from './plugins/modal';

const pinia = createPinia();
const app = createApp(App);

app.use(axios);
app.use(pinia);
app.use(router);
app.use(i18n);
app.use(toast);
app.use(modal);

app.mount('#app');
