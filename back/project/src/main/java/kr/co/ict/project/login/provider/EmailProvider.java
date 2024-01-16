package kr.co.ict.project.login.provider;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailProvider {
    
    private final JavaMailSender javaMailSender;

    // 메일 제목
    private final String SUBJECT = "[] 인증메일입니다.";

    // 전송받을 인증번호도 받기
    public boolean sendCertificationMail(String email, String certificationNumber){

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getCertificationMesage(certificationNumber);

            // 어디로 메일을 보낼거냐
            messageHelper.setTo(email);
            messageHelper.setSubject(SUBJECT);
            messageHelper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }

        return true;

    }

    private String getCertificationMesage(String certificationNumber){
        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align: center;'>[--------] 인증메일 </h1>";
        certificationMessage += "<h3 style='text-align: center;'> 인증코드 : <strong style='font-size: 32px; letter-spacing: 8px;'>" + certificationNumber + "</strong></h3>";
        return certificationMessage;
    }

}
