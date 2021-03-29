package com.BookKeeping.V1.Repository.custom;

import java.util.List;

import com.BookKeeping.V1.Dao.AccountDataDto;

public interface AccountDataRepositoryCustom {

	/**
	 * 查詢記帳列表
	 * 
	 * @param startDate
	 * @param endDate
	 * @param subject   會計科目
	 * @return
	 */
	public List<AccountDataDto> queryByDateIntervalAndSubject(String startDate, String endDate, Integer subject);
}
