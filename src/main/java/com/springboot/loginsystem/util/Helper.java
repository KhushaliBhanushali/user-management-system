package com.springboot.loginsystem.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;
import static com.springboot.loginsystem.constant.JWTUtil.ROLE;

public class Helper {

	public static boolean checkUserRole() {
		String role = "";
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attributes.getRequest().getSession();
		
		if(session.getAttribute("urole") != null) {
			role = session.getAttribute("urole").toString();
		}
		return role.equalsIgnoreCase(ROLE);
	}
}
