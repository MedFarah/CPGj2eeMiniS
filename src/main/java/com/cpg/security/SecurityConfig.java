
package com.cpg.security;

import javax.crypto.EncryptedPrivateKeyInfo;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	//	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(NoOpPasswordEncoder.getInstance())
		.usersByUsernameQuery("select username as principal, password as credentials,true from utilisateur where username=?")
		.authoritiesByUsernameQuery("select username as principal , authorities as role from utilisateur where username=?")
		
	//.passwordEncoder(passwordEncoder())
	.rolePrefix("ROLE_");
		
		//auth.inMemoryAuthentication().withUser("admin").password("{noop}pass").roles("admin");
		//auth.inMemoryAuthentication().withUser("user").password("{noop}pass").roles("user");

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//http.formLogin();
		http.formLogin().loginPage("/login").defaultSuccessUrl("/index").usernameParameter("username")
		.passwordParameter("password");
		
		http.csrf().disable();
		//http.authorizeRequests().anyRequest().authenticated();
		
		http.authorizeRequests().
		antMatchers("/login").permitAll()
		//http.authorizeRequests()
		.antMatchers("/supp","/delete","/updates").hasRole("admin")
		.anyRequest().authenticated();
	//	.anyRequest().permitAll().and().formLogin().permitAll();
		
		// http.authorizeRequests()
		// .antMatchers("/supp","/delete","/updates").hasRole("admin")
		// .anyRequest().authenticated();
		http.exceptionHandling().accessDeniedPage("/403");

	}
}
