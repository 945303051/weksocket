package org.tdds.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.tdds.entity.Machine;

import net.chenke.playweb.support.mybatis.DynaMapper;

public interface MachineMapper extends DynaMapper<Machine> {

	Map<String,Object> selectIdByName(@Param("machineName") String machineName);

	List<Machine> findMachine();

	List<Long> findMachineids();

	String findMachineName(Long id);

	List<Map<String, Object>> exportInfore(@Param("id") Long id);
}
