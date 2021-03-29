package com.BookKeeping.V1.Dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccountDataDto {

	@Id
	@Column(name = "DATA_ID")
	private Integer dataId;

	@Column(name = "SUBJECT_ID")
	private Integer subjectId;

	@Column(name = "SUBJECT_NAME")
	private String subjectName;

	@Column(name = "AMOUNT")
	private Integer amount;

	@Column(name = "ACCOUNT_ID")
	private Integer accountId;

	@Column(name = "CREATE_TIME")
	private Date createTime;

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
