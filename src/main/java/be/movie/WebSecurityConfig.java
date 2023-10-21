package be.movie;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import be.movie.web.UserDetailServiceImpl;


@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	
	http
	//POST, PUT tai DELETE ei toimi csrf:n kanssa
	//.csrf(csrf -> csrf.disable())
	.authorizeHttpRequests(authorize -> authorize
			.requestMatchers(antMatcher("/css/**")).permitAll()
			.requestMatchers(antMatcher("/signup")).permitAll()
			.requestMatchers(antMatcher("/saveuser")).permitAll()
			
			//API:a varten ->
			//.requestMatchers(antMatcher("/api/movies")).permitAll()
			//.requestMatchers(antMatcher("/api/movie/{id}")).permitAll()
			
			.anyRequest().authenticated()
	)
	.headers(headers -> headers
			.frameOptions(frameoptions -> 
			frameoptions.disable()		
		    )
	)
	.formLogin(formlogin -> formlogin
		
			.defaultSuccessUrl("/index", true)
			.permitAll()
	)
	.logout(logout -> logout
			.permitAll()
	);
			
	return http.build();
}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}

@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
}}
