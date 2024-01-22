package kr.co.ict.project.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAutoConfiguration
public class WebConfig implements WebMvcConfigurer {

   @Bean
   public WebMvcConfigurer crosConfigure() {
      return new WebMvcConfigurer() {
         @Override
         
         public void addCorsMappings(CorsRegistry registry) {
            System.out.println("Test==========");
            registry.addMapping("/**")
                  .allowedOrigins("http://192.168.0.230:8081/", "http://localhost:8081/", "http://192.168.0.4:8081/")
                  .allowedHeaders("*")
                  .allowedMethods("*").maxAge(3600);
         }

      };
   }
}