package cn.ifxcode.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Backup;
import cn.ifxcode.result.Result;
import cn.ifxcode.service.BackupService;
import cn.ifxcode.utils.BackUpUtil;

@Controller
public class BackUpController {
	
	private Logger logger = Logger.getLogger(BackUpController.class);
	
	@Resource
	private BackupService backupService;
	
	@RequestMapping("/system/admin/backuplist")
	public String backuplist() throws Exception{
		return "system/backup/backuplist";
	}
	
	@RequestMapping("/system/admin/backup/dataGrid")
	@ResponseBody
	public PageInfo dataGrid(Backup backup, Integer page, Integer rows, String sort, String order, 
			String starttime,String endtime) throws Exception{
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();
		
		if(StringUtils.isNotBlank(starttime)){
			condition.put("starttime", starttime);
		}
		if(StringUtils.isNotBlank(endtime)){
			condition.put("endtime", endtime);
		}
		
		pageInfo.setCondition(condition);
		backupService.findAll(pageInfo);
		
		return pageInfo;
	}
	
	@RequestMapping(value="/system/admin/backup/newBackup")
	@ResponseBody
	public Result newBackup() throws Exception{
		Result result = new Result();
		Backup backup = new Backup();
		backup.setCreatedate(new Date());
		backup.setBackdir(BackUpUtil.backupDB());
		backup.setBackname(this.getFilename(backup.getBackdir()));
		int row = backupService.insert(backup);
		if( row == 1){
			result.setSuccess(true);
            result.setMsg("插入备份成功");
		} else {
			logger.error("插入备份失败");
            result.setMsg("insert error");
		}
		return result;
	}
	
	public String getFilename(String dir) {
		return dir.split("\\\\")[4];
	}

}
