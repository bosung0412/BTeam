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
  components:{
    Navbar,
    Footer,
  },
}