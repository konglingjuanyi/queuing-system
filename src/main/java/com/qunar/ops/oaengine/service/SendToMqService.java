package com.qunar.ops.oaengine.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.oaengine.result.BaseResult;
import com.qunar.ops.oaengine.result.SendToMQInfo;

@Component
public class SendToMqService {

	@Autowired
	private RabbitTemplate amqpTemplate;

	public static final String[] ALLOW_SYS_NAMES = { "account_apply" };

	public void sendToMq(String sysName, SendToMQInfo info) throws Exception {
		List<String> tempList = Arrays.asList(ALLOW_SYS_NAMES);
		if (tempList.contains(sysName)) {
			amqpTemplate.convertAndSend("account", "account",
					BaseResult.getSuccessResult(info));
		}
	}

}
