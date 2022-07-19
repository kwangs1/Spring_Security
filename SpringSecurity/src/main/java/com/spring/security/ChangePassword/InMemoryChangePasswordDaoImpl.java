package com.spring.security.ChangePassword;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class InMemoryChangePasswordDaoImpl extends InMemoryDaoImpl implements IChangePassword{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(String username, String password) {
		// TODO Auto-generated method stub
		
	}

}
