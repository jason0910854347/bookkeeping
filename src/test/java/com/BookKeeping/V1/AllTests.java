package com.BookKeeping.V1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.BookKeeping.V1.Entity.AccountDataTest;
import com.BookKeeping.V1.Entity.AccountSubjectTest;
import com.BookKeeping.V1.Entity.SysAccountTest;
import com.BookKeeping.V1.Entity.SysRoleTest;
import com.BookKeeping.V1.Repository.AccountSubjectTest_findByCreateUser;
import com.BookKeeping.V1.Repository.SysAccountTest_findByAccount;
import com.BookKeeping.V1.Repository.SysAccountTest_findByAccountDataList;
import com.BookKeeping.V1.Service.AuthenticationServiceTest;
import com.BookKeeping.V1.Service.RegisterAccountServiceTest;

@RunWith(Suite.class)
@SuiteClasses({	
	AccountDataTest.class,
	
	AccountSubjectTest.class,
	AccountSubjectTest_findByCreateUser.class,
	
	SysAccountTest.class,
	SysAccountTest_findByAccount.class,
	SysAccountTest_findByAccountDataList.class,
	
	SysRoleTest.class,
	
	RegisterAccountServiceTest.class,
	
	AuthenticationServiceTest.class
})
public class AllTests {

}
