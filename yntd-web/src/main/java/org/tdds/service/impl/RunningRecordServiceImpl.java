package org.tdds.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdds.entity.RunningRecord;
import org.tdds.mapper.RunningRecordMapper;
import org.tdds.service.RunningRecordService;

import cn.hxz.webapp.syscore.support.BaseWorkbenchController;
import net.chenke.playweb.QueryFilters;
import net.chenke.playweb.support.mybatis.Page;
import net.chenke.playweb.support.mybatis.PageImpl;
import net.chenke.playweb.support.mybatis.PageRequest;
import tk.mybatis.mapper.entity.Example;

@Service
public class RunningRecordServiceImpl implements RunningRecordService {
	private static final String ORDER_BY="record_time desc";
	

	@Autowired
	private RunningRecordMapper runningRecordDao;

	@Override
	public int insert(RunningRecord rr) {
		return runningRecordDao.insert(rr);
	}

	@Override
	public Page<RunningRecord> findAllRecords(QueryFilters filters, PageRequest pageable) {
		Example example = new Example(RunningRecord.class);
		example.setOrderByClause(ORDER_BY);
		List<RunningRecord> entities=runningRecordDao.selectByExampleAndRowBounds(example, pageable);
		return new PageImpl<RunningRecord>(entities, pageable);
	}
}
