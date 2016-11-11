package cn.ifxcode.utils;

import java.util.ResourceBundle;

/**
 * 
 * 消息文本
 * @author Administrator
 *
 */
public class MessageTextUtil {

	public static ResourceBundle rb(){
		ResourceBundle rb=ResourceBundle.getBundle("message");
		return rb;
	}
	
	public static String message_book(){
		return rb().getString("message.book");
	}
	
	public static String message_childbook(){
		return rb().getString("message.childbook");
	}
	
	public static String message_blog(){
		return rb().getString("message.blog");
	}
	
	public static String message_childblog(){
		return rb().getString("message.childblog");
	}
	
	public static String message_contact(){
		return rb().getString("message.contact");
	}
	
}
