package com.i2g.rms.rest.security.stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import com.i2g.rms.rest.service.PasswordRelatedRestService;
import com.i2g.rms.util.security.RMSSecurityProperties;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class StatelessAuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final Logger _logger = LoggerFactory.getLogger(StatelessAuthenticationSecurityConfig.class);
	
	@Autowired
	private SpringSecurityUserDetailsServiceImpl userDetailsService;
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;
	@Autowired
	private PasswordRelatedRestService passwordRelatedRestService;

	public StatelessAuthenticationSecurityConfig() {
		super(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf().disable()
		.exceptionHandling()
		.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
		.and()
		.servletApi().and()
		.authorizeRequests()
		//public area
		.antMatchers("/").permitAll()
		.antMatchers("/favicon.ico").permitAll()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/assets/**").permitAll()
		.antMatchers("/**").permitAll()
		.antMatchers("/p/**").permitAll()
		//secured area
		.antMatchers("/s/**").hasAnyAuthority("ADMIN", "USER", "CLAIMS_HANDLER", "SUPERVISOR", "INVESTIGATOR")
		.anyRequest().authenticated()
		.and()
		.anonymous().and()
		.httpBasic().realmName(RMSSecurityProperties.STATELESS_REALM_NAME)
		.and()
		.headers().cacheControl().and()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// custom JSON based authentication by POST of {"username":"<name>","password":"<password>"} which sets the token header upon authentication
		http.addFilterBefore(new StatelessLoginFilter("/p/api/login", 
							tokenAuthenticationService, 
							userDetailsService,
							passwordRelatedRestService,
							authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		
		// custom Token based authentication based on the header
		// previously given to the client
		.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
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
}
