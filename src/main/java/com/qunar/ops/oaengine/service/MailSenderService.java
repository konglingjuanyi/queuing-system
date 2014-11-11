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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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

	/**
	 * 发送邮件
	 * 
	 * @param from
	 * @param to
	 * @param cc
	 * @param title
	 * @param content
	 * @throws MessagingException 
	 */
	public void sender(String from, String[] to, String[] cc, String title, String content) {
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


}
