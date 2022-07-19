package com.spring.security.RememberMe;

import java.security.SecureRandom;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.util.DigestUtils;
/**
* �� ������ ������� ��� ��ū�� IP �ּҸ� �߰��մϴ�.
 * �߰� ������ ���� �� ���� ����.
 *  
 * @author Mularien
 */
public class IPTokenBasedRememberMeServices extends TokenBasedRememberMeServices{
	
	//token�� �űԻ����� ���� ���� �ѹ� ���� ��ü
	SecureRandom random;
	
	public IPTokenBasedRememberMeServices(String key, UserDetailsService userDetailsService) {
		super(key, userDetailsService);
		random = new SecureRandom();
		
		// TODO Auto-generated constructor stub
	}
	//ThreadLocal HttpServletRequest�� �����ϰ� ���������� ������ ��ƿ��Ƽ �޼��� ���
    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public HttpServletRequest getContext() {
        return requestHolder.get();
    }

    public void setContext(HttpServletRequest context) {
        requestHolder.set(context);
    }

//HttpServletRequest�κ��� IP�ּҸ� ���������� ��ƿ��Ƽ �޼��� �߰�
    protected String getUserIPAddress(HttpServletRequest request) {
		return request.getRemoteAddr();
	}
	
//��Ⱚ�� �����ϱ� ���� �޼���
	@Override
	public void onLoginSuccess(HttpServletRequest request,
			HttpServletResponse response,
			Authentication successfulAuthentication) {
		try
		{
			setContext(request);
			super.onLoginSuccess(request, response, successfulAuthentication);
		}
		finally
		{
			setContext(null);
		}
	}

/*
 * ���� ũ�������� MD5 �ؽø� �����ϴ� �� ���Ǵ� �޼��� ȣ�� 
 * ��û���κ��� IP�ּҸ� �޾ƿ��� �����ӿ�ũ���� �����ϴ� ��ƿ��Ƽ Ŭ������ ����� ��ȯ�� ��Ű�� ���ڵ��ϵ���
 */
@Override
protected void setCookie(String[] tokens, int maxAge,
		HttpServletRequest request, HttpServletResponse response) {
	// append the IP adddress to the cookie
	String[] tokensWithIPAddress = Arrays.copyOf(tokens, tokens.length+1);
	tokensWithIPAddress[tokensWithIPAddress.length-1] = getUserIPAddress(request);
	super.setCookie(tokensWithIPAddress, maxAge, request, response);
}	
/*
 * ��Ű�� ������ �����ϴµ� ����� �޼���
 * �θ� �޼��带 ȣ���ϱ� ���� ���� IP�ּҸ� Ȯ���� �θ� �޼��� ȣ�� ���� �����ϵ��� ���� ����
 */
@Override
protected UserDetails processAutoLoginCookie(String[] cookieTokens,
		HttpServletRequest request, HttpServletResponse response) {
	try
	{
		setContext(request);
		// take off the last token
		String ipAddressToken = cookieTokens[cookieTokens.length-1];
		if(!getUserIPAddress(request).equals(ipAddressToken))
		{
			throw new InvalidCookieException("Cookie IP Address did not contain a matching IP (contained '" + ipAddressToken + "')");
		}
		
		return super.processAutoLoginCookie(Arrays.copyOf(cookieTokens, cookieTokens.length-1), request, response);
	}
	finally
	{
		setContext(null);
	}
}	
/*
 * ���� ũ�������� MD5 �ؽø� �����ϴ� �� ���Ǵ� �޼���
 * ��û���κ��� IP�ּҸ� �޾ƿ��� �����������ӿ�ũ���� �����ϴ� ��ƿ��Ƽ Ŭ������ ���
 * ��ȯ�� ��Ű�� ���ڵ��ϵ��� �޼��� ����
 */
@Override
protected String makeTokenSignature(long tokenExpiryTime, String username, String password) {
	return DigestUtils.md5DigestAsHex(
			(username + ":" + tokenExpiryTime + ":" + password + ":" + getKey() + ":" + getUserIPAddress(getContext()))
					.getBytes());
}

}
