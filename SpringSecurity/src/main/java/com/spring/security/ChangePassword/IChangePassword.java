package com.spring.security.ChangePassword;

import org.springframework.security.core.userdetails.UserDetailsService;
/*
 * ��й�ȣ ���� ����� �����ϴ� �޼��带 �����ϴ� �������̽�
 */
public interface IChangePassword extends UserDetailsService{
	void changePassword(String username, String password);
}
