package za.co.blacklightcode.emailclient.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;

@Service
public class  EmailService {
    Logger log = LoggerFactory.getLogger(EmailService.class);

    @Value("${spring.mail.username}")
    private String senderAccount;

    @Value("${email.recipient}")
    private String recipient;

    private final JavaMailSenderImpl javaMailSender;

    public EmailService(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail() throws MessagingException {
        log.info("Sending Email");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setText("TRYING TO SEND EMAILS", true);

        messageHelper.setFrom(senderAccount);
        messageHelper.setSubject("WILL YOU GET THIS EMAIL? ");
        messageHelper.setSentDate(new Date());
        messageHelper.setTo(recipient);
        javaMailSender.send(messageHelper.getMimeMessage());
        log.info(" Message:  {}",messageHelper.getMimeMessage());
    }

}
