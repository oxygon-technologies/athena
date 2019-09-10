package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		 SpringApplication.run(App.class, args);
	}
//	
//	@SuppressWarnings("deprecation")
//	@Bean
//	   public WebMvcConfigurer corsConfigurer() {
//	      return new WebMvcConfigurerAdapter() {
//	         @Override
//	         public void addCorsMappings(CorsRegistry registry) {
//	        	 System.out.println(" Cors Policy");
//	            registry.addMapping("/**").allowedOrigins("http://localhost:4200");
//	         }
//	      };
//	   }

}
