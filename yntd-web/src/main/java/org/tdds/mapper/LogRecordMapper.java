package org.tdds.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface LogRecordMapper {

	List<Map<String, Object>> selectDate();
	
	Map<String, Object> selectData(@Param(value="date")String date, @Param(value="status")String status);
	
	Map<String, Object> selectPieData(@Param(value="status")String status, @Param(value="name")String name, @Param(value="date")String date);

	Map<String, Object> findTimeLineSeriesData(@Param(value="status")String status,@Param(value="name")String name, @Param(value="time")String time);

	List<Map<String, Object>> findAll();
}
