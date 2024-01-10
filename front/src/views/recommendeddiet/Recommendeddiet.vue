<template>
    <div id="app">
      <!-- Navbar Start -->
        <Navbar /> 
      <!-- Navbar End -->
  
      <!-- Page Header Start -->
      <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
          <h1 class="display-5 text-white mb-4 animated slideInDown">추천 식단 정보</h1>
          <nav aria-label="breadcrumb" class="animated slideInDown"></nav>
        </div>
      </div>
      <!-- Page Header End -->
  
      <!-- Projects Start -->
      <div class="container-xxl py-5">
        <div class="container">
          <div class="row wow fadeInUp" data-wow-delay="0.3s">
            <div class="col-12 text-center">
              <ul class="list-inline rounded mb-5" id="portfolio-flters">
                <li class="mx-2 active" data-filter="*">모든 식단</li>
                <li class="mx-2" data-filter=".vegetable">야채</li>
                <li class="mx-2" data-filter=".meat">육류</li>
                <li class="mx-2" data-filter=".fruit">과일</li>
                <li class="mx-2" data-filter=".nuts">견과류</li>
              </ul>
            </div>
          </div>
  
          <!-- Add your project content here -->
  
        </div>
      </div>
      <!-- Projects End -->
  
      <div class="container">
        <div class="row mt-4">
          <div class="col-12 text-center">
            <p id="category-count"></p>
          </div>
        </div>
      </div>
      <!-- Copyright Start -->
      <Footer />
      <!-- Copyright End -->
    </div>
  </template>
  
  <script>
  import Navbar from '@/components/Navbar/Navbar.vue';
  import Footer from '@/components/Footer/Footer.vue';
  export default {
    components: {
      Navbar,
      Footer,
    }
  };
    </script>
      