package cn.ifxcode.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.ifxcode.model.BlogSign;

public class SignSplitUtil {

	public static List<BlogSign> signSplit(String signs,Integer bid){
		
		List<BlogSign> blogSigns = new ArrayList<BlogSign>();
		if(ValidataUtil.isNotBlank(signs)){
			for (int i = 0; i < signs.split(",").length; i++) {
				
				BlogSign blogSign = new BlogSign();
				blogSign.setBsname(signs.split(",")[i]);
				blogSign.setCreatetime(new Date());
				blogSign.setBlog_id(bid);
				
				blogSigns.add(blogSign);
			}
		}
		return blogSigns;
	}
	
	@Test
	public void test(){
		String signs = "spring,springmvc,mybatis,lucene,ajax";
		Integer bid = 1;
		System.out.println(signSplit(signs, bid).get(0).getBlogTopic().getBid());
	}
	
}
