import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import AOS from 'aos'//aos는 애니메이션
import 'aos/dist/aos.css' 
createApp(App).use(store).use(router).use(AOS.init()).mount('#app')
