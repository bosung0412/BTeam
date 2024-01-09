<style>

</style>
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
							<th>좋아요</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="project in paginatedProjects" :key="project.id">
                            <td>{{ project.id }}</td>
                            <td>{{ project.category }}</td>
                            <td>
                                <a href="boardDetail">{{ project.title }}</a>
                            </td>
                            <td>{{ project.author }}</td>
                            <td>{{ project.date }}</td>
                            <td>{{ project.likes }}</td>
                            <td>{{ project.views }}</td>
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

export default{
    data() {
        return {
            projects: [], // 게시판 글 데이터
            filteredProjects: [], // 필터링된 글 데이터
            itemsPerPage: 5, // 페이지당 보여줄 글의 수
            currentPage: 1 // 현재 페이지
        }
    },
    created() {
        this.projects = [
            { id: 1, category: 'first', title: '게시글 1' },
            { id: 2, category: 'second', title: '게시글 2' },
            { id: 3, category: 'third', title: '게시글 3' },
            { id: 4, category: 'firth', title: '게시글 4' },
            { id: 5, category: 'fifth', title: '게시글 5' },
            { id: 6, category: 'sixth', title: '게시글 6' },
        ];

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
        filterProjects(category) {
            this.filteredProjects = this.projects.filter(project => category === '*' || project.category === category);
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
        goToWritePage() {
            // 글 작성 페이지로 이동하는 로직을 추가할 수 있습니다.
            alert('글 작성 페이지로 이동합니다.');
        }
    }
};
</script>