package org.tdds.service.impl;

import java.util.List;
import java.util.Map;

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

	@Override
	public Double selectPowerOffTime(Long id) {
		return daoPoweroff.selectPowerOffTime(id);
	}

	@Override
	public List<Map<String, Object>> findAllRecordById(Long id) {
		return daoPoweroff.findAllRecordById(id);
	}

	@Override
	public Double findPieData(Long id) {
		 
		return daoPoweroff.findPieData(id);
	}

	@Override
	public Double findLineData(String str,Long id) {
		// TODO Auto-generated method stub
		return daoPoweroff.findLineData(str,id);
	}

	@Override
	public Double findGaugeData(Long id) {
		// TODO Auto-generated method stub
		return daoPoweroff.findGaugeData(id);
	}

	@Override
	public Double findLineData(String date) {
		 
		return daoPoweroff.findMemberLineData(date);
	}

}
