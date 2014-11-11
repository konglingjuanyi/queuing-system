package com.qunar.ops.oaengine.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import com.qunar.ops.oaengine.model.FormAppmain;
import com.qunar.ops.oaengine.model.FormUpdateLog;
import com.qunar.ops.oaengine.model.Formmain0114;
import com.qunar.ops.oaengine.model.Formmain0114History;
import com.qunar.ops.oaengine.model.Formson0115;
import com.qunar.ops.oaengine.model.Formson0115Example;
import com.qunar.ops.oaengine.model.Formson0115History;
import com.qunar.ops.oaengine.model.Formson0115Log;
import com.qunar.ops.oaengine.model.Formson0116;
import com.qunar.ops.oaengine.model.Formson0116Example;
import com.qunar.ops.oaengine.model.Formson0116History;
import com.qunar.ops.oaengine.model.Formson0116Log;
import com.qunar.ops.oaengine.model.Formson0117;
import com.qunar.ops.oaengine.model.Formson0117Example;
import com.qunar.ops.oaengine.model.Formson0117History;
import com.qunar.ops.oaengine.model.Formson0117Log;
import com.qunar.ops.oaengine.model.Formson0118;
import com.qunar.ops.oaengine.model.Formson0118Example;
import com.qunar.ops.oaengine.model.Formson0118History;
import com.qunar.ops.oaengine.model.Formson0118Log;
import com.qunar.ops.oaengine.model.Formson0119;
import com.qunar.ops.oaengine.model.Formson0119Example;
import com.qunar.ops.oaengine.model.Formson0119History;
import com.qunar.ops.oaengine.model.Formson0119Log;
import com.qunar.ops.oaengine.result.dailysubmit.EmployeeRelationsFeesInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.result.dailysubmit.HospitalityInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OtherCostsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OvertimeMealsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.TaxiFaresInfo;
import com.qunar.ops.oaengine.util.ModelUtils;

@Component
public class FormManager {

	public static final int OVERTIMEMEALS_INFO = 115;
	public static final int HOSPITALITY_INFO = 116;
	public static final int OTHERCOSTS_INFO = 117;
	public static final int EMPLOYEERELATIONSFEES_INFO = 118;
	public static final int TAXIFARES_INFO = 119;
	
	@Autowired
	private FormAppmainMapper formAppmainMapper;
	@Autowired
	private FormApproveLogMapper formApproveLogMapper;
	@Autowired
	private Formmain0114HistoryMapper formmain0114HistoryMapper;
	@Autowired
	private Formmain0114Mapper formmain0114Mapper;
	@Autowired
	private Formson0115Mapper formson0115Mapper;
	@Autowired
	private Formson0116Mapper formson0116Mapper;
	@Autowired
	private Formson0117Mapper formson0117Mapper;
	@Autowired
	private Formson0118Mapper formson0118Mapper;
	@Autowired
	private Formson0119Mapper formson0119Mapper;
	@Autowired
	private Formson0115HistoryMapper formson0115HistoryMapper;
	@Autowired
	private Formson0116HistoryMapper formson0116HistoryMapper;
	@Autowired
	private Formson0117HistoryMapper formson0117HistoryMapper;
	@Autowired
	private Formson0118HistoryMapper formson0118HistoryMapper;
	@Autowired
	private Formson0119HistoryMapper formson0119HistoryMapper;
	@Autowired
	private Formson0115LogMapper formson0115LogMapper;
	@Autowired
	private Formson0116LogMapper formson0116LogMapper;
	@Autowired
	private Formson0117LogMapper formson0117LogMapper;
	@Autowired
	private Formson0118LogMapper formson0118LogMapper;
	@Autowired
	private Formson0119LogMapper formson0119LogMapper;
	@Autowired
	private FormUpdateLogMapper formUpdateLogMapper;

	public void createFormInfo(String userId, FormInfo formInfo) {
		formmain0114Mapper.insert((Formmain0114) formInfo);

		createFormsonInfos(formInfo.getOvertimeMealsInfo(), OVERTIMEMEALS_INFO);
		createFormsonInfos(formInfo.getHospitalityInfo(), HOSPITALITY_INFO);
		createFormsonInfos(formInfo.getOtherCostsInfo(), OTHERCOSTS_INFO);
		createFormsonInfos(formInfo.getEmployeeRelationsFeesInfo(), EMPLOYEERELATIONSFEES_INFO);
		createFormsonInfos(formInfo.getTaxiFaresInfo(), TAXIFARES_INFO);
	}

	public void deleteFormInfo(String userId, Long formId)
			throws IllegalAccessException, InvocationTargetException {
		Date now = new Date();
		// 移动到历史记录表中
		copyFormInfoToHistory(formId);

		// 删除记录
		_deleteFormInfo(formId);

		// 插入日志数据
		addUpdateLog(userId, formId, "delete", "delete", "delete");
	}

	public void updateFormInfo(String userId, Long formId, FormInfo formInfo)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Formmain0114 formmain0114Old = formmain0114Mapper
				.selectByPrimaryKey(formId);
		Formmain0114 formmain0114New = (Formmain0114) formInfo;

		// 获取不同，记日志
		List<Map<String, Object>> res = ModelUtils.compareModel(
				Formmain0114.class, formmain0114New, formmain0114Old);
		for (int i = 0; i < res.size(); i++) {
			addUpdateLog(userId, formId, (String) res.get(i).get("field"),
					(String) res.get(i).get("oldValue"), (String) res.get(i)
							.get("newValue"));
		}

		// 更新主表
		formmain0114Mapper.updateByPrimaryKey(formmain0114New);

		// 更新子表
		updateFormson115Info(formId, formInfo);
		updateFormson116Info(formId, formInfo);
		updateFormson117Info(formId, formInfo);
		updateFormson118Info(formId, formInfo);
		updateFormson119Info(formId, formInfo);
	}

	private void updateFormson115Info(Long formId, FormInfo formInfo)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> formson = getFormsonInfo(formId);
		OvertimeMealsInfo[] overtimeMealsInfos = formInfo
				.getOvertimeMealsInfo();
		OvertimeMealsInfo[] overtimeMealsInfosOld = (OvertimeMealsInfo[]) formson
				.get("overtimeMealsInfos");
		Map<Long, Object> idsNew = getIds(overtimeMealsInfos, OVERTIMEMEALS_INFO);
		Map<Long, Object> idsOld = getIds(overtimeMealsInfosOld, OVERTIMEMEALS_INFO);
		for (int i = 0; i < overtimeMealsInfos.length; i++) {
			if (overtimeMealsInfos[i].getId() == null) {
				// 插入新数据
				formson0115Mapper.insert(overtimeMealsInfos[i]);
				// 同时复制到日志表中
				addFormsonLog(overtimeMealsInfosOld[i], OVERTIMEMEALS_INFO);
			}
		}
		for(int i = 0; i < overtimeMealsInfosOld.length; i++){
			if(!idsNew.containsKey(overtimeMealsInfosOld[i].getId())){
				//复制旧数据到日志表中
				addFormsonLog(overtimeMealsInfosOld[i], OVERTIMEMEALS_INFO);
				//删除旧数据
				formson0115Mapper.deleteByPrimaryKey(overtimeMealsInfosOld[i].getId());
			}else{
				//更新数据
				//判断是否有修改
				List<Map<String, Object>> res = ModelUtils.compareModel(
						OvertimeMealsInfo.class, idsNew.get(overtimeMealsInfosOld[i].getId()), overtimeMealsInfosOld[i]);
				if(res.size() > 0){
					//更改数据
					//复制旧数据到日志表中
					addFormsonLog(overtimeMealsInfosOld[i], OVERTIMEMEALS_INFO);
					//修改旧数据
					formson0115Mapper.updateByPrimaryKey(overtimeMealsInfosOld[i]);
				}else{
					//无需更改
				}
			}
		}
	}
	
	private void updateFormson116Info(Long formId, FormInfo formInfo)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> formson = getFormsonInfo(formId);
		HospitalityInfo[] hospitalityInfos = formInfo
				.getHospitalityInfo();
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
		for(int i = 0; i < hospitalityInfosOld.length; i++){
			if(!idsNew.containsKey(hospitalityInfosOld[i].getId())){
				//复制旧数据到日志表中
				addFormsonLog(hospitalityInfosOld[i], HOSPITALITY_INFO);
				//删除旧数据
				formson0116Mapper.deleteByPrimaryKey(hospitalityInfosOld[i].getId());
			}else{
				//更新数据
				//判断是否有修改
				List<Map<String, Object>> res = ModelUtils.compareModel(
						OvertimeMealsInfo.class, idsNew.get(hospitalityInfosOld[i].getId()), hospitalityInfosOld[i]);
				if(res.size() > 0){
					//更改数据
					//复制旧数据到日志表中
					addFormsonLog(hospitalityInfosOld[i], HOSPITALITY_INFO);
					//修改旧数据
					formson0116Mapper.updateByPrimaryKey(hospitalityInfosOld[i]);
				}else{
					//无需更改
				}
			}
		}
	}
	
	private void updateFormson117Info(Long formId, FormInfo formInfo)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> formson = getFormsonInfo(formId);
		OtherCostsInfo[] otherCostsInfos = formInfo
				.getOtherCostsInfo();
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
		for(int i = 0; i < otherCostsInfosOld.length; i++){
			if(!idsNew.containsKey(otherCostsInfosOld[i].getId())){
				//复制旧数据到日志表中
				addFormsonLog(otherCostsInfosOld[i], OTHERCOSTS_INFO);
				//删除旧数据
				formson0117Mapper.deleteByPrimaryKey(otherCostsInfosOld[i].getId());
			}else{
				//更新数据
				//判断是否有修改
				List<Map<String, Object>> res = ModelUtils.compareModel(
						OvertimeMealsInfo.class, idsNew.get(otherCostsInfosOld[i].getId()), otherCostsInfosOld[i]);
				if(res.size() > 0){
					//更改数据
					//复制旧数据到日志表中
					addFormsonLog(otherCostsInfosOld[i], OTHERCOSTS_INFO);
					//修改旧数据
					formson0117Mapper.updateByPrimaryKey(otherCostsInfosOld[i]);
				}else{
					//无需更改
				}
			}
		}
	}
	
	private void updateFormson118Info(Long formId, FormInfo formInfo)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> formson = getFormsonInfo(formId);
		EmployeeRelationsFeesInfo[] employeeRelationsFeesInfos = formInfo
				.getEmployeeRelationsFeesInfo();
		EmployeeRelationsFeesInfo[] employeeRelationsFeesInfosOld = (EmployeeRelationsFeesInfo[]) formson
				.get("employeeRelationsFeesInfos");
		Map<Long, Object> idsNew = getIds(employeeRelationsFeesInfos, EMPLOYEERELATIONSFEES_INFO);
		Map<Long, Object> idsOld = getIds(employeeRelationsFeesInfosOld, EMPLOYEERELATIONSFEES_INFO);
		for (int i = 0; i < employeeRelationsFeesInfos.length; i++) {
			if (employeeRelationsFeesInfos[i].getId() == null) {
				// 插入新数据
				formson0118Mapper.insert(employeeRelationsFeesInfos[i]);
				// 同时复制到日志表中
				addFormsonLog(employeeRelationsFeesInfosOld[i], EMPLOYEERELATIONSFEES_INFO);
			}
		}
		for(int i = 0; i < employeeRelationsFeesInfosOld.length; i++){
			if(!idsNew.containsKey(employeeRelationsFeesInfosOld[i].getId())){
				//复制旧数据到日志表中
				addFormsonLog(employeeRelationsFeesInfosOld[i], EMPLOYEERELATIONSFEES_INFO);
				//删除旧数据
				formson0118Mapper.deleteByPrimaryKey(employeeRelationsFeesInfosOld[i].getId());
			}else{
				//更新数据
				//判断是否有修改
				List<Map<String, Object>> res = ModelUtils.compareModel(
						OvertimeMealsInfo.class, idsNew.get(employeeRelationsFeesInfosOld[i].getId()), employeeRelationsFeesInfosOld[i]);
				if(res.size() > 0){
					//更改数据
					//复制旧数据到日志表中
					addFormsonLog(employeeRelationsFeesInfosOld[i], EMPLOYEERELATIONSFEES_INFO);
					//修改旧数据
					formson0118Mapper.updateByPrimaryKey(employeeRelationsFeesInfosOld[i]);
				}else{
					//无需更改
				}
			}
		}
	}
	
	private void updateFormson119Info(Long formId, FormInfo formInfo)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> formson = getFormsonInfo(formId);
		TaxiFaresInfo[] taxiFaresInfos = formInfo
				.getTaxiFaresInfo();
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
		for(int i = 0; i < taxiFaresInfosOld.length; i++){
			if(!idsNew.containsKey(taxiFaresInfosOld[i].getId())){
				//复制旧数据到日志表中
				addFormsonLog(taxiFaresInfosOld[i], TAXIFARES_INFO);
				//删除旧数据
				formson0119Mapper.deleteByPrimaryKey(taxiFaresInfosOld[i].getId());
			}else{
				//更新数据
				//判断是否有修改
				List<Map<String, Object>> res = ModelUtils.compareModel(
						OvertimeMealsInfo.class, idsNew.get(taxiFaresInfosOld[i].getId()), taxiFaresInfosOld[i]);
				if(res.size() > 0){
					//更改数据
					//复制旧数据到日志表中
					addFormsonLog(taxiFaresInfosOld[i], TAXIFARES_INFO);
					//修改旧数据
					formson0119Mapper.updateByPrimaryKey(taxiFaresInfosOld[i]);
				}else{
					//无需更改
				}
			}
		}
	}
	
	private <T> void addFormsonLog(T t, int type) throws IllegalAccessException, InvocationTargetException{
		//复制旧数据到日志表中
		Date now = new Date();
		switch (type) {
		case OVERTIMEMEALS_INFO:
			Formson0115Log formson0115Log = new Formson0115Log();
			formson0115Log.setDob(now);
			formson0115Log.setTs(now);
			BeanUtils.copyProperties(formson0115Log, (OvertimeMealsInfo)t);
			formson0115LogMapper.insert(formson0115Log);
			break;
		case HOSPITALITY_INFO:
			Formson0116Log formson0116Log = new Formson0116Log();
			formson0116Log.setDob(now);
			formson0116Log.setTs(now);
			BeanUtils.copyProperties(formson0116Log, (HospitalityInfo)t);
			formson0116LogMapper.insert(formson0116Log);
			break;
		case OTHERCOSTS_INFO:
			Formson0117Log formson0117Log = new Formson0117Log();
			formson0117Log.setDob(now);
			formson0117Log.setTs(now);
			BeanUtils.copyProperties(formson0117Log, (OtherCostsInfo)t);
			formson0117LogMapper.insert(formson0117Log);
			break;
		case EMPLOYEERELATIONSFEES_INFO:
			Formson0118Log formson0118Log = new Formson0118Log();
			formson0118Log.setDob(now);
			formson0118Log.setTs(now);
			BeanUtils.copyProperties(formson0118Log, (EmployeeRelationsFeesInfo)t);
			formson0118LogMapper.insert(formson0118Log);
			break;
		case TAXIFARES_INFO:
			Formson0119Log formson0119Log = new Formson0119Log();
			formson0119Log.setDob(now);
			formson0119Log.setTs(now);
			BeanUtils.copyProperties(formson0119Log, (TaxiFaresInfo)t);
			formson0119LogMapper.insert(formson0119Log);
			break;
		}
	}
	
	private <T> Map<Long, Object> getIds(T[] t, int type){
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

	public FormInfo getFormInfo(Long formId) throws IllegalAccessException,
			InvocationTargetException {
		FormInfo formInfo = new FormInfo();
		Formmain0114 formmain0114 = formmain0114Mapper
				.selectByPrimaryKey(formId);
		BeanUtils.copyProperties(formInfo, formmain0114);

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

	public static void main(String[] args) throws Exception {
		// SqlSessionFactoryBean sqlSessionFactory = new
		// ClassPathXmlApplicationContext(new
		// String[]{"spring.xml"}).getBean(SqlSessionFactoryBean.class);
		// SqlSession sqlSession = sqlSessionFactory.getObject().openSession();
		// FormAppmainMapper mapper =
		// sqlSession.getMapper(FormAppmainMapper.class);
		FormAppmainMapper mapper = new ClassPathXmlApplicationContext(
				new String[] { "spring.xml" }).getBean(FormAppmainMapper.class);
		FormAppmain main = new FormAppmain();
		main.setAppname("test");
		main.setId(10l);
		main.setState((short) 1);
		main.setTableName("test");
		mapper.insert(main);
		System.out.println(main.getId());
//		FormAppmain mod = mapper.selectByPrimaryKey(2l);
//		System.out.println(mod.getAppname());
	}

	private <T> void createFormsonInfos(T[] infos, int type) {
		for (int i = 0; i < infos.length; i++) {
			switch (type) {
			case OVERTIMEMEALS_INFO:
				formson0115Mapper.insert((Formson0115) infos[i]);
				break;
			case HOSPITALITY_INFO:
				formson0116Mapper.insert((Formson0116) infos[i]);
				break;
			case OTHERCOSTS_INFO:
				formson0117Mapper.insert((Formson0117) infos[i]);
				break;
			case EMPLOYEERELATIONSFEES_INFO:
				formson0118Mapper.insert((Formson0118) infos[i]);
				break;
			case TAXIFARES_INFO:
				formson0119Mapper.insert((Formson0119) infos[i]);
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

	private void copyFormInfoToHistory(Long formId)
			throws IllegalAccessException, InvocationTargetException {
		// 主表的数据到历史
		Formmain0114 formmain0114 = formmain0114Mapper
				.selectByPrimaryKey(formId);
		Formmain0114History formmain0114History = new Formmain0114History();
		BeanUtils.copyProperties(formmain0114History, formmain0114);
		formmain0114HistoryMapper.insert(formmain0114History);

		// 子表数据到历史表中
		copyFormsonInfoToHistory(formId);
	}

	private void copyFormsonInfoToHistory(Long formId)
			throws IllegalAccessException, InvocationTargetException {
		// 子表中的数据到历史
		Formson0115Example example115 = new Formson0115Example();
		example115.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0115> formson0115s = formson0115Mapper
				.selectByExample(example115);
		Formson0115History formson0115History;
		for (int i = 0; i < formson0115s.size(); i++) {
			formson0115History = new Formson0115History();
			BeanUtils.copyProperties(formson0115History, formson0115s.get(i));
			formson0115HistoryMapper.insert(formson0115History);
		}

		Formson0116Example example116 = new Formson0116Example();
		example116.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0116> formson0116s = formson0116Mapper
				.selectByExample(example116);
		Formson0116History formson0116History;
		for (int i = 0; i < formson0116s.size(); i++) {
			formson0116History = new Formson0116History();
			BeanUtils.copyProperties(formson0116History, formson0116s.get(i));
			formson0116HistoryMapper.insert(formson0116History);
		}

		Formson0117Example example117 = new Formson0117Example();
		example117.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0117> formson0117s = formson0117Mapper
				.selectByExample(example117);
		Formson0117History formson0117History;
		for (int i = 0; i < formson0117s.size(); i++) {
			formson0117History = new Formson0117History();
			BeanUtils.copyProperties(formson0117History, formson0117s.get(i));
			formson0117HistoryMapper.insert(formson0117History);
		}

		Formson0118Example example118 = new Formson0118Example();
		example118.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0118> formson0118s = formson0118Mapper
				.selectByExample(example118);
		Formson0118History formson0118History;
		for (int i = 0; i < formson0118s.size(); i++) {
			formson0118History = new Formson0118History();
			BeanUtils.copyProperties(formson0118History, formson0118s.get(i));
			formson0118HistoryMapper.insert(formson0118History);
		}

		Formson0119Example example119 = new Formson0119Example();
		example119.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0119> formson0119s = formson0119Mapper
				.selectByExample(example119);
		Formson0119History formson0119History;
		for (int i = 0; i < formson0119s.size(); i++) {
			formson0119History = new Formson0119History();
			BeanUtils.copyProperties(formson0119History, formson0119s.get(i));
			formson0119HistoryMapper.insert(formson0119History);
		}
	}

	private void _deleteFormInfo(Long formId) throws IllegalAccessException,
			InvocationTargetException {
		// 删除主表的记录
		formmain0114Mapper.deleteByPrimaryKey(formId);

		// 删除子表的记录
		_deleteFormsonInfo(formId);
	}

	private void _deleteFormsonInfo(Long formId) throws IllegalAccessException,
			InvocationTargetException {
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

	private Map<String, Object> getFormsonInfo(Long formId)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> res = new HashMap<String, Object>();
		Formson0115Example example115 = new Formson0115Example();
		example115.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0115> formson0115s = formson0115Mapper
				.selectByExample(example115);
		OvertimeMealsInfo[] overtimeMealsInfos = new OvertimeMealsInfo[formson0115s
				.size()];
		for (int i = 0; i < formson0115s.size(); i++) {
			BeanUtils
					.copyProperties(overtimeMealsInfos[i], formson0115s.get(i));
		}
		res.put("overtimeMealsInfos", overtimeMealsInfos);

		Formson0116Example example116 = new Formson0116Example();
		example116.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0116> formson0116s = formson0116Mapper
				.selectByExample(example116);
		HospitalityInfo[] hospitalityInfos = new HospitalityInfo[formson0116s
				.size()];
		for (int i = 0; i < formson0116s.size(); i++) {
			BeanUtils.copyProperties(hospitalityInfos[i], formson0116s.get(i));
		}
		res.put("hospitalityInfos", hospitalityInfos);

		Formson0117Example example117 = new Formson0117Example();
		example117.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0117> formson0117s = formson0117Mapper
				.selectByExample(example117);
		OtherCostsInfo[] otherCostsInfos = new OtherCostsInfo[formson0117s
				.size()];
		for (int i = 0; i < formson0117s.size(); i++) {
			BeanUtils.copyProperties(otherCostsInfos[i], formson0117s.get(i));
		}
		res.put("otherCostsInfos", otherCostsInfos);

		Formson0118Example example118 = new Formson0118Example();
		example118.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0118> formson0118s = formson0118Mapper
				.selectByExample(example118);
		EmployeeRelationsFeesInfo[] employeeRelationsFeesInfos = new EmployeeRelationsFeesInfo[formson0118s
				.size()];
		for (int i = 0; i < formson0118s.size(); i++) {
			BeanUtils.copyProperties(employeeRelationsFeesInfos[i],
					formson0118s.get(i));
		}
		res.put("employeeRelationsFeesInfos", employeeRelationsFeesInfos);

		Formson0119Example example119 = new Formson0119Example();
		example119.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0119> formson0119s = formson0119Mapper
				.selectByExample(example119);
		TaxiFaresInfo[] taxiFaresInfos = new TaxiFaresInfo[formson0119s.size()];
		for (int i = 0; i < formson0119s.size(); i++) {
			BeanUtils.copyProperties(taxiFaresInfos[i], formson0119s.get(i));
		}
		res.put("taxiFaresInfos", taxiFaresInfos);

		return res;
	}

}