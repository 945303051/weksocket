package org.tdds.service;

import java.util.List;
import java.util.Map;

import org.tdds.entity.PowerOffRecord;


public interface PowerOffRecordService {

	void insert(PowerOffRecord pr);

	Double selectPowerOffTime(Long id);

	List<Map<String, Object>> findAllRecordById(Long id);

	Double findPieData(Long id);

	Double findLineData(String str,Long id);

	Double findGaugeData(Long id);

	Double findLineData(String str);

}
