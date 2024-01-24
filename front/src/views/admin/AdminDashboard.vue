<template>
  <div id="app">
    <Navbar />
    <h1>Admin Dashboard</h1>
    
    <!-- 회원 목록 가져오기 -->

    <ul>
      <li v-for="user in users" :key="user.member_no">
        <!-- 회원 정보 출력 -->
        {{ user.memberNo }}- {{ user.userId }} - {{ user.name }} - {{ user.email }}
        <button @click="deleteMember(user.userId)">삭제</button>
      </li>
    </ul>

    <!-- 공지사항 목록 가져오기 -->
    <ul>
      <li v-for="faqBoard in faqBoards" :key="faqBoard.id">
        <!-- 공지사항 정보 출력 -->
        {{ faqBoard.oname }} - {{ faqBoard.ocategory }} - {{ faqBoard.ocategory }} - {{ faqBoard.ocontent }}
      </li>
    </ul>
  </div>
  <Footer />
</template>

<script>
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
        const response = await axios.get('http://192.168.0.230/project/api/admin/userList');
        this.users = response.data;
      } catch (error) {
        console.error('Error fetching members:', error);
      }
    },
    async deleteMember(userId) {
      try {
        await axios.delete(`http://192.168.0.230/project/api/admin/deleteUser/${userId}`);
        // 삭제 후 목록 다시 불러오기
        this.fetchMembers();
      } catch (error) {
        console.error(`Error deleting user ${userId}:`, error);
      }
    },
    async fetchFAQBoards() {
      try {
        const response = await axios.get('http://192.168.0.230/project/api/admin/faqboard');
        this.faqBoards = response.data;
      } catch (error) {
        console.error('Error fetching FAQ boards:', error);
      }
    },
  },
};
</script>
