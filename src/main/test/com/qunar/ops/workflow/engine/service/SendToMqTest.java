package com.qunar.ops.workflow.engine.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qunar.ops.workflow.oaengine.result.BaseResult;
import com.qunar.ops.workflow.oaengine.result.SendToMQInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class SendToMqTest extends AbstractJUnit4SpringContextTests{

	@Resource
	private RabbitTemplate amqpTemplate;
	
	@Test
	public void test() {
		SendToMQInfo info = new SendToMQInfo();
		info.setStartedUser("yongnian.jiang");
		amqpTemplate.convertAndSend("account_apply", "", BaseResult.getSuccessResult(info));
	}

}
