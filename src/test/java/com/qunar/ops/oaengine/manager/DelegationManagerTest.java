package com.qunar.ops.oaengine.manager;

import java.util.ArrayList;
import java.util.List;






import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qunar.ops.oaengine.manager.GroupManager.GroupInfo;
import com.qunar.ops.oaengine.model.Delegation;
import com.qunar.ops.oaengine.model.Group;
import com.qunar.ops.oaengine.model.GroupMember;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class DelegationManagerTest {
	
	@Autowired
	private DelegationManager manager;
	
	@Test
	public void appendDelegationTest(){
		List<String> agents = new ArrayList<String>();
		agents.add("nuby.zhang");
		this.manager.appendDelegation("tao.feng", agents);
	}

}
