package com.qunar.ops.oaengine.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.oaengine.dao.DelegationMapper;
import com.qunar.ops.oaengine.datasource.Read;
import com.qunar.ops.oaengine.exception.AgentAlreadyExistsException;
import com.qunar.ops.oaengine.model.Delegation;
import com.qunar.ops.oaengine.model.DelegationExample;
import com.qunar.ops.oaengine.model.DelegationExample.Criteria;




@Component
public class DelegationManager {
	
	@Autowired(required=true)
	private DelegationMapper delegationMapper;
	
	public void appendDelegation(String master, List<String> agents) throws AgentAlreadyExistsException{
		if(agents != null)for(String agent : agents){
			if(this.delegationIsExist(master, agent)){
				throw new AgentAlreadyExistsException("代理人已经存在", this.getClass());
			}
			Delegation delegation = new Delegation();
			delegation.setAgentUserId(agent);
			delegation.setMasterUserId(master);
			delegation.setIsDelete(false);
			delegation.setTs(new Date());
			this.delegationMapper.insert(delegation);
		}
	}
	
	public void deleteDelegation(String master, List<String> agents) {
		if(agents != null)for(String agent : agents){
			Delegation delegation = new Delegation();
			delegation.setAgentUserId(agent);
			delegation.setMasterUserId(master);
			delegation.setIsDelete(true);
			delegation.setTs(new Date());
			DelegationExample e = new DelegationExample();
			e.createCriteria().andMasterUserIdEqualTo(master).andAgentUserIdEqualTo(agent);
			this.delegationMapper.updateByExample(delegation, e);
		}
	}
	
	@Read
	public List<Delegation> findDelegationByMaster(String master){
		DelegationExample e = new DelegationExample();
		Criteria c = e.createCriteria();
		if(master != null){
			c.andMasterUserIdEqualTo(master);
		}
		c.andIsDeleteEqualTo(false);
		return this.delegationMapper.selectByExample(e);
	}
	
	public Boolean delegationIsExist(String master, String agent){
		DelegationExample e = new DelegationExample();
		Criteria c = e.createCriteria();
		c.andMasterUserIdEqualTo(master);
		c.andAgentUserIdEqualTo(agent);
		c.andIsDeleteEqualTo(false);
		return this.delegationMapper.countByExample(e) > 0 ? true : false;
	}

}
