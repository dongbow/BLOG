package cn.ifxcode.test;

import java.util.List;

import javax.annotation.Resource;  

import org.apache.log4j.Logger;  
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ifxcode.model.About;
import cn.ifxcode.model.Navigation;
import cn.ifxcode.service.AboutService;
import cn.ifxcode.service.NavigationService;

import com.alibaba.fastjson.JSON;  
  
public class TestEhcache {  
    private static Logger logger = Logger.getLogger(TestEhcache.class);  
    private ApplicationContext ac = null;  
//    @Resource  
//    private AboutService aboutService = null; 
    @Resource
    private NavigationService navigationService = null;
  
	@Before
	public void before() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//aboutService = (AboutService) ac.getBean("aboutService");
		navigationService = (NavigationService) ac.getBean("navigationService");
	}
  
//    @Test  
//    public void test0() {  
//        About about = aboutService.findAbout();
//        System.out.println("1");
//        logger.info(JSON.toJSONString(about));  
//    }  
//    
//    @Test  
//    public void test1() {  
//        About about = aboutService.findAbout();
//        System.out.println("2");
//        logger.info(JSON.toJSONString(about));  
//    }  
    
    @Test  
    public void test2() throws Exception {  
        List<Navigation> navigation = navigationService.findAll();
        System.out.println("1");
        logger.info(JSON.toJSONString(navigation));  
    }  
    
    @Test  
    public void test3() throws Exception {  
    	 List<Navigation> navigation = navigationService.findAll();
         System.out.println("2");
         logger.info(JSON.toJSONString(navigation));   
    } 
}