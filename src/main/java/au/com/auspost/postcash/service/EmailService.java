package au.com.auspost.postcash.service;

import au.com.auspost.postcash.domain.MoneyOrder;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.text.NumberFormat;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void send(MoneyOrder moneyOrder) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(moneyOrder.getPayeeEmail());
            helper.setReplyTo("davidrh@optusnet.com.au");
            helper.setFrom("davidrh@optusnet.com.au");
            helper.setSubject("Post cash voucher");
            helper.setText("<html><body><p>Name " + moneyOrder.getPayeeName() + "</p>" +
                    "<p>Amount " + NumberFormat.getCurrencyInstance().format(moneyOrder.getAmount()) + "</p>" +
                    "<img src='cid:identifier1234'></body></html>", true);
            byte[] image = Base64.decodeBase64(moneyOrder.getBarcodeBase64());
            helper.addInline("identifier1234", new ByteArrayResource(image), moneyOrder.getBarcodeMimeType());
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException(e);
        }
    }
}
