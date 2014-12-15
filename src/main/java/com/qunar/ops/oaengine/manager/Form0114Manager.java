package com.qunar.ops.oaengine.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.oaengine.dao.FormAppmainMapper;
import com.qunar.ops.oaengine.dao.FormApproveLogMapper;
import com.qunar.ops.oaengine.dao.FormUpdateLogMapper;
import com.qunar.ops.oaengine.dao.Formmain0114HistoryMapper;
import com.qunar.ops.oaengine.dao.Formmain0114Mapper;
import com.qunar.ops.oaengine.dao.Formson0115HistoryMapper;
import com.qunar.ops.oaengine.dao.Formson0115LogMapper;
import com.qunar.ops.oaengine.dao.Formson0115Mapper;
import com.qunar.ops.oaengine.dao.Formson0116HistoryMapper;
import com.qunar.ops.oaengine.dao.Formson0116LogMapper;
import com.qunar.ops.oaengine.dao.Formson0116Mapper;
import com.qunar.ops.oaengine.dao.Formson0117HistoryMapper;
import com.qunar.ops.oaengine.dao.Formson0117LogMapper;
import com.qunar.ops.oaengine.dao.Formson0117Mapper;
import com.qunar.ops.oaengine.dao.Formson0118HistoryMapper;
import com.qunar.ops.oaengine.dao.Formson0118LogMapper;
import com.qunar.ops.oaengine.dao.Formson0118Mapper;
import com.qunar.ops.oaengine.dao.Formson0119HistoryMapper;
import com.qunar.ops.oaengine.dao.Formson0119LogMapper;
import com.qunar.ops.oaengine.dao.Formson0119Mapper;
import com.qunar.ops.oaengine.exception.CompareModelException;
import com.qunar.ops.oaengine.exception.FormNotFoundException;
import com.qunar.ops.oaengine.model.FormUpdateLog;
import com.qunar.ops.oaengine.model.FormUpdateLogExample;
import com.qunar.ops.oaengine.model.Formmain0114;
import com.qunar.ops.oaengine.model.Formmain0114Example;
import com.qunar.ops.oaengine.model.Formmain0114History;
import com.qunar.ops.oaengine.model.Formmain0114HistoryExample;
import com.qunar.ops.oaengine.model.Formson0115;
import com.qunar.ops.oaengine.model.Formson0115Example;
import com.qunar.ops.oaengine.model.Formson0115History;
import com.qunar.ops.oaengine.model.Formson0115HistoryExample;
import com.qunar.ops.oaengine.model.Formson0115Log;
import com.qunar.ops.oaengine.model.Formson0116;
import com.qunar.ops.oaengine.model.Formson0116Example;
import com.qunar.ops.oaengine.model.Formson0116History;
import com.qunar.ops.oaengine.model.Formson0116HistoryExample;
import com.qunar.ops.oaengine.model.Formson0116Log;
import com.qunar.ops.oaengine.model.Formson0117;
import com.qunar.ops.oaengine.model.Formson0117Example;
import com.qunar.ops.oaengine.model.Formson0117History;
import com.qunar.ops.oaengine.model.Formson0117HistoryExample;
import com.qunar.ops.oaengine.model.Formson0117Log;
import com.qunar.ops.oaengine.model.Formson0118;
import com.qunar.ops.oaengine.model.Formson0118Example;
import com.qunar.ops.oaengine.model.Formson0118History;
import com.qunar.ops.oaengine.model.Formson0118HistoryExample;
import com.qunar.ops.oaengine.model.Formson0118Log;
import com.qunar.ops.oaengine.model.Formson0119;
import com.qunar.ops.oaengine.model.Formson0119Example;
import com.qunar.ops.oaengine.model.Formson0119History;
import com.qunar.ops.oaengine.model.Formson0119HistoryExample;
import com.qunar.ops.oaengine.model.Formson0119Log;
import com.qunar.ops.oaengine.result.ListInfo;
import com.qunar.ops.oaengine.result.dailysubmit.AlertInfo;
import com.qunar.ops.oaengine.result.dailysubmit.EmployeeRelationsFeesInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfoList;
import com.qunar.ops.oaengine.result.dailysubmit.HospitalityInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OtherCostsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OvertimeMealsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.TaxiFaresInfo;
import com.qunar.ops.oaengine.util.Constants;
import com.qunar.ops.oaengine.util.ModelUtils;

@Component
public class Form0114Manager {

	public static final int OVERTIMEMEALS_INFO = 115;
	public static final int HOSPITALITY_INFO = 116;
	public static final int OTHERCOSTS_INFO = 117;
	public static final int EMPLOYEERELATIONSFEES_INFO = 118;
	public static final int TAXIFARES_INFO = 119;
	
	@Autowired
	private WorkflowManager workflowManager;
	@Autowired(required=true)
	private FormAppmainMapper formAppmainMapper;
	@Autowired(required=true)
	private FormApproveLogMapper formApproveLogMapper;
	@Autowired(required=true)
	private Formmain0114HistoryMapper formmain0114HistoryMapper;
	@Autowired(required=true)
	private Formmain0114Mapper formmain0114Mapper;
	@Autowired(required=true)
	private Formson0115Mapper formson0115Mapper;
	@Autowired(required=true)
	private Formson0116Mapper formson0116Mapper;
	@Autowired(required=true)
	private Formson0117Mapper formson0117Mapper;
	@Autowired(required=true)
	private Formson0118Mapper formson0118Mapper;
	@Autowired(required=true)
	private Formson0119Mapper formson0119Mapper;
	@Autowired(required=true)
	private Formson0115HistoryMapper formson0115HistoryMapper;
	@Autowired(required=true)
	private Formson0116HistoryMapper formson0116HistoryMapper;
	@Autowired(required=true)
	private Formson0117HistoryMapper formson0117HistoryMapper;
	@Autowired(required=true)
	private Formson0118HistoryMapper formson0118HistoryMapper;
	@Autowired(required=true)
	private Formson0119HistoryMapper formson0119HistoryMapper;
	@Autowired(required=true)
	private Formson0115LogMapper formson0115LogMapper;
	@Autowired(required=true)
	private Formson0116LogMapper formson0116LogMapper;
	@Autowired(required=true)
	private Formson0117LogMapper formson0117LogMapper;
	@Autowired(required=true)
	private Formson0118LogMapper formson0118LogMapper;
	@Autowired(required=true)
	private Formson0119LogMapper formson0119LogMapper;
	@Autowired(required=true)
	private FormUpdateLogMapper formUpdateLogMapper;

	// --------------------------表单相关----------------------------
	/**
	 * 表单新增
	 * 
	 * @param userId
	 * @param formInfo
	 * @return
	 */
	public Long createFormInfo(String userId, FormInfo formInfo) {
		int res = formmain0114Mapper.insert((Formmain0114) formInfo);
		Long formId = formInfo.getId();
		createFormsonInfos(formInfo.getOvertimeMealsInfo(), OVERTIMEMEALS_INFO, formId);
		createFormsonInfos(formInfo.getHospitalityInfo(), HOSPITALITY_INFO, formId);
		createFormsonInfos(formInfo.getOtherCostsInfo(), OTHERCOSTS_INFO, formId);
		createFormsonInfos(formInfo.getEmployeeRelationsFeesInfo(),
				EMPLOYEERELATIONSFEES_INFO, formId);
		createFormsonInfos(formInfo.getTaxiFaresInfo(), TAXIFARES_INFO, formId);

		return formId;
	}

	/**
	 * 表单删除
	 * 
	 * @param userId
	 * @param formId
	 */
	public void deleteFormInfo(String userId, Long formId) {
		// 移动到历史记录表中
		copyFormInfoToHistory(formId);

		// 删除记录
		_deleteFormInfo(formId);

		// 插入日志数据
		addUpdateLog(userId, formId, "delete", "delete", "delete");
	}

	/**
	 * 表单更新
	 * 
	 * @param userId
	 * @param formId
	 * @param formInfo
	 * @return
	 * @throws CompareModelException
	 * @throws FormNotFoundException 
	 */
	public FormInfo updateFormInfo(String userId, Long formId, FormInfo formInfo)
			throws CompareModelException, FormNotFoundException {
		Formmain0114 formmain0114Old = formmain0114Mapper
				.selectByPrimaryKey(formId);
		
		if(formmain0114Old == null){
			throw new FormNotFoundException(String.format("通过formId:%s，找到%s个form", formId, 0), Form0114Manager.class);
		}
		
		Formmain0114 formmain0114New = (Formmain0114) formInfo;
		// 获取不同，记日志
		List<Map<String, Object>> res = ModelUtils.compareModel(
				Formmain0114.class, formmain0114New, formmain0114Old);
		for (int i = 0; i < res.size(); i++) {
			addUpdateLog(userId, formId, String.valueOf(res.get(i).get("field")),
					String.valueOf(res.get(i).get("oldValue")), String.valueOf(res.get(i)
							.get("newValue")));
		}

		// 更新主表
		formmain0114Mapper.updateByPrimaryKey(formmain0114New);

		// 更新子表
		updateFormson115Info(formId, formInfo);
		updateFormson116Info(formId, formInfo);
		updateFormson117Info(formId, formInfo);
		updateFormson118Info(formId, formInfo);
		updateFormson119Info(formId, formInfo);
		return formInfo;
	}

	/**
	 * 表单更新状态
	 * 
	 * @param userId
	 * @param formId
	 * @param formInfo
	 * @return
	 * @throws CompareModelException
	 * @throws FormNotFoundException 
	 */
	public long updateFormFinishedFlag(String userId, Long formId, int finishedFlag, String proc_inst_id, boolean needSetStartDate)
			throws FormNotFoundException {
		Formmain0114 formmain0114Old = formmain0114Mapper
				.selectByPrimaryKey(formId);
		
		if(formmain0114Old == null){
			throw new FormNotFoundException(String.format("通过formId:%s，找到%s个form", formId, 0), Form0114Manager.class);
		}
		
		// 获取不同，记日志
		addUpdateLog(userId, formId, "finishedflag",
				String.valueOf(formmain0114Old.getFinishedflag()), String.valueOf(finishedFlag));

		// 更新主表
		Formmain0114 formmain0114New = formmain0114Old;
		formmain0114New.setFinishedflag(finishedFlag);
		if(needSetStartDate){
			formmain0114New.setStartDate(new Date());
		}
		if(proc_inst_id != null){
			formmain0114New.setProcInstId(proc_inst_id);
		}
		int res = formmain0114Mapper.updateByPrimaryKey(formmain0114New);
		
		return formId;
	}
	
	/**
	 * 表单查询
	 * 
	 * @param formId
	 * @return
	 * @throws FormNotFoundException 
	 */
	public FormInfo getFormInfo(Long formId) throws FormNotFoundException {
		FormInfo formInfo = new FormInfo();
		Formmain0114 formmain0114 = formmain0114Mapper
				.selectByPrimaryKey(formId);

		if(formmain0114 == null){
			return getFormInfoHistory(formId);
//			throw new FormNotFoundException(String.format("通过formId:%s，找到%s个form", formId, 0), Form0114Manager.class);
		}
		
		BeanUtils.copyProperties(formmain0114, formInfo);

		Map<String, Object> formson = getFormsonInfo(formId);
		formInfo.setOvertimeMealsInfo((OvertimeMealsInfo[]) formson
				.get("overtimeMealsInfos"));
		formInfo.setHospitalityInfo((HospitalityInfo[]) formson
				.get("hospitalityInfos"));
		formInfo.setOtherCostsInfo((OtherCostsInfo[]) formson
				.get("otherCostsInfos"));
		formInfo.setEmployeeRelationsFeesInfo((EmployeeRelationsFeesInfo[]) formson
				.get("employeeRelationsFeesInfos"));
		formInfo.setTaxiFaresInfo((TaxiFaresInfo[]) formson
				.get("taxiFaresInfos"));

		return formInfo;
	}
	
	/**
	 * 表单查询History
	 * 
	 * @param formId
	 * @return
	 * @throws FormNotFoundException 
	 */
	public FormInfo getFormInfoHistory(Long formId) throws FormNotFoundException {
		FormInfo formInfo = new FormInfo();
		Formmain0114History formmain0114History = formmain0114HistoryMapper
				.selectByPrimaryKey(formId);

		if(formmain0114History == null){
			throw new FormNotFoundException(String.format("通过formId:%s，找到%s个form", formId, 0), Form0114Manager.class);
		}
		
		BeanUtils.copyProperties(formmain0114History, formInfo);

		Map<String, Object> formson = getFormsonInfoHistory(formId);
		formInfo.setOvertimeMealsInfo((OvertimeMealsInfo[]) formson
				.get("overtimeMealsInfos"));
		formInfo.setHospitalityInfo((HospitalityInfo[]) formson
				.get("hospitalityInfos"));
		formInfo.setOtherCostsInfo((OtherCostsInfo[]) formson
				.get("otherCostsInfos"));
		formInfo.setEmployeeRelationsFeesInfo((EmployeeRelationsFeesInfo[]) formson
				.get("employeeRelationsFeesInfos"));
		formInfo.setTaxiFaresInfo((TaxiFaresInfo[]) formson
				.get("taxiFaresInfos"));

		return formInfo;
	}
	
	/**
	 * 表单查询
	 * 
	 * @param proc_inst_id
	 * @return
	 * @throws FormNotFoundException 
	 */
	public FormInfo getFormInfoByInst(String proc_inst_id) throws FormNotFoundException {
		FormInfo formInfo = new FormInfo();
		Formmain0114Example example = new Formmain0114Example();
		example.createCriteria().andProcInstIdEqualTo(proc_inst_id);
		List<Formmain0114> formmain0114s = formmain0114Mapper
				.selectByExample(example);
		if(formmain0114s.size() == 0){
			//查找已完结的历史数据
			return getFormInfoByInstHistory(proc_inst_id);
//			throw new FormNotFoundException(String.format("通过proc_inst_id:%s，找到%s个form", proc_inst_id, formmain0114s.size()), Form0114Manager.class);
		}
		BeanUtils.copyProperties(formmain0114s.get(0), formInfo);

		Map<String, Object> formson = getFormsonInfo(formmain0114s.get(0).getId());
		formInfo.setOvertimeMealsInfo((OvertimeMealsInfo[]) formson
				.get("overtimeMealsInfos"));
		formInfo.setHospitalityInfo((HospitalityInfo[]) formson
				.get("hospitalityInfos"));
		formInfo.setOtherCostsInfo((OtherCostsInfo[]) formson
				.get("otherCostsInfos"));
		formInfo.setEmployeeRelationsFeesInfo((EmployeeRelationsFeesInfo[]) formson
				.get("employeeRelationsFeesInfos"));
		formInfo.setTaxiFaresInfo((TaxiFaresInfo[]) formson
				.get("taxiFaresInfos"));

		return formInfo;
	}
	
	/**
	 * 表单查询——已完结的历史记录
	 * 
	 * @param proc_inst_id
	 * @return
	 * @throws FormNotFoundException 
	 */
	public FormInfo getFormInfoByInstHistory(String proc_inst_id) throws FormNotFoundException {
		FormInfo formInfo = new FormInfo();
		Formmain0114HistoryExample example = new Formmain0114HistoryExample();
		example.createCriteria().andProcInstIdEqualTo(proc_inst_id);
		List<Formmain0114History> formmain0114Historys = formmain0114HistoryMapper
				.selectByExample(example);
		if(formmain0114Historys.size() == 0){
			throw new FormNotFoundException(String.format("通过proc_inst_id:%s，找到%s个form", proc_inst_id, formmain0114Historys.size()), Form0114Manager.class);
		}
		BeanUtils.copyProperties(formmain0114Historys.get(0), formInfo);

		Map<String, Object> formson = getFormsonInfoHistory(formmain0114Historys.get(0).getId());
		formInfo.setOvertimeMealsInfo((OvertimeMealsInfo[]) formson
				.get("overtimeMealsInfos"));
		formInfo.setHospitalityInfo((HospitalityInfo[]) formson
				.get("hospitalityInfos"));
		formInfo.setOtherCostsInfo((OtherCostsInfo[]) formson
				.get("otherCostsInfos"));
		formInfo.setEmployeeRelationsFeesInfo((EmployeeRelationsFeesInfo[]) formson
				.get("employeeRelationsFeesInfos"));
		formInfo.setTaxiFaresInfo((TaxiFaresInfo[]) formson
				.get("taxiFaresInfos"));

		return formInfo;
	}

	/**
	 * 获取用户草稿中列表
	 * @param processKey
	 * @param userId
	 * @param startTime - 允许null
	 * @param endTime - 允许null
	 * @param pageNo
	 * @param pageSize - 默认20
	 * @return FormInfoList 用户申请流程中列表
	 * 
	 */
	public FormInfoList getUserDraftList(String userId, int pageNo, int pageSize) {
		
		pageNo = pageNo <= 0 ? 1 : pageNo;
		pageSize = pageSize > 0 ? pageSize : 20;
		
		FormInfoList formInfoList = new FormInfoList();
		List<FormInfo> formInfos = new ArrayList<FormInfo>();
		Formmain0114Example example = new Formmain0114Example();
		com.qunar.ops.oaengine.model.Formmain0114Example.Criteria criteria = example.createCriteria();
		criteria.andStartMemberIdEqualTo(userId);
		criteria.andFinishedflagEqualTo(Constants.PROC_GRIFT);
		int count = formmain0114Mapper.countByExample(example);

		example.setOffset((pageNo - 1) * pageSize);
		example.setLimit(pageSize);
		example.setOrderByClause("start_date desc");
		List<Formmain0114> formmain0114s = formmain0114Mapper.selectByExample(example);
		FormInfo formInfo;
		for(int i = 0; i < formmain0114s.size(); i++){
			formInfo = new FormInfo();
			BeanUtils.copyProperties(formmain0114s.get(i), formInfo);
			
			Map<String, Object> formson = getFormsonInfo(formInfo.getId());
			formInfo.setOvertimeMealsInfo((OvertimeMealsInfo[]) formson
					.get("overtimeMealsInfos"));
			formInfo.setHospitalityInfo((HospitalityInfo[]) formson
					.get("hospitalityInfos"));
			formInfo.setOtherCostsInfo((OtherCostsInfo[]) formson
					.get("otherCostsInfos"));
			formInfo.setEmployeeRelationsFeesInfo((EmployeeRelationsFeesInfo[]) formson
					.get("employeeRelationsFeesInfos"));
			formInfo.setTaxiFaresInfo((TaxiFaresInfo[]) formson
					.get("taxiFaresInfos"));
			
			formInfos.add(formInfo);
		}
		formInfoList.setCount(count);
		formInfoList.setPageSize(pageSize);
		formInfoList.setPageNo(pageNo);
		formInfoList.setFormInfos(formInfos);
		
		return formInfoList;
	}
	
	/**
	 * 获取用户申请流程中列表
	 * @param processKey
	 * @param userId
	 * @param startTime - 允许null
	 * @param endTime - 允许null
	 * @param pageNo
	 * @param pageSize - 默认20
	 * @return FormInfoList 用户申请流程中列表
	 * 
	 */
	public FormInfoList getUserApplyList(String userId,
			Date startTime, Date endTime, int pageNo, int pageSize) {
		
		pageNo = pageNo <= 0 ? 1 : pageNo;
		pageSize = pageSize > 0 ? pageSize : 20;
		
		FormInfoList formInfoList = new FormInfoList();
		List<FormInfo> formInfos = new ArrayList<FormInfo>();
		Formmain0114Example example = new Formmain0114Example();
		com.qunar.ops.oaengine.model.Formmain0114Example.Criteria criteria = example.createCriteria();
		criteria.andFinishedflagNotEqualTo(Constants.PROC_GRIFT);
		criteria.andStartMemberIdEqualTo(userId);
		if(startTime != null){
			criteria = criteria.andStartDateGreaterThanOrEqualTo(startTime);
		}
		if(endTime != null){
			criteria = criteria.andStartDateLessThanOrEqualTo(endTime);
		}
		int count = formmain0114Mapper.countByExample(example);
		example.setOffset((pageNo - 1) * pageSize);
		example.setLimit(pageSize);
		example.setOrderByClause("start_date desc");
		List<Formmain0114> formmain0114s = formmain0114Mapper.selectByExample(example);
		FormInfo formInfo;
		for(int i = 0; i < formmain0114s.size(); i++){
			formInfo = new FormInfo();
			BeanUtils.copyProperties(formmain0114s.get(i), formInfo);
			
			Map<String, Object> formson = getFormsonInfo(formInfo.getId());
			formInfo.setOvertimeMealsInfo((OvertimeMealsInfo[]) formson
					.get("overtimeMealsInfos"));
			formInfo.setHospitalityInfo((HospitalityInfo[]) formson
					.get("hospitalityInfos"));
			formInfo.setOtherCostsInfo((OtherCostsInfo[]) formson
					.get("otherCostsInfos"));
			formInfo.setEmployeeRelationsFeesInfo((EmployeeRelationsFeesInfo[]) formson
					.get("employeeRelationsFeesInfos"));
			formInfo.setTaxiFaresInfo((TaxiFaresInfo[]) formson
					.get("taxiFaresInfos"));
			
			formInfos.add(formInfo);
		}
		formInfoList.setCount(count);
		formInfoList.setPageSize(pageSize);
		formInfoList.setPageNo(pageNo);
		formInfoList.setFormInfos(formInfos);
		
		return formInfoList;
	}
	
	/**
	 * 获取用户申请草稿状态的总数
	 * @param userId
	 * @return int 用户申请草稿状态的总数
	 * 
	 */
	public int getUserDraftCount(String userId) {
		Formmain0114Example example = new Formmain0114Example();
		com.qunar.ops.oaengine.model.Formmain0114Example.Criteria criteria = example.createCriteria();
		criteria.andStartMemberIdEqualTo(userId);
		criteria.andFinishedflagEqualTo(Constants.PROC_GRIFT);
		int count = formmain0114Mapper.countByExample(example);

		return count;
	}
	
	/**
	 * 获取用户申请流程中总数
	 * @param userId
	 * @return int 用户申请流程中总数
	 * 
	 */
	public int getUserApplyCount(String userId) {
		Formmain0114Example example = new Formmain0114Example();
		com.qunar.ops.oaengine.model.Formmain0114Example.Criteria criteria = example.createCriteria();
		criteria.andStartMemberIdEqualTo(userId);
		int count = formmain0114Mapper.countByExample(example);

		return count;
	}
	
	/**
	 * 获取用户历史流程总数
	 * @param userId
	 * @return int 用户历史流程总数
	 * 
	 */
	public int getUserApplyHisCount(String userId) {
		Formmain0114HistoryExample example = new Formmain0114HistoryExample();
		com.qunar.ops.oaengine.model.Formmain0114HistoryExample.Criteria criteria = example.createCriteria();
		criteria.andStartMemberIdEqualTo(userId);
		int count = formmain0114HistoryMapper.countByExample(example);
		return count;
	}
	
	/**
	 * 获取用户历史流程中列表
	 * @param userId
	 * @param startTime - 允许null
	 * @param endTime - 允许null
	 * @param pageNo
	 * @param pageSize - 默认20
	 * @return FormInfoList 用户历史流程中列表
	 * 
	 */
	public FormInfoList getUserApplyHisList(String userId,
			Date startTime, Date endTime, int pageNo, int pageSize) {
		
		pageNo = pageNo <= 0 ? 1 : pageNo;
		pageSize = pageSize > 0 ? pageSize : 20;
		
		FormInfoList formInfoList = new FormInfoList();
		List<FormInfo> formInfos = new ArrayList<FormInfo>();
		Formmain0114HistoryExample example = new Formmain0114HistoryExample();
		com.qunar.ops.oaengine.model.Formmain0114HistoryExample.Criteria criteria = example.createCriteria();
		criteria.andStartMemberIdEqualTo(userId);
		if(startTime != null){
			criteria = criteria.andStartDateGreaterThanOrEqualTo(startTime);
		}
		if(endTime != null){
			criteria = criteria.andStartDateLessThanOrEqualTo(endTime);
		}
		int count = formmain0114HistoryMapper.countByExample(example);
		example.setOffset((pageNo - 1) * pageSize);
		example.setLimit(pageSize);
		example.setOrderByClause("start_date desc");
		List<Formmain0114History> formmain0114Historys = formmain0114HistoryMapper.selectByExample(example);
		FormInfo formInfo;
		for(int i = 0; i < formmain0114Historys.size(); i++){
			formInfo = new FormInfo();
			BeanUtils.copyProperties(formmain0114Historys.get(i), formInfo);
			
			Map<String, Object> formson = getFormsonInfoHistory(formInfo.getId());
			formInfo.setOvertimeMealsInfo((OvertimeMealsInfo[]) formson
					.get("overtimeMealsInfos"));
			formInfo.setHospitalityInfo((HospitalityInfo[]) formson
					.get("hospitalityInfos"));
			formInfo.setOtherCostsInfo((OtherCostsInfo[]) formson
					.get("otherCostsInfos"));
			formInfo.setEmployeeRelationsFeesInfo((EmployeeRelationsFeesInfo[]) formson
					.get("employeeRelationsFeesInfos"));
			formInfo.setTaxiFaresInfo((TaxiFaresInfo[]) formson
					.get("taxiFaresInfos"));
			
			formInfos.add(formInfo);
		}
		formInfoList.setCount(count);
		formInfoList.setPageSize(pageSize);
		formInfoList.setPageNo(pageNo);
		formInfoList.setFormInfos(formInfos);
		
		return formInfoList;
	}

	public FormInfoList todoList(String processKey, String userId,
			Date startTime, Date endTime, String owner, int pageNo, int pageSize) {
		FormInfoList formInfoList = new FormInfoList();
		List<FormInfo> formInfos = new ArrayList<FormInfo>();
		return null;
	}
	
	// --------------------------日志相关----------------------------
	/**
	 * 表单变更日志
	 * 
	 * @param formId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ListInfo<AlertInfo> getAlertInfos(Long formId, int pageNo,
			int pageSize) {
		pageNo = pageNo <= 0 ? 1 : pageNo;
		pageSize = pageSize > 0 ? pageSize : 20;
		
		List<AlertInfo> alertInfos = new ArrayList<AlertInfo>();

		// 获取主表的更新
		FormUpdateLogExample example = new FormUpdateLogExample();
		example.createCriteria().andFormIdEqualTo(formId);
		int count = formUpdateLogMapper.countByExample(example);
		example.setOffset((pageNo - 1) * pageSize);
		example.setLimit(pageSize);
		example.setOrderByClause("ts desc");
		List<FormUpdateLog> logs = formUpdateLogMapper.selectByExample(example);
		AlertInfo alertInfo;
		for (int i = 0; i < logs.size(); i++) {
			alertInfo = new AlertInfo();
			BeanUtils.copyProperties(logs.get(i), alertInfo);
			alertInfos.add(alertInfo);
		}

		ListInfo<AlertInfo> res = new ListInfo<AlertInfo>();
		res.setPageNo(pageNo);
		res.setPageSize(pageSize);
		res.setCount(count);

		return res;
	}

	private void updateFormson115Info(Long formId, FormInfo formInfo)
			throws CompareModelException {
		Map<String, Object> formson = getFormsonInfo(formId);
		OvertimeMealsInfo[] overtimeMealsInfos = formInfo
				.getOvertimeMealsInfo();
		OvertimeMealsInfo[] overtimeMealsInfosOld = (OvertimeMealsInfo[]) formson
				.get("overtimeMealsInfos");
		Map<Long, Object> idsNew = getIds(overtimeMealsInfos,
				OVERTIMEMEALS_INFO);
		Map<Long, Object> idsOld = getIds(overtimeMealsInfosOld,
				OVERTIMEMEALS_INFO);
		for (int i = 0; i < overtimeMealsInfos.length; i++) {
			if (overtimeMealsInfos[i].getId() == null) {
				// 插入新数据
				formson0115Mapper.insert(overtimeMealsInfos[i]);
				// 同时复制到日志表中
				addFormsonLog(overtimeMealsInfosOld[i], OVERTIMEMEALS_INFO);
			}
		}
		for (int i = 0; i < overtimeMealsInfosOld.length; i++) {
			if (!idsNew.containsKey(overtimeMealsInfosOld[i].getId())) {
				// 复制旧数据到日志表中
				addFormsonLog(overtimeMealsInfosOld[i], OVERTIMEMEALS_INFO);
				// 删除旧数据
				formson0115Mapper.deleteByPrimaryKey(overtimeMealsInfosOld[i]
						.getId());
			} else {
				// 更新数据
				// 判断是否有修改
				List<Map<String, Object>> res = ModelUtils.compareModel(
						OvertimeMealsInfo.class,
						idsNew.get(overtimeMealsInfosOld[i].getId()),
						overtimeMealsInfosOld[i]);
				if (res.size() > 0) {
					// 更改数据
					// 复制旧数据到日志表中
					addFormsonLog(overtimeMealsInfosOld[i], OVERTIMEMEALS_INFO);
					// 修改旧数据
					formson0115Mapper
							.updateByPrimaryKey(overtimeMealsInfosOld[i]);
				} else {
					// 无需更改
				}
			}
		}
	}

	private void updateFormson116Info(Long formId, FormInfo formInfo)
			throws CompareModelException {
		Map<String, Object> formson = getFormsonInfo(formId);
		HospitalityInfo[] hospitalityInfos = formInfo.getHospitalityInfo();
		HospitalityInfo[] hospitalityInfosOld = (HospitalityInfo[]) formson
				.get("hospitalityInfos");
		Map<Long, Object> idsNew = getIds(hospitalityInfos, HOSPITALITY_INFO);
		Map<Long, Object> idsOld = getIds(hospitalityInfosOld, HOSPITALITY_INFO);
		for (int i = 0; i < hospitalityInfos.length; i++) {
			if (hospitalityInfos[i].getId() == null) {
				// 插入新数据
				formson0116Mapper.insert(hospitalityInfos[i]);
				// 同时复制到日志表中
				addFormsonLog(hospitalityInfosOld[i], HOSPITALITY_INFO);
			}
		}
		for (int i = 0; i < hospitalityInfosOld.length; i++) {
			if (!idsNew.containsKey(hospitalityInfosOld[i].getId())) {
				// 复制旧数据到日志表中
				addFormsonLog(hospitalityInfosOld[i], HOSPITALITY_INFO);
				// 删除旧数据
				formson0116Mapper.deleteByPrimaryKey(hospitalityInfosOld[i]
						.getId());
			} else {
				// 更新数据
				// 判断是否有修改
				List<Map<String, Object>> res = ModelUtils.compareModel(
						OvertimeMealsInfo.class,
						idsNew.get(hospitalityInfosOld[i].getId()),
						hospitalityInfosOld[i]);
				if (res.size() > 0) {
					// 更改数据
					// 复制旧数据到日志表中
					addFormsonLog(hospitalityInfosOld[i], HOSPITALITY_INFO);
					// 修改旧数据
					formson0116Mapper
							.updateByPrimaryKey(hospitalityInfosOld[i]);
				} else {
					// 无需更改
				}
			}
		}
	}

	private void updateFormson117Info(Long formId, FormInfo formInfo)
			throws CompareModelException {
		Map<String, Object> formson = getFormsonInfo(formId);
		OtherCostsInfo[] otherCostsInfos = formInfo.getOtherCostsInfo();
		OtherCostsInfo[] otherCostsInfosOld = (OtherCostsInfo[]) formson
				.get("otherCostsInfos");
		Map<Long, Object> idsNew = getIds(otherCostsInfos, OTHERCOSTS_INFO);
		Map<Long, Object> idsOld = getIds(otherCostsInfosOld, OTHERCOSTS_INFO);
		for (int i = 0; i < otherCostsInfos.length; i++) {
			if (otherCostsInfos[i].getId() == null) {
				// 插入新数据
				formson0117Mapper.insert(otherCostsInfos[i]);
				// 同时复制到日志表中
				addFormsonLog(otherCostsInfosOld[i], OTHERCOSTS_INFO);
			}
		}
		for (int i = 0; i < otherCostsInfosOld.length; i++) {
			if (!idsNew.containsKey(otherCostsInfosOld[i].getId())) {
				// 复制旧数据到日志表中
				addFormsonLog(otherCostsInfosOld[i], OTHERCOSTS_INFO);
				// 删除旧数据
				formson0117Mapper.deleteByPrimaryKey(otherCostsInfosOld[i]
						.getId());
			} else {
				// 更新数据
				// 判断是否有修改
				List<Map<String, Object>> res = ModelUtils.compareModel(
						OvertimeMealsInfo.class,
						idsNew.get(otherCostsInfosOld[i].getId()),
						otherCostsInfosOld[i]);
				if (res.size() > 0) {
					// 更改数据
					// 复制旧数据到日志表中
					addFormsonLog(otherCostsInfosOld[i], OTHERCOSTS_INFO);
					// 修改旧数据
					formson0117Mapper.updateByPrimaryKey(otherCostsInfosOld[i]);
				} else {
					// 无需更改
				}
			}
		}
	}

	private void updateFormson118Info(Long formId, FormInfo formInfo)
			throws CompareModelException {
		Map<String, Object> formson = getFormsonInfo(formId);
		EmployeeRelationsFeesInfo[] employeeRelationsFeesInfos = formInfo
				.getEmployeeRelationsFeesInfo();
		EmployeeRelationsFeesInfo[] employeeRelationsFeesInfosOld = (EmployeeRelationsFeesInfo[]) formson
				.get("employeeRelationsFeesInfos");
		Map<Long, Object> idsNew = getIds(employeeRelationsFeesInfos,
				EMPLOYEERELATIONSFEES_INFO);
		Map<Long, Object> idsOld = getIds(employeeRelationsFeesInfosOld,
				EMPLOYEERELATIONSFEES_INFO);
		for (int i = 0; i < employeeRelationsFeesInfos.length; i++) {
			if (employeeRelationsFeesInfos[i].getId() == null) {
				// 插入新数据
				formson0118Mapper.insert(employeeRelationsFeesInfos[i]);
				// 同时复制到日志表中
				addFormsonLog(employeeRelationsFeesInfosOld[i],
						EMPLOYEERELATIONSFEES_INFO);
			}
		}
		for (int i = 0; i < employeeRelationsFeesInfosOld.length; i++) {
			if (!idsNew.containsKey(employeeRelationsFeesInfosOld[i].getId())) {
				// 复制旧数据到日志表中
				addFormsonLog(employeeRelationsFeesInfosOld[i],
						EMPLOYEERELATIONSFEES_INFO);
				// 删除旧数据
				formson0118Mapper
						.deleteByPrimaryKey(employeeRelationsFeesInfosOld[i]
								.getId());
			} else {
				// 更新数据
				// 判断是否有修改
				List<Map<String, Object>> res = ModelUtils.compareModel(
						OvertimeMealsInfo.class,
						idsNew.get(employeeRelationsFeesInfosOld[i].getId()),
						employeeRelationsFeesInfosOld[i]);
				if (res.size() > 0) {
					// 更改数据
					// 复制旧数据到日志表中
					addFormsonLog(employeeRelationsFeesInfosOld[i],
							EMPLOYEERELATIONSFEES_INFO);
					// 修改旧数据
					formson0118Mapper
							.updateByPrimaryKey(employeeRelationsFeesInfosOld[i]);
				} else {
					// 无需更改
				}
			}
		}
	}

	private void updateFormson119Info(Long formId, FormInfo formInfo)
			throws CompareModelException {
		Map<String, Object> formson = getFormsonInfo(formId);
		TaxiFaresInfo[] taxiFaresInfos = formInfo.getTaxiFaresInfo();
		TaxiFaresInfo[] taxiFaresInfosOld = (TaxiFaresInfo[]) formson
				.get("taxiFaresInfos");
		Map<Long, Object> idsNew = getIds(taxiFaresInfos, TAXIFARES_INFO);
		Map<Long, Object> idsOld = getIds(taxiFaresInfosOld, TAXIFARES_INFO);
		for (int i = 0; i < taxiFaresInfos.length; i++) {
			if (taxiFaresInfos[i].getId() == null) {
				// 插入新数据
				formson0119Mapper.insert(taxiFaresInfos[i]);
				// 同时复制到日志表中
				addFormsonLog(taxiFaresInfosOld[i], TAXIFARES_INFO);
			}
		}
		for (int i = 0; i < taxiFaresInfosOld.length; i++) {
			if (!idsNew.containsKey(taxiFaresInfosOld[i].getId())) {
				// 复制旧数据到日志表中
				addFormsonLog(taxiFaresInfosOld[i], TAXIFARES_INFO);
				// 删除旧数据
				formson0119Mapper.deleteByPrimaryKey(taxiFaresInfosOld[i]
						.getId());
			} else {
				// 更新数据
				// 判断是否有修改
				List<Map<String, Object>> res = ModelUtils.compareModel(
						OvertimeMealsInfo.class,
						idsNew.get(taxiFaresInfosOld[i].getId()),
						taxiFaresInfosOld[i]);
				if (res.size() > 0) {
					// 更改数据
					// 复制旧数据到日志表中
					addFormsonLog(taxiFaresInfosOld[i], TAXIFARES_INFO);
					// 修改旧数据
					formson0119Mapper.updateByPrimaryKey(taxiFaresInfosOld[i]);
				} else {
					// 无需更改
				}
			}
		}
	}

	private <T> void addFormsonLog(T t, int type) {
		// 复制旧数据到日志表中
		Date now = new Date();
		switch (type) {
		case OVERTIMEMEALS_INFO:
			Formson0115Log formson0115Log = new Formson0115Log();
			formson0115Log.setDob(now);
			formson0115Log.setTs(now);
			BeanUtils.copyProperties((OvertimeMealsInfo) t, formson0115Log);
			formson0115LogMapper.insert(formson0115Log);
			break;
		case HOSPITALITY_INFO:
			Formson0116Log formson0116Log = new Formson0116Log();
			formson0116Log.setDob(now);
			formson0116Log.setTs(now);
			BeanUtils.copyProperties((HospitalityInfo) t, formson0116Log);
			formson0116LogMapper.insert(formson0116Log);
			break;
		case OTHERCOSTS_INFO:
			Formson0117Log formson0117Log = new Formson0117Log();
			formson0117Log.setDob(now);
			formson0117Log.setTs(now);
			BeanUtils.copyProperties((OtherCostsInfo) t, formson0117Log);
			formson0117LogMapper.insert(formson0117Log);
			break;
		case EMPLOYEERELATIONSFEES_INFO:
			Formson0118Log formson0118Log = new Formson0118Log();
			formson0118Log.setDob(now);
			formson0118Log.setTs(now);
			BeanUtils.copyProperties((EmployeeRelationsFeesInfo) t, formson0118Log);
			formson0118LogMapper.insert(formson0118Log);
			break;
		case TAXIFARES_INFO:
			Formson0119Log formson0119Log = new Formson0119Log();
			formson0119Log.setDob(now);
			formson0119Log.setTs(now);
			BeanUtils.copyProperties((TaxiFaresInfo) t, formson0119Log);
			formson0119LogMapper.insert(formson0119Log);
			break;
		}
	}

	private <T> Map<Long, Object> getIds(T[] t, int type) {
		Map<Long, Object> ids = new HashMap<Long, Object>();
		for (int i = 0; i < t.length; i++) {
			switch (type) {
			case OVERTIMEMEALS_INFO:
				ids.put(((OvertimeMealsInfo) t[i]).getId(), t[i]);
				break;
			case HOSPITALITY_INFO:
				ids.put(((HospitalityInfo) t[i]).getId(), t[i]);
				break;
			case OTHERCOSTS_INFO:
				ids.put(((OtherCostsInfo) t[i]).getId(), t[i]);
				break;
			case EMPLOYEERELATIONSFEES_INFO:
				ids.put(((EmployeeRelationsFeesInfo) t[i]).getId(), t[i]);
				break;
			case TAXIFARES_INFO:
				ids.put(((TaxiFaresInfo) t[i]).getId(), t[i]);
				break;
			}
		}
		return ids;
	}

	public static void main(String[] args) throws Exception {
		// SqlSessionFactoryBean sqlSessionFactory = new
		// ClassPathXmlApplicationContext(new
		// String[]{"spring.xml"}).getBean(SqlSessionFactoryBean.class);
		// SqlSession sqlSession = sqlSessionFactory.getObject().openSession();
		// FormAppmainMapper mapper =
		// sqlSession.getMapper(FormAppmainMapper.class);
		// FormAppmainMapper mapper = new ClassPathXmlApplicationContext(
		// new String[] { "spring.xml" }).getBean(FormAppmainMapper.class);
		// FormAppmain main = new FormAppmain();
		// main.setAppname("test");
		// main.setId(10l);
		// main.setState((short) 1);
		// main.setTableName("test");
		// mapper.insert(main);
		// System.out.println(main.getId());
		// FormAppmain mod = mapper.selectByPrimaryKey(2l);
		// System.out.println(mod.getAppname());

		// 获取主表的更新
		// FormUpdateLogMapper formUpdateLogMapper = new
		// ClassPathXmlApplicationContext(
		// new String[] { "spring.xml" }).getBean(FormUpdateLogMapper.class);
		// FormUpdateLogExample example = new FormUpdateLogExample();
		// example.createCriteria().andFormIdEqualTo(1l);
		// example.setOffset((1 - 1) * 1);
		// example.setLimit(1);
		// List<GroupResultInfo> logs = formUpdateLogMapper
		// .groupByExampleTs(example);
		// System.out.println(logs);

	}

	private <T> void createFormsonInfos(T[] infos, int type, Long formId) {
		for (int i = 0; i < infos.length; i++) {
			switch (type) {
			case OVERTIMEMEALS_INFO:
				Formson0115 temp0115 = (Formson0115) infos[i];
				temp0115.setFormmain0114id(formId);
				formson0115Mapper.insert((Formson0115) infos[i]);
				break;
			case HOSPITALITY_INFO:
				Formson0116 temp0116 = (Formson0116) infos[i];
				temp0116.setFormmain0114id(formId);
				formson0116Mapper.insert(temp0116);
				break;
			case OTHERCOSTS_INFO:
				Formson0117 temp0117 = (Formson0117) infos[i];
				temp0117.setFormmain0114id(formId);
				formson0117Mapper.insert(temp0117);
				break;
			case EMPLOYEERELATIONSFEES_INFO:
				Formson0118 temp0118 = (Formson0118) infos[i];
				temp0118.setFormmain0114id(formId);
				formson0118Mapper.insert(temp0118);
				break;
			case TAXIFARES_INFO:
				Formson0119 temp0119 = (Formson0119) infos[i];
				temp0119.setFormmain0114id(formId);
				formson0119Mapper.insert(temp0119);
				break;
			}
		}
	}

	private void addUpdateLog(String userId, Long formId, String formItem,
			String oldValue, String newValue) {
		Date now = new Date();
		FormUpdateLog updateLog = new FormUpdateLog();
		updateLog.setCreateUser(userId);
		updateLog.setDob(now);
		updateLog.setTs(now);
		updateLog.setFormId(formId);
		updateLog.setFormItem(formItem);
		updateLog.setNewValue(newValue);
		updateLog.setOldValue(oldValue);
		formUpdateLogMapper.insert(updateLog);
	}

	private void copyFormInfoToHistory(Long formId) {
		// 主表的数据到历史
		Formmain0114 formmain0114 = formmain0114Mapper
				.selectByPrimaryKey(formId);
		Formmain0114History formmain0114History = new Formmain0114History();
		BeanUtils.copyProperties(formmain0114, formmain0114History);
		formmain0114HistoryMapper.insert(formmain0114History);

		// 子表数据到历史表中
		copyFormsonInfoToHistory(formId);
	}

	private void copyFormsonInfoToHistory(Long formId) {
		// 子表中的数据到历史
		Formson0115Example example115 = new Formson0115Example();
		example115.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0115> formson0115s = formson0115Mapper
				.selectByExample(example115);
		Formson0115History formson0115History;
		for (int i = 0; i < formson0115s.size(); i++) {
			formson0115History = new Formson0115History();
			BeanUtils.copyProperties(formson0115s.get(i), formson0115History);
			formson0115HistoryMapper.insert(formson0115History);
		}

		Formson0116Example example116 = new Formson0116Example();
		example116.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0116> formson0116s = formson0116Mapper
				.selectByExample(example116);
		Formson0116History formson0116History;
		for (int i = 0; i < formson0116s.size(); i++) {
			formson0116History = new Formson0116History();
			BeanUtils.copyProperties(formson0116s.get(i), formson0116History);
			formson0116HistoryMapper.insert(formson0116History);
		}

		Formson0117Example example117 = new Formson0117Example();
		example117.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0117> formson0117s = formson0117Mapper
				.selectByExample(example117);
		Formson0117History formson0117History;
		for (int i = 0; i < formson0117s.size(); i++) {
			formson0117History = new Formson0117History();
			BeanUtils.copyProperties(formson0117s.get(i), formson0117History);
			formson0117HistoryMapper.insert(formson0117History);
		}

		Formson0118Example example118 = new Formson0118Example();
		example118.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0118> formson0118s = formson0118Mapper
				.selectByExample(example118);
		Formson0118History formson0118History;
		for (int i = 0; i < formson0118s.size(); i++) {
			formson0118History = new Formson0118History();
			BeanUtils.copyProperties(formson0118s.get(i), formson0118History);
			formson0118HistoryMapper.insert(formson0118History);
		}

		Formson0119Example example119 = new Formson0119Example();
		example119.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0119> formson0119s = formson0119Mapper
				.selectByExample(example119);
		Formson0119History formson0119History;
		for (int i = 0; i < formson0119s.size(); i++) {
			formson0119History = new Formson0119History();
			BeanUtils.copyProperties(formson0119s.get(i), formson0119History);
			formson0119HistoryMapper.insert(formson0119History);
		}
	}

	private void _deleteFormInfo(Long formId) {
		// 删除主表的记录
		formmain0114Mapper.deleteByPrimaryKey(formId);

		// 删除子表的记录
		_deleteFormsonInfo(formId);
	}

	private void _deleteFormsonInfo(Long formId) {
		// 删除子表的记录
		Formson0115Example example115 = new Formson0115Example();
		example115.createCriteria().andFormmain0114idEqualTo(formId);
		formson0115Mapper.deleteByExample(example115);

		Formson0116Example example116 = new Formson0116Example();
		example116.createCriteria().andFormmain0114idEqualTo(formId);
		formson0116Mapper.deleteByExample(example116);

		Formson0117Example example117 = new Formson0117Example();
		example117.createCriteria().andFormmain0114idEqualTo(formId);
		formson0117Mapper.deleteByExample(example117);

		Formson0118Example example118 = new Formson0118Example();
		example118.createCriteria().andFormmain0114idEqualTo(formId);
		formson0118Mapper.deleteByExample(example118);

		Formson0119Example example119 = new Formson0119Example();
		example119.createCriteria().andFormmain0114idEqualTo(formId);
		formson0119Mapper.deleteByExample(example119);
	}

	private Map<String, Object> getFormsonInfo(Long formId) {
		Map<String, Object> res = new HashMap<String, Object>();
		Formson0115Example example115 = new Formson0115Example();
		example115.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0115> formson0115s = formson0115Mapper
				.selectByExample(example115);
		OvertimeMealsInfo[] overtimeMealsInfos = new OvertimeMealsInfo[formson0115s
				.size()];
		for (int i = 0; i < formson0115s.size(); i++) {
			overtimeMealsInfos[i] = new OvertimeMealsInfo();
			BeanUtils
					.copyProperties(formson0115s.get(i), overtimeMealsInfos[i]);
		}
		res.put("overtimeMealsInfos", overtimeMealsInfos);

		Formson0116Example example116 = new Formson0116Example();
		example116.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0116> formson0116s = formson0116Mapper
				.selectByExample(example116);
		HospitalityInfo[] hospitalityInfos = new HospitalityInfo[formson0116s
				.size()];
		for (int i = 0; i < formson0116s.size(); i++) {
			hospitalityInfos[i] = new HospitalityInfo();
			BeanUtils.copyProperties(formson0116s.get(i), hospitalityInfos[i]);
		}
		res.put("hospitalityInfos", hospitalityInfos);

		Formson0117Example example117 = new Formson0117Example();
		example117.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0117> formson0117s = formson0117Mapper
				.selectByExample(example117);
		OtherCostsInfo[] otherCostsInfos = new OtherCostsInfo[formson0117s
				.size()];
		for (int i = 0; i < formson0117s.size(); i++) {
			otherCostsInfos[i] = new OtherCostsInfo();
			BeanUtils.copyProperties(formson0117s.get(i), otherCostsInfos[i]);
		}
		res.put("otherCostsInfos", otherCostsInfos);

		Formson0118Example example118 = new Formson0118Example();
		example118.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0118> formson0118s = formson0118Mapper
				.selectByExample(example118);
		EmployeeRelationsFeesInfo[] employeeRelationsFeesInfos = new EmployeeRelationsFeesInfo[formson0118s
				.size()];
		for (int i = 0; i < formson0118s.size(); i++) {
			employeeRelationsFeesInfos[i] = new EmployeeRelationsFeesInfo();
			BeanUtils.copyProperties(formson0118s.get(i),
					employeeRelationsFeesInfos[i]);
		}
		res.put("employeeRelationsFeesInfos", employeeRelationsFeesInfos);

		Formson0119Example example119 = new Formson0119Example();
		example119.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0119> formson0119s = formson0119Mapper
				.selectByExample(example119);
		TaxiFaresInfo[] taxiFaresInfos = new TaxiFaresInfo[formson0119s.size()];
		for (int i = 0; i < formson0119s.size(); i++) {
			taxiFaresInfos[i] = new TaxiFaresInfo();
			BeanUtils.copyProperties(formson0119s.get(i), taxiFaresInfos[i]);
		}
		res.put("taxiFaresInfos", taxiFaresInfos);

		return res;
	}
	
	private Map<String, Object> getFormsonInfoHistory(Long formId) {
		Map<String, Object> res = new HashMap<String, Object>();
		Formson0115HistoryExample example115History = new Formson0115HistoryExample();
		example115History.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0115History> formson0115Historys = formson0115HistoryMapper
				.selectByExample(example115History);
		OvertimeMealsInfo[] overtimeMealsInfos = new OvertimeMealsInfo[formson0115Historys
				.size()];
		for (int i = 0; i < formson0115Historys.size(); i++) {
			overtimeMealsInfos[i] = new OvertimeMealsInfo();
			BeanUtils
					.copyProperties(formson0115Historys.get(i), overtimeMealsInfos[i]);
		}
		res.put("overtimeMealsInfos", overtimeMealsInfos);

		Formson0116HistoryExample example116History = new Formson0116HistoryExample();
		example116History.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0116History> formson0116Historys = formson0116HistoryMapper
				.selectByExample(example116History);
		HospitalityInfo[] hospitalityInfos = new HospitalityInfo[formson0116Historys
				.size()];
		for (int i = 0; i < formson0116Historys.size(); i++) {
			hospitalityInfos[i] = new HospitalityInfo();
			BeanUtils.copyProperties(formson0116Historys.get(i), hospitalityInfos[i]);
		}
		res.put("hospitalityInfos", hospitalityInfos);

		Formson0117HistoryExample example117History = new Formson0117HistoryExample();
		example117History.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0117History> formson0117Historys = formson0117HistoryMapper
				.selectByExample(example117History);
		OtherCostsInfo[] otherCostsInfos = new OtherCostsInfo[formson0117Historys
				.size()];
		for (int i = 0; i < formson0117Historys.size(); i++) {
			otherCostsInfos[i] = new OtherCostsInfo();
			BeanUtils.copyProperties(formson0117Historys.get(i), otherCostsInfos[i]);
		}
		res.put("otherCostsInfos", otherCostsInfos);

		Formson0118HistoryExample example118History = new Formson0118HistoryExample();
		example118History.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0118History> formson0118Historys = formson0118HistoryMapper
				.selectByExample(example118History);
		EmployeeRelationsFeesInfo[] employeeRelationsFeesInfos = new EmployeeRelationsFeesInfo[formson0118Historys
				.size()];
		for (int i = 0; i < formson0118Historys.size(); i++) {
			employeeRelationsFeesInfos[i] = new EmployeeRelationsFeesInfo();
			BeanUtils.copyProperties(formson0118Historys.get(i),
					employeeRelationsFeesInfos[i]);
		}
		res.put("employeeRelationsFeesInfos", employeeRelationsFeesInfos);

		Formson0119HistoryExample example119History = new Formson0119HistoryExample();
		example119History.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0119History> formson0119Historys = formson0119HistoryMapper
				.selectByExample(example119History);
		TaxiFaresInfo[] taxiFaresInfos = new TaxiFaresInfo[formson0119Historys.size()];
		for (int i = 0; i < formson0119Historys.size(); i++) {
			taxiFaresInfos[i] = new TaxiFaresInfo();
			BeanUtils.copyProperties(formson0119Historys.get(i), taxiFaresInfos[i]);
		}
		res.put("taxiFaresInfos", taxiFaresInfos);

		return res;
	}

}
