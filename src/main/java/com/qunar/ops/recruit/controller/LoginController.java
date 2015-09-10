package com.qunar.ops.recruit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.qunar.ops.recruit.model.Hr;
import com.qunar.ops.recruit.service.HrService;
import com.qunar.ops.recruit.service.InterviewerService;
import com.qunar.ops.recruit.util.RecruitConst;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InterviewerService interservice;
	@Autowired
	private HrService hrService;
	/**
	 * login
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/tologin")
	public ModelAndView welcom(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/login");
		return mav;
	}

	/**
	 * index
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String toindex(HttpServletRequest request,
			HttpServletResponse response, String username, String password, String j_code, ModelMap model) {
		String kaptcha = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		Object user = request.getSession().getAttribute("user");
		if(user == null){
			if(kaptcha != null && kaptcha.equals(j_code)){
				if(username != null && password != null){
					Hr hr = hrService.getHrByUserNameAndPass(username,password);
					if(hr == null){
						String message= RecruitConst.USERNAM_OR_PASSWORD_ERROR_MSG;
						model.addAttribute("message",message);
						model.addAttribute("flag",-1);
						return "/login";
					}else{
//							System.out.println("hr login");
						model.addAttribute("flag",0);
						request.getSession().setAttribute("user", hr);
						return "/hr_index";
					}
				}else{
					model.addAttribute("flag",-1);
					return "/login";
				}
			}else{
				//验证码错误
				return "/login";
			}
		}else{//session中存在用户信息
			if(user instanceof Hr){
				model.addAttribute("flag",0);
				return "/hr_index";
			}else{
				return "/login";
			}
		}
	
	}
	
}
