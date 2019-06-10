package org.tdds.service;

import java.util.List;
import java.util.Map;

import org.tdds.entity.WaitingRecord;


public interface WaitingRecordService {

	void insert(WaitingRecord wRecord);

	Double selectWaitingTime(Long id);

	List<Map<String, Object>> findAllRecordById(Long id);

	Double findPieData(Long id);

	Double findLineData(String str,Long id);
	
	Double findGaugeData(Long id);

	Double findLineData(String str);
}
