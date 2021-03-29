package com.BookKeeping.V1.Repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.BookKeeping.V1.Dao.SysAccountDto;
import com.BookKeeping.V1.Repository.custom.SysAccountRepositoryCustom;

@Repository("SysAccountRepository")
public class SysAccountRepositoryCustomImpl implements SysAccountRepositoryCustom {
 
	@PersistenceContext
	public EntityManager entityManager;

	/**
	 * 搜尋帳號清單(依權限向下搜尋)
	 */
	@Override
	public List<SysAccountDto> queryByAccountDataList(Integer roleLevel) {
		StringBuilder sb = new StringBuilder();
		sb.append("  SELECT sysA.\"ACCOUNT_ID\", sysA.\"USER_NAME\", sysA.\"ACCOUNT\", ");
		sb.append("  sysA.\"PASSWORD\", sysR.\"ROLE_NAME\", sysA.\"CREATE_TIME\" ");
		sb.append("  FROM \"SYS_ACCOUNT\" sysA ");
		sb.append("  LEFT JOIN  \"SYS_ROLE\" sysR ON sysA.\"ROLE_ID\" = sysR.\"ROLE_ID\" ");
		sb.append("  WHERE sysR.\"LEVEL\" <= :roleLevel ");
		sb.append("  ORDER BY sysR.\"LEVEL\" DESC  ");

		Query query = entityManager.createNativeQuery(sb.toString(), SysAccountDto.class);

		query.setParameter("roleLevel", roleLevel);

		return query.getResultList();
	}

}
