package org.webservice.service_1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.eclipse.angus.mail.util.logging.MailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import jakarta.mail.internet.MimeUtility;
import lombok.RequiredArgsConstructor;

//이미 탈퇴된 아이디
//이메일 서버오류로 구현 실패
//나중에 추후 구현
@Service
@RequiredArgsConstructor
public class etcserviceImpl implements etcservice{
	
	private JavaMailSender mailSender;
	private static int varifynum;
	private final String sender="springservice1111@naver.com";
	
	@Override
	public int createcertification() {
		int valienum= (int) (Math.random()*(9000000))+1000000;
		return valienum;
	}

	@Override
	public void createmessage(String email, String title, String text) {
		/*
		mailSender=javasnd();
		//ApplicationContext context=new ClassPathXmlApplicationContext("classpath:servlet-context.xml");
		//mailSender=(JavaMailSenderImpl)context.getBean("mailSender");
		MimeMessage msg=mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper=new MimeMessageHelper(msg,"UTF-8");
			helper.setFrom(sender);
			helper.setTo(email);
			helper.setSubject(title);
			helper.setText(text,true);
			System.out.println(msg.getSubject());
			mailSender.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public JavaMailSender javasnd() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties properties=new Properties();
        
        /*mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(sender);
        mailSender.setPassword("pyqe czva ejxj qgbf");
 
        Properties props = mailSender.getJavaMailProperties();
        props.setProperty("mail.transport.protocol", "smtp");
 
        props.setProperty("mail.smtp.auth", String.valueOf(auth));
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.ssl.trust", "*");
        props.setProperty("mail.smtp.ssl.enable", "false");
        return mailSender;
       
        mailSender.setHost("smtp.naver.com");
        mailSender.setPort(465);
        mailSender.setUsername("springservice1111@naver.com");
        mailSender.setPassword("test1234");
        //properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        //properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.ssl.enable", "true"); // SSL 활성화
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.naver.com");
        //properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        mailSender.setJavaMailProperties(properties);
         */
        return mailSender;
        
	}
	
	
	@Override
	public void createjms(String email, int number) {
		/*
        //JavaMailSender sendertest = javasnd();
		this.varifynum=number;
		String title="웹서비스 인증 메일입니다.";
		String text;
		text="<div>"+"<div><h1>웹서비스로 부터 인증메일이 왔습니다. 아래의 숫자를 확인하고 사이트에 입력해주세요</h1></div><br>"
			 +"<div><h4>인증번호</h4>"+"<h1>"+number+"</h1></div><br>"
			 +"<div><h4>관리자 이메일: springwebservice3131@gmail.com </h4></div>"
			 +"</div>";
		
		//text="test입니다";
		createmessage(email,title,text);
		*/
	}
	
	@Override
	public boolean varify(int number) {
		if(number==varifynum) {
			return true;
		}else {
			return false;
		}
	}

}
