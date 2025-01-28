package com.mayuri.blogapplication.security;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayuri.blogapplication.exception.BadApiRequestException;
import com.mayuri.blogapplication.payload.UserDto;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	 @Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private ModelMapper mapper;

	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtHelper helper;
	    
	    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

	    
	    @PostMapping("/login")
	    public ResponseEntity<JwtResponce> login(@RequestBody JwtRequest request)
	    {
	        logger.info("Email : {}", request.getUsername());
	        logger.info("Password : {}", request.getPassword());

	        this.doAuthenticate(request.getUsername(),request.getPassword());
	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
	        String token = this.helper.generateToken(userDetails);
	        UserDto userDto = mapper.map(userDetails,UserDto.class);
	        JwtResponce response = new  JwtResponce();
	        response.setToken(token);
	        response.setUser(userDto);
	        
	        return new ResponseEntity<>(response , HttpStatus.OK);

	    }


	    private void doAuthenticate(String email, String password) {

	        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
	        try{
	            authenticationManager.authenticate(authenticationToken);
	        }
	        catch(BadCredentialsException e){
	            throw new BadApiRequestException("Invalid Username or Password !!");
	        }
	    }



}
