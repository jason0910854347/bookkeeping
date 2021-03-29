package com.BookKeeping.V1.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/console/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/systemManage123", "/admin/news123/**")
				.hasAnyRole(new String[] { "USER", "ADMIN" })// 這些路徑只能讓"USER","ADMIN"腳色存取
				.antMatchers("/news/queryNews/**").hasAnyRole(new String[] { "ADMIN" })// 這些路徑只能讓"ADMIN"腳色存取
				.antMatchers("/h2/**", "/h2-console/**", "/login_page", "/loginAction", "/admin/logoutAccount", "/")
				.permitAll()// 這些路徑允許存取，不用任何腳色
				.antMatchers("/admin/**").authenticated()// 這些路徑，只要經過認證取得腳色就可以存取
				.and().formLogin().loginPage("/login_page")// 未經認證取得腳色，會導到此路徑
				.loginProcessingUrl("/loginAction")// 只要存取此路徑，會進行傳送帳號密碼進行認證
				.defaultSuccessUrl("/admin/systemManage123")// 成功認證取得腳色，會導到此路徑
				.usernameParameter("username1")// 從登入網頁請求取得帳號的變數名稱
				.passwordParameter("password1");// 從登入網頁請求取得密碼的變數名稱;

		http.csrf().disable();// 關閉CSRF防護
		http.headers().frameOptions().sameOrigin();// 防護攻擊
	}

}
