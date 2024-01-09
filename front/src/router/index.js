import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('../views/AboutView.vue')
  },
  {
    path: '/findid',
    name: 'findid',
    component: () => import('../views/bosung/findid.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/jihwan/login.vue')
  },
  {
    path: '/join',
    name: 'join',
    component: () => import('../views/jihwan/join.vue')
  },
  {
    path: '/ingredientsplus',
    name: 'ingredientsplus',
    component: () => import('../views/jihwan/ingredientsplus.vue')
  },
  {
    path: '/refrigeratorcheck',
    name: 'refrigeratorcheck',
    component: () => import('../views/jihwan/refrigeratorcheck.vue')
  },
  {
    path: '/findpassword',
    name: 'findpassword',
    component: () => import('../views/bosung/findpassword.vue')
  },
  {
    path: '/resultfindid',
    name: 'resultfindid',
    component: () => import('../views/bosung/resultfindid.vue')
  },
  {
    path: '/resultpassword',
    name: 'resultpassword',
    component: () => import('../views/bosung/resultpassword.vue')
  },
  {
    path: '/camera',
    name: 'camera',
    component: () => import('../views/youngin/CameraView.vue')
  },
  {
    path: '/main',
    name: 'main',
    component: () => import('../views/youngin/MainView.vue')
  },
  {
    path: '/foodbuy',
    name: 'foodbuy',
    component: () => import('../views/youngin/FoodBuyView.vue')
  },
  {
    path: '/achievable',
    name: 'achievable',
    component: () => import('../views/sangmin/achievable.vue')
  },
  {
    path: '/memberupdate',
    name: 'memberupdate',
    component: () => import('../views/sangmin/memberupdate.vue')
  },
  {
    path: '/findaccount',
    name: 'findaccount',
    component: () => import('../views/sangmin/findaccount.vue')
  },
  {
    path: '/memberupdate',
    name: 'memberupdate',
    component: () => import('../views/sangmin/memberupdate.vue')
  },
  {
    path: '/boardList',
    name: 'boardList',
    component: () => import('../views/kyoungjin/Board_list.vue')
  },
  {
    path: '/boardDetail',
    name: 'boardDetail',
    component: () => import('../views/kyoungjin/Board_detail.vue')
  },
  {
    path: '/boardwrite',
    name: 'boardwrite',
    component: () => import('../views/kyoungjin/Board_write.vue')
  },
  {
    path: '/boardModify',
    name: 'boardModify',
    component: () => import('../views/kyoungjin/Board_modify.vue')
  },
  {
    path: '/calendar',
    name: 'calendar',
    component: () => import('../views/kyoungjin/Calendar.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router