package kr.co.ict.project.social_3.common.filter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.ict.project.social_3.DTO.UsersDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public class MateAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public MateAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }// MateAuthenticationFilter

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken;
        try {
            authenticationToken = getAuthenticationToken(request);
            setDetails(request, authenticationToken);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } // catch

        return this.getAuthenticationManager().authenticate(authenticationToken);
    }// attemptAuthentication

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

            UsersDTO usersDTO = objectMapper.readValue(request.getInputStream(), UsersDTO.class);

            System.out.println("1.MateAuthenticationFilter 시작!");
            System.out.println("아이디: " + usersDTO.getId() + ",비밀번호: " + usersDTO.getPassword());
            return new UsernamePasswordAuthenticationToken(usersDTO.getId(), usersDTO.getPassword());

        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());

        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        } // catch

    }// getAuthenticationToken

}// MateAuthenticationFilter