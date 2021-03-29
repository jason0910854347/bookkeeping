package com.BookKeeping.V1.Service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.BookKeeping.V1.Entity.SysAccount;
import com.BookKeeping.V1.Entity.SysRole;
import com.BookKeeping.V1.Repository.SysAccountRepository;
import com.BookKeeping.V1.Repository.SysRoleRepository;
import com.BookKeeping.V1.Vo.UserInfo;

@Service
public class AuthenticationService implements AuthenticationProvider {

	@Autowired
	private SysAccountRepository sysAccountRepository;

	@Autowired
	private SysRoleRepository sysRoleRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		UserInfo userInfo = getAuthenticate(authentication.getName(), authentication.getCredentials().toString());

		// 將帳號、密碼、角色回傳給Spring Security底層
		return new UsernamePasswordAuthenticationToken(userInfo.getAccount(), userInfo.getPassword(),
				userInfo.getGrantedAuthority());
	}

	public UserInfo getAuthenticate(String account, String password) {

		// 查詢是否有帳號資料
		SysAccount sysAccount = getSysAccountRepository().findByAccount(account);

		if (StringUtils.isNotBlank(account)) {

			// bcrypt加密工具
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			// passwordEncoder.matches驗證密碼與資料庫是否一致

			// 密碼正確才會繼續執行
			if (passwordEncoder.matches(password, sysAccount.getPassWord())) {

				// 取得帳號的相關角色
				if (sysAccount.getRoleId() != null) {
					SysRole sysRole = getSysRoleRepository().findByRoleId(sysAccount.getRoleId());
					String[] roleArr = new String[1];
					if (sysRole != null) {
						roleArr[0] = sysRole.getRoleName();
					}
					List<GrantedAuthority> grantedAuthority = AuthorityUtils.createAuthorityList(roleArr);

					UserInfo userInfo = new UserInfo();
					userInfo.setAccount(sysAccount.getAccount());
					userInfo.setPassword(sysAccount.getPassWord());
					userInfo.setGrantedAuthority(grantedAuthority);

					return userInfo;
				}
			}
		}
		return null;
	}

	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	protected SysAccountRepository getSysAccountRepository() {
		return sysAccountRepository;
	}

	protected SysRoleRepository getSysRoleRepository() {
		return sysRoleRepository;
	}

}
