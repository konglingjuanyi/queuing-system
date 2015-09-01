package com.qunar.ops.recruit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.qunar.ops.recruit.model.Interviewer;
import com.qunar.ops.recruit.model.Phase;
import com.qunar.ops.recruit.model.PhaseInterviewer;
import com.qunar.ops.recruit.result.BaseResult;
import com.qunar.ops.recruit.result.CommonRequest;
import com.qunar.ops.recruit.service.InterviewerService;
import com.qunar.ops.recruit.service.PhaseInterviewService;
import com.qunar.ops.recruit.service.PhaseService;
import com.qunar.ops.recruit.service.StudentService;
import com.qunar.ops.recruit.util.RecruitConst;

@Controller
public class PhaseController {
	
	@Autowired
	private PhaseService phService;
	@Autowired
	private InterviewerService interService;
	@Autowired
	private PhaseInterviewService piService;
	@Autowired
	StudentService studentService;
	/**
	 * 期次管理
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hr/getPhaseInfos")
	public String getInterviewers(HttpServletRequest request,  ModelMap model, Integer currentPage) {
		List<Phase> list = null;
		if(currentPage != null)
			list = phService.getPhaseInfos(currentPage, RecruitConst.PAGE_SIZE);
		else{
			list = phService.getPhaseInfos();
		}
		PageInfo page = new PageInfo(list);
		int pagecount=(int) ((page.getTotal()+RecruitConst.PAGE_SIZE-1)/RecruitConst.PAGE_SIZE);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pagecount", pagecount);
		model.addAttribute("list", list);
		return "/phase_manage";
	}
	
	@RequestMapping(value = "/hr/addPhaseInfo")
	public String addPhaseInfo(HttpServletRequest request,@RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
		Phase inter = phService.createPhaseInfo(vars);
		if(phService.getPhaseInfoBy(inter.getYearInfo(),inter.getPhaseName()) != null){
			return "forward:/hr/getPhaseInfos?currentPage="+1;
		}else{
			phService.addPhaseInfo(inter);
			return "forward:/hr/getPhaseInfos?currentPage="+1;
		}
	}
	
	/**
	 * goto添加期次页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hr/gotoaddPhaseInfos")
	public String gotoaddPhaseInfos(HttpServletRequest request, ModelMap model,@RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
		Phase ph = (Phase) phService.getPhaseInfoBy(vars.get("yearinfo"),vars.get("phasename"));
		List<Interviewer> list =  interService.getInterviewers();
		List<PhaseInterviewer> pi=piService.getPhaseInterviewerBy(vars.get("yearinfo"),vars.get("phasename"));
		List<Map<String,List<Map<String,String>>>> list1= new ArrayList();
		Set<String> cityset=new HashSet();
		for(PhaseInterviewer pview:pi){
			cityset.add(pview.getCity());
		}
		Iterator<String> it=cityset.iterator();
		while(it.hasNext())
		{
			String name=it.next();
			List<PhaseInterviewer> singlecity=piService.getSinglecityBy(name,vars.get("yearinfo"),vars.get("phasename"));
			List<Map<String,String>> list2=new ArrayList();
			for(PhaseInterviewer val:singlecity){
				Map<String,String> m1=new HashMap();
				m1.put(val.getIntervierName(), val.getRoom());
				list2.add(m1);
			}
			Map<String,List<Map<String,String>>> m2=new HashMap();
			m2.put(name, list2);
			list1.add(m2);
		}
		model.addAttribute("listcity", list1);
		model.addAttribute("ph", ph);
		model.addAttribute("list", list);
		return "/add_phase";
	}
	
	/**
	 * goto添加期次页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hr/addAllcity")
	public String addAllcity(HttpServletRequest request, ModelMap model,@RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
		String phase=vars.get("phase");
		String year=vars.get("year");
		String all_value=vars.get("all_value");
		if(all_value!=null && !"".equals(all_value)){
			piService.addPhaseInterviewInfo(all_value,phase,year);
			return "forward:/hr/getPhaseInfos?currentPage="+1;
		}else{
			return "forward:/hr/getPhaseInfos?currentPage="+1;
		}
	}
	
	@RequestMapping(value = "/hr/setOverPhaseInfo")
	public String  deleteStudentInfo(HttpServletRequest request, String year,String name){
		studentService.setOverStudentInfoBy(year,name);
		return "forward:/hr/getPhaseInfos?currentPage="+1;
	}
	
}
