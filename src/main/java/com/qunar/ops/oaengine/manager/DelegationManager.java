package com.qunar.ops.oaengine.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.oaengine.dao.DelegationMapper;
import com.qunar.ops.oaengine.model.Delegation;
import com.qunar.ops.oaengine.model.DelegationExample;
import com.qunar.ops.oaengine.model.DelegationExample.Criteria;

@Component
public class DelegationManager {
	
	@Autowired
	private DelegationMapper mapper;
	
	public void appendDelegation(String master, List<String> agents){
		if(agents != null)for(String agent : agents){
			Delegation delegation = new Delegation();
			delegation.setAgentUserId(agent);
			delegation.setMasterUserId(master);
			delegation.setIsDelete(false);
			delegation.setTs(new Date());
			this.mapper.insert(delegation);
		}
	}
	
	public void deleteDelegation(String master, List<String> agents){
		if(agents != null)for(String agent : agents){
			Delegation delegation = new Delegation();
			delegation.setAgentUserId(agent);
			delegation.setMasterUserId(master);
			delegation.setIsDelete(true);
			delegation.setTs(new Date());
			DelegationExample e = new DelegationExample();
			e.createCriteria().andMasterUserIdEqualTo(master).andAgentUserIdEqualTo(agent);
			this.mapper.updateByExample(delegation, e);
		}
	}
	
	public List<Delegation> findDelegationByMaster(String master){
		DelegationExample e = new DelegationExample();
		Criteria c = e.createCriteria();
		if(master != null){
			c.andMasterUserIdEqualTo(master);
		}
		c.andIsDeleteEqualTo(false);
		return this.mapper.selectByExample(e);
	}

}
