package org.tdds.service;


import java.util.List;
import java.util.Map;

import org.tdds.entity.MonitoringList;


public interface LogRecordService {

	int insert(MonitoringList lr);
	 
	/**
	 * 查询时间轴数据
	 * @param id
	 * @param status
	 * @return
	 */
	Double findData(String date,String status,Long id);
	
	/**
	 * 排名
	 * @param id
	 * @return
	 */
	Double findRankData(Long id);

	List<Map<String, Object>> exportPowerOffData(Long machineId, String startTime, String endTime);

	List<Map<String, Object>> exportAlarmData(Long machineId, String startTime, String endTime);

	List<Map<String, Object>> exportWaittingData(Long machineId, String startTime, String endTime);
}
