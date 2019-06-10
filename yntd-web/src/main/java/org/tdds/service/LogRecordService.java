package org.tdds.service;


import org.tdds.entity.MonitoringList;


public interface LogRecordService {

	int insert(MonitoringList lr);
	 
	/**
	 * 查询时间轴数据
	 * @param id
	 * @param status
	 * @return
	 */
	Double findData(String date,String status,Long id);
	
	/**
	 * 排名
	 * @param id
	 * @return
	 */
	Double findRankData(Long id);
}
