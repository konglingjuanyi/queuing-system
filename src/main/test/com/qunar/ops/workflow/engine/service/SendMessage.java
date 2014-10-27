package com.qunar.ops.workflow.engine.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SendMessage {

	public static void main(String[] agrs) throws Exception {
		String path = "spring.xml";
	    AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(path);
	    RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
	    template.convertAndSend("Hello, world!");
	    Thread.sleep(1000);
	    ctx.destroy();
	}
}
