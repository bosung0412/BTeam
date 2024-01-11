<template>
  <!-- 중앙 정렬된 콘텐츠 섹션 -->
  <div class="container-fluid">
    <!-- Navbar Start -->
	<Navbar/>

	<!-- Page Header Start -->
	<div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
	  <div class="container text-center py-3">
		<h3 class="display-5 text-white mb-2 animated slideInDown">공지사항</h3>
	  </div>
	</div>
	<!-- Page Header End -->

	<div class="row justify-content-center align-items-center">
	  <div class="col-lg-8">
		<div class="bg-light rounded p-4 mb-4 content-section">
		  <form>
			<!-- detail start -->
			<div class="bigtitle">
				<strong class="mb-3" style="font-size: 30px;">{{ result.oname }}</strong>
				<span class="oregdate">{{ result.oregdate }}</span>
			</div>
			<input type="hidden" name="ono" readonly :value="result.ono">
			<div id="postContent" style="border: 1px solid #ccc; padding: 10px;">
			  <p>{{ result.ocontent }}</p>
			</div>

			<ul class="bottom">
			  <li class="prev">
				  <span>이전글</span> |
				  <template v-if="prevPost.prevno">
					<a class="post" :href="prevPost.prevno">{{ prevPost.prevname }}</a>
				  </template>
				  <template v-else>&nbsp;&nbsp;&nbsp;&nbsp;이전 글이 없습니다.</template>
				  <span class="date">{{ prevPost.prevdate }}</span>
			  </li>
			  <hr class="hr-line">
				<li class="next">
					<span>다음글</span> |
					<span>
						<template v-if="nextPost.nextno">
						<a class="post" :href="nextPost.nextno">{{ nextPost.nextname }}</a>
						</template>
						<template v-else>&nbsp;&nbsp;&nbsp;&nbsp;다음 글이 없습니다.</template>
					</span>
					<span class="date">{{ nextPost.nextdate }}</span>
				</li>
			</ul>
			<button @click="$router.push('/boardList')" class="btn btn-secondary me-2">목록</button>
		  </form>
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
import axios from 'axios';
const BASE_URL = 'http://192.168.0.74/project/';

export default {
  components: {
    Navbar,
    Footer,
  },
  data() {
    return {
      result: {},
      prevPost: {},
      nextPost: {},
    };
  },
  mounted() {
    this.fetchData('boardDetail', this.$route.params.ono, 'result');
    this.fetchData('prevDetail', this.$route.params.ono, 'prevPost');
    this.fetchData('nextDetail', this.$route.params.ono, 'nextPost');
  },
  methods: {
    fetchData(endpoint, ono, dataProp) {
      axios.get(`${BASE_URL}${endpoint}?ono=${ono}`)
        .then((resp) => {
          this[dataProp] = resp.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
<style>
  #postContent {height: 500px; overflow-y: scroll; margin-bottom: 10%;}
  .container-fluid {padding: 0;}
  .bigtitle {border-bottom: 2px solid #000000; margin: 0 0 3% 0;}
  .bigtitle > .oregdate {position: absolute; right: 0; margin: 1% 20% 0 0; font-size: 14px;}
  button {float: right; width: 10%;}
  ul {list-style-type: none;}
  .date {font-size: 14px;}
  .bottom li {margin-bottom: 10px;}
  .bottom li span {margin-right: 10px;}
  .date {float: right;}
  .hr-line {border: 0; border-top: 1px solid #ccc; margin: 10px 0;}
  .post {margin-left: 2%; color: #000000;}
</style>