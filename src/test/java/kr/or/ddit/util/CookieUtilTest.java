package kr.or.ddit.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CookieUtilTest {

	@Test
	public void test() {
		/***Given***/
		String cookieString = "rememberMe=Y; userId=brown; test=testValue";

		/***When***/
		String cookieValue = CookieUtil.getCookie(cookieString, "userId");
		String rememberMeValue = CookieUtil.getCookie(cookieString, "rememberMe");
		String testCookieValue = CookieUtil.getCookie(cookieString, "test");
		String notExistsCookieValue = CookieUtil.getCookie(cookieString, "notExists");

		/***Then***/
		assertEquals("brown", cookieValue);
		assertEquals("Y", rememberMeValue);
		assertEquals("testValue", testCookieValue);
		assertNull(notExistsCookieValue);
	}

}
