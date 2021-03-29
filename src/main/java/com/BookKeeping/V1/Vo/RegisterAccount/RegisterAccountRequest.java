package com.BookKeeping.V1.Vo.RegisterAccount;

public class RegisterAccountRequest {

	/** 帳號 */
	private String account;

	/** 密碼 */
	private String password;

	/** 姓名 */
	private String userName;

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

}
