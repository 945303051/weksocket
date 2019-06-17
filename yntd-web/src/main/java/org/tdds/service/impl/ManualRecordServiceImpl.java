package org.tdds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdds.entity.ManualRecord;
import org.tdds.mapper.ManualRecordMapper;
import org.tdds.service.ManualRecordService;

import net.chenke.playweb.QueryFilters;
import net.chenke.playweb.support.mybatis.Page;
import net.chenke.playweb.support.mybatis.PageImpl;
import net.chenke.playweb.support.mybatis.PageRequest;
import tk.mybatis.mapper.entity.Example;

@Service
public class ManualRecordServiceImpl implements ManualRecordService {
	private  static final String ORDER_BY="id desc";
	@Autowired
	private ManualRecordMapper daoManualRecord;

	@Override
	public void insert(ManualRecord mr) { 
		daoManualRecord.insert(mr);
	}

	@Override
	public Page<ManualRecord> findAllRecords(QueryFilters filters, PageRequest pageable) {
		Example example = new Example(ManualRecord.class);
		example.setOrderByClause(ORDER_BY);
		List<ManualRecord> entities = daoManualRecord.selectByExampleAndRowBounds(example, pageable);
		return new PageImpl<ManualRecord>(entities, pageable);
	}
	
}
