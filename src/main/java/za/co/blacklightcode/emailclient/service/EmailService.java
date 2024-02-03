package za.co.blacklightcode.emailclient.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Value("${spring.mail.password}")
    private String senderAccountPassword;

    @Value("${spring.mail.host}")
    private String emailHost;

    @Value("${spring.mail.port}")
    private Integer port=587;

    @Value("${email.recipient}")
    private String recipient;

    @Value("${spring.mail.protocol}")
    private String protocol;

    public void sendEmail() throws MessagingException {
        log.info("Sending Email");
        JavaMailSenderImpl mailSender = getJavaMailSender(senderAccount,senderAccountPassword);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setText("TRYING TO SEND EMAILS", true);

        messageHelper.setFrom(senderAccount);
        messageHelper.setSubject("WILL YOU GET THIS EMAIL? ");
        messageHelper.setSentDate(new Date());
        messageHelper.setTo(recipient);
        mailSender.send(messageHelper.getMimeMessage());
        log.info(" Message:  {}",messageHelper.getMimeMessage());
    }

    /**
     * getJavaMailSender
     * @return
     */
    public JavaMailSenderImpl getJavaMailSender(String senderAccount,String senderAccountPassword) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailHost);
        mailSender.setPort(port);
        mailSender.setUsername(senderAccount);
        mailSender.setPassword(senderAccountPassword);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        // uncomment the bellow for mail debug
        //props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", emailHost);
        return mailSender;
    }
}
