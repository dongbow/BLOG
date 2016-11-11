package cn.ifxcode.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Dd;
import cn.ifxcode.service.DdService;
import cn.ifxcode.service.DdlService;

@Controller
public class DDLController {

	private Logger logger = Logger.getLogger(DDLController.class);
	
	@Resource
	private DdService ddService;
	@Resource
	private DdlService ddlService;
	
	@RequestMapping("system/admin/ddl")
	public ModelAndView ddlPage() throws Exception{
		return new ModelAndView("system/manager/ddl/ddl");
	}
	
	@RequestMapping("system/admin/ddl/dataGrid")
	@ResponseBody
	public PageInfo dataGrid(Dd dd,Integer page, Integer rows, String sort, String order) throws Exception{
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();
		
		if(StringUtils.isNotBlank(dd.getName())){
			condition.put("name", dd.getName());
		}
		
		pageInfo.setCondition(condition);
		ddlService.findAllDDAndDDL(pageInfo);
		return pageInfo;
	}
	
	@RequestMapping("/system/admin/ddl/ddlist")
	@ResponseBody
	public List<Dd> ddlistJson() throws Exception{
		List<Dd> dd = ddService.findAllIdAndName();
		logger.info("查询数据字典库完成");
		return dd;
	}
}
