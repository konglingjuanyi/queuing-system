package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.model.Interviewer;
import com.qunar.ops.recruit.model.PhaseInterviewer;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.util.RecruitConst;

@Component
public class PcHrService {

	static Map<PhaseInterviewer, Student> map = new HashMap<PhaseInterviewer, Student>();
	
	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public List<Interviewer> getAllInterviewer() {
		return null;
	}

	public static void put(PhaseInterviewer inter, Student s) {
		if(!map.containsKey(inter))
			map.put(inter, s);
	}

	public static void changeState(PhaseInterviewer inter,
			String state) {
		for (PhaseInterviewer i : map.keySet()) {
			if(i.equals(inter)){
				i.setStatus(state);
				break;
			}
		}
		
	}

	public static Map<PhaseInterviewer, Student> getAllInterviewers(
			String[] yearPhaseAndCity) {
		Map<PhaseInterviewer, Student> ret = new HashMap<PhaseInterviewer, Student>();
		for (PhaseInterviewer i : map.keySet()) {
			if(i.getYear().equals(yearPhaseAndCity[0]) && 
					i.getPhase().equals(yearPhaseAndCity[1]) &&
					i.getCity().equals(yearPhaseAndCity[2])){
				ret.put(i, map.get(i));
			}
		}
		return ret;
	}

	public static Student get(PhaseInterviewer pi) {
		return map.get(pi);
	}

	public static void changeStateOfStudent(PhaseInterviewer pi,
			String state) {
		map.get(pi).setState(state);
		
	}

	public static void change(PhaseInterviewer pi, Student s) {
		map.put(pi, s);
		
	}

	public static boolean containsKey(PhaseInterviewer pi) {
		// TODO Auto-generated method stub
		return map.containsKey(pi);
	}

	public static void recorveryMap(PhaseInterviewService ps, List<Student> l3) {
		for (Student student : l3) {
			String state = student.getState();
			if(state.equals(RecruitConst.STUDENT_STATE_GOING2ONEROOM) || 
					state.equals(RecruitConst.STUDENT_STATE_ONE_VIEW)){
				PhaseInterviewer phaseInter = ps.getPhaseInterviewerBy(student.getYear(), 
						student.getPhaseNo(), student.getLocation(), student.getFirstTry());
				map.put(phaseInter, student);
			}else{
				PhaseInterviewer phaseInter = ps.getPhaseInterviewerBy(student.getYear(), 
						student.getPhaseNo(), student.getLocation(), student.getSecondTry());
				map.put(phaseInter, student);
			}
		}
		
	}

	public static void remove(PhaseInterviewer pi) {
		map.remove(pi);
		
	}

	
}
