package com.BookKeeping.V1.Repository.custom;

import java.util.List;

import com.BookKeeping.V1.Dao.SysAccountDto;

public interface SysAccountRepositoryCustom {

	/**
	 * 搜尋帳號清單(依權限向下搜尋)
	 * 
	 * @param account
	 * @return
	 */
	public List<SysAccountDto> queryByAccountDataList(Integer roleLevel);
}
