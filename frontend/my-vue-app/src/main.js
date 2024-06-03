import { createApp } from 'vue'
/*import './style.css'*/
import App from './App.vue'
import router from "./router";

// Создание и монтирование приложения Vue
createApp(App).use(router).mount('#app');