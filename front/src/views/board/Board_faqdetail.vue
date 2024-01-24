<template>
    <div id="app">
      <Navbar />
      <!-- Navbar End -->
    
      <!-- Page Header Start -->
      <div class="container-fluid page-header py-4 mb-2 wow fadeIn" :data-wow-delay="'0.1s'">
        <div class="container text-center py-3">
          <h3 class="display-5 text-white mb-2 animated slideInDown">자주 묻는 질문</h3>
        </div>
      </div>
      <!-- Page Header End -->

      <div class="container-xxl py-5">
        <div class="container">
          <div class="row wow fadeInUp" data-wow-delay="0.3s">
            <div class="col-12 text-center">
              <div class="list-inline rounded mb-5" id="portfolio-flters">
                <router-link to="/boardList">게시판</router-link> &gt; 
                <router-link to="/boardList/faqBoard">자주묻는질문(FAQ)</router-link> 
                &gt; {{ ocategory }}
              </div>
            </div>
          </div>
          <div class="faqlist" v-for="list in faqlist" :key="list.ono">
            <div class="top" @click="toggleContent(list)">
              <span id="q">Q</span>
              {{ list.oname }}
            </div>
            <div class="bottom" v-if="list.showContent">
              {{ list.ocontent }}
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
  name: 'faqDetail',
  components: {
    Navbar,
    Footer,
  },
  data() {
    return {
      faqlist: [],
    }
  },
  mounted() {
    this.ocontent = this.$route.params.ocontent;
    this.ocategory = this.$route.params.ocategory;
    this.fetchData(this.$route.params.ocategory)
  },
  methods: {
    fetchData(ocategory){
      axios.get(`http://localhost/project/faqDetail?ocategory=${ocategory}`)
      .then((resp) => {
        console.log(resp.data)
        this.faqlist = resp.data;
      })
      .catch((error) => {
          console.log(error);
      });
    },
    toggleContent(item) {
      // 클릭한 FAQ 항목에 대해 showContent 속성을 토글
      item.showContent = !item.showContent;
    },
  }
}
</script>
<style>
  .faqlist {padding: 0 2% 0 2%;}
  #q {color: #A5D299; font-size: 35px; margin: 3% 3% 0 0;}
  .top {padding: 1%; vertical-align: super; font-size: 20px; border-top: 1px solid rgb(49, 49, 49); cursor: pointer;}
  .bottom {height: auto; padding: 0 7% 0 7%; font-size: 18px;}
</style>