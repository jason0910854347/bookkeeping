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

import com.BookKeeping.V1.Repository.AccountDataRepository;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@ActiveProfiles(value = "devMem")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false) // 手動控制測試資料的建立與清除
public class AccountDataTest {

	@Autowired
	private AccountDataRepository accountDataRepository;

	private AccountData accountData;

	/** 測試: 新增 */
	@Before
	public void beforeEach() {
		accountData = new AccountData();
		accountData.setSubjectId(1);
		accountData.setAmount(10);
		accountData.setAccountId(1);
		accountData.setCreateTime(DateUtil.now());
		accountDataRepository.saveAndFlush(accountData);
	}

	/** 測試: 刪除 */
	@After
	public void afterEach() {
		accountDataRepository.delete(accountData);
	}

	/** 測試: 查詢、修改 */
	@Test
	public void test_查詢及修改() {
		Optional<AccountData> queryAccountData = accountDataRepository.findById(accountData.getDataId());

		assertNotNull(queryAccountData.get());

		assertEquals(1, queryAccountData.get().getSubjectId());

		assertEquals(10, queryAccountData.get().getAmount());

		assertEquals(1, queryAccountData.get().getAccountId());
	}
}
