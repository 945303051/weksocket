package org.tdds.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdds.entity.PowerOffRecord;
import org.tdds.mapper.PowerOffRecordMapper;
import org.tdds.service.PowerOffRecordService;

import net.chenke.playweb.QueryFilters;
import net.chenke.playweb.support.mybatis.Page;
import net.chenke.playweb.support.mybatis.PageImpl;
import net.chenke.playweb.support.mybatis.PageRequest;
import tk.mybatis.mapper.entity.Example;

@Service
public class PowerOffRecordServiceImpl implements PowerOffRecordService{
	
	private  static final String ORDER_BY="id desc";
	
	@Autowired
	private PowerOffRecordMapper daoPoweroff;
	
	@Override
	public void insert(PowerOffRecord pr) {
		daoPoweroff.insert(pr);
	}

	@Override
	public Page<PowerOffRecord> findAllRecords(QueryFilters filters, PageRequest pageable){
		Example example = new Example(PowerOffRecord.class);
		example.setOrderByClause("ORDER_BY");
		List<PowerOffRecord> entities= daoPoweroff.selectByExampleAndRowBounds(example, pageable);
		return new PageImpl<PowerOffRecord>(entities, pageable);
	}

	@Override
	public List<Map<String, Object>> exportData(QueryFilters filters) {
		// TODO Auto-generated method stub
		return null;
	}
}
