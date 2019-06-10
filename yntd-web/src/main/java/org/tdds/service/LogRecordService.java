package org.tdds.service;

import java.util.List;
import java.util.Map;

import org.tdds.entity.MonitoringList;

import net.chenke.playweb.QueryFilters;

public interface LogRecordService {

	int insert(MonitoringList lr);

	List<Map<String, Object>> selectXaxis();

	Double selectData(Long id,String status);
	
	Map<String, Object> findSeriesData(String status, String name, String date);

	Double selectRankData(Long machineId);

	Map<String, Object> findTimeLineSeriesData(String status, String name, String time);

	List<Map<String, Object>> findAll();


	List<Map<String, Object>> findAllRecords(QueryFilters filters,Long id);

	Double findPieData(String status, Long id);

	Double findLineData(String str, String status,Long id);

	Double findGaugeData(String status, Long id);

	Double findLineData(String str, String status);
	
}
