package com.BookKeeping.V1.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "SYS_ROLE")
@TableGenerator(name = "SEQ_STORE", initialValue = 10000)
public class SysRole {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_STORE")
	@Column(name = "ROLE_ID")
	private Integer roleId;

	@Column(name = "ROLE_NAME")
	private String roleName;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "LEVEL")
	private Integer level;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
