import { createApp } from 'vue'
import App from './App.vue';
import vuetify from './plugins/vuetify';
import store from './store/store';

const app = createApp(App);
app.use(vuetify);
app.use(store);
app.mount('#app');
