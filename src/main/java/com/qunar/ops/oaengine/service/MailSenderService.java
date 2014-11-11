package com.qunar.ops.oaengine.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.qunar.ops.oaengine.domain.QMail;

@Component
@Configuration
public class MailSenderService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${mail.host}")
	String host;
	@Value("${mail.port}")
	String port;
	@Value("${mail.username}")
	String username;
	@Value("${mail.password}")
	String password;
	@Value("${mail.smtp.auth}")
	String auth;
	@Value("${mail.smtp.starttls.enable}")
	String starttls;
	@Autowired
	private RabbitTemplate amqpTemplate;
	
	/**
	 * 发送邮件
	 */
	public void senderMail(QMail mail){
		String from = mail.getFrom();
		String[] to = mail.getTo();
		String[] cc = mail.getCc();
		String title = mail.getTitle();
		String content = mail.getContent();
		final String username = this.username;
		final String password = this.password;

		Properties props = new Properties();
		props.put("mail.smtp.auth", this.auth);
		props.put("mail.smtp.starttls.enable", this.starttls);
		props.put("mail.smtp.host", this.host);
		props.put("mail.smtp.port", this.port);

		Session session = Session.getDefaultInstance(props);
		if (this.auth.equals("true")) {
			session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});
		}
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(StringUtils.join(to, ",")));
			if(cc != null){
				message.setRecipients(Message.RecipientType.CC,
						InternetAddress.parse(StringUtils.join(cc, ",")));
			}
			message.setSubject(title);
			message.setText(content);
			Transport.send(message);
		}catch (MessagingException e) {
			logger.error("send mail error!!!", e);
		}
	}

	public void sender(String from, String[] to, String[] cc, String title, String content){
		try {
			QMail mail = new QMail();
			mail.setCc(cc);
			mail.setContent(content);
			mail.setFrom(from);
			mail.setTo(to);
			mail.setTitle(title);
			this.amqpTemplate.convertAndSend("oa.sendmail", mail);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
