package com.mayuri.blogapplication.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
    private static Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		
		String username =null;
		String token= null;
		
		if(header != null && header.startsWith("Bearer")) {
			 token = header.substring(7);
			 try {
			 username = jwtHelper.getUsernameFromToken(token);
			 }
			 catch(IllegalArgumentException e){
	                logger.info("Illegal Argument while fetching the username !! ");
	                e.printStackTrace();

	            }catch(ExpiredJwtException e){
	                logger.info("Given token is expired !!");
	                e.printStackTrace();

	            }catch(MalformedJwtException e){
	                logger.info("Some change has done in token !! Invalid Token !!");
	                e.printStackTrace();

	            }catch(Exception e){
	                e.printStackTrace();
	            }
			 
			 
		}else {
			logger.info("Jwt token des not being with Bearer ");
		}
		
		 if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null){

	            //fetch user  from token
	            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
	            boolean isValidatedToken = this.jwtHelper.validateToken(token, userDetails);
	            if(isValidatedToken){
	                //set Authentication
	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authentication);

	            }else{
	                logger.info("Validation fails !!");
	            }

	        }

	        filterChain.doFilter(request,response);
		
		
		
		
	}

}
