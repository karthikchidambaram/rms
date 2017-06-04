package com.i2g.rms.service.security.stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/*@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)*/
public class StatelessAuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final Logger _logger = LoggerFactory.getLogger(StatelessAuthenticationSecurityConfig.class);
	
	private static String STATELESS_REALM_NAME = "STATELESS_RESTFUL_REALM";

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
		.exceptionHandling()
		.authenticationEntryPoint(getBasicAuthEntryPoint())
		.and()
		.anonymous().and()
		.servletApi().and()
		.authorizeRequests()
		
		// common mappings
		.antMatchers("/").permitAll()
		.antMatchers("/favicon.ico").permitAll()
		.antMatchers("/resources/**").permitAll()
		
		// allow anonymous POSTs to login
		.antMatchers(HttpMethod.POST, "/p/api/login").permitAll()
		// allow anonymous GETs to API
		.antMatchers(HttpMethod.GET, "/p/api/**").permitAll()
		
		// public area
		.antMatchers("/p/**").permitAll()
		// secured area
		.antMatchers("/s/admin/**").hasAuthority("ADMIN")
		.antMatchers("/s/**").hasAnyAuthority("ADMIN", "USER", "TESTER", "CLAIMS_HANDLER", "SUPERVISOR", "INVESTIGATOR")
		.anyRequest().authenticated().and()
		
		// custom JSON based authentication by POST of {"username":"<name>","password":"<password>"} which sets the token header upon authentication
		.addFilterBefore(new StatelessLoginFilter("/p/api/login", tokenAuthenticationService, userDetailsService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		// custom Token based authentication based on the header
		// previously given to the client
		.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
		
		.httpBasic().realmName(STATELESS_REALM_NAME)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.headers().cacheControl();
		
		/*
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/p/api/login").permitAll()
		.antMatchers("/p/**").permitAll()
		.antMatchers("/s/**").hasAnyAuthority("ADMIN", "USER", "TESTER", "CLAIMS_HANDLER", "SUPERVISOR", "INVESTIGATOR")
		.and()
		// custom JSON based authentication by POST of {"username":"<name>","password":"<password>"} which sets the token header upon authentication
		.addFilterBefore(new StatelessLoginFilter("/p/api/login", tokenAuthenticationService, userDetailsService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		// custom Token based authentication based on the header
		// previously given to the client
		.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
		*/
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
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected UserDetailsService userDetailsService() {
		_logger.info("Inside StatelessAuthenticationSecurityConfig.userDetailsService()");
		return userDetailsService;
	}
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		_logger.info("Inside StatelessAuthenticationSecurityConfig.configureGlobalSecurity(AuthenticationManagerBuilder auth)");
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		_logger.info("Inside StatelessAuthenticationSecurityConfig.passwordEncoder()");
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		_logger.info("Inside StatelessAuthenticationSecurityConfig.authenticationProvider()");
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public BasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		_logger.info("Inside StatelessAuthenticationSecurityConfig.getBasicAuthEntryPoint()");
		BasicAuthenticationEntryPoint basicAuthEntryPoint = new BasicAuthenticationEntryPoint();
		basicAuthEntryPoint.setRealmName(STATELESS_REALM_NAME);
		return basicAuthEntryPoint;
	}	
}
