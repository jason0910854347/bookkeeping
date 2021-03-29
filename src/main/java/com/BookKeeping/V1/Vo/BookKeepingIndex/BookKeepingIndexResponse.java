package com.BookKeeping.V1.Vo.BookKeepingIndex;

import java.util.List;

import com.BookKeeping.V1.Vo.Conpoent.comboBox.ComboBox;

public class BookKeepingIndexResponse {

	/** 登入者 */
	private String userName;

	/** 類別(下拉選單) */
	private ComboBox subjectComboBox;

	/** 記帳資料明細 */
	private List<BkData> bkData;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ComboBox getSubjectComboBox() {
		return subjectComboBox;
	}

	public void setSubjectComboBox(ComboBox subjectComboBox) {
		this.subjectComboBox = subjectComboBox;
	}

	public List<BkData> getBkData() {
		return bkData;
	}

	public void setBkData(List<BkData> bkData) {
		this.bkData = bkData;
	}

}
