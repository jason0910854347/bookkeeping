package com.BookKeeping.V1.Vo;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class UserInfo {

	public static String userInfo = "userInfo";

	private Integer id;

	private String account;

	private String password;

	private String userName;

	private List<GrantedAuthority> grantedAuthority;

	public static String getUserInfo() {
		return userInfo;
	}

	public static void setUserInfo(String userInfo) {
		UserInfo.userInfo = userInfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<GrantedAuthority> getGrantedAuthority() {
		return grantedAuthority;
	}

	public void setGrantedAuthority(List<GrantedAuthority> grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}

}
