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
			  <li class="mx-2" @click="filterProjects('*')">전체</li>
			  <li class="mx-2" @click="filterProjects('.first')">카테고리2</li>
			  <li class="mx-2" @click="filterProjects('.second')">카테고리3</li>
		      <button onclick="window.location.href='boardWrite'" type="button" class="btn btn-primary mx-2">글작성</button>
			</ul>
		  </div>
		</div>

		<table class="table">
		  <thead>
			<tr>
			  <th>번호</th>
			  <th>카테고리</th>
			  <th>제목</th>
			  <th>작성자</th>
			  <th>작성일자</th>
			  <th>조회수</th>
			</tr>
		  </thead>
		  <tbody>
			<tr v-for="project in paginatedProjects" :key="project.ono">
              <td>{{ project.ono }}</td>
              <td>{{ project.ocategory }}</td>
              <td>
                <a @click="$event => href(project)">{{ project.oname }}</a>
              </td>
              <td>{{ project.id }}</td>
              <td>{{ project.oregdate }}</td>
              <td>100</td>
            </tr>
		  </tbody>
		</table>
        <nav aria-label="Page navigation example">
          <ul class="pagination">
            <li class="page-item" :class="{ 'disabled': currentPage === 1 }">
              <a class="page-link" href="#" aria-label="Previous" @click.prevent="prevPage">
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
            <li class="page-item" v-for="page in pages" :key="page" :class="{ 'active': currentPage === page }">
              <a class="page-link" href="#" @click.prevent="changePage(page)">{{ page }}</a>
            </li>
            <li class="page-item" :class="{ 'disabled': currentPage === totalPages }">
              <a class="page-link" href="#" aria-label="Next" @click.prevent="nextPage">
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </ul>
        </nav>
	  </div>
	</div>
	<!-- Projects End -->

    <!-- Footer Start -->
    <Footer /> 
  </div>
</template>

<script>
import Navbar from '@/components/Navbar/Navbar.vue';
import Footer from '../../components/Footer/Footer.vue';
import axios from 'axios'

export default{
  name: "boardList",
  // 데이터 초기화
  data() {
    return {
      projects: [], // 게시판 글 데이터
      filteredProjects: [], // 필터링된 글 데이터
      itemsPerPage: 5, // 페이지당 보여줄 글의 수
      currentPage: 1 // 현재 페이지
    }
  },
  mounted() {
    this.fetchData();
    this.filterProjects('*');
  },
  components:{
    Navbar,
    Footer,
  },
  computed: {
    paginatedProjects() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;
      return this.filteredProjects.slice(startIndex, endIndex);
    },
    totalPages() {
      return Math.ceil(this.filteredProjects.length / this.itemsPerPage);
    },
      pages() {
        const pagesArray = [];
        for (let i = 1; i <= this.totalPages; i++) {
          pagesArray.push(i);
        }
        return pagesArray;
      },
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
    changePage(page) {
      this.currentPage = page;
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    href(project){
        console.log(project)
        this.$router.push({name:'boardDetail', params:project})
    }
  }
};
</script>