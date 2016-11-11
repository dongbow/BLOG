package cn.ifxcode.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DateJson {

	@ResponseBody
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getDate() {
		Date date = new Date();
		return date;
	}

	@Test
	public void test(){
		String a = this.getDate().toString();
		System.out.println(a);
	}
}
