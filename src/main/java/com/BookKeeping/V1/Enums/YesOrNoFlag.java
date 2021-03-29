package com.BookKeeping.V1.Enums;

public enum YesOrNoFlag {

	Yes(1, "是"),

	No(0, "否");

	/** 權限代碼 */
	private int flag;

	/** 權限名稱 i18n */
	private String flagName;

	private YesOrNoFlag(int flag, String flagName) {
		this.flag = flag;
		this.flagName = flagName;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getFlagName() {
		return flagName;
	}

	public void setFlagName(String flagName) {
		this.flagName = flagName;
	}

}
