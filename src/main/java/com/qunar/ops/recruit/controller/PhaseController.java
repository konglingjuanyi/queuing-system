package com.qunar.ops.recruit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qunar.ops.recruit.model.Phase;
import com.qunar.ops.recruit.service.PhaseService;

@Controller
public class PhaseController {
	
	@Autowired
	private PhaseService phService;
	/**
	 * 期次管理
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hr/getPhaseInfos")
	public String getInterviewers(HttpServletRequest request,  ModelMap model) {
		System.out.println("===========================");
		List<Phase> list = phService.getPhaseInfos();
		/*List<ResultPlusAdditionalInfo> rets = new LinkedList<ResultPlusAdditionalInfo>();
		for (Interviewer interviewer : list) {
			ResultPlusAdditionalInfo info = new ResultPlusAdditionalInfo();
			info.setObj(interviewer);
			info.addStringInfo(interviewer.getFirstFe()+interviewer.getFirstQa()+interviewer.getFirstRd()+"");
			info.addStringInfo(interviewer.getSecondFe()+interviewer.getSecondQa()+interviewer.getSecondRd()+"");
			rets.add(info);
		}*/
		model.addAttribute("list", list);
		return "/phase_manage";
	}
	
}
