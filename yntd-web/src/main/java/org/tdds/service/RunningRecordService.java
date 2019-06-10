package org.tdds.service;


import org.tdds.entity.RunningRecord;

public interface RunningRecordService {

	int insert(RunningRecord rr);

	Double selectRankData(Long machineId);

	Double findLineData(String str);

}
