import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import axios from './plugins/axios';
import modal from './plugins/modal';
import toast from './plugins/toast';


const app = createApp(App);

app.use(router);
app.use(axios);
app.use(modal);
app.use(toast);

app.mount('#app');
