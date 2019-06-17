package org.tdds.service.impl;


import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdds.entity.RunningRecord;
import org.tdds.mapper.RunningRecordMapper;
import org.tdds.service.RunningRecordService;

import net.chenke.playweb.QueryFilters;
import net.chenke.playweb.support.mybatis.Page;
import net.chenke.playweb.support.mybatis.PageImpl;
import net.chenke.playweb.support.mybatis.PageRequest;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class RunningRecordServiceImpl implements RunningRecordService {
	
	private  static final String ORDER_BY="id desc";

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
		Criteria criteria = example.createCriteria();
		String recordTime = Objects.toString(filters.get("recordTime"), null);
		if(StringUtils.isNotBlank(recordTime)&& recordTime.indexOf("&")>-1){
			String startTime=recordTime.split("&")[0];
			String endTime=recordTime.split("&")[1];
			criteria.andBetween("recordTime",startTime, endTime);
		}
		List<RunningRecord> entities=runningRecordDao.selectByExampleAndRowBounds(example, pageable);
		return new PageImpl<RunningRecord>(entities, pageable);
	}
}
