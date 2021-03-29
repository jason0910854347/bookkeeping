package com.BookKeeping.V1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BookKeeping.V1.Entity.SysAccount;
import com.BookKeeping.V1.Repository.custom.SysAccountRepositoryCustom;

public interface SysAccountRepository extends JpaRepository<SysAccount, Integer>, SysAccountRepositoryCustom {

	/**
	 * 搜尋帳號
	 * 
	 * @param account
	 * @return
	 */
	public SysAccount findByAccount(String account);
	
}
