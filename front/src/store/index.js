import { createStore } from 'vuex';
// import jwt from 'jsonwebtoken';

export default createStore({
  state: {
    authToken: null // 초기상태 설정
  },
  getters: {
    getAuthToken: (state) => state.authToken, //토큰값 반환
    isAuthenticated: state => !!state.authToken, // 인증된 상태 여부 확인

    // getUserId:(state)=>{
    //   //토큰을 디코드해줌
    //   const decodedToken = jwt.decode(state.authToken);
    //   //사용자 id 반환
    //   return decodedToken ? decodedToken.id : null;
    // }
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
      commit('setAuthToken', null);
    },
  }
})
