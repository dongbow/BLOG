package cn.ifxcode.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import cn.ifxcode.model.User;

public class ValidataUtil {

	private static final String usernameRegex = "^[0-9a-zA-Z]{3,20}$";
	private static final String nicknameRegex = "[\u4e00-\u9fa5_\\a-zA-Z0-9_]{3,20}";
	private static final String emailRegex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	
	public static boolean isNotBlank(String str){
		return StringUtils.isNotBlank(str);
	}
	
	public static boolean matchLength(String str,int min,int max){
		if(str.length() >= min && str.length() <= max){
			return true;
		}
		return false;
	}
	
	public static boolean verifycode(String str){
		if(isNotBlank(str) && matchLength(str, 4, 4)){
			return true;
		}
		return false;
	}

	public static boolean regex(String regex, String value){
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
		return  m.matches();
	}
	
	public static boolean right(User user) {
		if(isNotBlank(user.getUsername())
				&& isNotBlank(user.getNickname())
				&& isNotBlank(user.getPassword())
				&& isNotBlank(user.getEmail())){
			if(regex(usernameRegex, user.getUsername())
				&& regex(nicknameRegex, user.getNickname())
				&& regex(emailRegex, user.getEmail())){
				return true;
			}
		}
		return false;
	}
	
	@Test
	public void testRegex() {
		User user = new User();
		user.setUsername("admin");
		user.setNickname("' AND '8281'='8281");
		user.setPassword("123456");
		user.setEmail("123456@qq.com");
		System.out.println(right(user));
	}
}
