package org.tdds.service.impl;

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
}
