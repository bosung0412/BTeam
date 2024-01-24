<template>
  <nav class="navbar navbar-expand-lg navbar-light sticky-top" style="top:0px">
    <router-link to="/main" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
      <img class="navimg" src="../../assets/img/samplelogo.png" style="width: 70px; height: 70px; top: 0; left: 10px;" />
    </router-link>
    <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <div class="navbar-nav ms-auto p-4 p-lg-0">
        <!-- <router-link to="/mypage" class="nav-item nav-link">마이페이지</router-link> -->
        <router-link to="/calendar" class="nav-item nav-link">캘린더</router-link>
        <router-link to="/camera" class="nav-item nav-link">식단관리</router-link>
        <!-- <router-link to="/foodbuy" class="nav-item nav-link">식재료구매</router-link> -->
        <router-link to="/boardList" class="nav-item nav-link">게시판</router-link>
        <router-link v-show="isAdmin" to="/adminpage" class="nav-item nav-link">관리자</router-link>
      </div>
    </div>
  </nav>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Navbar',
  data() {
    return {
      isAdmin: false, // 어드민 여부를 저장하는 변수
    };
  },
  created() {
    // 여기에서 어드민 여부를 확인하고 변수를 업데이트하는 로직 추가
    // 예시: 로그인된 사용자의 아이디를 가져와서 isAdmin을 설정
    const loggedInUserId = 'admin1'; // 실제 사용자 아이디로 교체
    this.checkAdminStatus(loggedInUserId);
  },
  methods: {
    async checkAdminStatus(id) {
      try {
        // 사용자 ID로 사용자 정보 가져오기
        const response = await axios.get(`http://192.168.0.230/project/api/admin/faqboard/${id}`);

        // 예시: 사용자 정보에서 ROLE 값을 확인하여 어드민 여부 판단
        const userRole = response.data.role;

        // ROLE_ADMIN인 경우에만 isAdmin을 true로 설정
        this.isAdmin = userRole === 'ROLE_ADMIN';
      } catch (error) {
        console.error('어드민 여부 확인 중 에러 발생:', error);
      }
    },
  },
};
</script>


/* 특정 디바이스 사이즈에 대한 스타일 */
<style scoped>  
@media screen and (max-width: 1080px) and (max-height: 2220px) {
  .navbar{
    height: 70px;
  }
  .navimg{
    width: 50px !important; /* !important를 사용하여 다른 스타일이 덮어쓰지 않도록 */
    height: 50px !important;
    margin-top: 10px;
    margin-bottom: 65px;
  }
  .navbar-toggler{
    margin-top: 10px;
    margin-bottom: 60px;
  }
}

</style>