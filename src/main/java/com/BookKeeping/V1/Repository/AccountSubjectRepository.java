package com.BookKeeping.V1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BookKeeping.V1.Entity.AccountSubject;

public interface AccountSubjectRepository extends JpaRepository<AccountSubject, Integer> {

	/**
	 * 使用者建立之科目列表
	 * 
	 * @param createUser
	 * @return
	 */
	public List<AccountSubject> findByAccountId(Integer accountId);

}
