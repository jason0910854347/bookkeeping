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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.BookKeeping.V1.Dao.SysAccountDto;
import com.BookKeeping.V1.Entity.SysAccount;
import com.BookKeeping.V1.Entity.SysRole;
import com.BookKeeping.V1.Enums.SysAuthority;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@ActiveProfiles(value = "devMem")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false) // 手動控制測試資料的建立與清除
public class SysAccountTest_findByAccountDataList {

	@Autowired
	private SysAccountRepository sysAccountRepository;

	@Autowired
	private SysRoleRepository sysRoleRepository;

	private SysAccount sysAccount;

	private SysRole sysRole;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private String account = "JiaHong";
	private String password = "password_Jason";
	private String userName = "JiaHong";

	/** 測試: 新增 */
	@Before
	public void beforeEach() {
		sysRole = new SysRole();
		sysRole.setLevel(SysAuthority.SuperAdmin.getRole());
		sysRole.setRoleName(SysAuthority.SuperAdmin.getRoleName());
		sysRole.setRemark(SysAuthority.SuperAdmin.getRoleName());
		sysRoleRepository.saveAndFlush(sysRole);

		sysAccount = new SysAccount();
		sysAccount.setAccount(account);
		sysAccount.setPassWord(passwordEncoder.encode(password));
		sysAccount.setUserName(userName);
		sysAccount.setRoleId(sysRole.getRoleId());
		sysAccount.setCreateTime(DateUtil.now());
		sysAccountRepository.saveAndFlush(sysAccount);
	}

	/** 測試: 刪除 */
	@After
	public void afterEach() {
		sysAccountRepository.delete(sysAccount);
		sysRoleRepository.delete(sysRole);
	}

	/** 測試: 帳號清單與權限管理 */
	@Test
	public void test_帳號清單與權限管理() {
		List<SysAccountDto> manageSysAccount = sysAccountRepository.queryByAccountDataList(SysAuthority.SuperAdmin.getRole());

		assertNotNull(manageSysAccount);

		assertEquals(1, manageSysAccount.size());

		assertEquals("JiaHong", manageSysAccount.get(0).getAccount());

		assertEquals(true, passwordEncoder.matches("password_Jason", manageSysAccount.get(0).getPassWord()));

		assertEquals("JiaHong", manageSysAccount.get(0).getUserName());

		assertEquals("系統管理員", manageSysAccount.get(0).getRoleName());

	}
}
