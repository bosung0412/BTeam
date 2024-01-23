<template>
  <div id="app">
    <Navbar />

    <!-- 페이지 헤더 시작 -->
    <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
      <div class="container text-center py-5">
        <h1 class="display-5 text-white mb-4 animated slideInDown">추천 음식 리스트</h1>
        <nav aria-label="breadcrumb" class="animated slideInDown"></nav>
      </div>
    </div>
    <!-- 페이지 헤더 끝 -->

    <!-- 검색 입력 필드 및 버튼 그룹 시작 -->
    <div class="container mt-3">
      <div class="input-group mb-2">
        <input
          v-model="searchQuery"
          type="text"
          class="form-control"
          placeholder="음식 검색..."
          aria-label="음식 검색"
          aria-describedby="search-button"
          @keyup.enter="search"
        />
        <div class="input-group-append">
          <button @click="search" class="btn btn-success" type="button" id="search-button">검색</button>
        </div>
      </div>
    </div>
    <!-- 검색 입력 필드 및 버튼 그룹 끝 -->

    <!-- 카드 그리드 시작 -->
    <div class="container mt-3">
      <div class="row">
        <div class="col-md-4" v-for="row in paginatedList" :key="row.nutrient_id">
          <div class="card mb-4">
            <div class="card-body">
              <h5 class="card-title">{{ row.name }}</h5>
              <p class="card-text">총 칼로리: {{ row.cal }}</p>
              <button @click="goToDetail(row.nutrient_id)" class="btn btn-success">자세히 보기</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 카드 그리드 끝 -->

    <!-- 페이지네이션 및 수동 페이지네이션 시작 -->
    <div class="d-flex justify-content-center my-4">
      <button @click="previousPage" :disabled="currentPage === 1" type="button" class="btn btn-success">이전 페이지</button>
      <button @click="nextPage" :disabled="currentPage === totalPages" type="button" class="btn btn-success">다음 페이지</button>
    </div>
    <!-- 페이지네이션 및 수동 페이지네이션 끝 -->

    <!-- 푸터 시작 -->
    <Footer />
    <!-- 푸터 끝 -->
  </div>
</template>

<script>
import Navbar from "@/components/Navbar/Navbar.vue";
import Footer from "@/components/Footer/Footer.vue";
import axios from "axios";

export default {
  name: "foodlist",
  components: {
    Navbar,
    Footer
  },
  data() {
    return {
      list: [],
      perPage: 12, // 페이지당 표시할 항목 수
      currentPage: 1, // 현재 페이지
      searchQuery: "", // 검색어
      filteredList: [] // 검색 결과를 저장할 배열
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      axios.get("http://192.168.0.4/project/api/v1/auth/foodlist")
        .then((resp) => {
          this.list = resp.data;
          this.filteredList = resp.data; // 초기에 전체 리스트를 필터된 리스트로 설정
        })
        .catch((error) => {
          console.error(error);
        });
    },
    goToDetail(nutrient_id) {
      this.$router.push({ name: "recommendeddietdetail", params: { nutrient_id } });
    },
    previousPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    pageChanged(pageNum) {
      this.currentPage = pageNum;
    },
    linkGen(pageNum) {
      // URL 생성 로직을 여기에 구현합니다.
      return `#${pageNum}`;
    },
    search() {
      // 검색어로 리스트 필터링
      if (this.searchQuery.trim() === "") {
        // 검색어가 없으면 전체 리스트 표시
        this.filteredList = this.list;
      } else {
        this.currentPage = 1; // 검색 시 첫 페이지로 이동
        this.filteredList = this.list.filter((item) =>
          item.name.toLowerCase().includes(this.searchQuery.toLowerCase())
        );
      }
    }
  },
  computed: {
    paginatedList() {
      const start = (this.currentPage - 1) * this.perPage;
      const end = start + this.perPage;
      return this.filteredList.slice(start, end); // 검색된 리스트를 기반으로 페이지네이션
    },
    totalPages() {
      return Math.ceil(this.filteredList.length / this.perPage); // 검색된 리스트를 기반으로 페이지 수 계산
    }
  }
};
</script>

<!-- 스타일 -->
<style>
.card {
  border: none;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.card-title {
  font-size: 1.5em; /* 예시 크기, 조정 가능 */
}

.btn-success {
  background-color: #4CAF50; /* 색상 코드는 변경 가능 */
}

/* 카드 제목 중앙 정렬 */
.text-center {
  text-align: center;
}

/* 반응형 레이아웃을 위한 추가 스타일 */
@media (max-width: 768px) {
  .col-md-4 {
    flex: 0 0 100%;
    max-width: 100%;
  }
}
</style>