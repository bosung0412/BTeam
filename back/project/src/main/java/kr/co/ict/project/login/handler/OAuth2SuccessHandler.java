package kr.co.ict.project.login.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.ict.project.login.entity.CustomOAuth2User;
import kr.co.ict.project.login.provider.JwtProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    
    private final JwtProvider jwtProvider;

   @Override
	public void onAuthenticationSuccess(
        HttpServletRequest request,
         HttpServletResponse response,
		Authentication authentication
        ) throws IOException, ServletException {
           
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

            String userId = oAuth2User.getName();
            String token = jwtProvider.create(userId);

            response.sendRedirect("http://localhost:3000/auth/oauth-response/" + token + "/3600");

	}
}
