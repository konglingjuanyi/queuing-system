package com.qunar.ops.workflow.engine.command;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.NeedsActiveTaskCmd;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmException;
import org.activiti.engine.runtime.Execution;

public class TurnBackTaskCmd extends NeedsActiveTaskCmd<Void> {
	private static final long serialVersionUID = -1886082581314486951L;

	private Map<String, String> destinationTasks;
	private Map<String, String> flowActivity;
	private String reason;

	public TurnBackTaskCmd(String taskId, Map<String, String> destinationTasks, Map<String, String> flowActivity, String reason) {
		super(taskId);
		this.destinationTasks = destinationTasks;
		this.flowActivity = flowActivity;
		this.reason = reason;
	}

	@Override
	protected Void execute(CommandContext commandContext, TaskEntity task) {
		ExecutionEntity execution = task.getExecution();
		
		if (execution != null && !destinationTasks.isEmpty()) {
			List<Execution> executions = execution.getEngineServices().getRuntimeService().createExecutionQuery().processInstanceId(task.getProcessInstanceId()).list();
			if(executions.size() > 1){
				List<Execution> tmps = new ArrayList<Execution>();
				for(Execution exec : executions){
					if(this.flowActivity.containsKey(exec.getActivityId())){
						tmps.add(exec);
					}
				}
				for(Execution exec : tmps){
					ExecutionEntity _exec = (ExecutionEntity)exec;
					List<TaskEntity> _tasks = _exec.getTasks();
					for(TaskEntity _task : _tasks){
						_task.setVariableLocal("turnback_reason", reason==null?"turnback":reason);
						Context.getCommandContext().getTaskEntityManager().deleteTask(_task, reason==null?"turnback":reason, false);
						_exec.removeTask(_task);
					}
				}
			}else{
				task.setVariableLocal("turnback_reason", reason==null?"turnback":reason);
				Context.getCommandContext().getTaskEntityManager().deleteTask(task, reason==null?"turnback":reason, false);
				execution.removeTask(task);
			}
			signal(execution, destinationTasks);
		}
		return null;
	}
	
	
	private void signal(ExecutionEntity execution, Map<String, String> destinationTasks) {
		try {
			Method method = ExecutionEntity.class.getDeclaredMethod("ensureActivityInitialized");
			method.setAccessible(true);
			method.invoke(execution);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//SignallableActivityBehavior activityBehavior = (SignallableActivityBehavior) execution.getActivity().getActivityBehavior();
		try {
			TurnBackTaskActivityBehavior mutab = new TurnBackTaskActivityBehavior();
			mutab.signal(execution, destinationTasks);
			/*if (activityBehavior instanceof UserTaskActivityBehavior) {
				TurnBackTaskActivityBehavior mutab = new TurnBackTaskActivityBehavior();
				mutab.setActivityBehavior((UserTaskActivityBehavior) activityBehavior);
				mutab.signal(execution, destinationTasks, null, null);
			} else {
				activityBehavior.signal(execution, null, null);
			}*/
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new PvmException("couldn't process signal '" + ""
					+ "' on activity '" + execution.getActivity().getId()
					+ "': " + e.getMessage(), e);
		}
	}

}
