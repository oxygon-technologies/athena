package application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import application.service.CustomUserDetailsService;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableSwagger2
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//@Autowired
//private UserDetailsService userDetailsService;
//	// Authentication : User --> Roles
//	protected void configure(AuthenticationManagerBuilder auth)
//			throws Exception {
//		@Auto
//	auth.userDetailsService(userDetailsService)
////		auth.inMemoryAuthentication()
////				.passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
////				.withUser("user1").password("secret1").roles("USER").and()
////				.withUser("admin1").password("secret1")
////				.roles("USER", "ADMIN");
//		
//	}

	// Authorization : Role -> Access
//	protected void configure(HttpSecurity http) throws Exception {
//		http.httpBasic().and().authorizeRequests()
//				.antMatchers("/users/**").hasRole("USER")
//				.antMatchers("/**").hasRole("ADMIN").and()
//				.csrf().disable().headers().frameOptions().disable();
//	}
	
	  @Autowired
	    private CustomUserDetailsService userDetailsService;

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	        auth.userDetailsService(userDetailsService)
	        .passwordEncoder(getPasswordEncoder());
	    }


	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        http.csrf().disable();
	        http.httpBasic().and()
	        		.authorizeRequests()
	        		.antMatchers("/users/login").permitAll()
	                .antMatchers("/**").authenticated()
	                .anyRequest().permitAll()
	                .and()
	                .headers().frameOptions().disable();
	    }

	    private PasswordEncoder getPasswordEncoder() {
	        return new PasswordEncoder() {
	            @Override
	            public String encode(CharSequence charSequence) {
	                return new BCryptPasswordEncoder().encode(charSequence);// charSequence.toString();
	            }

	            @Override
	            public boolean matches(CharSequence charSequence, String s) {
	            	boolean bIsCorrect = charSequence.toString().equals(s);
	                return  bIsCorrect;
	            }
	        };
	    }
	    
	    @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())                          
	          .build();                                           
	    }
	    

}
