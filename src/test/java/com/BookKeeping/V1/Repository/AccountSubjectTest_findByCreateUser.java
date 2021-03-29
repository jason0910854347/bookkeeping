package com.BookKeeping.V1.Repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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

import com.BookKeeping.V1.Entity.AccountSubject;
import com.BookKeeping.V1.Repository.AccountSubjectRepository;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@ActiveProfiles(value = "devMem")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false) // 手動控制測試資料的建立與清除
public class AccountSubjectTest_findByCreateUser {

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

	/** 測試: 搜尋該使用者建立會計科目 */
	@Test
	public void test_搜尋該使用者建立會計科目() {
		List<AccountSubject> userCreateAccountSubject = accountSubjectRepository.findByAccountId(accountSubject.getAccountId());

		assertNotNull(userCreateAccountSubject);

		assertEquals(1, userCreateAccountSubject.size());

		assertEquals("飲食", userCreateAccountSubject.get(0).getSubjectName());

		assertEquals(1, userCreateAccountSubject.get(0).getAccountId());
	}
}
