package br.com.api.states.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfigurations implements WebMvcConfigurer {

	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**").allowedOrigins("https://editor.swagger.io").allowedMethods("GET", "POST", "PUT",
				"DELETE");
	}

}
