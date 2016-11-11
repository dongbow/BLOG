package cn.ifxcode.utils;

import org.apache.commons.lang.StringUtils;

public class UptoLow {
	
	public static String exChange(String str){  
	    StringBuffer sbf = new StringBuffer();  
	    if(StringUtils.isNotBlank(str)){  
	        for(int i=0;i<str.length();i++){  
	            char c = str.charAt(i);  
	            if(Character.isUpperCase(c)){  
	                c=Character.toLowerCase(c);  
	            }
	            sbf.append(c);
	        }  
	    }   
	    return sbf.toString();  
	}  
}
