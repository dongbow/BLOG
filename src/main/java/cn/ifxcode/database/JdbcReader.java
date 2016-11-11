package cn.ifxcode.database;

import java.util.ResourceBundle;

public class JdbcReader {

	public static ResourceBundle rb(){
		ResourceBundle rb=ResourceBundle.getBundle("jdbc");
		return rb;
	}
	
	public static String getDriver(){
		return rb().getString("driver");
	}
	
	public static String getUrl(){
		return rb().getString("url");
	}
	
	public static String getName(){
		return rb().getString("username");
	}
	
	public static String getPassword(){
		return rb().getString("password");
	}
	
}
