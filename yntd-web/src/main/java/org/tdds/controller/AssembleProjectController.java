package org.tdds.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tdds.entity.AssembleProject;
import org.tdds.service.AssembleProjectService;

import cn.hxz.webapp.syscore.support.BasePortalController;
import net.chenke.playweb.QueryFilters;
import net.chenke.playweb.support.mybatis.Page;
import net.chenke.playweb.support.mybatis.PageRequest;
import net.chenke.playweb.util.FiltersUtils;
import net.chenke.playweb.util.HashUtils;

@Controller
@RequestMapping("/member/assemblyProgress")
public class AssembleProjectController extends BasePortalController{
	
	@Autowired
	private AssembleProjectService bizAssembleProject;
	
	private static final String uuid = HashUtils.MD5(AssembleProjectController.class.getName());
	
	@RequestMapping(value="toIndex", method=RequestMethod.GET)
	public String toIndex(){
		
		return this.view("/www/default");
	}
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(){
		return this.view("/www/projectlist");
	}
	
	@RequestMapping(value="gain", method=RequestMethod.GET)
	public String gain(Model model,@RequestParam(value = "id") Long id){
		model.addAttribute("id",id);
		return this.view("/www/detail");
	}
	
	@RequestMapping(value="data", method=RequestMethod.GET)
	public Object data(Model model,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "para", required = false) String para){
		Map<String, Object> map = new HashMap<>();
		List<AssembleProject> projects = bizAssembleProject.findAllProject(para);
		map.put("projects",projects);
		map.put("count",projects.size());
		return map;
	}
	/*@RequestMapping(value="data")
	public Object data(Model model,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "para", required = false) String pname){
		QueryFilters filters = FiltersUtils.getQueryFilters(request, response, uuid);
		PageRequest pageable = FiltersUtils.getPageable(filters);
		Page<AssembleProject> projects = bizAssembleProject.findAllProject(filters,pageable);
		Map<String, Object> map = new HashMap<>();
		map.put("projects",projects);
		map.put("number",pageable.getPageNumber());
		return map;
//		List<AssembleProject> projects = bizAssembleProject.findAllProject(pname); 
//		map.put("count",projects.size());
	}*/
	
	@RequestMapping(value="details")
	@ResponseBody
	public Object details(Model model,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id") Long id){
		AssembleProject project = bizAssembleProject.findProjectDetails(id);
		Map<String, Object> map = new HashMap<>();
		map.put("project", project);
		return map;
	}
	
}
