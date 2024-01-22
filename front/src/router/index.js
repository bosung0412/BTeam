import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {path: '/',name: 'home',component: HomeView},
  {path: '/findid',name: 'findid',component: () => import('../views/member/findid.vue')},
  {path: '/login',name: 'login',component: () => import('../views/member/login.vue')},
  {path: '/join',name: 'join',component: () => import('../views/member/join.vue')},
  {path: '/ingredientsplus',name: 'ingredientsplus',component: () => import('../views/Camera/ingredientsplus.vue')},
  {path: '/refrigeratorcheck',name: 'refrigeratorcheck',component: () => import('../views/food/refrigeratorcheck.vue')},
  {path: '/findpassword',name: 'findpassword',component: () => import('../views/member/findpassword.vue')},
  {path: '/resultfindid',name: 'resultfindid',component: () => import('../views/member/resultfindid.vue')},
  {path: '/resultpassword',name: 'resultpassword',component: () => import('../views/member/resultpassword.vue')},
  {path: '/camera',name: 'camera',component: () => import('../views/Camera/CameraView.vue')},
  {path: '/main',name: 'main',component: () => import('../views/MainView.vue')},
  {path: '/foodbuy',name: 'foodbuy',component: () => import('../views/food/FoodBuyView.vue')},
  {path: '/achievable',name: 'achievable',component: () => import('../views/member/achievable.vue')},
  {path: '/memberupdate',name: 'memberupdate',component: () => import('../views/member/memberupdate.vue')},
  {path: '/findaccount',name: 'findaccount',component: () => import('../views/member/findaccount.vue')},
  {path: '/memberupdate',name: 'memberupdate',component: () => import('../views/member/memberupdate.vue')},
  {path: '/boardList',name: 'boardList',component: () => import('../views/board/Board_list.vue')},
  {path: '/boardList/boardDetail/:ono',name: 'boardDetail',component: () => import('../views/board/Board_detail.vue')},
  {path: '/boardList/faqBoard',name: 'faqBoard',component: () => import('../views/board/Board_faq.vue')},
  {path: '/boardList/faqBoard/faqDetail/:ocategory',name: 'faqDetail',component: () => import('../views/board/Board_faqdetail.vue')},
  {path: '/calendar',name: 'calendar',component: () => import('../views/Calendar/Calendar.vue')},
  {path: '/recommendeddiet',name: 'recommendeddietlist',component: () => import('../views/recommendeddiet/RecommendeddietList.vue')},
  {path: '/recommendeddietdetail/:nutrient_id',name: 'recommendeddietdetail',component: () => import('../views/recommendeddiet/RecommendeddietDetail.vue')},
  {path: '/foodmenu',name: 'foodmenu',component: () => import('../views/recommendeddiet/FoodMenu.vue')},
  {path: '/faqlist',name: 'faqlist',component: () => import('../views/board/FAQList.vue')},
]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router