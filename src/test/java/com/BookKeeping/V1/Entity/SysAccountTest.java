package com.BookKeeping.V1.Entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.BookKeeping.V1.Repository.SysAccountRepository;
import com.BookKeeping.V1.Util.DateUtil;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@ActiveProfiles(value = "devMem")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false) // 手動控制測試資料的建立與清除
public class SysAccountTest {

	@Autowired
	private SysAccountRepository sysAccountRepository;

	private SysAccount sysAccount;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/** 測試: 新增 */
	@Before
	public void beforeEach() {
		sysAccount = new SysAccount();
		sysAccount.setAccount("account");
		sysAccount.setPassWord(passwordEncoder.encode("password_Jason"));
		sysAccount.setUserName("JiaHong");
		sysAccount.setRoleId(1);
		sysAccount.setCreateTime(DateUtil.getCurrentTimestamp());
		sysAccountRepository.saveAndFlush(sysAccount);
	}

	/** 測試: 刪除 */
	@After
	public void afterEach() {
		sysAccountRepository.delete(sysAccount);
	}

	/** 測試: 查詢、修改 */
	@Test
	public void test_查詢及修改() {
		Optional<SysAccount> querySysAccount = sysAccountRepository.findById(sysAccount.getAccountId());

		assertNotNull(querySysAccount.get());

		assertEquals("account", querySysAccount.get().getAccount());

		assertEquals(true, passwordEncoder.matches("password_Jason", querySysAccount.get().getPassWord()));

		assertEquals("JiaHong", querySysAccount.get().getUserName());

		assertEquals(1, querySysAccount.get().getRoleId());
	}
	
}
