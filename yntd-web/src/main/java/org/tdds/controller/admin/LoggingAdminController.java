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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tdds.entity.ManualRecord;
import org.tdds.entity.MonitoringList;
import org.tdds.entity.PowerOffRecord;
import org.tdds.entity.RunningRecord;
import org.tdds.entity.WaitingRecord;
import org.tdds.entity.WarningRecord;
import org.tdds.service.LogRecordService;
import org.tdds.service.ManualRecordService;
import org.tdds.service.MonitoringService;
import org.tdds.service.PowerOffRecordService;
import org.tdds.service.RunningRecordService;
import org.tdds.service.WaitingRecordService;
import org.tdds.service.WarningRecordService;

import cn.hxz.webapp.syscore.support.BaseWorkbenchController;
import cn.hxz.webapp.util.ExcelExportUtils;
import cn.hxz.webapp.util.echarts.StatusEnum;
import net.chenke.playweb.QueryFilters;
import net.chenke.playweb.support.mybatis.Page;
import net.chenke.playweb.support.mybatis.PageRequest;
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
	private RunningRecordService bizRunning;
	
	@Autowired
	private PowerOffRecordService bizPowerOff;
	
	@Autowired
	private WarningRecordService bizWarning;
	
	@Autowired
	private WaitingRecordService bizWaiting;
	
	@Autowired
	private ManualRecordService bizManual;
	
	//西部大森 running=manual
	private static final String[] STATUS = {"RUNNING", "POWEROFF", "ALARM", "WAITING"/*,"MANUAL"*/};
	
	private static final String uuid = HashUtils.MD5(LoggingAdminController.class.getName());
	
	
	@RequestMapping(value = "/{type}/list", method = RequestMethod.GET)
	public String list(@PathVariable String type,Model model,HttpServletRequest request,HttpServletResponse response){
		QueryFilters filters = FiltersUtils.getQueryFilters(request, response, uuid+type);
		model.addAttribute("filters",filters);
		return this.view("/tdds/logging/"+type+"/list");
	}
	
	@RequestMapping(value = "/{type}/data", method = RequestMethod.GET)
	@ResponseBody
	public Object data(HttpServletRequest request,HttpServletResponse response,@PathVariable String type){
		QueryFilters filters = FiltersUtils.getQueryFilters(request, response, uuid+type);
		PageRequest pageable = FiltersUtils.getPageable(filters);
		Map<String, Object> map=new HashMap<>();

		if(type.equalsIgnoreCase(STATUS[0].toLowerCase())){
			Page<RunningRecord> runningRecords = bizRunning.findAllRecords(filters,pageable);
			map.put("runningRecords",runningRecords);
		}else if(type.equalsIgnoreCase(STATUS[1].toLowerCase())){
			Page<PowerOffRecord> powerOffRecords = bizPowerOff.findAllRecords(filters,pageable);
			map.put("powerOffRecords",powerOffRecords);
		}else if(type.equalsIgnoreCase(STATUS[2].toLowerCase())){
			Page<WarningRecord> warningRecords = bizWarning.findAllRecords(filters,pageable);
			map.put("warningRecords",warningRecords);
		}else if(type.equalsIgnoreCase(STATUS[3].toLowerCase())){
			Page<WaitingRecord> waitingRecords = bizWaiting.findAllRecords(filters,pageable);
			map.put("waitingRecords",waitingRecords);
		}else{
			Page<ManualRecord> manualRecords = bizManual.findAllRecords(filters,pageable);
			map.put("manualRecords",manualRecords);
		}
			map.put("number",pageable.getPageNumber());
		return map;
	}
	
	@RequestMapping(value = "/{type}/selectTimeDifferenceData", method = RequestMethod.POST)
	public String selectTimeDifferenceData(HttpServletRequest request,
			HttpServletResponse response,@PathVariable String type,
			@RequestParam(value="startTime",required=true)String startTime,
			@RequestParam(value="endTime",required=true)String endTime){
		if(StringUtils.isNotBlank(startTime)&& StringUtils.isNotBlank(endTime)){
			 String recordTime=startTime+"&"+endTime;
			 FiltersUtils.updateFilter(request, response, uuid, "recordTime", recordTime);
		}
		return this.redirect("/logging/"+type+"/list");
	}
	
	@RequestMapping(value = "/pie", method = RequestMethod.GET)
	@ResponseBody
	public Object pie(@RequestParam(value="id",required=false)Long id,HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> entities = new LinkedList<>();
		for(String status:STATUS){
			Map<String, Object> entity = new HashMap<>();
			Double num= bizLogRecord.findData(null,status,id);
			entity.put("value", num);
			entity.put("name",StatusEnum.getValue(status));
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
		MonitoringList monitor = bizMonitoring.findByName(name);
		for(String status: STATUS){
			monitor.setMachineSignal(StatusEnum.getValue(status));
		}
		return monitor;
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
		map.put("name", StatusEnum.getValue(status));
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
				entity.put("name", StatusEnum.getValue(status));
				entity.put("type", "line");
				maps.add(entity);
		}
		map.put("xAxis",days);
		map.put("series",maps);
		return map;
	}
	
	@RequestMapping(value = "/line2", method = RequestMethod.GET)
	@ResponseBody
	public Object line2(@RequestParam(value="id",required=false)Long id,
			@RequestParam(value="para")String para,
			HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time= sdf.format(new Date());
		List<String> days = getMonthDate(time);
		List<Map<String, Object>> maps = new ArrayList<>();
		Map<String, Object> entity = new HashMap<>();
		List<Object> value=new LinkedList<>();
		for(String str:days){
			Double num=bizLogRecord.findData(str,para,id);
			value.add(num);
		}
		entity.put("data", value);
		entity.put("type", "line");
		maps.add(entity);
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
	
	@RequestMapping(value = "/{type}/exportdata", method = RequestMethod.GET)
	public void exportData(HttpServletRequest request,
			HttpServletResponse response,@PathVariable(value = "type") String type) throws UnsupportedEncodingException {
		QueryFilters filters = FiltersUtils.getQueryFilters(request, response, uuid+type);
		String filename =null;
		List<Map<String, Object>> entities = new ArrayList<>();
		if(type.equalsIgnoreCase(STATUS[0].toLowerCase())){
			filename="设备运行日志";
			entities=bizRunning.exportData(filters);
		}else if(type.equalsIgnoreCase(STATUS[1].toLowerCase())){
			filename="设备停机日志";
			entities=bizPowerOff.exportData(filters);
		}else if(type.equalsIgnoreCase(STATUS[2].toLowerCase())){
			filename="设备报警日志";
			entities=bizWarning.exportData(filters);
		}else if(type.equalsIgnoreCase(STATUS[3].toLowerCase())){
			filename="设备等待日志";
			entities=bizWaiting.exportData(filters);
		}else{
			filename="设备手动日志";
			entities=bizManual.exportData(filters);
		}
		byte[] bytes = ExcelExportUtils.createExcel(type+"_", null, entities);
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
