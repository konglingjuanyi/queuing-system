package com.qunar.ops.recruit.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.model.Interviewer;
import com.qunar.ops.recruit.model.PhaseInterviewer;
import com.qunar.ops.recruit.model.Student;

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

	
}
