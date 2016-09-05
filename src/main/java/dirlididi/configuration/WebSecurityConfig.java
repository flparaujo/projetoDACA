package dirlididi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
		.and()
		 	.authorizeRequests()
		 	.antMatchers("/api/stats/user").hasRole("NORMAL")
		 	.antMatchers("/api/solved/").hasRole("NORMAL")
		 	.antMatchers(HttpMethod.POST,"/api/solved/").hasRole("NORMAL")
		 	.antMatchers(HttpMethod.POST,"/api/problem/").hasRole("ADMIN")
		 	.antMatchers(HttpMethod.PUT,"/api/problem/").hasRole("ADMIN")
		 	.antMatchers("/h2-console/**").permitAll()
		.and()
		.csrf()
		 	.disable();
		 	
	  http.headers().frameOptions().disable();
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("NORMAL");
    }
	
	 @Override
	 public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        //auth
	         //       .userDetailsService(userDetailsService)
	           //     .passwordEncoder(new BCryptPasswordEncoder());
	    }
}
