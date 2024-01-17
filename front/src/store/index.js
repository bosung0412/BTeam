import { createStore } from 'vuex'

export default createStore({
  state: {
    authToken: null // 초기상태 설정
  },
  getters: {
    isAuthenticated: state => !!state.authToken // 인증된 상태 여부 확인
  },
  mutations: {
    setAuthToken(state, token) {
    state.authToken = token // 토큰 설정 뮤테이션
  },
},
  clearAuthToken(state) {
    state.authToken = null // 토큰 초기화 뮤테이션
  },
  actions: {
    login({ commit }, token) {
      commit('setAuthToken', token) // 로그인 시 토큰 저장
    },
    logout({ commit }) {
      commit('clearAuthToken') // 로그아웃 시 토큰 초기화
    },
  }
})
