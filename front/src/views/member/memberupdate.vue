<template>
    <div>
        <Navbar />
        <div class="container-fluid page-header py-4 mb-2 wow fadeIn" data-wow-delay="0.1s">
    <div class="container text-center py-5">
        <h1 class="display-5 text-white mb-4 animated slideInDown">***님의 정보를 수정해주세요</h1>
        <nav aria-label="breadcrumb animated slideInDown">
        </nav>
    </div>
</div>
<div class="container-fluid py-5">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-7">
					<div class="rounded p-4 p-sm-5 wow fadeInUp maincol" data-wow-delay="0.1s">
						<div class="row g-3">
							<form>
								<div class="col-sm-8">
									<label for="Id" class="form-label">아이디</label>
									<div class="input-group">
										<input type="text" class="form-control" id="id" name="id" readonly>
									</div>

									<label for="Password" class="form-label">비밀번호</label>
									<input type="text" class="form-control" id="password" name="password">

									<label for="Passwordchk" class="form-label">비밀번호 확인</label>
									<input type="text" class="form-control" id="passwordchk" name="passwordchk">

									<label for="Name" class="form-label">이름</label>
									<input type="text" class="form-control" id="name" name="name">

									<label for="Address" class="form-label">주소</label>
									<input type="text" class="form-control" id="address" name="address">

									<label for="Tel" class="form-label">휴대전화</label>
								<input type="text" class="form-control" id="phoneNumber" maxlength="13" oninput="formatPhoneNumber()">

									<label for="Email" class="form-label mt-3">이메일</label>
									<div class="input-group">
										<input type="text" class="form-control" id="email" name="email">
										<div class="input-group-append">
											<button type="button" class="btnall" style="margin-bottom: 0px;">중복확인</button>
										</div>
									</div>
								</div>
							</form>



							<p class="fs-5 fw-bold text-primary">추가 정보</p>
							<div class="field gender">
								<b>성별</b>
								<div class="d-flex">
									<div class="form-check me-3">
										<input class="form-check-input" type="checkbox" value="남자" id="man" name="man">
										<label class="form-check-label" for="man">남자</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value="여자" id="woman"
											name="woman" style="order: 1;">
										<label class="form-check-label" for="woman">여자</label>
									</div>
								</div>
							</div>
							<div class="col-sm-6">
								<label for="Allergy" class="form-label">질병</label>
								<input type="text" class="form-control border" id="allergy" name="allergy">
								<label for="Height" class="form-label">키(cm)</label>
								<input type="text" class="form-control border" id="height" name="height">
								<label for="weight" class="form-label">몸무게(kg)</label>
								<input type="text" class="form-control border" id="weight" name="Weight">
							</div>
							<div class="col-12 text-center">
								<button onclick="window.location.href='login.html'" type="button"
									class="btn btn-primary mx-2 btnall">회원정보수정</button>
								<button onclick="window.location.href='#'" type="button"
									class="btn btn-primary mx-2 btnall">취소</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
        <Footer />
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