package cn.ifxcode.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.Test;

public class RoleJudge {

	private static List<Integer> roles = new ArrayList<Integer>();
	
	public static ResourceBundle rb(){
		ResourceBundle rb=ResourceBundle.getBundle("role");
		return rb;
	}
	
	public static String rids(){
		String rids=rb().getString("role.rid");
		return rids;
	}
	
	public static List<Integer> stringSplit(){
		String rids = rids();
		for (int i = 0; i < rids.split(",").length; i++) {
			roles.add(Integer.parseInt(rids.split(",")[i]));
		}
		return roles;
	}
	
	public static boolean roleJudge(int rid){
		List<Integer> ids = stringSplit();
		for (int i = 0; i < ids.size(); i++) {
			if(rid == ids.get(i)){
				return true;
			}	
		}
		return false;
	}
	
	@Test
	public void test(){
		List<Integer> ids = stringSplit();
		for (int i = 0; i < ids.size(); i++) {
			System.out.println(ids.get(i));
		}
	}
}
