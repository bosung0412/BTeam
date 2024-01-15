package kr.co.ict.project.social_2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import kr.co.ict.project.social_2.config.jwt.JwtAuthenticationFilter;
import kr.co.ict.project.social_2.config.jwt.JwtTokenProvider;
import kr.co.ict.project.social_2.config.oauth.CustomOAuth2UserService;
import kr.co.ict.project.social_2.config.oauth.HttpCookieOAuth2AuthorizationRequestRepository;
import kr.co.ict.project.social_2.config.oauth.OAuth2AuthenticationFailureHandler;
import kr.co.ict.project.social_2.config.oauth.OAuth2AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
    // JWT 생성 및 검증
    private final JwtTokenProvider jwtTokenProvider;
    // OAuth 2.0을 사용하여 사용자 정보를 가져옴
    private final CustomOAuth2UserService customOAuth2UserService;
    // oauth 성공했을 시
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    // oauth 실패했을 시
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider, CustomOAuth2UserService customOAuth2UserService,
            OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler,
            OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.customOAuth2UserService = customOAuth2UserService;
        this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
        this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // http
    // .csrf().disable()
    // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    // .and()
    // .formLogin().disable()
    // .httpBasic().disable()
    // .authorizeRequests()
    // .antMatchers ("/api/**", "/login/**", "/oauth2/**").permitAll ()
    // .and()
    // .oauth2Login()
    // .authorizationEndpoint().baseUri("/oauth2/authorize")
    // .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository())
    // .and()
    // .redirectionEndpoint()
    // .baseUri("/login/oauth2/code/**")
    // .and()
    // .userInfoEndpoint().userService(customOAuth2UserService)
    // .and()
    // .successHandler(oAuth2AuthenticationSuccessHandler)
    // .failureHandler(oAuth2AuthenticationFailureHandler)
    // .and()
    // .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
    // UsernamePasswordAuthenticationFilter.class);
    // return http.build();
    // }

    // 여기 새로운 security로 바꿈 .and 없어짐
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector)
            throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        (sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers(new MvcRequestMatcher(introspector, "/api/user")).permitAll())
                .oauth2Login(oauth2Login -> oauth2Login.userInfoEndpoint(
                        userInfoEndpointConfig -> userInfoEndpointConfig.userService(customOAuth2UserService)));
        return http.build();
    }
}
