package com.BookKeeping.V1.Dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SysAccountDto {
	
	@Id
	@Column(name = "ACCOUNT_ID")
	private Integer accountId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "ACCOUNT")
	private String account;

	@Column(name = "PASSWORD")
	private String passWord;

	@Column(name = "ROLE_NAME")
	private String roleName;

	@Column(name = "CREATE_TIME")
	private Date createTime;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
