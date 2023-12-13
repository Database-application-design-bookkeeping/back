package com.hardews.jizhang.utils;

import com.hardews.jizhang.dto.MailDto;
import lombok.Value;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class SendQQMailUtil {

    private static Properties properties = new Properties();

    static {
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "14.18.245.164");// 主机名//这是qq的地址，这样写方便服务器解析
        properties.put("mail.smtp.port", "465");// 端口号
        properties.put("mail.smtp.socketFactory.port", "465");//设置ssl端口
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.localhost", "127.0.0.1");//主机映射
    }
    public static R sendEmail(MailDto mailDto) throws MessagingException {
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("twentyue@qq.com"));
        // 设置收件人邮箱地址
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailDto.getRecipient()));
        // 设置邮件标题
        message.setSubject(mailDto.getSubject());


        // 设置邮件内容
        message.setText(mailDto.getContent());
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect("twentyue@qq.com", "ityfazccegdgdfji");
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        return R.ok("发送成功");
    }
}