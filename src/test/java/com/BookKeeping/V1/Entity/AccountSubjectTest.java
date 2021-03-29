package com.BookKeeping.V1.Entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.assertj.core.util.DateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.BookKeeping.V1.Repository.AccountSubjectRepository;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@ActiveProfiles(value = "devMem")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false) // 手動控制測試資料的建立與清除
public class AccountSubjectTest {

	@Autowired
	private AccountSubjectRepository accountSubjectRepository;

	private AccountSubject accountSubject;

	/** 測試: 新增 */
	@Before
	public void beforeEach() {
		accountSubject = new AccountSubject();
		accountSubject.setSubjectName("飲食");
		accountSubject.setAccountId(1);
		accountSubject.setCreateTime(DateUtil.now());
		accountSubjectRepository.saveAndFlush(accountSubject);
	}

	/** 測試: 刪除 */
	@After
	public void afterEach() {
		accountSubjectRepository.delete(accountSubject);
	}

	/** 測試: 查詢、修改 */
	@Test
	public void test_查詢及修改() {
		Optional<AccountSubject> queryAccountSubject = accountSubjectRepository.findById(accountSubject.getSubjectId());

		assertNotNull(queryAccountSubject.get());

		assertEquals("飲食", queryAccountSubject.get().getSubjectName());

		assertEquals(1, queryAccountSubject.get().getAccountId());
	}
}
