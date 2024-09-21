package org.webservice.service_1;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;

import lombok.extern.log4j.Log4j;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

//이메일 서버오류로 구현 실패
//나중에 추후 구현
@Log4j
@Service
public class mailserviceImpl implements mailservice{
	
	
	//private JavaMailSenderImpl mailSender;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public int getrandnumber() {
		Random random=new Random();
		int number=random.nextInt(1000000-100000+1);
		
		return number;
	}
	
	@Override
	public boolean makemail(String toEmail,int number) {
		
		try {
		//String strnum=Integer.toString(number);
		//sendEmail(toEmail,"인증용 메일입니다 번호를 보고 입력해 주세요",strnum);
		return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public void sendEmail(String toEmail, String subject, String body)  {
		/*
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage);
		try {
			mimeMessageHelper.setTo(toEmail);
		} catch (MessagingException e) {
			System.out.println("메일 to 설정 오류");
			e.printStackTrace();
		}
		try {
			mimeMessageHelper.setSubject(subject);
		} catch (MessagingException e) {
			System.out.println("메일 subject 설정 오류");
			e.printStackTrace();
		}
		try {
			mimeMessageHelper.setText(body, false);
		} catch (MessagingException e) {
			System.out.println("메일 body 설정 오류");
			e.printStackTrace();
		}
		
		System.out.println("test:"+"\n\n\n\n\n");
		System.out.println(mailSender.toString()+"\n\n\n\n\n");
		mailSender.send(mimeMessage);
		*/
		
		//SimpleMailMessage message=new SimpleMailMessage();
		
		/*
        Properties properties=new Properties();
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("");
        mailSender.setPassword("");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(properties);
        */
        
        /*
		message.setFrom("");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		mailSender.send(message);*/
		System.out.println("메일 전송 성공");
		
		
	}
}
