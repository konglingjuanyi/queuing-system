package com.qunar.ops.workflow.engine.stragety;

import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;

import com.qunar.ops.workflow.engine.result.BaseResult;
import com.qunar.ops.workflow.engine.result.DataResult;
import com.qunar.ops.workflow.engine.service.EngineService;

public class HistoryHandler {
	
	public BaseResult buildSqlResult(
			TaskService taskService, 
			HistoryService historyService,
			RepositoryService repositoryService,
			EngineService service,
			Map<String, Object> vars, String userId){
		String processKeys = (String) vars.get("processKeys");
		if (processKeys == null) {
			return BaseResult.getSuccessResult(new DataResult());
		}
		return HistoryFactory.baseResult(processKeys).diffentApprove(taskService, historyService, repositoryService, service, vars, userId);
	}
}
