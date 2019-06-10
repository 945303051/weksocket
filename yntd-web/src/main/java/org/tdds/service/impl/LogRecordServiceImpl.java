package org.tdds.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdds.entity.ManualRecord;
import org.tdds.entity.MonitoringList;
import org.tdds.entity.PowerOffRecord;
import org.tdds.entity.RunningRecord;
import org.tdds.entity.WaitingRecord;
import org.tdds.entity.WarningRecord;
import org.tdds.mapper.LogRecordMapper;
import org.tdds.service.LogRecordService;
import org.tdds.service.MachineService;
import org.tdds.service.ManualRecordService;
import org.tdds.service.PowerOffRecordService;
import org.tdds.service.RunningRecordService;
import org.tdds.service.WaitingRecordService;
import org.tdds.service.WarningRecordService;


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
	private ManualRecordService bizManualRecord;
	
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
			ManualRecord mr = new ManualRecord();
			mr.setMachineId(mid);
			bizManualRecord.insert(mr);
		}
			return 0;
	}
	
	private Long getMachineIdByName(String machineName){
		return bizMachine.selectMidByName(machineName);
	}

	@Override
	public Double findRankData(Long machineId) {
		return daoLogRecord.selectRankData(machineId);
	}

	@Override
	public Double findData(String date, String status,Long id) {
		Map<String, Object> map = new HashMap<>();
		if(date!=null){
			map.put("date",date);
		}else if(id!=null){
			map.put("machineId",id);
		}
		Double count=0.0;
		if(status.equalsIgnoreCase(STATUS[0])){
			count=daoLogRecord.findRunningData(map);
		}else if(status.equalsIgnoreCase(STATUS[1])){
			count=daoLogRecord.findPoweroffData(map);
		}else if(status.equalsIgnoreCase(STATUS[2])){
			count=daoLogRecord.findAlarmData(map);
		}else if(status.equalsIgnoreCase(STATUS[3])){
			count=daoLogRecord.findWaittingData(map);
		}else if(status.equalsIgnoreCase(STATUS[4])){
			count=daoLogRecord.findManaulData(map);
		}
		return count;
	}


	@Override
	public List<Map<String, Object>> exportPowerOffData(Long machineId, String startTime, String endTime) {
		return daoLogRecord.exportPowerOffData(machineId,startTime,endTime);
	}

	@Override
	public List<Map<String, Object>> exportAlarmData(Long machineId, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return daoLogRecord.exportAlarmData(machineId,startTime,endTime);
	}

	@Override
	public List<Map<String, Object>> exportWaittingData(Long machineId, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return daoLogRecord.exportWaittingData(machineId,startTime,endTime);
	}

}


