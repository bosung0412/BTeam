import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

createApp(App).use(store).use(router).mount('#app')

app.config.globalProperties.$axios = axios; //전역 변수 설정 -> this.$axios
app.config.globalProperties.$serverUrl = 'http://192.168.0.240/springvue0103'// 서버 주소 