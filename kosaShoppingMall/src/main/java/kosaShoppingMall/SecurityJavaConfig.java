
package kosaShoppingMall;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityJavaConfig 
	extends WebSecurityConfigurerAdapter{
	@Override
	//사용하지 않겠다 디제이블?
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.cors().disable() //cors방지
			.csrf().disable() //csrf방지
			.formLogin().disable()
			.headers().frameOptions().disable();
	}
	@Bean
	// 암호와 모듈..
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}