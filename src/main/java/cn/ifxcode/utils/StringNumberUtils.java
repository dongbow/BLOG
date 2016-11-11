package cn.ifxcode.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 
 * 防止包含数字的链接
 * 被恶意改变影响代码正常运行
 * 利用正则提取字符串中全部数字
 * @author Administrator
 *
 */
public class StringNumberUtils {

	public static Integer getDigit(String text) {
		
	    List<String> digitList = new ArrayList<String>();
	    Pattern p = Pattern.compile("(\\d+)");
	    Matcher m = p.matcher(text);
	    while (m.find()) {
	        String find = m.group(1).toString();
	        digitList.add(find);
	    }
	    
	    StringBuffer buffer = new StringBuffer();
		for (String string : digitList) {
			buffer.append(string);
		}
		return Integer.parseInt(buffer.toString());
	}
	
	@Test
	public void test(){
		System.out.println(getDigit("24‘"));
	}
}
