package org.tdds.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tdds.entity.AssembleProject;
import org.tdds.service.AssembleProjectService;

import cn.hxz.webapp.syscore.support.BaseWorkbenchController;
import net.chenke.playweb.QueryFilters;
import net.chenke.playweb.support.mybatis.Page;
import net.chenke.playweb.support.mybatis.PageRequest;
import net.chenke.playweb.util.FiltersUtils;
import net.chenke.playweb.util.HashUtils;

@Controller
@RequestMapping("${adminPath}/assemble")
public class AssembleAdminController extends BaseWorkbenchController{

	@Autowired
	private AssembleProjectService bizAssembleProject;
	
	private static final String uuid = HashUtils.MD5(AssembleAdminController.class.getName());
	
	@RequestMapping(value="process", method=RequestMethod.GET)
	public String process(Model model,HttpServletRequest request, HttpServletResponse response){
		QueryFilters filters = FiltersUtils.getQueryFilters(request, response, uuid);
		Map<String, Object> map = new HashMap<>();
		model.addAttribute("filters",filters);
		return this.view("/tdds/assemble/process");
	}
	
	/*@RequestMapping(value="data", method=RequestMethod.GET)
	public Object data(Model model,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "para", required = false) String para){
		Map<String, Object> map = new HashMap<>();
		List<AssembleProject> projects = new ArrayList<AssembleProject>();
		if(para != null){
			projects = bizAssembleProject.findAllProject(para);
			map.put("resault",projects);
		}else{
			projects = bizAssembleProject.findAllProject(para);
			map.put("projects",projects);
			map.put("count",projects.size());
		}
		return map;
	}*/
	
	@RequestMapping(value="data")
	public Object data(Model model,HttpServletRequest request, HttpServletResponse response){
		QueryFilters filters = FiltersUtils.getQueryFilters(request, response, uuid);
		PageRequest pageable = FiltersUtils.getPageable(filters);
		Page<AssembleProject> projects = bizAssembleProject.findAllProject(filters,pageable);
		Map<String, Object> map = new HashMap<>();
		map.put("projects",projects);
		map.put("number",pageable.getPageNumber());
		return map;
	}
	
	@RequestMapping(value="details", method=RequestMethod.GET)
	@ResponseBody
	public Object details(Model model,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id") Long id){
		AssembleProject project = bizAssembleProject.findProjectDetails(id);
		Map<String, Object> map = new HashMap<>();
		project.setId(id);
		map.put("project", project);
		return map;
	}
	
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public String edit(Model model,HttpServletRequest request, HttpServletResponse response,@ModelAttribute AssembleProject project){
		
		 bizAssembleProject.updateProject(project);
		 return this.view("/tdds/assemble/process");
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add(){
		return this.view("/tdds/assemble/save");
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String save(Model model,HttpServletRequest request, HttpServletResponse response, AssembleProject project){
		   
		bizAssembleProject.addProject(project);
		return this.redirect("/assemble/process");
	}
	
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String delete(Model model,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id") Long id){
		
		 bizAssembleProject.deleteProject(id);
		 return this.view("/tdds/assemble/process");
	}
}
