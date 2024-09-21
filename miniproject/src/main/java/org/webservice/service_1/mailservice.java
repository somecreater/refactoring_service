package org.webservice.service_1;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

public interface mailservice {
	
	public int getrandnumber();
	public boolean makemail(String toEmail,int number);
	public void sendEmail(String toEmail, String subject, String body);
}
