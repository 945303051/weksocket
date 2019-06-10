package org.tdds.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;



public interface LogRecordMapper {

	Double findRunningData(@Param(value="map")Map<String, Object> map);

	Double findPoweroffData(@Param(value="map")Map<String, Object> map);

	Double findAlarmData(@Param(value="map")Map<String, Object> map);

	Double findWaittingData(@Param(value="map")Map<String, Object> map);

	Double findManaulData(@Param(value="map")Map<String, Object> map);

	Double selectRankData(@Param(value="machineId")Long machineId);
	
}
