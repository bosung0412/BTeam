package kr.co.ict.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import kr.co.ict.project.config.WebConfig;

@SpringBootApplication
@Import(WebConfig.class)
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
