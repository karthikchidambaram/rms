package com.i2g.rms.service.security.stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/*@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)*/
public class StatelessAuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final Logger _logger = LoggerFactory.getLogger(StatelessAuthenticationSecurityConfig.class);
	
	private static String STATELESS_REALM_NAME = "STATELESS_REALM_NAME";
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	public StatelessAuthenticationSecurityConfig() {
		super(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
				http
				.csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint(getBasicAuthEntryPoint())
				.and()
				.authorizeRequests()
				//allow anonymous resource requests
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/favicon.ico").permitAll()
				//public area
				.antMatchers("/p/**").permitAll()
				//secured area
				.antMatchers("/s/**").hasAnyAuthority("ADMIN", "USER", "TESTER")
				.anyRequest().authenticated()
				.and()
				.httpBasic().realmName(STATELESS_REALM_NAME)
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				// custom JSON based authentication by POST of {"username":"<name>","password":"<password>"} which sets the token header upon authentication
				.addFilterBefore(new StatelessLoginFilter("/p/api/login", tokenAuthenticationService, userDetailsService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
				// custom Token based authentication based on the header previously given to the client
				.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
				.anonymous().and()
				.servletApi().and()
				.headers().cacheControl();
	}
	
	@Bean
	public BasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		_logger.info("Inside StatelessAuthenticationSecurityConfig.getBasicAuthEntryPoint()");
		BasicAuthenticationEntryPoint basicAuthEntryPoint = new BasicAuthenticationEntryPoint();
		basicAuthEntryPoint.setRealmName(STATELESS_REALM_NAME);
		return basicAuthEntryPoint;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		_logger.info("Inside StatelessAuthenticationSecurityConfig.authenticationManagerBean()");
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		_logger.info("Inside StatelessAuthenticationSecurityConfig.configure(AuthenticationManagerBuilder auth)");
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}
}
