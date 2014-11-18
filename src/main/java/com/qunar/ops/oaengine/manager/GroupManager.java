package com.qunar.ops.oaengine.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.oaengine.dao.GroupMapper;
import com.qunar.ops.oaengine.dao.GroupMemberMapper;
import com.qunar.ops.oaengine.model.Group;
import com.qunar.ops.oaengine.model.GroupExample;
import com.qunar.ops.oaengine.model.GroupExample.Criteria;
import com.qunar.ops.oaengine.model.GroupMember;
import com.qunar.ops.oaengine.model.GroupMemberExample;

@Component
public class GroupManager {
	@Autowired(required=true)
	private GroupMapper groupMapper;
	
	@Autowired(required=true)
	private GroupMemberMapper groupMemberMapper;
	
	static Map<String, String> groupNames = new HashMap<String, String>();
	{
		groupNames.put("fin_check", "财务报销审核组");
		groupNames.put("fin_check_mdd", "财务报销审核组-目的地");
		groupNames.put("tb_check", "HRBP-TB报销审核组");
		groupNames.put("cashier", "财务报销出纳组");
	}
	
	//@PostConstruct
	protected void init() {
		int count = groupMapper.countByExample(new GroupExample());
		if(count > 0 ) return;
		for(Entry<String, String> g : groupNames.entrySet()){
			Group group = new Group();
			group.setGroupKey(g.getKey());
			group.setGroupName(g.getValue());
			this.groupMapper.insert(group);
		}
		
	}
	
	public void appendMember(String groupKey, String userId){
		GroupMemberExample e = new GroupMemberExample();
		e.createCriteria().andGroupKeyEqualTo(groupKey).andMemberUserIdEqualTo(userId);
		int count = this.groupMemberMapper.countByExample(e);
		if(count > 0) return;
		GroupMember member = new GroupMember();
		member.setGroupKey(groupKey);
		member.setMemberUserId(userId);
		this.groupMemberMapper.insert(member);
	}
	
	public void removeMember(String groupKey, String userId){
		GroupMemberExample e = new GroupMemberExample();
		e.createCriteria().andGroupKeyEqualTo(groupKey).andMemberUserIdEqualTo(userId);
		this.groupMemberMapper.deleteByExample(e);
	}
	
	public List<GroupInfo> getGroup(String groupKey){
		List<GroupInfo> infos = new ArrayList<GroupManager.GroupInfo>();
		GroupExample ge = new GroupExample();
		Criteria c = ge.createCriteria();
		if(groupKey != null){
			c.andGroupKeyEqualTo(groupKey);
		}
		List<Group> groups = groupMapper.selectByExample(ge);
		if(groups != null)for(Group group : groups){
			GroupInfo info = new GroupInfo(group);
			GroupMemberExample e = new GroupMemberExample();
			e.createCriteria().andGroupKeyEqualTo(info.getGroupKey());
			info.setMembers(this.groupMemberMapper.selectByExample(e));
			infos.add(info);
		}
		return infos;
	}
	
	public class GroupInfo extends Group{
		private List<GroupMember> members;
		
		public GroupInfo(Group group) {
			super.setGroupKey(group.getGroupKey());
			super.setGroupName(group.getGroupName());;
		}

		public List<GroupMember> getMembers() {
			return members;
		}

		public void setMembers(List<GroupMember> members) {
			this.members = members;
		}
		
		
		
	}
}
