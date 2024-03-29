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
    private final String SUBJECT = "냉장고든램지와 함께 하시겠습니까?";

    // 이메일을 전송하기 위한 method
    public boolean sendCertificationMail(String email, String certificationNumber) {

        try {
            // imeMessage 및 MimeMessageHelper 클래스를 사용하여 이메일을 구성
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getCertificationMesage(certificationNumber);

            // 어디로 메일을 보낼거냐
            messageHelper.setTo(email);
            // 제목
            messageHelper.setSubject(SUBJECT);
            // 메일내용 설정하고 HTML 형식으로 지정
            messageHelper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }

        return true;

    }

    // 전달된 인증 번호를 포함한 HTML 형식의 이메일 내용을 생성
    private String getCertificationMesage(String certificationNumber) {
        String certificationMessage = "";
        certificationMessage += "<div class='col-sm-10 col-xl-8'>";
        certificationMessage += "<div class='ms-3'>";
        certificationMessage += "<h2 style='text-align: center;' class='mt-5'>↓↓누르고 냉장고든램지와 함께하세요.↓↓</h2>";
        certificationMessage += "</div>";
        certificationMessage += "<div>";
        certificationMessage += "<img src='../assets/img/gom.png' class='img-fluid' style='margin-left: 400px'>";
        certificationMessage += "<div class='ms-3'>";
        certificationMessage += "<h3 style='text-align: center;margin-left: -300px;'> 인증코드 : <strong style='font-size: 32px; letter-spacing: 8px;'>"
                + certificationNumber + "</strong></h3>";
        certificationMessage += "</div>";
        certificationMessage += "</div>";
        certificationMessage += "</div>";

        // certificationMessage += "<h1 style='text-align: center;'>↓↓누르고 냉장고든램지와
        // 함께하세요.↓↓";
        // certificationMessage += "<h3 style='text-align: center;'> 인증코드 : <strong
        // style='font-size: 32px; letter-spacing: 8px;'>"
        // + certificationNumber + "</strong></h3>";
        return certificationMessage;
    }

}
