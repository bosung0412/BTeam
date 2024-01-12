<template>
  <div id="app">
    <Navbar />
    <!-- Navbar End -->
  
    <!-- Page Header Start -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" :data-wow-delay="'0.1s'">
      <div class="container text-center py-3">
        <h3 class="display-5 text-white mb-2 animated slideInDown">게시판</h3>
      </div>
    </div>
    <!-- Page Header End -->

    <!-- Projects Start -->
    <div class="container-xxl py-5">
      <div class="container">
        <div class="row wow fadeInUp" data-wow-delay="0.3s">
          <div class="col-12 text-center">
            <ul class="list-inline rounded mb-5" id="portfolio-flters">
              <li class="mx-5">
                <router-link to="/boardList">공지사항</router-link>
              </li>
              <li class="mx-5" @click="filterProjects('faq')">자주 묻는 질문(FAQ)</li>
            </ul>
          </div>
        </div>
        <div class="faq-list">
          <div class="faq-item">
            <div class="img-container">
              <img src="#" alt="Image Description">
            </div>
            <div class="title">
              <h4>계정관리</h4>
              <p>로그인, 보호조치, 도용 등 문제 해결 방법</p>
              <a href="#">자세히 보기</a>
            </div>
          </div>
          <div class="faq-item">
            <div class="img-container">
              <img src="#" alt="Image Description">
            </div>
            <div class="title">
              <h4>홈페이지 관련</h4>
              <p>접속 장애 및 해결 방법</p>
              <a href="#">자세히 보기</a>
            </div>
          </div>
        </div>
        <div class="faq-list">
          <div class="faq-item">
            <div class="img-container">
              <img src="#" alt="Image Description">
            </div>
            <div class="title">
              <h4>앱 관련</h4>
              <p>앱 설치 및 각종 오류 모음</p>
              <a href="#">자세히 보기</a>
            </div>
          </div>
          <div class="faq-item">
            <div class="img-container">
              <img src="#" alt="Image Description">
            </div>
            <div class="title">
              <h4>기능 관련</h4>
              <p>기능별 오류 및 문의사항 모음</p>
              <a href="#">자세히 보기</a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Footer Start -->
	<Footer /> 
  </div>
</template>
<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '../../components/Footer/Footer.vue';
import axios from 'axios'

export default {
  name: 'faqboard',
  data(){
    return {
      projects: [],
      filteredProjects: [], // 필터링된 글 데이터
    }
  },
  components:{
    Navbar,
    Footer,
  },
  mounted() {
    this.fetchData();
    this.filterProjects('*');
  },
  methods: {
    fetchData(){
      axios.get("http://192.168.0.74/project/boardList")
      .then((resp) => {
        this.projects = resp.data;
        this.filterProjects('*');
      })
    },
    filterProjects(category) {
      this.filteredProjects = this.projects.filter(project => category === '*' || project.ocategory === category);
      this.currentPage = 1;
    },
  },
}
</script>
<style>
  .faq-list {display: flex; justify-content: space-between; margin: 1% 5% 1% 5%; padding: 2%;}
  .faq-item {display: flex; align-items: center; height: 150px; font-size: 20px; border: 1px solid #fff; border-radius: 20px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); width: 45%;}
  .img-container {margin: 0 5% 0 10px; width: 25%;}
</style>