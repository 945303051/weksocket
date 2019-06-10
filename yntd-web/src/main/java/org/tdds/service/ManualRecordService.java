package org.tdds.service;

import org.tdds.entity.ManualRecord;

public interface ManualRecordService {

	void insert(ManualRecord mr);

	Double selectManualTimes(Long id);

}
