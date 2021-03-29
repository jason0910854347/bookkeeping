package com.BookKeeping.V1.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.BookKeeping.V1.Entity.SysAccount;
import com.BookKeeping.V1.Entity.SysRole;
import com.BookKeeping.V1.Enums.SysAuthority;
import com.BookKeeping.V1.Repository.SysAccountRepository;
import com.BookKeeping.V1.Repository.SysRoleRepository;
import com.BookKeeping.V1.Vo.RegisterAccount.RegisterAccountRequest;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@ActiveProfiles(value = "devMem")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false) // 手動控制測試資料的建立與清除
public class RegisterAccountServiceTest {

	@Autowired
	private SysAccountRepository sysAccountRepository;

	@Autowired
	private SysRoleRepository sysRoleRepository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private SysAccount sysAccount;

	private SysRole sysRole;

	private RegisterAccountService registerAccountService = new RegisterAccountService() {

		@Override
		public SysAccountRepository getSysAccountRepository() {
			return sysAccountRepository;
		}

	};

	/** 測試: 新增 */
	@Before
	public void beforeEach() {
		sysRole = new SysRole();
		sysRole.setRoleName("user");
		sysRole.setRemark("一般使用者");
		sysRole.setLevel(SysAuthority.User.getRole());
		sysRoleRepository.saveAndFlush(sysRole);
	}

	/** 測試: 刪除 */
	@After
	public void afterEach() {
		sysRoleRepository.delete(sysRole);
		sysAccountRepository.delete(sysAccount);
	}

	/** 測試: 新增帳號 */
	@Test
	public void test_新增帳號() {
		RegisterAccountRequest rq = new RegisterAccountRequest();
		rq.setAccount("Jason1997");
		rq.setPassword("password_Jason");
		rq.setUserName("Jason");

		registerAccountService.findData(rq);

		sysAccount = sysAccountRepository.findByAccount(rq.getAccount());

		assertEquals("Jason1997", sysAccount.getAccount());

		assertEquals(true, passwordEncoder.matches("password_Jason", sysAccount.getPassWord()));

		assertEquals("Jason", sysAccount.getUserName());

		assertEquals(SysAuthority.User.getRole(), sysAccount.getRoleId());

	}
}
