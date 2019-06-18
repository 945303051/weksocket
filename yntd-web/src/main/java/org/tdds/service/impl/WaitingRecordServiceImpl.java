package org.tdds.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdds.entity.WaitingRecord;
import org.tdds.mapper.WaitingRecordMapper;
import org.tdds.service.WaitingRecordService;

import net.chenke.playweb.QueryFilters;
import net.chenke.playweb.support.mybatis.Page;
import net.chenke.playweb.support.mybatis.PageImpl;
import net.chenke.playweb.support.mybatis.PageRequest;
import tk.mybatis.mapper.entity.Example;

@Service
public class WaitingRecordServiceImpl implements WaitingRecordService{

	private  static final String ORDER_BY="id desc";
	
	@Autowired
	private WaitingRecordMapper daoWaitingRecord;
	
	@Override
	public void insert(WaitingRecord wRecord) {
		daoWaitingRecord.insert(wRecord);
		
	}

	@Override
	public Page<WaitingRecord> findAllRecords(QueryFilters filters, PageRequest pageable) {
		Example example=new Example(WaitingRecord.class);
		example.setOrderByClause(ORDER_BY);
		List<WaitingRecord> entities = daoWaitingRecord.selectByExampleAndRowBounds(example, pageable);
		return new PageImpl<WaitingRecord>(entities, pageable);
	}

	@Override
	public List<Map<String, Object>> exportData(QueryFilters filters) {
		// TODO Auto-generated method stub
		return null;
	}
}
