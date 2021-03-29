package com.BookKeeping.V1.Repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.BookKeeping.V1.Dao.AccountDataDto;
import com.BookKeeping.V1.Repository.custom.AccountDataRepositoryCustom;

@Repository("AccountDataRepository")
public class AccountDataRepositoryCustomImpl implements AccountDataRepositoryCustom {

	@PersistenceContext
	public EntityManager entityManager;

	/**
	 * 查詢記帳列表
	 */
	@Override
	public List<AccountDataDto> queryByDateIntervalAndSubject(String startDate, String endDate, Integer subject) {
		StringBuilder sb = new StringBuilder();
		sb.append("  SELECT data.\"DATA_ID\", data.\"SUBJECT_ID\", subject.\"SUBJECT_NAME\", data.\"AMOUNT\", ");
		sb.append("  data.\"ACCOUNT_ID\", data.\"CREATE_TIME\" ");
		sb.append("  FROM public.\"ACCOUNT_DATA\" data ");
		sb.append("  LEFT JOIN \"ACCOUNT_SUBJECT\" subject ON data.\"SUBJECT_ID\" = subject.\"SUBJECT_ID\" ");
		sb.append("  AND data.\"ACCOUNT_ID\" = subject.\"ACCOUNT_ID\" ");
		sb.append("  WHERE 1=1 ");

		if (StringUtils.isNotBlank(startDate)) {
			sb.append("  AND CAST (data.\"CREATE_TIME\" as date) >= :startDate ");
		}

		if (StringUtils.isNotBlank(endDate)) {
			sb.append("  AND CAST (data.\"CREATE_TIME\"  as date) <= :endDate");
		}

		if (subject != null) {
			sb.append("  AND data.\"SUBJECT_ID\" =:subject ");
		}
		sb.append("  ORDER BY data.\"CREATE_TIME\" DESC  ");

		Query query = entityManager.createNativeQuery(sb.toString(), AccountDataDto.class);

		if (StringUtils.isNotBlank(startDate)) {
			query.setParameter("startDate", startDate);
		}

		if (StringUtils.isNotBlank(endDate)) {
			query.setParameter("endDate", endDate);
		}

		if (subject != null) {
			query.setParameter("subject", subject);
		}

		return query.getResultList();
	}
}