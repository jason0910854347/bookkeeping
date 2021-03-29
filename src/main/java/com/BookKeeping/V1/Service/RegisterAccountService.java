package com.BookKeeping.V1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.BookKeeping.V1.Entity.SysAccount;
import com.BookKeeping.V1.Enums.SysAuthority;
import com.BookKeeping.V1.Repository.SysAccountRepository;
import com.BookKeeping.V1.Util.DateUtil;
import com.BookKeeping.V1.Vo.RegisterAccount.RegisterAccountRequest;
import com.BookKeeping.V1.Vo.RegisterAccount.RegisterAccountResponse;

@Service
public class RegisterAccountService {

	@Autowired
	private SysAccountRepository sysAccountRepository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public RegisterAccountResponse findData(RegisterAccountRequest rq) {
		RegisterAccountResponse res = new RegisterAccountResponse();
		SysAccount sysAccount = new SysAccount();
		try {
			sysAccount.setAccount(rq.getAccount());
			sysAccount.setPassWord(passwordEncoder.encode(rq.getPassword()));
			sysAccount.setUserName(rq.getUserName());
			sysAccount.setRoleId(SysAuthority.User.getRole());
			sysAccount.setCreateTime(DateUtil.getCurrentTimestamp());
			getSysAccountRepository().saveAndFlush(sysAccount);

			return res;
			
		} catch (Exception apiE) {
			
			throw apiE;
		}
	}

	public SysAccountRepository getSysAccountRepository() {
		return sysAccountRepository;
	}

}
