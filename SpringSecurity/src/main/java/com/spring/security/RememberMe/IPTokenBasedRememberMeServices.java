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
* 이 구현은 사용자의 기억 토큰에 IP 주소를 추가합니다.
 * 추가 수준의 보안 및 부인 방지.
 *  
 * @author Mularien
 */
public class IPTokenBasedRememberMeServices extends TokenBasedRememberMeServices{
	
	//token값 신규생성을 위한 랜덤 넘버 생성 객체
	SecureRandom random;
	
	public IPTokenBasedRememberMeServices(String key, UserDetailsService userDetailsService) {
		super(key, userDetailsService);
		random = new SecureRandom();
		
		// TODO Auto-generated constructor stub
	}
	//ThreadLocal HttpServletRequest를 설정하고 가져오도록 간단한 유틸리티 메서드 사용
    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public HttpServletRequest getContext() {
        return requestHolder.get();
    }

    public void setContext(HttpServletRequest context) {
        requestHolder.set(context);
    }

//HttpServletRequest로부터 IP주소를 가져오도록 유틸리티 메서드 추가
    protected String getUserIPAddress(HttpServletRequest request) {
		return request.getRemoteAddr();
	}
	
//쿠기값을 설정하기 위한 메서드
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
 * 인증 크리덴셜의 MD5 해시를 생성하는 데 사용되는 메서드 호출 
 * 요청으로부터 IP주소를 받아오고 프레임워크에서 제공하는 유틸리티 클래스를 사용해 반환된 쿠키를 인코딩하도록
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
 * 쿠키의 내용을 검증하는데 사용할 메서드
 * 부모 메서드를 호출하기 전에 먼저 IP주소를 확인해 부모 메서드 호출 여부 결정하도록 내용 수정
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
 * 인증 크리덴셜의 MD5 해시를 생성하는 데 사용되는 메서드
 * 요청으로부터 IP주소를 받아오고 스프링프레임워크에서 제공하는 유틸리티 클래스를 사용
 * 반환된 쿠키를 인코딩하도록 메서드 구현
 */
@Override
protected String makeTokenSignature(long tokenExpiryTime, String username, String password) {
	return DigestUtils.md5DigestAsHex(
			(username + ":" + tokenExpiryTime + ":" + password + ":" + getKey() + ":" + getUserIPAddress(getContext()))
					.getBytes());
}

}
