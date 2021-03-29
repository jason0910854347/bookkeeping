package com.BookKeeping.V1.Enums;

public enum SysAuthority {

	SuperAdmin(2, "系統管理員"),

	Admin(1, "管理員"),

	User(0, "一般使用者");

	/** 權限代碼 */
	private int role;

	/** 權限名稱 i18n */
	private String roleName;

	private SysAuthority(int role, String roleName) {
		this.role = role;
		this.roleName = roleName;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
