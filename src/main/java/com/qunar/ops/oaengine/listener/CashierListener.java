package com.qunar.ops.oaengine.listener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.DelegateExecution;  
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qunar.ops.oaengine.manager.DelegationManager;
import com.qunar.ops.oaengine.manager.GroupManager;
import com.qunar.ops.oaengine.result.Request;
import com.qunar.ops.oaengine.service.EmployeeInfoService;
import com.qunar.ops.oaengine.service.MailSenderService;
import com.qunar.ops.oaengine.util.OAControllerUtils;


public class CashierListener implements TaskListener{
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MailSenderService mailSenderService;
	
	@Autowired
	protected RepositoryService repositoryService;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		
		List<String> candidates = new ArrayList<String>();
		//候选人优先级：手工指定->流程设定->根据规则从骆驼帮获取
		Map<String, Object> vars = delegateTask.getExecution().getVariables();
		delegateTask.addCandidateUser("出纳");
		
	}
}
