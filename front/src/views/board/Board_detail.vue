<template>
  <!-- 중앙 정렬된 콘텐츠 섹션 -->
  <div class="container-fluid">
    <!-- Navbar Start -->
	<Navbar/>

	<!-- Page Header Start -->
	<div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
	  <div class="container text-center py-3">
		<h3 class="display-5 text-white mb-2 animated slideInDown">게시글 상세보기</h3>
	  </div>
	</div>
	<!-- Page Header End -->

	<div class="row justify-content-center align-items-center">
	  <div class="col-lg-8">
		<div class="bg-light rounded p-4 mb-4 content-section">
		  <form>
			<!-- detail start -->
			<h3 class="mb-3">{{ result.oname }}</h3>
			<input type="hidden" name="ono" readonly :value="result.ono">
			<div id="postContent" class="mb-3" style="border: 1px solid #ccc; padding: 10px;">
			  <p>{{ result.ocontent }}</p>
			</div>

			<label for="id" class="form-label">작성자 아이디</label>
			<input type="text" class="form-control mb-3" id="id" name="id" readonly :value="result.id"/>
			<!-- detail end -->

			<!-- comments start -->
			<label for="comment" class="form-label">댓글 입력</label>
			<textarea class="form-control mb-3" id="comment" name="comment" rows="3"
				placeholder="댓글을 입력하세요"></textarea>
			<button onclick="submitComment()" type="button" class="btn btn-primary mb-3">댓글 작성</button>

			<div class="d-flex justify-content-end">
			  <button onclick="window.location.href='board.html'" class="btn btn-secondary me-2">목록</button>
			  <button onclick="window.location.href='boardModify'" class="btn btn-secondary me-2">수정</button>
			  <button class="btn btn-danger">삭제</button>
			</div>

			<h3 class="mb-3">댓글</h3>
			<ul class="list-group">
			  <li class="list-group-item">댓글 내용 1</li>
			  <li class="list-group-item">댓글 내용 2</li>
			</ul>
			<!-- comments end -->
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
export default {
  components:{
    Navbar,
    Footer,
  },
  data(){
    return {
      result : {}
    }
  },
  // window.onload
  created(){
    this.result = {
	  ono:this.$route.params.ono,
      id:this.$route.params.id,
      oname:this.$route.params.oname,
      ocontent:this.$route.params.ocontent,
      oregdate:this.$route.params.oregdate,
	  ocategory:this.$route.params.ocategory
    }
  }
}
</script>
<style>
  #postContent {height: 300px; overflow-y: scroll;}
  .container-fluid {padding: 0;}
</style>