package com.spring.security.ChangePassword;

import org.springframework.security.core.userdetails.UserDetailsService;
/*
 * 비밀번호 변깅 기능을 제공하는 메서드를 정의하는 인터페이스
 */
public interface IChangePassword extends UserDetailsService{
	void changePassword(String username, String password);
}
