package org.tdds.controller.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tdds.entity.Machine;
import org.tdds.entity.MonitoringList;
import org.tdds.service.LogRecordService;
import org.tdds.service.MachineService;
import org.tdds.service.MonitoringService;

import com.alibaba.druid.sql.dialect.mysql.ast.MysqlForeignKey.On;

import cn.hxz.webapp.syscore.support.BaseWorkbenchController;
import cn.hxz.webapp.util.ExcelExportUtils;
import cn.hxz.webapp.util.echarts.StatusEnum;
import net.chenke.playweb.QueryFilters;
import net.chenke.playweb.util.FiltersUtils;
import net.chenke.playweb.util.HashUtils;

@Controller
@RequestMapping("${adminPath}/logging")
public class LoggingAdminController extends BaseWorkbenchController{
	@Autowired
	private LogRecordService bizLogRecord;
	
	@Autowired
	private MonitoringService bizMonitoring;
	
	@Autowired
	private MachineService bizMachine;
	
	private static final String[] STATUS = {"POWEROFF","ALARM","WAITTING"};
	
	private static final String uuid = HashUtils.MD5(LoggingAdminController.class.getName());
	
	@RequestMapping(value = "/charts", method = RequestMethod.GET)
	public String list(@RequestParam(value="id",required=false)Long id,HttpServletRequest request,HttpServletResponse response){
		QueryFilters filters = FiltersUtils.getQueryFilters(request, response, uuid);
		Map<String, Object > map = new HashMap<>();
		map.put("filters", filters);
		return this.view("/tdds/logging/charts");
	}
	
	@RequestMapping(value = "/pie", method = RequestMethod.GET)
	@ResponseBody
	public Object pie(@RequestParam(value="id",required=false)Long id,HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> entities = new LinkedList<>();
		for(String status:STATUS){
			Map<String, Object> entity = new HashMap<>();
			Double num= bizLogRecord.findData(null,status,id);
			entity.put("value", num);
			entity.put("name",status);
			entities.add(entity);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("resault", entities);
		return map;
	}
	
	@RequestMapping(value = "/gauge", method = RequestMethod.GET)
	@ResponseBody
	public Object gauge(@RequestParam(value="id",required=false)Long id,HttpServletRequest request,HttpServletResponse response){
		 List<Map<String, Object>> entities = new ArrayList<>();
		for(String status:STATUS){
			Map<String, Object> entity = new HashMap<>();
			entity.put("data", getGaugeData(status,id));
			entities.add(entity);
		}
		return entities;
	}
	
	@RequestMapping(value = "/monitor", method = RequestMethod.GET)
	@ResponseBody
	public Object monitor(@RequestParam(value="name",required=false)String name,HttpServletRequest request,HttpServletResponse response){
		MonitoringList montior = bizMonitoring.findByName(name);
		return montior;
	}
	
	@ResponseBody
	private Object getGaugeData(String status,Long id){
		List<Map<String, Object>> list = new LinkedList<>();
		Double num=bizLogRecord.findData(null,status,id);
		Map<String, Object> map = new HashMap<>();
		if(num==null){
			map.put("value", 0);
		}else{
			map.put("value", num);
		}
		map.put("name", status);
		list.add(map);
		return list;
	}
	
	@RequestMapping(value = "/line", method = RequestMethod.GET)
	@ResponseBody
	public Object line(@RequestParam(value="id",required=false)Long id,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time= sdf.format(new Date());
		List<String> days = getMonthDate(time);
		List<Map<String, Object>> maps = new ArrayList<>();
		for(String status:STATUS){
			Map<String, Object> entity = new HashMap<>();
			List<Object> value=new LinkedList<>();
			for(String str:days){
				Double num=bizLogRecord.findData(str,status,id);
				value.add(num);
			}
				entity.put("data", value);
				entity.put("name", status);
				entity.put("type", "line");
				maps.add(entity);
		}
		map.put("xAxis",days);
		map.put("series",maps);
		return map;
	}
	
	private List<String> getMonthDate(String time){
		List<String> days = new LinkedList<>();
		String strs[] = time.split("-");
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(strs[0]);
        int month = Integer.parseInt(strs[1]) - 1;
        calendar.set(year, month, 1);
        int maxDay = calendar.getMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int j=1; j<=maxDay; j++) {
            calendar.set(year, month, j);
            time = sdf.format(calendar.getTime());
            days.add(time);
        }
		return days;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/exportdata", method = RequestMethod.GET)
	public void exportData(Model model,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "machineId") Long machineId,
			@RequestParam(value = "node") String node,
			@RequestParam(value = "startTime") String startTime,
			@RequestParam(value = "endTime") String endTime,
			@RequestParam(value = "status") String status) throws UnsupportedEncodingException {
		String filename =null;
		List<Map<String,Object>> entities  = new ArrayList<>();
		if(status!=null){
			String statusZh=StatusEnum.getValue(status);
			filename="机台"+statusZh+"信息";
			if(status.equalsIgnoreCase(STATUS[0])) {
				entities=bizLogRecord.exportPowerOffData(machineId,startTime,endTime);
			}else if(status.equalsIgnoreCase(STATUS[1])) {
				entities=bizLogRecord.exportAlarmData(machineId,startTime,endTime);
			}else if(status.equalsIgnoreCase(STATUS[2])) {
				entities =bizLogRecord.exportWaittingData(machineId,startTime,endTime);
			}
		} 
		
		/*List<Map<String,Object>> entities = bizLogRecord.exportData(map);*/
		
		byte[] bytes = ExcelExportUtils.createExcel(node, null, entities);
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(filename+ ".xlsx","utf-8"));
		try {
			OutputStream out = response.getOutputStream();
			out.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

 
}
