package com.qunar.ops.oaengine.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.qunar.ops.oaengine.domain.CompletMessage;
import com.qunar.ops.oaengine.domain.RefuseMessage;
import com.qunar.ops.oaengine.domain.TaskMessage;



public class MessageQueueService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Autowired
	private RabbitTemplate amqpTemplate;
	
	//@Autowired
	protected EngineService service;
	
	//@Autowired
	protected ProxyService prosyService;
	
	public void handleApprove(TaskMessage task){
		if(task == null) return;
		if (task instanceof CompletMessage) {
			CompletMessage msg = (CompletMessage) task;
			logger.info("异步审批开始... taskId=" + msg.getTask().getId());
			try{
				this.service.completWithoutReturn(msg.getTask(), msg.getUserId(), msg.getVars(), msg.getForm());
				prosyService.removeTask(msg.getTask().getId());
			}catch (Exception e) {
				logger.error("异步审始失败, 错误信息:"+EngineConst.WF_ERROR_SYS_MSG, e);
				msg.setStatus(-1);
				msg.setErrorMessage(e.getMessage());
				this.prosyService.updateTask(msg.getTask().getId(), msg);
			}
		}else if(task instanceof RefuseMessage){
			RefuseMessage msg = (RefuseMessage) task;
			logger.info("异步拒绝开始... taskId=" + msg.getTask().getId());
			try{
				this.service.removeWithoutReturn(msg.getTask().getProcessInstanceId(), msg.getUserId(), false, msg.getReason());
				prosyService.removeTask(msg.getTask().getId());
			}catch (Exception e) {
				logger.error("异步拒绝失败, 错误信息:"+EngineConst.WF_ERROR_SYS_MSG, e);
				msg.setStatus(-1);
				msg.setErrorMessage(e.getMessage());
				this.prosyService.updateTask(msg.getTask().getId(), msg);
			}
		}
	}
}
