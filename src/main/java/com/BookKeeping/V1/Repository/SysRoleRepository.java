package com.BookKeeping.V1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BookKeeping.V1.Entity.SysRole;

public interface SysRoleRepository extends JpaRepository<SysRole, Integer> {

	/**
	 * 搜尋角色名稱
	 * 
	 * @param roleName
	 * @return
	 */
	public SysRole findByRoleName(String roleName);

	/**
	 * 搜尋角色Id
	 * 
	 * @param roleId
	 * @return
	 */
	public SysRole findByRoleId(Integer roleId);

}
