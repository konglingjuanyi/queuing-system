package com.qunar.ops.oaengine.manager;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.qunar.ops.oaengine.dao.FormAppmainMapper;
import com.qunar.ops.oaengine.dao.FormApproveLogMapper;
import com.qunar.ops.oaengine.dao.FormUpdateLogMapper;
import com.qunar.ops.oaengine.dao.Formmain0114HistoryMapper;
import com.qunar.ops.oaengine.dao.Formmain0114Mapper;
import com.qunar.ops.oaengine.dao.Formson0115HistoryMapper;
import com.qunar.ops.oaengine.dao.Formson0115Mapper;
import com.qunar.ops.oaengine.dao.Formson0116HistoryMapper;
import com.qunar.ops.oaengine.dao.Formson0116Mapper;
import com.qunar.ops.oaengine.dao.Formson0117HistoryMapper;
import com.qunar.ops.oaengine.dao.Formson0117Mapper;
import com.qunar.ops.oaengine.dao.Formson0118HistoryMapper;
import com.qunar.ops.oaengine.dao.Formson0118Mapper;
import com.qunar.ops.oaengine.dao.Formson0119HistoryMapper;
import com.qunar.ops.oaengine.dao.Formson0119Mapper;
import com.qunar.ops.oaengine.model.FormAppmain;
import com.qunar.ops.oaengine.model.FormUpdateLog;
import com.qunar.ops.oaengine.model.Formmain0114;
import com.qunar.ops.oaengine.model.Formmain0114History;
import com.qunar.ops.oaengine.model.Formson0115;
import com.qunar.ops.oaengine.model.Formson0115Example;
import com.qunar.ops.oaengine.model.Formson0115History;
import com.qunar.ops.oaengine.model.Formson0116;
import com.qunar.ops.oaengine.model.Formson0116Example;
import com.qunar.ops.oaengine.model.Formson0116History;
import com.qunar.ops.oaengine.model.Formson0117;
import com.qunar.ops.oaengine.model.Formson0117Example;
import com.qunar.ops.oaengine.model.Formson0117History;
import com.qunar.ops.oaengine.model.Formson0118;
import com.qunar.ops.oaengine.model.Formson0118Example;
import com.qunar.ops.oaengine.model.Formson0118History;
import com.qunar.ops.oaengine.model.Formson0119;
import com.qunar.ops.oaengine.model.Formson0119Example;
import com.qunar.ops.oaengine.model.Formson0119History;
import com.qunar.ops.oaengine.result.dailysubmit.EmployeeRelationsFeesInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.result.dailysubmit.HospitalityInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OtherCostsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.OvertimeMealsInfo;
import com.qunar.ops.oaengine.result.dailysubmit.TaxiFaresInfo;
import com.qunar.ops.oaengine.util.ModelUtils;

@Component
public class FormManager {

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
	private Formson0115HistoryMapper formson0115LogMapper;
	@Autowired
	private Formson0116HistoryMapper formson0116LogMapper;
	@Autowired
	private Formson0117HistoryMapper formson0117LogMapper;
	@Autowired
	private Formson0118HistoryMapper formson0118LogMapper;
	@Autowired
	private Formson0119HistoryMapper formson0119LogMapper;
	@Autowired
	private FormUpdateLogMapper formUpdateLogMapper;

	public void createFormInfo(String userId, FormInfo formInfo) {
		formmain0114Mapper.insert((Formmain0114) formInfo);

		createFormsonInfos(formInfo.getOvertimeMealsInfo(), 115);
		createFormsonInfos(formInfo.getHospitalityInfo(), 116);
		createFormsonInfos(formInfo.getOtherCostsInfo(), 117);
		createFormsonInfos(formInfo.getEmployeeRelationsFeesInfo(), 118);
		createFormsonInfos(formInfo.getTaxiFaresInfo(), 119);
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

	public void updateFormInfo(String userId, Long formId, FormInfo formInfo) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Formmain0114 formmain0114Old = formmain0114Mapper.selectByPrimaryKey(formId);
		Formmain0114 formmain0114New = (Formmain0114)formInfo;
		
		//获取不同，记日志
		List<Map<String, Object>> res = ModelUtils.compareModel(Formmain0114.class, formmain0114New, formmain0114Old);
		for(int i = 0; i < res.size(); i++){
			addUpdateLog(userId, formId, (String)res.get(i).get("field"), (String)res.get(i).get("oldValue"), (String)res.get(i).get("newValue"));
		}
		
		//更新主表
		formmain0114Mapper.updateByPrimaryKey(formmain0114New);
		
		//更新子表
		updateFormsonInfo(formId, formInfo);
	}
	
	private void updateFormsonInfo(Long formId, FormInfo formInfo) throws IllegalAccessException, InvocationTargetException{
		Map<String, Object> formson = getFormsonInfo(formId);
		OvertimeMealsInfo[] overtimeMealsInfos = formInfo.getOvertimeMealsInfo();
		OvertimeMealsInfo[] overtimeMealsInfosOld = (OvertimeMealsInfo[])formson.get("overtimeMealsInfos");
		for(int i = 0; i < overtimeMealsInfos.length; i++){
			if(overtimeMealsInfos[i].getId() == null){
				//插入新数据
				
			}else{
				
			}
		}
	}
	
	public FormInfo getFormInfo(Long formId) throws IllegalAccessException,
			InvocationTargetException {
		FormInfo formInfo = new FormInfo();
		Formmain0114 formmain0114 = formmain0114Mapper
				.selectByPrimaryKey(formId);
		BeanUtils.copyProperties(formInfo, formmain0114);

		Map<String, Object> formson = getFormsonInfo(formId);
		formInfo.setOvertimeMealsInfo((OvertimeMealsInfo[])formson.get("overtimeMealsInfos"));
		formInfo.setHospitalityInfo((HospitalityInfo[])formson.get("hospitalityInfos"));
		formInfo.setOtherCostsInfo((OtherCostsInfo[])formson.get("otherCostsInfos"));
		formInfo.setEmployeeRelationsFeesInfo((EmployeeRelationsFeesInfo[])formson.get("employeeRelationsFeesInfos"));
		formInfo.setTaxiFaresInfo((TaxiFaresInfo[])formson.get("taxiFaresInfos"));

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
		FormAppmain mod = mapper.selectByPrimaryKey(2l);
		System.out.println(mod.getAppname());
	}

	private <T> void createFormsonInfos(T[] infos, int type) {
		for (int i = 0; i < infos.length; i++) {
			switch (type) {
			case 115:
				formson0115Mapper.insert((Formson0115) infos[i]);
				break;
			case 116:
				formson0116Mapper.insert((Formson0116) infos[i]);
				break;
			case 117:
				formson0117Mapper.insert((Formson0117) infos[i]);
				break;
			case 118:
				formson0118Mapper.insert((Formson0118) infos[i]);
				break;
			case 119:
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
		List<Formson0115> formson0115s = formson0115Mapper.selectByExample(example115);
		OvertimeMealsInfo[] overtimeMealsInfos = new OvertimeMealsInfo[formson0115s.size()];
		for(int i = 0; i < formson0115s.size(); i++){
			BeanUtils.copyProperties(overtimeMealsInfos[i], formson0115s.get(i));
		}
		res.put("overtimeMealsInfos", overtimeMealsInfos);

		Formson0116Example example116 = new Formson0116Example();
		example116.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0116> formson0116s = formson0116Mapper.selectByExample(example116);
		HospitalityInfo[] hospitalityInfos = new HospitalityInfo[formson0116s.size()];
		for(int i = 0; i < formson0116s.size(); i++){
			BeanUtils.copyProperties(hospitalityInfos[i], formson0116s.get(i));
		}
		res.put("hospitalityInfos", hospitalityInfos);

		Formson0117Example example117 = new Formson0117Example();
		example117.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0117> formson0117s = formson0117Mapper.selectByExample(example117);
		OtherCostsInfo[] otherCostsInfos = new OtherCostsInfo[formson0117s.size()];
		for(int i = 0; i < formson0117s.size(); i++){
			BeanUtils.copyProperties(otherCostsInfos[i], formson0117s.get(i));
		}
		res.put("otherCostsInfos", otherCostsInfos);

		Formson0118Example example118 = new Formson0118Example();
		example118.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0118> formson0118s = formson0118Mapper.selectByExample(example118);
		EmployeeRelationsFeesInfo[] employeeRelationsFeesInfos = new EmployeeRelationsFeesInfo[formson0118s.size()];
		for(int i = 0; i < formson0118s.size(); i++){
			BeanUtils.copyProperties(employeeRelationsFeesInfos[i], formson0118s.get(i));
		}
		res.put("employeeRelationsFeesInfos", employeeRelationsFeesInfos);

		Formson0119Example example119 = new Formson0119Example();
		example119.createCriteria().andFormmain0114idEqualTo(formId);
		List<Formson0119> formson0119s = formson0119Mapper.selectByExample(example119);
		TaxiFaresInfo[] taxiFaresInfos = new TaxiFaresInfo[formson0119s.size()];
		for(int i = 0; i < formson0119s.size(); i++){
			BeanUtils.copyProperties(taxiFaresInfos[i], formson0119s.get(i));
		}
		res.put("taxiFaresInfos", taxiFaresInfos);
		
		return res;
	}

}
