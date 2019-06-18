package org.tdds.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdds.entity.WarningRecord;
import org.tdds.mapper.WarningRecordMapper;
import org.tdds.service.WarningRecordService;

import net.chenke.playweb.QueryFilters;
import net.chenke.playweb.support.mybatis.Page;
import net.chenke.playweb.support.mybatis.PageImpl;
import net.chenke.playweb.support.mybatis.PageRequest;
import tk.mybatis.mapper.entity.Example;

@Service
public class WarningRecordServiceImpl implements WarningRecordService{
	
	private  static final String ORDER_BY="id desc";

	@Autowired
	private WarningRecordMapper daoWarningRecord;
	
	@Override
	public void insert(WarningRecord warningRecord) {
		daoWarningRecord.insert(warningRecord);
	}

	@Override
	public Page<WarningRecord> findAllRecords(QueryFilters filters, PageRequest pageable) {
		Example example=new Example(WarningRecord.class);
		example.setOrderByClause(ORDER_BY);
		List<WarningRecord> entities = daoWarningRecord.selectByExampleAndRowBounds(example, pageable);
		return new PageImpl<WarningRecord>(entities, pageable);
	}

	@Override
	public List<Map<String, Object>> exportData(QueryFilters filters) {
		// TODO Auto-generated method stub
		return null;
	}
}
