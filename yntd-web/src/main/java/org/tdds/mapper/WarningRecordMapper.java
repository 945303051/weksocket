package org.tdds.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.tdds.entity.WarningRecord;

import net.chenke.playweb.support.mybatis.DynaMapper;

public interface WarningRecordMapper extends DynaMapper<WarningRecord>{
	Double selectWarningTime(@Param(value="id")Long id);

	List<Map<String, Object>> findAllRecordById(@Param(value="id")Long id);

	Double findPieData(@Param(value="id")Long id);

	Double findLineData(@Param(value="str")String str,@Param(value="id")Long id);

	Double findGaugeData(@Param(value="id")Long id);

	Double findMemberLineData(@Param(value="date")String date);
}
