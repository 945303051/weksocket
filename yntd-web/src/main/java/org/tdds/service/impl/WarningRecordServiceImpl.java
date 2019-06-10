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
}
