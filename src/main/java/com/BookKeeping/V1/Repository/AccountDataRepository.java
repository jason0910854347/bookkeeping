package com.BookKeeping.V1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BookKeeping.V1.Entity.AccountData;
import com.BookKeeping.V1.Repository.custom.AccountDataRepositoryCustom;

public interface AccountDataRepository extends JpaRepository<AccountData, Integer>, AccountDataRepositoryCustom {

}
