package org.tdds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdds.entity.PowerOffRecord;
import org.tdds.mapper.PowerOffRecordMapper;
import org.tdds.service.PowerOffRecordService;

@Service
public class PowerOffRecordServiceImpl implements PowerOffRecordService{
	
	@Autowired
	private PowerOffRecordMapper daoPoweroff;
	
	@Override
	public void insert(PowerOffRecord pr) {
		daoPoweroff.insert(pr);
	}
}
