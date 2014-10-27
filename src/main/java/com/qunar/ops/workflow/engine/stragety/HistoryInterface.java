package com.qunar.ops.workflow.engine.stragety;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.NativeHistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qunar.ops.workflow.engine.result.BaseResult;
import com.qunar.ops.workflow.engine.result.DataResult;
import com.qunar.ops.workflow.engine.service.EngineService;

public abstract class HistoryInterface {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public abstract BaseResult diffentApprove(TaskService taskService,
			HistoryService historyService, RepositoryService repositoryService,
			EngineService service, Map<String, Object> vars, String userId);

	public BaseData createVmSql(Map<String, Object> vars, String userId) {
		String startTime = (String) vars.get("startTime");
		String endTime = (String) vars.get("endTime");
		String sstart = (String) vars.get("start");
		String slength = (String) vars.get("length");
		String approveUser = (String) vars.get("approveUser");
		String keywords = (String) vars.get("keywords");
		String processKeys = (String) vars.get("processKeys");
		String status1 = (String) vars.get("status1");
		String status2 = (String) vars.get("status2");
		String status3 = (String) vars.get("status3");
		int start = sstart == null ? 0 : Integer.valueOf(sstart);
		int length = slength == null ? 0 : Integer.valueOf(slength);
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("userId", userId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sql = " from ACT_HI_PROCINST RES "
				+ "inner join ACT_RE_PROCDEF D on RES.PROC_DEF_ID_ = D.ID_ "
				+ "inner join ACT_HI_TASKINST T on RES.PROC_INST_ID_ = T.PROC_INST_ID_ and T.END_TIME_ is not null "
				+ "inner join ACT_HI_VARINST A0 on RES.PROC_INST_ID_ = A0.PROC_INST_ID_ WHERE ";
		String keys = splitProcessKeys(processKeys);
		sql += " D.KEY_ in (" + keys + ")";
		if (startTime != null) {
			try {
				Date _startTime = sdf.parse(startTime);
				sql += " and RES.START_TIME_ >= #{startTime}";
				params.put("startTime", _startTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
			}
		}
		if (endTime != null) {
			try {
				Date _endTime = sdf.parse(endTime);
				sql += " and RES.START_TIME_ <= #{endTime}";
				params.put("endTime", _endTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
			}
		}
		if (approveUser != null) {
			sql += " and RES.START_USER_ID_ = #{approveUser}";
			params.put("approveUser", approveUser);
		}
		if (keywords != null) {
			sql += " and A0.VAR_TYPE_ = 'string' and A0.TEXT_ LIKE #{keywords}";
			params.put("keywords", keywords);
		}
		if (status1 != null && status2 != null && status3 != null) {

		} else {
			if (status1 != null) {
				// 选取处理中的记录，已完成
				sql += " and RES.END_TIME_ is not NULL and RES.DELETE_REASON_ is NULL";
			}
			if (status2 != null) {
				// 已拒绝
				sql += " and RES.END_TIME_ is not NULL and RES.DELETE_REASON_ is not NULL";
			}
			if (status3 != null) {
				// 已完成
				sql += " and RES.END_TIME_ is NULL";
			}
		}

		String queryRecord = "select distinct RES.*" + sql;
		// + " order by RES.START_TIME_ desc";
		String queryCount = "select count(distinct RES.ID_)" + sql;
		BaseData baseData = new BaseData(params, start, length, queryRecord,
				queryCount);
		return baseData;
	}

	public BaseData createOtherSql(Map<String, Object> vars, String userId) {
		String startTime = (String) vars.get("startTime");
		String endTime = (String) vars.get("endTime");
		String sstart = (String) vars.get("start");
		String slength = (String) vars.get("length");
		String approveUser = (String) vars.get("approveUser");
		String processKeys = (String) vars.get("processKeys");
		System.out.println(processKeys);
		String keywords = (String) vars.get("keywords");
		String status1 = (String) vars.get("status1");
		String status2 = (String) vars.get("status2");
		String status3 = (String) vars.get("status3");
		int start = sstart == null ? 0 : Integer.valueOf(sstart);
		int length = slength == null ? 0 : Integer.valueOf(slength);
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("userId", userId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sql = " from ACT_HI_PROCINST RES "
				+ "inner join ACT_RE_PROCDEF D on RES.PROC_DEF_ID_ = D.ID_ "
				+ "inner join ACT_HI_VARINST A0 on RES.PROC_INST_ID_ = A0.PROC_INST_ID_ WHERE RES.END_TIME_ is not NULL ";
		String keys = splitProcessKeys(processKeys);
		sql += "and D.KEY_ in (" + keys + ")";
		if (startTime != null) {
			try {
				Date _startTime = sdf.parse(startTime);
				sql += " and RES.START_TIME_ >= #{startTime}";
				params.put("startTime", _startTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
			}
		}
		if (endTime != null) {
			try {
				Date _endTime = sdf.parse(endTime);
				sql += " and RES.START_TIME_ <= #{endTime}";
				params.put("endTime", _endTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
			}
		}
		if (approveUser != null) {
			sql += " and RES.START_USER_ID_ = #{approveUser}";
			params.put("approveUser", approveUser);
		}
		if (keywords != null) {
			sql += " and A0.VAR_TYPE_ = 'string' and A0.TEXT_ LIKE #{keywords}";
			params.put("keywords", keywords);
		}
		if (status1 != null && status2 != null && status3 != null) {

		} else {
			if (status1 != null) {
				// 选取处理中的记录，已完成
				sql += " and RES.DELETE_REASON_ is NULL";
			}
			if (status2 != null) {
				// 已拒绝
				sql += " and RES.DELETE_REASON_ is not NULL";
			}
		}

		String queryRecord = "select distinct RES.*" + sql;
		// + " order by RES.START_TIME_ desc";
		String queryCount = "select count(distinct RES.ID_)" + sql;
		BaseData baseData = new BaseData(params, start, length, queryRecord,
				queryCount);
		return baseData;
	}

	public BaseResult actualBuildResult(TaskService taskService,
			HistoryService historyService, RepositoryService repositoryService,
			EngineService service, BaseData baseData) {
		String queryRecord = baseData.getQueryRecord();
		String queryCount = baseData.getQueryCount();
		Map<String, Object> params = baseData.getParams();
		int start = baseData.getStart();
		int length = baseData.getLength();
		Map<String, Object[]> infos = new HashMap<String, Object[]>();
		List<HistoricProcessInstance> pis = new ArrayList<HistoricProcessInstance>();
		NativeHistoricProcessInstanceQuery query = historyService
				.createNativeHistoricProcessInstanceQuery();
		query.sql(queryRecord);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			query.parameter(entry.getKey(), entry.getValue());
		}
		pis = query.sql(queryRecord).listPage(start, length);
		query = historyService.createNativeHistoricProcessInstanceQuery();
		query.sql(queryCount);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			query.parameter(entry.getKey(), entry.getValue());
		}
		long count = 0;
		count = query.count();
		DataResult result = new DataResult();
		result.setCount(count);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (HistoricProcessInstance pi : pis) {
			String processInstanceId = pi.getId();
			Object[] arr = infos.get(pi.getId());
			ProcessDefinition _pd = repositoryService.getProcessDefinition(pi
					.getProcessDefinitionId());
			List<HistoricVariableInstance> _vars = historyService
					.createHistoricVariableInstanceQuery()
					.processInstanceId(pi.getId()).list();
			Map<String, Object> _var = new HashMap<String, Object>();
			if (arr == null) {
				for (HistoricVariableInstance v : _vars) {
					_var.put(v.getVariableName(), v.getValue());
				}
				arr = new Object[] { _pd, _var };
				infos.put(pi.getId(), arr);
			}

			ProcessDefinition pd = (ProcessDefinition) arr[0];
			Map<String, Object> var = (Map<String, Object>) arr[1];
			// String summary = (String) var.get("summary");
			String startedUser = pi.getStartUserId();
			String delReason = pi.getDeleteReason();
			String resultStr = "";
			String reason = "";
			if (delReason == null) {
				resultStr = "正常";
				reason = (String) var.get("reason");
			} else {
				resultStr = "驳回";
				reason = delReason;
			}
			String end = "";
			String summary = "";

			String process = "";
			HistoricTaskInstanceQuery taskQuery = historyService
					.createHistoricTaskInstanceQuery();
			taskQuery.processInstanceId(processInstanceId);
			List<HistoricTaskInstance> taskInfos = taskQuery.list();
			int size = taskInfos.size();
			if (pi.getEndTime() != null) {
				end = sdf.format(pi.getEndTime());
				for (int i = 0; i < size; i++) {
					HistoricTaskInstance taskInfo = taskInfos.get(i);
					String newEnd = "";
					if (taskInfo.getEndTime() == null) {
						newEnd = "时间为空";
					} else {
						newEnd = sdf.format(taskInfo.getEndTime());
					}
					String approve = taskInfo.getDeleteReason();
					approve = "deleted".equals(approve) ? " 拒绝 " : " 同意 ";
					process += taskInfo.getName() + ":"
							+ taskInfo.getAssignee() + approve + newEnd
							+ " || ";
					if ("拒绝".equals(approve.trim())) {
						// summary = taskInfo.getTaskName() + ":" +
						// taskInfo.getAssign() + approve + end;
						summary += "驳回 审批结束";
						resultStr = "驳回";
						break;
					}
					if (i == size - 1) {
						summary = taskInfo.getName() + ":"
								+ taskInfo.getAssignee() + approve + end;
						summary = "正常 审批结束";
					}
				}

			} else {
				for (int i = 0; i < size - 1; i++) {
					HistoricTaskInstance taskInfo = taskInfos.get(i);
					String newEnd = "";
					if (taskInfo.getEndTime() == null) {
						newEnd = "时间为空";
					} else {
						newEnd = sdf.format(taskInfo.getEndTime());
					}
					String approve = taskInfo.getDeleteReason();
					approve = "deleted".equals(approve) ? " 拒绝 " : " 同意 ";
					String tempStr = taskInfo.getName() + ":"
							+ taskInfo.getAssignee() + approve + newEnd
							+ " || ";
					process += tempStr + " || ";
					if ("拒绝".equals(approve.trim())) {
						summary = tempStr;
						resultStr = "驳回";
						break;
					}
					if (taskInfo.getEndTime() != null
							&& taskInfos.get(i + 1).getEndTime() == null) {
						end = sdf.format(taskInfo.getEndTime());
						summary = taskInfo.getName() + ":"
								+ taskInfo.getAssignee() + approve + end;
						break;
					}
				}
			}
			result.append(new String[] { pi.getId(), pd.getName(), startedUser,
					summary, sdf.format(pi.getStartTime()), end, pd.getKey(),
					resultStr, pi.getBusinessKey(), reason, process });
		}
		return BaseResult.getSuccessResult(result);
	}
	
	private String splitProcessKeys(String processKeys){
		String keys = "";
		for (String key : processKeys.split(",")) {
			if("".equals(key)){
				continue;
			}
			keys += "'" + key + "',";
		}
		keys = keys.substring(0, keys.length() - 1);
		return keys;
	}
	
	class BaseData {
		private Map<String, Object> params;
		private int start;
		private int length;
		private String queryRecord;
		private String queryCount;

		public BaseData() {
			super();
			// TODO Auto-generated constructor stub
		}

		public BaseData(Map<String, Object> params, int start, int length,
				String queryRecord, String queryCount) {
			super();
			this.params = params;
			this.start = start;
			this.length = length;
			this.queryRecord = queryRecord;
			this.queryCount = queryCount;
		}

		public Map<String, Object> getParams() {
			return params;
		}

		public void setParams(Map<String, Object> params) {
			this.params = params;
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

		public String getQueryRecord() {
			return queryRecord;
		}

		public void setQueryRecord(String queryRecord) {
			this.queryRecord = queryRecord;
		}

		public String getQueryCount() {
			return queryCount;
		}

		public void setQueryCount(String queryCount) {
			this.queryCount = queryCount;
		}
	}

}
