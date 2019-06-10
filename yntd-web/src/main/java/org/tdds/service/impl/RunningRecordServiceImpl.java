package org.tdds.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdds.entity.RunningRecord;
import org.tdds.mapper.RunningRecordMapper;
import org.tdds.service.RunningRecordService;

@Service
public class RunningRecordServiceImpl implements RunningRecordService {

	@Autowired
	private RunningRecordMapper runningRecordDao;

	@Override
	public int insert(RunningRecord rr) {
		return runningRecordDao.insert(rr);
	}

	@Override
	public Double selectRankData(Long machineId) {
		return runningRecordDao.selectRankData(machineId);
	}

	@Override
	public Double findLineData(String date) {
	 
		return runningRecordDao.findMemberLineData(date);
	}
}
