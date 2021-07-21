package com.narpath.batchmail;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;

public class MailProcessor implements ItemProcessor<Employee, SimpleMailMessage> {

	@Override
	public SimpleMailMessage process(Employee item) throws Exception {
		// TODO Auto-generated method stub
		
		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setFrom("empsupport@gmail.com");	
		msg.setTo(item.getEmail());
		msg.setSubject("Welcome "+item.getName());
		msg.setText(item.toString());
		return msg;
	}

}
