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
      <table class="table table-hover mt-3">
        <thead class="table-white">
          <tr>
            <th>음식명</th>
            <th>음식 이미지</th>
            <th>지방</th>
            <th>포화 지방</th>
            <th>콜레스테롤</th>
            <th>나트륨</th>
            <th>칼륨</th>
            <th>탄수화물</th>
            <th>식이섬유</th>
            <th>단백질</th>
            </tr>
        </thead>
        <tbody>
          <tr class="cursor-pointer" v-for="row in list" :key="row.fno" @click="$event => href(row.fno)">
            <td>{{row.fname}}</td>
            <td>{{row.fimage}}</td>
            <td>{{row.fprovince}}</td>
            <td>{{row.ffprovince}}</td>
            <td>{{row.fcholesterol}}</td>
            <td>{{row.fsalt}}</td>
            <td>{{row.fpotasium}}</td>
            <td>{{row.fcarbohydrate}}</td>
            <td>{{row.fdietaryfiber}}</td>
            <td>{{row.fprotein}}</td>
          </tr>
        </tbody>
      </table>
    <!-- Projects End -->
      <div class="container">
        <div class="row mt-4">
          <div class="col-12 text-center">
            <p id="category-count"></p>
          </div>
        </div>
      </div>
      <!-- Copyright Start -->
      <Footer>
      </Footer>
      <!-- Copyright End -->
    </div>
  </template>
  <script>
  
  window.onload = function() {
    console.log("window onload!")
  }

  import Navbar from '@/components/Navbar/Navbar.vue';
  import Footer from '@/components/Footer/Footer.vue';
  import axios from "axios";
  
  export default {
    name : "foodlist",
      data() {
        return {
          list: [],
        }
      },
      created() {
        this.fetchData();
      },
      methods: {
        fetchData() {
          axios.get("http://192.168.0.88/project/foodlist")
            .then((resp) => {
              console.log(resp.data);
              this.list = resp.data
            })
            .catch((error) => {
              console.log(error);
            })
        }
      },
     components: {
      Navbar,
      Footer,

     }
  };
    </script>