package com.mayuri.blogapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mayuri.blogapplication.security.JwtAuthenticationEntryPoint;
import com.mayuri.blogapplication.security.JwtAuthenticationFilter;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled= true)
public class SecurityConfig   {
	  @Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private JwtAuthenticationEntryPoint authenticationEntryPoint;

	    @Autowired
	    private JwtAuthenticationFilter authenticationFilter;
	    
	    
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    	
	    	 http.csrf()
             .disable() // Disable CSRF for stateless APIs
             .cors()
             .disable() // Disable CORS (configure if needed elsewhere)
             .authorizeRequests()
             .requestMatchers("/auth/login")
             .permitAll() // Make /login public
             .requestMatchers(HttpMethod.GET)
             .permitAll()
             .requestMatchers("/user/create")
             .permitAll()
             .anyRequest()
             .authenticated() // All other requests require authentication
             .and()
             .exceptionHandling()
             .authenticationEntryPoint(authenticationEntryPoint) // Handle unauthorized access
             .and()
             .sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Stateless session management

             http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

     return http.build();
	    	
	    	}
	    
	    
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
	        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	        return daoAuthenticationProvider;
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
	    
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	       return configuration.getAuthenticationManager();
	    }
}
