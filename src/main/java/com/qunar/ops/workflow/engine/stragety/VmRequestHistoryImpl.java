package com.qunar.ops.workflow.engine.stragety;

import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;

import com.qunar.ops.workflow.engine.result.BaseResult;
import com.qunar.ops.workflow.engine.service.EngineService;

public class VmRequestHistoryImpl extends HistoryInterface{
	
	public BaseResult diffentApprove(TaskService taskService,
			HistoryService historyService, RepositoryService repositoryService,
			EngineService service,
			Map<String, Object> vars, String userId) {
		BaseData baseData = constructBaseData(vars, userId);
		return actualBuildResult(taskService, historyService, repositoryService, service, baseData);
	}
	
	private BaseData constructBaseData(Map<String, Object> vars, String userId){
		BaseData baseData = createVmSql(vars, userId);
		String queryRecord = baseData.getQueryRecord();
		queryRecord += " order by RES.START_TIME_ desc";
		baseData.setQueryRecord(queryRecord);
		return baseData;
	}
}
