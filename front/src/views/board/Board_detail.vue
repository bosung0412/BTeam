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
					<h3 class="mb-3">게시글 부제목</h3>
					<div id="postContent" class="mb-3" style="border: 1px solid #ccc; padding: 10px;">
						<p>여기에 게시글 내용이 출력됩니다.</p>
					</div>

					<label for="username" class="form-label">작성자 아이디</label>
					<input type="text" class="form-control mb-3" id="username" name="username" value="사용자 아이디" readonly>

					<label for="comment" class="form-label">댓글 입력</label>
					<textarea class="form-control mb-3" id="comment" name="comment" rows="3"
						placeholder="댓글을 입력하세요"></textarea>
					<button onclick="submitComment()" type="button" class="btn btn-primary mb-3">댓글 작성</button>

					<div class="d-flex justify-content-end">
						<button onclick="window.location.href='board.html'" class="btn btn-secondary me-2">목록</button>
						<button onclick="window.location.href='boardModify'"
							class="btn btn-secondary me-2">수정</button>
						<button class="btn btn-danger">삭제</button>
					</div>

					<h3 class="mb-3">댓글</h3>
					<ul class="list-group">
						<li class="list-group-item">댓글 내용 1</li>
						<li class="list-group-item">댓글 내용 2</li>
					</ul>
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
						<!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
					</div>
				</div>
			</div>
		</div>
		<!-- Copyright End -->
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
}
</script>
<style>
    #postContent {
        height: 300px;
        overflow-y: scroll;
    }
</style>