package com.dxc.socialmedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.dxc.socialmedia.services.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	.cors().and().csrf().disable()
    	.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
    	.authorizeRequests() //less visible at top
    	.antMatchers("/**").permitAll()
//    	.antMatchers("/user/all").hasAnyAuthority("admin")
//    	.antMatchers("/data/admin/delete").hasAnyAuthority("admin")
//    	.antMatchers("/user/admin/deleteuser").hasAnyAuthority("admin")
//    	.antMatchers("/data/all").hasAnyAuthority("user","admin")
//    	.antMatchers("/data/get/**").hasAnyAuthority("user","admin")
//    	.antMatchers("/user/login/**").permitAll()
//		.antMatchers("/user/adduser").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling()
		 .and()
		.httpBasic();
            
            
    }
    
    
    
}



//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.inMemoryAuthentication().withUser("ben")
//			.password(passwordEncoder().encode("ben123")).roles("user")
//			.and()
//			.withUser("admin").password("admin").roles("admin");
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.cors().and().csrf().disable() 
//		.authorizeRequests()
//	.antMatchers("/data/all").hasRole("user")
//	.antMatchers("/user/login/**").permitAll()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.httpBasic();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//}

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
////	@Autowired
////	private UserDetailsService userDetailsService;
//	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return new MyDetailService();
//	}
//	
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		System.out.println(passwordEncoder().encode("ben123"));
//        auth.authenticationProvider(authenticationProvider());  
//    }
//	
//
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userDetailsService());
//		provider.setPasswordEncoder(new BCryptPasswordEncoder());
//		return provider;
//	}
//	@Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//	
//	
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
////			.antMatchers("/user/adduser").permitAll()
////			.antMatchers("/home")
////           .hasAuthority("user")
//            .antMatchers("/user/all").hasAnyAuthority("user")
//            .anyRequest()
//			.authenticated()
//			.and()
//			.httpBasic();
//		System.out.println("configure method");
//			
//	}
//
//}



//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
// 
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new MyDetailService();
//    }
//     
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//     
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//         
//        return authProvider;
//    }
// 
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
// 
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////    	CorsConfiguration corsConfiguration = new CorsConfiguration();
////        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
////        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200/"));
////        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
////        corsConfiguration.setAllowCredentials(true);
////        corsConfiguration.setExposedHeaders(List.of("Authorization"));
////        
////		http.authorizeRequests()
////		.anyRequest()
////		.authenticated()
////		.and()
////		.csrf().disable().cors().configurationSource(request -> corsConfiguration)
////		.and()
////		.httpBasic();
//    	http.authorizeRequests()
//    	.antMatchers("/user/all").hasAnyAuthority("user")
//		.anyRequest()
//		.authenticated()
//		.and()
//		.httpBasic();
//    }
//}