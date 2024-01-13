package kr.co.ict.project.social;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

// 자동로깅
@Slf4j
@Component
public class KakaoLoginService {
	@Autowired
	// private MemberService memberservice;

	// 인증코드로 token요청하기
	public String requestToken(String code) {
		String access_Token = "";
		String refresh_Token = "";

		String strUrl = "https://kauth.kakao.com/oauth/token"; // 토큰 요청 보낼 주소
		KakaoToken kakaoToken = new KakaoToken(); // 요청받을 객체

		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // url Http 연결 생성

			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);// outputStreamm으로 post 데이터를 넘김

			// 파라미터 세팅
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();

			// 0번 파라미터 grant_type 입니다 authorization_code로 고정
			sb.append("grant_type=authorization_code");

			// 1번 파라미터 client_id입니다.
			sb.append("&client_id=27be1209a5e94ef12e0e5d5a27ae9161");

			// 2번 파라미터 redirect_uri입니다.
			sb.append("&redirect_uri=http://localhost:8081/MainView");

			// 3번 파라미터 code
			sb.append("&code=" + code);
			// client secret 키
			sb.append("&client_secret=ifoSW84ILZ0Rszl1U4eviIYvQo3CCJS7");

			// BufferedWriter는 버퍼를 잡아놔서 뒤처리를 해야함(flush(), close())
			bw.write(sb.toString());
			bw.flush();// 실제 요청을 보내는 부분

			// 실제 요청을 보내는 부분, 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			log.info("responsecode(200이면성공): {}", responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}

			log.info("response body: {}", result);

			// Jackson으로 json 파싱할 것임
			ObjectMapper mapper = new ObjectMapper();

			// kakaoToken에 result를 KakaoToken.class 형식으로 변환하여 저장
			kakaoToken = mapper.readValue(result, KakaoToken.class);
			System.out.println(kakaoToken);

			// api호출용 access token
			access_Token = kakaoToken.getAccess_token();

			// access 토큰 만료되면 refresh token사용(유효기간 더 김)
			refresh_Token = kakaoToken.getRefresh_token();

			log.info(access_Token);
			log.info(refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("카카오토큰생성완료 제ㅔ발 받아주ㅝ");
		return access_Token;
	}

}
