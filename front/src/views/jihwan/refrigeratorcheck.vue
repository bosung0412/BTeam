<template>
  <div id="app">
    <!-- Navbar Start -->
    <nav class="navbar navbar-expand-lg navbar-light sticky-top">
      <router-link to="/" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
        <img src="img/samplelogo.png" style="width: 70px; height: 70px; top: 0; left: 10px;" />
      </router-link>
      <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <div class="navbar-nav ms-auto p-4 p-lg-0">
          <router-link to="/calendar" class="nav-item nav-link">캘린더</router-link>
          <router-link to="/board" class="nav-item nav-link">게시판</router-link>
        </div>
      </div>
    </nav>
    <!-- Navbar End -->

    <!-- Page Header Start -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
      <div class="container text-center py-5">
        <h1 class="display-5 text-white mb-4 animated slideInDown">냉장고 식재료</h1>
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
              <li class="mx-2 active" data-filter="*">전체</li>
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
    <div class="container-fluid copyright py-4">
      <div class="container">
        <div class="row">
          <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
            &copy; <a class="border-bottom" href="#">냉장고든램지</a>, All Right Reserved.
          </div>
          <div class="col-md-6 text-center text-md-end">
            <!-- You can keep the credit link here if required -->
          </div>
        </div>
      </div>
    </div>
    <!-- Copyright End -->
  </div>
</template>

<script>
export default {
  // Add your component logic here
}
</script>

<style scoped>
/* Add your component-specific styles here */
</style>
