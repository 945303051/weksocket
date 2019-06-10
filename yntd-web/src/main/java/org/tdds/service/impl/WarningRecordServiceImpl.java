package org.tdds.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdds.entity.WarningRecord;
import org.tdds.mapper.WarningRecordMapper;
import org.tdds.service.WarningRecordService;

@Service
public class WarningRecordServiceImpl implements WarningRecordService{

	@Autowired
	private WarningRecordMapper daoWarningRecord;
	
	@Override
	public void insert(WarningRecord warningRecord) {
		daoWarningRecord.insert(warningRecord);
	}

	@Override
	public Double selectWarningTime(Long id) {
		return daoWarningRecord.selectWarningTime(id);
	}
 
	@Override
	public List<Map<String, Object>> findAllRecordById(Long id) {
		return daoWarningRecord.findAllRecordById(id);
	}

	@Override
	public Double findPieData(Long id) {
		// TODO Auto-generated method stub
		return daoWarningRecord.findPieData(id);
	}

	@Override
	public Double findLineData(String str,Long id) {
		 
		return daoWarningRecord.findLineData(str,id);
	}

	@Override
	public Double findGaugeData(Long id) {
		// TODO Auto-generated method stub
		return daoWarningRecord.findGaugeData(id);
	}

	@Override
	public Double findLineData(String date) {
		return daoWarningRecord.findMemberLineData(date);
	}

}
