package org.tdds.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdds.entity.MonitoringList;
import org.tdds.entity.PowerOffRecord;
import org.tdds.entity.RunningRecord;
import org.tdds.entity.WaitingRecord;
import org.tdds.entity.WarningRecord;
import org.tdds.mapper.LogRecordMapper;
import org.tdds.service.LogRecordService;
import org.tdds.service.MachineService;
import org.tdds.service.PowerOffRecordService;
import org.tdds.service.RunningRecordService;
import org.tdds.service.WaitingRecordService;
import org.tdds.service.WarningRecordService;

import net.chenke.playweb.QueryFilters;

@Service
public class LogRecordServiceImpl implements LogRecordService {

	private static final String[] STATUS = {"RUNNING", "POWEROFF", "ALARM", "WAITTING","MANUAL"};
	
	@Autowired
	private LogRecordMapper daoLogRecord;
	
	@Autowired
	private WaitingRecordService bizWaiting;
	
	@Autowired
	private PowerOffRecordService bizPowerOff;
	
	@Autowired
	private WarningRecordService bizWarning;
	
	@Autowired
	private RunningRecordService bizRunning;
	
	
	@Autowired
	private MachineService bizMachine;
 
	public int insert(MonitoringList ml) {
		Long mid = getMachineIdByName(ml.getMachineName());
		if(StringUtils.isNotBlank(ml.getMachineSignal()) && ml.getMachineSignal().equalsIgnoreCase(STATUS[0])){
			RunningRecord rr = new RunningRecord();
			rr.setMachineId(mid);
			bizRunning.insert(rr);
		}else if(StringUtils.isNotBlank(ml.getMachineSignal()) && ml.getMachineSignal().equalsIgnoreCase(STATUS[1])){
			PowerOffRecord pr= new PowerOffRecord();
			pr.setMachineId(mid);
			bizPowerOff.insert(pr);
		}else if(StringUtils.isNotBlank(ml.getMachineSignal()) && ml.getMachineSignal().equalsIgnoreCase(STATUS[2])) {
			WaitingRecord wRecord = new WaitingRecord();
			wRecord.setMachineId(mid);
			bizWaiting.insert(wRecord);
		}else if(StringUtils.isNotBlank(ml.getMachineSignal()) && ml.getMachineSignal().equalsIgnoreCase(STATUS[3])){
			WarningRecord warningRecord = new WarningRecord();
			warningRecord.setMachineId(mid);
			bizWarning.insert(warningRecord);
		}else if(StringUtils.isNotBlank(ml.getMachineSignal()) && ml.getMachineSignal().equalsIgnoreCase(STATUS[4])){
			RunningRecord rr = new RunningRecord();
			rr.setMachineId(mid);
			bizRunning.insert(rr);
		}
			return 0;
	}
	
	private Long getMachineIdByName(String machineName){
		return bizMachine.selectMidByName(machineName);
	}

	@Override
	public List<Map<String, Object>> selectXaxis() {
		return daoLogRecord.selectDate();
	}
	
	@Override
	public Double selectData(Long id,String status) {
		Double num =null;
		 if(status.equalsIgnoreCase(STATUS[1])){
			 num= bizPowerOff.selectPowerOffTime(id);
		 }else if(status.equalsIgnoreCase(STATUS[2])){
			 num= bizWarning.selectWarningTime(id);
		 }else if(status.equalsIgnoreCase(STATUS[3])){
			 num= bizWaiting.selectWaitingTime(id);
		 }
		 	return num;
	}

	@Override
	public Map<String, Object> findSeriesData(String status, String name, String date) {
		 
		return daoLogRecord.selectPieData(status, name, date);
	}

	@Override
	public Double selectRankData(Long machineId) {
		 
		return bizRunning.selectRankData(machineId);
	}

	@Override
	public Map<String, Object> findTimeLineSeriesData(String status, String name, String time) {
		 
		return daoLogRecord.findTimeLineSeriesData(status, name, time);
	}

	@Override
	public List<Map<String, Object>> findAll() {
		return daoLogRecord.findAll();
	}

	@Override
	public List<Map<String, Object>> findAllRecords(QueryFilters filters,Long id) {
		String type = Objects.toString(filters.get("type"), null);
		List<Map<String, Object>> entities = new ArrayList<>();
		if(type.equalsIgnoreCase(STATUS[1])){
			entities=bizPowerOff.findAllRecordById(id);
		}else if(type.equalsIgnoreCase(STATUS[3])){
			entities=bizWaiting.findAllRecordById(id);
		}else if(type.equalsIgnoreCase(STATUS[2])){
			entities=bizWarning.findAllRecordById(id);
		}
		return entities;
	}

	@Override
	public Double findPieData(String status, Long id) {
		Double count=0.0;
		if(status.equalsIgnoreCase(STATUS[1])){
			count=bizPowerOff.findPieData(id);
		}else if(status.equalsIgnoreCase(STATUS[2])){
			count=bizWarning.findPieData(id);
		}else if(status.equalsIgnoreCase(STATUS[3])){
			count=bizWaiting.findPieData(id);
		}else{
			count=0.0;
		}
		return count;
	}

	@Override
	public Double findLineData(String str,String status,Long id) {
		Double count=0.0;
		if(status.equalsIgnoreCase(STATUS[1])){
			count=bizPowerOff.findLineData(str,id);
		}else if(status.equalsIgnoreCase(STATUS[2])){
			count=bizWarning.findLineData(str,id);
		}else if(status.equalsIgnoreCase(STATUS[3])){
			count=bizWaiting.findLineData(str,id);
		}else{
			count=0.0;
		}
		return count;
	}

	@Override
	public Double findGaugeData(String status, Long id) {
		Double count=0.0;
		if(status.equalsIgnoreCase(STATUS[1])){
			count=bizPowerOff.findGaugeData(id);
		}else if(status.equalsIgnoreCase(STATUS[2])){
			count=bizWarning.findGaugeData(id);
		}else if(status.equalsIgnoreCase(STATUS[3])){
			count=bizWaiting.findGaugeData(id);
		}else{
			count=0.0;
		}
		return count;
	}

	@Override
	public Double findLineData(String str, String status) {
		Double count=0.0;
		if(status.equalsIgnoreCase(STATUS[0])||status.equalsIgnoreCase(STATUS[4])){
			count=bizRunning.findLineData(str);
		}
		else if(status.equalsIgnoreCase(STATUS[1])){
			count=bizPowerOff.findLineData(str);
		}else if(status.equalsIgnoreCase(STATUS[2])){
			count=bizWarning.findLineData(str);
		}else if(status.equalsIgnoreCase(STATUS[3])){
			count=bizWaiting.findLineData(str);
		}else{
			count=0.0;
		}
		return count;
	}
}


