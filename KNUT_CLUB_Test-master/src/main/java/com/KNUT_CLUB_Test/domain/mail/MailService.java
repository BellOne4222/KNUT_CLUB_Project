package com.KNUT_CLUB_Test.domain.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public void sendMail(String emails, String generatedString) throws Exception {

        /* 메일 발송 */
        try {
            MailHandler mail = new MailHandler(javaMailSender);
            mail.setMultiTo(emails);
            mail.setSubject("KNUTCLUB 인증번호 발송");
            mail.setText(generatedString);
            mail.send();
            log.debug("메일 발송 완료");
        } catch (Exception e) {
            log.error("send error : " + e.toString());
        }

    }
}
