package dirlididi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import dirlididi.domain.Normal;
import dirlididi.repositories.NormalRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
		.and()
		 	.authorizeRequests()
		 	.antMatchers("/api/stats/user").hasAnyAuthority("NORMAL")
		 	.antMatchers("/api/solved/").hasAnyAuthority("NORMAL")
		 	.antMatchers(HttpMethod.POST,"/api/solved/").hasAnyAuthority("NORMAL")
		 	.antMatchers(HttpMethod.POST,"/api/problem/").hasAnyAuthority("ADMIN")
		 	.antMatchers(HttpMethod.PUT,"/api/problem/").hasAnyAuthority("ADMIN")
		 	.antMatchers("/h2-console/**").permitAll()
		.and()
		.csrf()
		 	.disable();
		 	
	  http.headers().frameOptions().disable();
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(userDetailsService());
		
		auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("NORMAL");
    }
	 
	 @Bean
	 protected UserDetailsService userDetailsService() {
		 return new UserDetailsService() {
			@Autowired
	        NormalRepository normalRepository;
			
			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				Normal usuario = normalRepository.findNormalByEmail(email);
				//System.out.println(usuario.getTipo().toString());
				  if (usuario != null) {
	                    return new User(usuario.getEmail(), usuario.getSenha(), true, true, true, true,
	                            AuthorityUtils.createAuthorityList("NORMAL"));
	                } else {
	                    throw new UsernameNotFoundException("could not find the user '" + usuario + "'");
	                }
			}
		};
	 }
}
