package org.tdds.service;

import java.util.List;

import org.tdds.entity.AssembleProject;

import net.chenke.playweb.QueryFilters;
import net.chenke.playweb.support.mybatis.Page;
import net.chenke.playweb.support.mybatis.PageRequest;

public interface AssembleProjectService {

	List<AssembleProject> findAllProject(String pname);
	
	AssembleProject findProjectDetails(Long id);
	
	void updateProject(AssembleProject project);
	
	void addProject(AssembleProject project);
	
	void deleteProject(Long id);
	
	Page<AssembleProject> findAllProject(QueryFilters filters, PageRequest pageable);
}
