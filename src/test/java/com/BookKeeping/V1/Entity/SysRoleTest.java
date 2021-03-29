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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.BookKeeping.V1.Enums.SysAuthority;
import com.BookKeeping.V1.Repository.SysRoleRepository;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@ActiveProfiles(value = "devMem")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false) // 手動控制測試資料的建立與清除
public class SysRoleTest {

	@Autowired
	private SysRoleRepository sysRoleRepository;

	private SysRole sysRole;

	/** 測試: 新增 */
	@Before
	public void beforeEach() {
		sysRole = new SysRole();
		sysRole.setRoleName("admin");
		sysRole.setRemark("管理權限");
		sysRole.setLevel(SysAuthority.SuperAdmin.getRole());
		sysRoleRepository.saveAndFlush(sysRole);
	}

	/** 測試: 刪除 */
	@After
	public void afterEach() {
		sysRoleRepository.delete(sysRole);
	}

	/** 測試: 查詢、修改 */
	@Test
	public void test_查詢及修改() {
		Optional<SysRole> querySysRole = sysRoleRepository.findById(sysRole.getRoleId());

		assertNotNull(querySysRole.get());

		assertEquals("admin", querySysRole.get().getRoleName());

		assertEquals("管理權限", querySysRole.get().getRemark());

		assertEquals(2, querySysRole.get().getLevel());
	}
}
