package com.i2g.rms.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final Logger _logger = LoggerFactory.getLogger(SecurityConfig.class);

	private static String REALM_NAME = "RESTFUL_REALM";

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		_logger.info("Inside SecurityConfig.configure(AuthenticationManagerBuilder auth)");

		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		_logger.info("Inside SecurityConfig.configure(HttpSecurity http)");

		http
		.csrf().disable()
		.exceptionHandling()
		.authenticationEntryPoint(getBasicAuthEntryPoint())
		.and()
		.authorizeRequests()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/p/**").permitAll()
		.antMatchers("/s/**").hasAnyAuthority("ADMIN", "USER", "TESTER")
		.anyRequest().authenticated()
		.and()
		.httpBasic().realmName(REALM_NAME)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public BasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		_logger.info("Inside SecurityConfig.getBasicAuthEntryPoint()");

		BasicAuthenticationEntryPoint basicAuthEntryPoint = new BasicAuthenticationEntryPoint();
		basicAuthEntryPoint.setRealmName(REALM_NAME);
		return basicAuthEntryPoint;
	}	
}