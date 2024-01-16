<template>
  <div id="app">
    <!-- Navbar Start -->
    <Navbar />
    <!-- Navbar End -->

    <!-- Page Header Start -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
      <div class="container text-center py-5">
        <h1 class="display-5 text-white mb-4 animated slideInDown">추천 식단 리스트</h1>
        <button @click="Add" type="button" class="btn btn-primary btnall">식단 추가</button>
        <nav aria-label="breadcrumb" class="animated slideInDown"></nav>
      </div>
    </div>
    <!-- Page Header End -->

    <!-- Projects Start -->
    <table class="table table-hover mt-3">
      <thead class="table-white">
        <tr>
          <th>식단 명</th>
          <th>식단 이미지</th>
          <th>총 칼로리</th>
        </tr>
      </thead>
      <tbody>
        <tr class="cursor-pointer" v-for="row in list" :key="row.nutrient_id" @click="goToDetail(row.nutrient_id)">
          <td>{{ row.name }}</td>
          <td>
            <img :src="'http://192.168.0.88/project/images/' + row.food_img" alt="Image" />
          </td>
          <td>{{ row.cal }}</td>
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
    <Footer></Footer>
    <!-- Copyright End -->
  </div>
</template>

<script>
import Navbar from "@/components/Navbar/Navbar.vue";
import Footer from "@/components/Footer/Footer.vue";
import axios from "axios";

export default {
  name: "foodlist",
  data() {
    return {
      list: [],
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      axios
        .get("http://192.168.0.88/project/foodlist")
        .then((resp) => {
          console.log(resp.data);
          this.list = resp.data;
        })
        .catch((error) => {
          console.log(error);
        })
    },
    goToDetail(nutrient_id) {
  this.$router.push({ name: "recommendeddietdetail", params: { nutrient_id } });
    },
    Add() {
      this.$router.push('/Recommendeddietadd');
    },
  },
  components: {
    Navbar,
    Footer,
  },
};
</script>