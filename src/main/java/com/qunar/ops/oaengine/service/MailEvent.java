package com.qunar.ops.oaengine.service;

import com.lmax.disruptor.EventFactory;
import com.qunar.ops.oaengine.domain.QMail;

public class MailEvent {
	private QMail mail;
	
	public QMail getMail(){
		return this.mail;
	}
	
	public void setMail(QMail mail){
		this.mail = mail;
	}
	
	public final static EventFactory<MailEvent> EVENT_FACTORY = new EventFactory<MailEvent>() {
		public MailEvent newInstance() {
			return new MailEvent();
		}
	};
}
