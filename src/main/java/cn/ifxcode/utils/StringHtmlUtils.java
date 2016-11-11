package cn.ifxcode.utils;

import org.junit.Test;
import org.springframework.web.util.HtmlUtils;

/**
 * 对用户输入的字符进行转义
 * @author Administrator
 *
 */

public class StringHtmlUtils {

	public static String stringHtmlEscape(String content){
		return HtmlUtils.htmlEscape(content);
	}
	
	public static String stringHtmlUnescape(String content){
		return HtmlUtils.htmlUnescape(content);
	}
	
	@Test
	public void Test(){
		String content = "<script type='text/javascript'>"
				+"$(document).ready("
				+ "alert(1););</script>";
		System.out.println(stringHtmlEscape(content));
		System.out.println(stringHtmlUnescape(content));
	}
	
}
