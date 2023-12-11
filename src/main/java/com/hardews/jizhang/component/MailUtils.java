package com.hardews.jizhang.component;
import com.hardews.jizhang.dto.MailDto;
import com.hardews.jizhang.utils.R;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtils {
    //邮件发送者
    @Value("${spring.mail.username}")
    private String MAIL_SENDER;

    @Autowired
    private JavaMailSender javaMailSender;

    public R sendSimpleMail(MailDto mailBean) {
        try {
            SimpleMailMessage mailMessage= new SimpleMailMessage();
            mailMessage.setFrom(MAIL_SENDER);
            mailMessage.setTo(mailBean.getRecipient());
            mailMessage.setSubject(mailBean.getSubject());
            mailMessage.setText(mailBean.getContent());

            javaMailSender.send(mailMessage);
            return R.ok("邮箱验证码发送成功");
        } catch (Exception e) {
            LogManager.getLogger().error(e);
            return R.error(400,"邮箱验证码发送失败" );
        }
    }
}
