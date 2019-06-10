package org.tdds.service;

import java.util.List;
import java.util.Map;

import org.tdds.entity.WarningRecord;

import net.chenke.playweb.support.dynamicDataSource.DataSource;

public interface WarningRecordService {

	void insert(WarningRecord warningRecord);

	Double selectWarningTime(Long id);

	List<Map<String, Object>> findAllRecordById(Long id);

	Double findPieData(Long id);

	Double findLineData(String str,Long id);

	Double findGaugeData(Long id);

	Double findLineData(String str);

}
