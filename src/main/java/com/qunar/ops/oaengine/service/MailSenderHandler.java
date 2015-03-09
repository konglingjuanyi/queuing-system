package com.qunar.ops.oaengine.service;

import com.lmax.disruptor.EventHandler;

public class MailSenderHandler implements EventHandler<MailEvent> {
	private MailSenderService service;
	public MailSenderHandler(MailSenderService service) {
		this.service = service;
	}
	
	@Override
	public void onEvent(MailEvent event, long sequence, boolean endOfBatch) throws Exception {
		if(event.getMail() == null) return;
		this.service.senderMail(event.getMail());
	}

}
