package com.qunar.ops.workflow.engine.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;

public class TurnBackTaskActivityBehavior {

	public static final String TASK_ROLL_BACK = "rollback";

	private UserTaskActivityBehavior activityBehavior;

	public UserTaskActivityBehavior getActivityBehavior() {
		return activityBehavior;
	}

	public void setActivityBehavior(UserTaskActivityBehavior activityBehavior) {
		this.activityBehavior = activityBehavior;
	}
	


	public synchronized void signal(ActivityExecution execution, Map<String, String> destinationTasks) {
		try {
			if (!destinationTasks.isEmpty()) {
				
				List<PvmTransition> transitions = new ArrayList<PvmTransition>();
				ActivityImpl currentActivity = (ActivityImpl) execution.getActivity();
				ProcessDefinitionImpl pd = (ProcessDefinitionImpl) currentActivity.getProcessDefinition();
				for(Map.Entry<String, String> v : destinationTasks.entrySet()){
					String destinationTaskKey = v.getKey();
					String assignee = v.getValue();
					TransitionImpl transitionImpl = currentActivity.createOutgoingTransition();
					ActivityImpl destionationTask = (ActivityImpl) pd.findActivity(destinationTaskKey);
					TaskDefinition td = (TaskDefinition) destionationTask.getProperty("taskDefinition");
//					Expression oldAssigneeExpression = null;
//					if(td != null){
//						oldAssigneeExpression = td.getAssigneeExpression();
//						Expression assigneeExpression = Context
//								.getProcessEngineConfiguration().getExpressionManager()
//								.createExpression(assignee);
//						td.getCandidateUserIdExpressions().clear();
//						td.setAssigneeExpression(assigneeExpression);
//						destionationTask.setProperty("taskDefinition", td);
//					}
					transitionImpl.setDestination(destionationTask);
					transitions.add(transitionImpl);
//					if(td != null){
//						td.setAssigneeExpression(oldAssigneeExpression);
//					}
					currentActivity.getOutgoingTransitions().remove(transitionImpl);
					destionationTask.getIncomingTransitions().remove(transitionImpl);
				}
				execution.takeAll(transitions, Collections.<ActivityExecution> emptyList());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
