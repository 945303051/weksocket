package org.tdds.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdds.entity.AssembleProject;
import org.tdds.mapper.AssembleProjectMapper;
import org.tdds.service.AssembleProjectService;

import net.chenke.playweb.QueryFilters;
import net.chenke.playweb.support.mybatis.Page;
import net.chenke.playweb.support.mybatis.PageImpl;
import net.chenke.playweb.support.mybatis.PageRequest;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class AssembleProjectServiceImpl implements AssembleProjectService {
	
	private  static final String ORDER_BY="id desc";
	@Autowired
	private AssembleProjectMapper assembleDao;
	
	@Override
	public List<AssembleProject> findAllProject(String pname) {

		return assembleDao.findAllProject(pname);
	}
	
	@Override
	public AssembleProject findProjectDetails(Long id) {
 
		return assembleDao.findProjectDetails(id);
	}

	@Override
	public void updateProject(AssembleProject project) {
		
		assembleDao.updateProject(project);
	}

	@Override
	public void addProject(AssembleProject project) {

		assembleDao.addProject(project);
	}

	@Override
	public void deleteProject(Long id) {

		assembleDao.deleteProject(id);
	}

	public Page<AssembleProject> findAllProject(QueryFilters filters, PageRequest pageable) {
		Example example = new Example(AssembleProject.class);
		example.setOrderByClause(ORDER_BY);
		Criteria criteria= example.createCriteria();
		if(StringUtils.hasText(Objects.toString(filters.get("name"), null))){
			criteria.andEqualTo("name",Objects.toString(filters.get("name")));
		}
		List<AssembleProject> entities=assembleDao.selectByExampleAndRowBounds(example, pageable);
		return new PageImpl<AssembleProject>(entities, pageable);
	}

}
