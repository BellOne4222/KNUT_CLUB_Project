package com.KNUT_CLUB_Test.domain.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public class MailHandler {

    private final JavaMailSender javaMailSender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;

    public MailHandler(JavaMailSender javaMailSender) throws MessagingException {
        this.javaMailSender = javaMailSender;
        message = javaMailSender.createMimeMessage();

        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    }

    public void send() throws Exception {
        try {
            javaMailSender.send(message);
        } catch(Exception e) {
            //던지려고 하는 익셉션 vo 만들어놓음. e.printStackTrace(); 로 던져도 됨.
            e.printStackTrace();
        }
    }

    /* 발신자 이메일(보낸 사람) */
    public void setForm(String email) throws UnsupportedEncodingException, MessagingException {
        messageHelper.setFrom(email);
    }

    //수신자 이메일(받는 사람)
    public void setTo(String email) throws MessagingException {
        messageHelper.setTo(email);
    }

    //수신자 이메일(다수 받는 사람)
    public void setMultiTo(String email) throws MessagingException {
        messageHelper.setTo(InternetAddress.parse(email));
    }

    //메일 제목
    public void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
    }

    //메일 내용
    public void setText(String text) throws MessagingException {
        //		  , html형식 적용여부o
        messageHelper.setText(text, true);
    }

}
