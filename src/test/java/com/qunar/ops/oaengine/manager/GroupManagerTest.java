package com.qunar.ops.oaengine.manager;

import java.util.List;




import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qunar.ops.oaengine.manager.GroupManager.GroupInfo;
import com.qunar.ops.oaengine.model.Group;
import com.qunar.ops.oaengine.model.GroupMember;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class GroupManagerTest {
	
	@Autowired
	private GroupManager manager;
	
	@Test
	public void appendMemberTest(){
		GroupMember member = new GroupMember();
		this.manager.appendMember("tb_check", "nuby.zhang");
		this.manager.appendMember("fin_check", "nuby.zhang");
		this.manager.appendMember("fin_check_mdd", "nuby.zhang");
		this.manager.appendMember("cashier", "nuby.zhang");
		List<GroupInfo> infos = this.manager.getGroup("tb_check");
		GroupInfo info = infos.get(0);
		Assert.assertEquals(info.getGroupKey(), "tb_check");
	}

}
