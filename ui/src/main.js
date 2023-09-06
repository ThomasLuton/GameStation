import { createApp } from 'vue';
import { createPinia } from 'pinia';
import App from './App.vue';
import router from './router';
import axios from './plugins/axios';
import toast from './plugins/toast';

const pinia = createPinia();
const app = createApp(App);


app.use(router);
app.use(axios);
app.use(toast);
app.use(pinia);

app.mount('#app');
