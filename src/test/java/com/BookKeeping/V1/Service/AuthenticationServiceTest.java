package com.BookKeeping.V1.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.BookKeeping.V1.Entity.SysAccount;
import com.BookKeeping.V1.Entity.SysRole;
import com.BookKeeping.V1.Enums.SysAuthority;
import com.BookKeeping.V1.Repository.SysAccountRepository;
import com.BookKeeping.V1.Repository.SysRoleRepository;
import com.BookKeeping.V1.Util.DateUtil;
import com.BookKeeping.V1.Vo.UserInfo;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@ActiveProfiles(value = "devMem")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false) // 手動控制測試資料的建立與清除
public class AuthenticationServiceTest {

	@Autowired
	private SysAccountRepository sysAccountRepository;

	@Autowired
	private SysRoleRepository sysRoleRepository;

	private SysAccount sysAccount;

	private SysRole sysRole;

	private BCryptPasswordEncoder passwordEncoder;

	String accountStr = "userA";
	String password = "123";
	String roleName = "AdminTest";

	private AuthenticationService authenticationService = new AuthenticationService() {
		protected SysAccountRepository getSysAccountRepository() {
			return sysAccountRepository;
		}

		protected SysRoleRepository getSysRoleRepository() {
			return sysRoleRepository;
		}
	};

	/** 測試: 新增 */
	@Before
	public void beforeEach() {
		passwordEncoder = new BCryptPasswordEncoder();

		sysRole = new SysRole();
		sysRole.setRoleName(roleName);
		sysRole.setRemark("管理權限");
		sysRole.setLevel(SysAuthority.Admin.getRole());
		sysRoleRepository.saveAndFlush(sysRole);

		sysAccount = new SysAccount();
		sysAccount.setUserName("userName");
		sysAccount.setAccount(accountStr);
		sysAccount.setPassWord(passwordEncoder.encode(password));
		sysAccount.setRoleId(sysRole.getRoleId());
		sysAccount.setCreateTime(DateUtil.getCurrentTimestamp());
		sysAccountRepository.saveAndFlush(sysAccount);
	}

	/** 測試: 刪除 */
	@After
	public void afterEach() {
		sysAccountRepository.delete(sysAccount);
		sysRoleRepository.delete(sysRole);
	}

	/** 測試: 查詢 */
	@Test
	public void test_查詢() {

		UserInfo userInfo = authenticationService.getAuthenticate(accountStr, password);

		assertNotNull(userInfo);

		assertEquals(accountStr, userInfo.getAccount());

		SimpleGrantedAuthority simpleGrantedAuthority = (SimpleGrantedAuthority) userInfo.getGrantedAuthority().stream()
				.collect(Collectors.toList()).get(0);

		assertEquals(roleName, simpleGrantedAuthority.getAuthority());

	}
}
