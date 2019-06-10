package org.tdds.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdds.entity.WaitingRecord;
import org.tdds.mapper.WaitingRecordMapper;
import org.tdds.service.WaitingRecordService;

@Service
public class WaitingRecordServiceImpl implements WaitingRecordService{

	@Autowired
	private WaitingRecordMapper daoWaitingRecord;
	
	@Override
	public void insert(WaitingRecord wRecord) {
		daoWaitingRecord.insert(wRecord);
		
	}

	@Override
	public Double selectWaitingTime(Long id) {
		return daoWaitingRecord.selectWaitingTime(id);
	}


	@Override
	public List<Map<String, Object>> findAllRecordById(Long id) {
		return daoWaitingRecord.findAllRecordById(id);
	}

	@Override
	public Double findPieData(Long id) {
		 
		return daoWaitingRecord.findPieData(id);
	}

	@Override
	public Double findLineData(String str,Long id) {
		 
		return daoWaitingRecord.findLineData(str,id);
	}

	@Override
	public Double findGaugeData(Long id) {
		 
		return daoWaitingRecord.findGaugeData(id);
	}

	@Override
	public Double findLineData(String str) {
		 
		return daoWaitingRecord.findMemberLineData(str);
	}

}
