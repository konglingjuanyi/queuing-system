package com.qunar.ops.oaengine.stragety;

import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;

import com.qunar.ops.oaengine.result.BaseResult;
import com.qunar.ops.oaengine.service.EngineService;

public class VmOtherRequestHistoryImpl extends HistoryInterface {

	public BaseResult diffentApprove(TaskService taskService,
			HistoryService historyService, RepositoryService repositoryService,
			EngineService service, Map<String, Object> vars, String userId) {
		BaseData baseData = constructBaseData(vars, userId);
		return actualBuildResult(taskService, historyService,
				repositoryService, service, baseData);
	}

	private BaseData constructBaseData(Map<String, Object> vars, String userId) {
		String vm_request = "vm_request";
		String processKeys = (String) vars.get("processKeys");
		vars.put("processKeys", vm_request);
		BaseData baseData1 = createVmSql(vars, userId);
		processKeys = processKeys.replace(vm_request, "");
		vars.put("processKeys", processKeys);
		BaseData baseData2 = createOtherSql(vars, userId);
		String queryRecord1 = baseData1.getQueryRecord();
		String queryCount1 = baseData1.getQueryCount();
		String queryRecord2 = baseData2.getQueryRecord();
		String queryCount2 = baseData2.getQueryCount();
		String queryRecord = queryRecord1 + " union " + queryRecord2; 
		String queryCount = queryCount1 + " union " + queryCount2;
		queryRecord = "select * from (" + queryRecord + ") foo order by START_TIME_ desc";
		queryCount = "select sum(count) from (" + queryCount + ") foo";
		BaseData baseData = new BaseData();
		baseData.setLength(baseData1.getLength());
		baseData.setStart(baseData1.getStart());
		baseData.setQueryRecord(queryRecord);
		baseData.setQueryCount(queryCount);
		baseData.setParams(baseData1.getParams());
		return baseData;
	}
}
