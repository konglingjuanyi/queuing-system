package com.qunar.ops.oaengine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class MailSenderService {

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

		System.out.println(this.auth + "===" + this.username + "===" + this.password);
		
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
			List<Address> addrs = new ArrayList<Address>();
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(StringUtils.join(to, ",")));
			message.setRecipients(Message.RecipientType.CC,
					InternetAddress.parse(StringUtils.join(cc, ",")));
			message.setSubject("A testing mail header !!!");
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");
			Transport.send(message);
			System.out.println("Done");
		}catch (MessagingException e) {
			// throw new RuntimeException(e);
			System.out.println("Username or Password are incorrect ... exiting !");
		}

	}

	public static void main(String[] args) {
		String from = "abc";
		String[] to = { "nuby@sohu.com" };
		String[] cc = { "nuby@sohu.com" };
		
		ApplicationContext c = new ClassPathXmlApplicationContext(new String[]{"spring.xml"});
		MailSenderService ser = c.getBean(MailSenderService.class);
		
		ser.sender(from, to, cc, "abc", "bac");
		
	}

}
