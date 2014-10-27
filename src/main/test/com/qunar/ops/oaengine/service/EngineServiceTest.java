package com.qunar.ops.oaengine.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qunar.ops.oaengine.result.ProcessInstanceDetailInfo;
import com.qunar.ops.oaengine.result.ProcessInstanceInfo;
import com.qunar.ops.oaengine.service.EngineService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class EngineServiceTest {
	@Autowired
	private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private EngineService engineService;
    @Rule
    public ActivitiRule activitiSpringRule;
    
    //@Test
    public void traceTest(){
    	ProcessDefinitionEntity pd = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition("demo_1:1:2904");
    	Map<String, TaskDefinition> tds = pd.getTaskDefinitions();
    	for(String key : tds.keySet()){
    		ActivityImpl act = pd.findActivity(key);
    		List<PvmTransition> incomings = act.getIncomingTransitions();
    		for(PvmTransition incoming : incomings){
    			PvmActivity source = incoming.getSource();
    			this.findIncomingTasks(source);
    		}
    		System.out.println("------------------------"+key);
    	}
    	List<HistoricTaskInstance> list = this.historyService.createHistoricTaskInstanceQuery().processInstanceBusinessKey("").taskDefinitionKey("").orderByHistoricTaskInstanceEndTime().list();
    	for(HistoricTaskInstance ht : list){
    		ht.getId();
    	}
    }
    
    public void findIncomingTasks(PvmActivity act){
    	List<PvmTransition> incomings = act.getIncomingTransitions();
    	if(incomings.size() == 0) return;
		for(PvmTransition incoming : incomings){
			PvmActivity source = incoming.getSource();
			if("userTask".equals(source.getProperty("type"))){
				System.out.println(source.getId());
			}else{
				System.out.println(source.getProperty("type"));
				this.findIncomingTasks(source);
			}
		}
    	
    }

    //@Test
    @Deployment
    public void deploymentProcessTest() {
        // TODO 测试部署流程
    }

    //@Test
    public void test() {
        List<HistoricProcessInstance> finish = historyService
                .createHistoricProcessInstanceQuery().finished().list();
        for (HistoricProcessInstance h : finish) {
            System.out.println("结束的流程：" + h.getStartUserId());
        }
    }
    
    //@Test
    public void findeProcessInstanceByOwnerTest(){
    	List<ProcessInstanceDetailInfo> infos = (List<ProcessInstanceDetailInfo>)this.engineService.findeHistoryProcessInstanceByOwner("physical_request", "nuby.zhang", new HashMap<String, Object>());
    	for(ProcessInstanceInfo info : infos){
    		System.out.println(info.getProcessInstanceId());
    	}
    }

}
