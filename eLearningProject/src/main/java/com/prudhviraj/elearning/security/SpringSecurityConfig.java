package com.prudhviraj.elearning.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;




@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvc
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Authentication : set user/password details and mention the role.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().withUser("Prudhviraj").password(passwordEncoder().encode("Prudhviraj@123")).roles("admin").and().withUser("Lokesh").password(passwordEncoder().encode("Lokesh@123")).roles("student");
	}
	@Bean
	 PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Authorization : mention which role can access which URL
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		//http.authorizeRequests().antMatchers("/admin").hasRole("admin").anyRequest().authenticated().and().httpBasic();
		http
		
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers("/api/v1/auth/**").permitAll()
		.antMatchers("/v3/api-docs").permitAll()
		.antMatchers(HttpMethod.GET).permitAll()
		.antMatchers(HttpMethod.PUT).permitAll()
		.antMatchers(HttpMethod.DELETE).permitAll()
		.antMatchers(HttpMethod.POST).permitAll()
		.antMatchers("/").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic()
		.and()
		.exceptionHandling()
		;
		
				
	}

}


