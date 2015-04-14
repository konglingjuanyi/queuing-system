package com.qunar.ops.oaengine.listener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.runtime.ExecutionImpl;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.qunar.ops.oaengine.result.Request;
import com.qunar.ops.oaengine.service.MailSenderService;
import com.qunar.ops.oaengine.util.OAControllerUtils;

public class NotificationListener implements TaskListener{
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MailSenderService mailSenderService;
	
	@Autowired
	protected RepositoryService repositoryService;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		DelegateExecution execution = delegateTask.getExecution();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(execution.getProcessDefinitionId());
		ActivityImpl impl = processDefinition.findActivity(execution.getCurrentActivityId());
		if(impl == null) return;
		String from = "oa@qunar.com";
		String[] to = null;
		String candidates = (String)impl.getProperties().get("documentation");
		if(candidates != null){
			to = candidates.split(",");
		}
		Map<String, Object> vars = execution.getVariables();
		String owner = (String)vars.get("owner");
		Request request = (Request)vars.get("request");
		if(request == null) {
			this.logger.warn("Request is null,taskkey={}, pid={}", execution.getCurrentActivityId(), execution.getProcessInstanceId());
			return;
		}
		if(owner == null) {
			this.logger.warn("owner is null,taskkey={}, pid={}, oid={}", execution.getCurrentActivityId(), execution.getProcessInstanceId(), request.getOid());
			return;
		}
		if(to == null || to.length == 0 ) {
			this.logger.warn("收件人没有找到, taskkey={}, pid={}, oid={}", execution.getCurrentActivityId(), execution.getProcessInstanceId(), request.getOid());
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		String now = sdf.format(new Date());
		String content = "["+now+"]<"+owner+">发起《日常报销申请》 报销总金额："+(OAControllerUtils.centMoneyToYuan(request.getAmountMoney()))+"元";
		this.mailSenderService.sender(from, to, null, content, content);
		
	}
}
