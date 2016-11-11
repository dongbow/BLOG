package cn.ifxcode.utils;

import org.junit.Test;

public class HtmlToStringUtil {
	
	public static String NoHTML(String htmlString){
		
		//剔出<html>的标签  
		String content = htmlString.replaceAll("</?[^>]+>", ""); 
		//去除字符串中的空格,回车,换行符,制表符
		content = content.replaceAll("<a>\\s*|\t|\r|\n</a>", "");
        
		int length = content.length() > 255 ? 255 : content.length();
        return content.substring(0, length-3)+"...";
    }
	
	public static String forIndex(String html){
		//剔出<html>的标签  
		String content = html.replaceAll("</?[^>]+>", ""); 
		//去除字符串中的空格,回车,换行符,制表符
		content = content.replaceAll("<a>\\s*|\t|\r|\n</a>", "");
		return content;
	}
	
	@Test
	public void test(){
		String test = "<p><strong>@Resource</strong> 注解被用来激活一个命名资源（named resource）的依赖注入，"
				+ "在JavaEE应用程序中，该注解被典型地转换为绑定于JNDI context中的一个对象。 Spring确实支持使用<strong>"
				+ "@Resource</strong>通过JNDI lookup来解析对象，默认地，拥有与<strong>@Resource</strong>注解所提供名字相匹配"
				+ "的&ldquo;bean name（bean名字）&rdquo;的Spring管理对象会被注入。 在下面的例子中，Spring会向加了"
				+ "注解的setter方法传递bean名为&ldquo;<strong>dataSource</strong>&rdquo;的Spring管理对象的引用。</p>"
				+ "<pre class='brush:java;toolbar:false;'>";
		System.out.println(NoHTML(test));
	}
	
}
