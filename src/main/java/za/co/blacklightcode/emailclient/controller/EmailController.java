package za.co.blacklightcode.emailclient.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.blacklightcode.emailclient.service.EmailService;

/**
 *
 */
@RestController
public class EmailController {
    private static final Logger log = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @PostMapping("/SendEmail")
    public ResponseEntity<String> archiveEmail() {
        try {
            emailService.sendEmail();
        }
        catch (Exception e){
            log.info("Exception: {}",e.getMessage());
            return new ResponseEntity<>("ISE", HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>("Email Sent", HttpStatusCode.valueOf(200));
    }



}
