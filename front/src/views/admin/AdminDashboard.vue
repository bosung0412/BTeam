<template>
  <div id="app">
    <Navbar />
    <h1>Admin Dashboard</h1>

    <!-- 회원 목록 가져오기 -->
    <table class="data-table">
      <thead>
        <tr>
          <th>회원번호</th>
          <th>아이디</th>
          <th>이름</th>
          <th>이메일</th>
          <th>생일</th>
          <th>주소</th>
          <th>핸드폰 번호</th>
          <th>성별</th>
          <th>질병</th>
          <th>키</th>
          <th>몸무게</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.memberNo">
          <!-- 회원 정보 출력 -->
          <td>{{ user.memberNo }}</td>
          <td>{{ user.userId }}</td>
          <td>{{ user.name }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.birth }}</td>
          <td>{{ user.address }}</td>
          <td>{{ user.phoneno }}</td>
          <td>{{ user.gender }}</td>
          <td>{{ user.disease }}</td>
          <td>{{ user.height }}cm</td>
          <td>{{ user.weight }}</td>

          <td>
            <button @click="deleteMember(user.memberNo)">삭제</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- 공지사항 목록 가져오기 -->
    <table class="data-table">
      <thead>
        <!-- 새로운 공지사항 작성 버튼 -->
    <button @click="faqwriteGo">공지사항 작성</button>
        <tr>
          <th>공지사항 번호</th>
          <th>공지사항 제목</th>
          <th>카테고리</th>
          <th>내용</th>
        </tr>
      </thead>
      <tbody>     
        <tr v-for="faqBoard in faqBoards" :key="faqBoard.ono">
  <!-- 공지사항 정보 출력 -->
  <td>{{ faqBoard.ono }}</td>
  <td>{{ faqBoard.oname }}</td>
  <td>{{ faqBoard.ocategory }}</td>
  <td>{{ faqBoard.ocontent }}</td>
  <td>
    <button @click="deleteFAQBoard(faqBoard.ono)">삭제</button>
  </td>
</tr>
      </tbody>
    </table>
  </div>
  <Footer />
</template>

<style>
.data-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

.data-table th, .data-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.data-table th {
  background-color: #f2f2f2;
}
</style>

<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '@/components/Footer/Footer.vue';
import axios from 'axios';

export default {
  data() {
    return {
      users: [],
      faqBoards: [],
    };
  },
  created() {
    // 페이지 로드 시 자동으로 목록 가져오기
    this.fetchMembers();
    this.fetchFAQBoards();
  },
  components:{
    Navbar,
    Footer,
  },
  methods: {
    async fetchMembers() {
      try {
        const response = await axios.get('http://localhost/project/api/admin/userList');
        this.users = response.data;
      } catch (error) {
        console.error('Error fetching members:', error);
      }
    },
    async deleteMember(userId) {
      try {
        await axios.delete(`http://localhost/project/api/admin/deleteUser/${userId}`);
        // 삭제 후 목록 다시 불러오기
        this.fetchMembers();
      } catch (error) {
        console.error(`Error deleting user ${userId}:`, error);
      }
    },
    async fetchFAQBoards() {
      try {
        const response = await axios.get('http://localhost/project/api/admin/faqboard');
        this.faqBoards = response.data;
      } catch (error) {
        console.error('Error fetching FAQ boards:', error);
      }
    },
    async deleteFAQBoard(ono) {
    try {
      await axios.delete(`http://localhost/project/api/admin/faqboard/${ono}`);
      // 삭제 후 목록 다시 불러오기
      this.fetchFAQBoards();
    } catch (error) {
      console.error(`Error deleting FAQ board ${ono}:`, error);
    }
  },
    faqwriteGo() { // 
      this.$router.push('/faqwrite');
    },
  },
};
</script>