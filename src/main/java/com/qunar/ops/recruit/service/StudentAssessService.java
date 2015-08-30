package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.StudentAssessMapper;
import com.qunar.ops.recruit.model.StudentAssess;
import com.qunar.ops.recruit.model.StudentAssessExample;

@Component
public class StudentAssessService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	StudentAssessMapper saMapper;
	
	public StudentAssess getStudentAssessByStudentId(int id) {
		StudentAssessExample example = new StudentAssessExample();
		StudentAssessExample.Criteria criteria = example.createCriteria();
		criteria.andStudenIdEqualTo(id);
		List<StudentAssess> list = saMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public void add(StudentAssess sa) {
		saMapper.insert(sa);
		
	}

	public StudentAssess createStudentAssess(Map<String,String> vars){
		StudentAssess stu=new StudentAssess();
		stu.setOneCode(vars.get("one_code")==""?0:Integer.valueOf(vars.get("one_code")));
		stu.setOneCodeDetail(vars.get("one_code_detail"));
		stu.setOneAlgorithm(vars.get("one_algorithm")==""?0:Integer.valueOf(vars.get("one_algorithm")));
		stu.setOneAlgorithmDetail(vars.get("one_algorithm_detail"));
		stu.setOneNetwork(vars.get("one_network")==""?0:Integer.valueOf(vars.get("one_network")));
		stu.setOneNetworkDetail(vars.get("one_network_detail"));
		stu.setOneExperience(vars.get("one_experience")==""?0:Integer.valueOf(vars.get("one_experience")));
		stu.setOneExperienceDetail(vars.get("one_experience_detail"));
		stu.setOneOther(vars.get("one_other")==""?0:Integer.valueOf(vars.get("one_other")));
		stu.setOneOtherDetail(vars.get("one_other_detail"));
		stu.setOneLogic(vars.get("one_logic")==""?0:Integer.valueOf(vars.get("one_logic")));
		stu.setOneLogicDetail(vars.get("one_logic_detail"));
		stu.setOneTeam(vars.get("one_team")==""?0:Integer.valueOf(vars.get("one_team")));
		stu.setOneTeamDetail(vars.get("one_team_detail"));
		stu.setOneCreative(vars.get("one_creative")==""?0:Integer.valueOf(vars.get("one_creative")));
		stu.setOneCreativeDetail(vars.get("one_creative_detail"));
		stu.setOneContinuouslearning(vars.get("one_continuouslearning")==""?0:Integer.valueOf(vars.get("one_continuouslearning")));
		stu.setOneContinuouslearningDetail(vars.get("one_continuouslearning_detail"));
		stu.setOneOutstanding(vars.get("one_outstanding")==""?0:Integer.valueOf(vars.get("one_outstanding")));
		stu.setOneOutstandingDetail(vars.get("one_outstanding_detail"));
		
		stu.setTwoCode(vars.get("two_code")==""?0:Integer.valueOf(vars.get("two_code")));
		stu.setTwoCodeDetail(vars.get("two_code_detail"));
		stu.setTwoAlgorithm(vars.get("two_algorithm")==""?0:Integer.valueOf(vars.get("two_algorithm")));
		stu.setTwoAlgorithmDetail(vars.get("two_algorithm_detail"));
		stu.setTwoNetwork(vars.get("two_network")==""?0:Integer.valueOf(vars.get("two_network")));
		stu.setTwoNetworkDetail(vars.get("two_network_detail"));
		stu.setTwoExperience(vars.get("two_experience")==""?0:Integer.valueOf(vars.get("two_experience")));
		stu.setTwoExperienceDetail(vars.get("two_experience_detail"));
		stu.setTwoOther(vars.get("two_other")==""?0:Integer.valueOf(vars.get("two_other")));
		stu.setTwoOtherDetail(vars.get("two_other_detail"));
		stu.setTwoLogic(vars.get("two_logic")==""?0:Integer.valueOf(vars.get("two_logic")));
		stu.setTwoLogicDetail(vars.get("two_logic_detail"));
		stu.setTwoTeam(vars.get("two_team")==""?0:Integer.valueOf(vars.get("two_team")));
		stu.setTwoTeamDetail(vars.get("two_team_detail"));
		stu.setTwoCreative(vars.get("two_creative")==""?0:Integer.valueOf(vars.get("two_creative")));
		stu.setTwoCreativeDetail(vars.get("two_creative_detail"));
		stu.setTwoContinuouslearning(vars.get("two_continuouslearning")==""?0:Integer.valueOf(vars.get("two_continuouslearning")));
		stu.setTwoContinuouslearningDetail(vars.get("two_continuouslearning_detail"));
		stu.setTwoOutstanding(vars.get("two_outstanding")==""?0:Integer.valueOf(vars.get("two_outstanding")));
		stu.setTwoOutstandingDetail(vars.get("two_outstanding_detail"));
		
		stu.setOneConclusion(vars.get("one_conclusion")=="请选择"?"":vars.get("one_conclusion"));
		stu.setTwoConclusion(vars.get("two_conclusion")=="请选择"?"":vars.get("two_conclusion"));
		stu.setOneSuggestSalary(vars.get("one_suggest_salary")==""?0:Integer.valueOf(vars.get("one_suggest_salary")));
		stu.setOneSuggestSalaryDetail(vars.get("one_suggest_salary_detail"));
		stu.setOneSum(vars.get("one_sum")==""?0:Integer.valueOf(vars.get("one_sum")));
		stu.setTwoConclusionDetail(vars.get("two_conclusion_detail"));
		stu.setTwoSum(vars.get("two_sum")==""?0:Integer.valueOf(vars.get("two_sum")));
		stu.setTwoSuggestSalary(vars.get("two_suggest_salary")==""?0:Integer.valueOf(vars.get("two_suggest_salary")));
		stu.setOneAllocationIdea(vars.get("one_allocation_idea"));
		stu.setTwoAllocationIdea(vars.get("two_allocation_idea"));
		stu.setHrDetailIdea(vars.get("hr_detail_idea"));
		stu.setHrName(vars.get("hr_name"));
		stu.setHrSuggestSalary(vars.get("hr_suggest_salary")==""?0:Integer.valueOf(vars.get("hr_suggest_salary")));
		return stu;
	}

	public void update(StudentAssess sa) {
		saMapper.updateByPrimaryKey(sa);
	}

	public void updateBy(StudentAssess sa) {
		// TODO Auto-generated method stub
		saMapper.updateByPrimaryKeySelective(sa);
	}

}
