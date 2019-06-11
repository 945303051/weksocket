package org.tdds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdds.entity.ManualRecord;
import org.tdds.mapper.ManualRecordMapper;
import org.tdds.service.ManualRecordService;

@Service
public class ManualRecordServiceImpl implements ManualRecordService {
	
	@Autowired
	private ManualRecordMapper daoManualRecord;

	@Override
	public void insert(ManualRecord mr) { 
		daoManualRecord.insert(mr);
	}

	@Override
	public Double selectManualTimes(Long id) {
		return null;
	}
	
}
