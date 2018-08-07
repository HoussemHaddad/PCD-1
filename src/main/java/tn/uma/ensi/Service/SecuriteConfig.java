package tn.uma.ensi.Service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecuriteConfig extends WebSecurityConfigurerAdapter{
@Autowired
	private DataSource dataSource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	/*	auth.inMemoryAuthentication()
		.withUser("admin").password("admin").roles("USER","ADMIN");
		auth.inMemoryAuthentication()
		.withUser("user").password("user").roles("USER");
	*/
		
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select login as principal,	mot_de_passe as credentials,active from secretaire where login=?")
		.authoritiesByUsernameQuery("select login as principal, role as role from user_role where login=?")
		.rolePrefix("ROLE_");
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/index").hasRole("USER");
		http.authorizeRequests().antMatchers("/save","/delete","/EditSecretaire","/FormSecretaire","/ListeSecretaire").hasRole("ADMIN");
	http.exceptionHandling().accessDeniedPage("/403");
	http.logout().deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true);
	}
	
	
}
