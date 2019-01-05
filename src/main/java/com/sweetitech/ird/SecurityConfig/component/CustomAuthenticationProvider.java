package com.sweetitech.ird.SecurityConfig.component;

import com.sweetitech.ird.SecurityConfig.Authority;
import com.sweetitech.ird.SecurityConfig.User;
import com.sweetitech.ird.SecurityConfig.oauth.SettingsRepository;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class CustomAuthenticationProvider
  implements AuthenticationProvider {
 
	
	@Value("${hrmoduleapi.resource}")
    private String baseUrl;
	
	
	 @Autowired
     private OAuth2RestTemplate restTemplate;
	
     @Autowired
     private SettingsRepository settings;
     
     @Autowired
     private AccessTokenRequest accesstokenrequest;
	
     private Logger logger = LoggerFactory.getLogger(this.getClass());
 		List<User> users = new ArrayList<User>();
  
 	/*@PostConstruct
 	void init() {
 		users.add(new User("user", "user", "ROLE_USER"));
 		users.add(new User("admin@sweetiTech.com", "Sweet", "ROLE_ADMIN"));
 	}*/
  
 	@Override
 	public Authentication authenticate(Authentication authentication) throws AuthenticationException {


 		String name = authentication.getName();
 		String password = authentication.getCredentials().toString();
 		//Optional<User> optionalUser = users.stream().filter(u -> u.index(name, password)).findFirst();
 		
 		/*if (!optionalUser.isPresent()) {
 			logger.error("Authentication failed for user = " + name);
 			throw new BadCredentialsException("Authentication failed for user = " + name);
 		}*/
 		  String endpoint = baseUrl+ApiUrlList.oauthtoken;
 		 //User user = restTemplate.postForObject(endpoint, User.class);
 		/* settings.setName(name);
 		 settings.setPassword(password);*/

 		HttpHeaders headers = new HttpHeaders();
 		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
 		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
 		params.add("username", name);
 		params.add("password", password);
 		params.add("grant_type", "password");
 		params.add("client_id", "hrm-client-sweet");
 		params.add("client_secret", "hrm-secret-sweet" );
 		
 		
 		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);

 		//HttpEntity<re>
 		try {
 		    ResponseEntity<User> response = restTemplate.exchange(endpoint,
 		        HttpMethod.POST, 
 		       request,
 		      User.class);
 		    settings.setAccessToken(response.getBody().getAccess_token());
 		    settings.setRefreshToken(response.getBody().getRefresh_token());
 		    Calendar calendar = Calendar.getInstance();
 		    calendar.setTimeInMillis(response.getBody().getExpires_in());

 		    List<Authority> authorities = (List<Authority>) response.getBody().getAuthorities();
 		   
 		    settings.setRolename(authorities.get(0).getAuthority());
 		    

 		    settings.setUserId(response.getBody().getUserId());
			System.out.println("checking session data"+ settings.toString());
 		    
 		    
 		   /*	if(response.getBody().getIsDeleted().equals("true"))
 		   	{
 		   		throw new BadCredentialsException("Your Account has been Deactivated. Please Contact Administrator");
 		   	}*/
 		    
 		    
 		} catch (OAuth2AccessDeniedException e) {
 		   
 			throw new BadCredentialsException("Invalid Username or Password");
 			//throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
 			//throw new BadCredentialsException(SpringSecurityMessageSource.getAccessor().getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Invalid Username or Password"));
 			//this.message.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials") ;		    
 			// You can get the body too but you will have to deserialize it yourself
 		    // e.getResponseBodyAsByteArray()
 		    // e.getResponseBodyAsString()
 		}
 		catch ( ResourceAccessException e) {
  		   
 			throw new BadCredentialsException("Could not connect to remote server. Please wait a while and try again.");
 			//throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
 			//throw new BadCredentialsException(SpringSecurityMessageSource.getAccessor().getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Invalid Username or Password"));
 			//this.message.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials") ;		    
 			// You can get the body too but you will have to deserialize it yourself
 		    // e.getResponseBodyAsByteArray()
 		    // e.getResponseBodyAsString()
 		}
 		
 		
 	//	logger.info("Succesful Authentication with password = " + password);
 		
 		
 		
 		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
 		//grantedAuthorities.add(new SimpleGrantedAuthority(optionalUser.get().getRole()));
 		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(name, password,
 				grantedAuthorities);
  
 		//logger.info("Succesful Authentication with user = " + name);
 		//logger.info("Succesful Authentication with password = " + password);
 		return auth;
 	}
  
 	@Override
 	public boolean supports(Class<?> authentication) {
		System.out.println("something wrong here !!");
 		return authentication.equals(UsernamePasswordAuthenticationToken.class);
 	}
}