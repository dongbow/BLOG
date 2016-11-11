package cn.ifxcode.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.ifxcode.model.Resource;
import cn.ifxcode.service.ResourceService;

@Controller
public class ResourceController {

	private Logger logger = Logger.getLogger(ResourceController.class);
	
	@javax.annotation.Resource
	private ResourceService resourceService;
	
	@RequestMapping("/system/admin/resourcelist")
	private ModelAndView resourcePage() throws Exception{
		return new ModelAndView("system/manager/resource/resourcelist");
	}
	
	@RequestMapping(value="/system/admin/resource/treeGrid",method =RequestMethod.POST)
	@ResponseBody
	public List<Resource> treeGrid() throws Exception{
		List<Resource> resources = resourceService.findAllResource();
		return resources;
	}
	
}
