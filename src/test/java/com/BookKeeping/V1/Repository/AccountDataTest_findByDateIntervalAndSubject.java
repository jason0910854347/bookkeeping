package com.BookKeeping.V1.Repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.List;

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

import com.BookKeeping.V1.Dao.AccountDataDto;
import com.BookKeeping.V1.Entity.AccountData;
import com.BookKeeping.V1.Entity.AccountSubject;
import com.BookKeeping.V1.Repository.AccountDataRepository;
import com.BookKeeping.V1.Repository.AccountSubjectRepository;
import com.BookKeeping.V1.Util.DateUtil;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@ActiveProfiles(value = "devMem")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false) // 手動控制測試資料的建立與清除
public class AccountDataTest_findByDateIntervalAndSubject {

	@Autowired
	private AccountDataRepository accountDataRepository;

	@Autowired
	private AccountSubjectRepository accountSubjectRepository;

	private AccountData accountData;

	private AccountSubject accountSubject;

	/**
	 * 測試: 新增
	 * 
	 * @throws ParseException
	 */
	@Before
	public void beforeEach() throws ParseException {

		accountSubject = new AccountSubject();
		accountSubject.setAccountId(1);
		accountSubject.setSubjectName("飲食");
		accountSubject.setCreateTime(DateUtil.getCurrentTimestamp());
		accountSubjectRepository.saveAndFlush(accountSubject);

		accountData = new AccountData();
		accountData.setSubjectId(accountSubject.getSubjectId());
		accountData.setAmount(300);
		accountData.setAccountId(1);
		accountData.setCreateTime(DateUtil.strToDate("20201010", DateUtil.PATTEN_YEAR_MONTH_DAY_NO_HYPHEN));
		accountDataRepository.saveAndFlush(accountData);

	}

	/** 測試: 刪除 */
	@After
	public void afterEach() {
		accountSubjectRepository.delete(accountSubject);
		accountDataRepository.delete(accountData);
	}

	/** 測試: 搜尋日期區間與指定會計科目 */
	@Test
	public void test_搜尋日期區間與指定會計科目() {
		List<AccountDataDto> searchDateIntervalAndSubject = accountDataRepository.queryByDateIntervalAndSubject("20201009", "20201011", null);

		assertNotNull(searchDateIntervalAndSubject);

		assertEquals(1, searchDateIntervalAndSubject.size());

		assertEquals("飲食", searchDateIntervalAndSubject.get(0).getSubjectName());

		assertEquals(300, searchDateIntervalAndSubject.get(0).getAmount());

		assertEquals(1, searchDateIntervalAndSubject.get(0).getAccountId());
	}
}
