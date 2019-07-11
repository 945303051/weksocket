package org.tdds.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.tdds.entity.AssembleProject;

import net.chenke.playweb.support.mybatis.DynaMapper;

public interface AssembleProjectMapper extends DynaMapper<AssembleProject>{

	List<AssembleProject> findAllProject(@Param(value="pname")String pname);
	
	AssembleProject findProjectDetails(@Param(value="id")Long id);
	
	void updateProject(@Param(value="project")AssembleProject project);
	
	void addProject(AssembleProject project);
	
	void deleteProject(@Param(value="id")Long id);
}
