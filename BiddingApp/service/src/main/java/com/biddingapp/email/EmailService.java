package com.biddingapp.email;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

	public static String sendEmail(String receiver) {

		final String username = "biddingapp.lucian@gmail.com";
		final String password = "777biddingapp";
		Properties props = getProprieties();

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		String activationLink = UUID.randomUUID().toString();

		try {
			initializaMessage(receiver, username, session, activationLink);
			
		} catch (MessagingException e) {
			
			throw new RuntimeException(e);
		}
		return activationLink;
	}

	
	private static void initializaMessage(String receiver, final String username, Session session, String activationLink) throws MessagingException{
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(receiver));
		message.setSubject("Email Validation");
		message.setText("Validate your account by accessing this link: " +
				"\n\n http://localhost:8080/web/activate.xhtml?key="+activationLink);

		Transport.send(message);
	}

	private static Properties getProprieties() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		return props;
	}
}