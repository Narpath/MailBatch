package com.narpath.batchmail;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SendMail implements ItemWriter<SimpleMailMessage> {
	
	private JavaMailSender mailSender;
	
	public SendMail(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void write(List<? extends SimpleMailMessage> messages) throws Exception {
		
		messages.stream().forEach((message)->mailSender.send(message));
	}

}
