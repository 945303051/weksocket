package org.tdds.service;



import org.tdds.entity.RunningRecord;

import net.chenke.playweb.QueryFilters;
import net.chenke.playweb.support.mybatis.Page;
import net.chenke.playweb.support.mybatis.PageRequest;

public interface RunningRecordService {

	int insert(RunningRecord rr);

	Page<RunningRecord> findAllRecords(QueryFilters filters, PageRequest pageable);
}
