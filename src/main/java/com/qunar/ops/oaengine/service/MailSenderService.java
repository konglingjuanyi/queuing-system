package com.qunar.ops.oaengine.service;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class MailSenderService {

	private @Value("${email.host}")
	String emailHost;

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
	public void sender(final String from, final String[] to, final String[] cc, final String title, final String content) {
		JavaMailSenderImpl ms = new JavaMailSenderImpl();
		ms.setHost("mail.163.com");
		ms.setUsername("nuby");
		ms.setPassword("19800112");
		Properties pp = new Properties();
		pp.setProperty("mail.debug", "true");
		pp.setProperty("mail.smtp.auth", "true");
		pp.setProperty("mail.smtp.starttls.enable", "true");
		pp.setProperty("mail.transport.protocol", "smtp");
		ms.setJavaMailProperties(pp);
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setTo(to);
				message.setSubject(title);
				message.setCc(cc);
				message.setFrom(from);
				message.setText(content, true);
			}
		};
		ms.send(preparator);
	}
	
}
