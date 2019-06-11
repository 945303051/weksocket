package org.tdds.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tdds.service.LogRecordService;

@Controller
@RequestMapping("${adminPath}/index")
public class IndexAdminController {

	private static final String[] STATUS = {"RUNNING", "POWEROFF", "ALARM", "WAITING","MANUAL"};
	
	@Autowired
	private LogRecordService bizLogRecord;
	
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	@ResponseBody
	public Object data(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> entity = new HashMap<>();
		for(String status:STATUS){
			Double num= bizLogRecord.findData(null,status,null);
			entity.put(status, num);
		}
		return entity;
	}
}
