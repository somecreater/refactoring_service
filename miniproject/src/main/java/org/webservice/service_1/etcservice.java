package org.webservice.service_1;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;


public interface etcservice {
	public int createcertification();
	public void createjms(String email, int number);
	public void createmessage(String email, String title, String text);
	public boolean varify(int number);
}
