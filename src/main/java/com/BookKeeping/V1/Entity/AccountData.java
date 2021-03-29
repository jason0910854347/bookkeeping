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
@Table(name = "ACCOUNT_DATA")
@TableGenerator(name = "SEQ_STORE", initialValue = 10000)
public class AccountData {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_STORE")
	@Column(name = "DATA_ID")
	private Integer dataId;

	@Column(name = "SUBJECT_ID")
	private Integer subjectId;

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
