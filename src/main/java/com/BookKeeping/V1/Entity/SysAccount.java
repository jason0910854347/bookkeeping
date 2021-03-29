package com.BookKeeping.V1.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "SYS_ACCOUNT")
@TableGenerator(name = "SEQ_STORE", initialValue = 10000)
public class SysAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_STORE")
	@Column(name = "ACCOUNT_ID")
	private Integer accountId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "ACCOUNT")
	private String account;

	@Column(name = "PASSWORD")
	private String passWord;

	@Column(name = "ROLE_ID")
	private Integer roleId;

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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
