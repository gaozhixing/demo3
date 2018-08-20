package com.example.demo1.util;

public class StringUtil {
	
	public static Boolean isBlank(String str) {
		if (str == "" || str == null) {
			return true;
		}
		return false;
	}

}
