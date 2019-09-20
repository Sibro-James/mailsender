package com.sibro.mailsender.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class SendMailUtil {
    @Autowired
    private JavaMailSender mailSender; //框架自带的

    /**发送邮件
     * @param to   收件人
     * @param subject 邮件主题
     * @param content 正文
     * @param cc 抄送
     * @param filePath 附件
     */
    public void sendMail(String to, String subject, String content,String[] cc, String[] filePath){
        MimeMessage message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom("shibo@wondersgroup.com");
            helper.setTo(to);
            helper.setSubject(subject);
            if (cc!=null){
                helper.setCc(cc);
            }
            helper.setText(content);
            //获取附件和附件名
            FileSystemResource file=null;
            String fileName="";
            if(filePath!=null){
                //添加多个附件可以使用多条
                // helper.addAttachment(fileName,file);
                for (String f : filePath){
                    file = new FileSystemResource(new File(f));
                    fileName = f.substring(f.lastIndexOf(File.separator));
                    helper.addAttachment(fileName,file);
                }
            }
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("收件人为："+to+" 的邮件发送失败！");
        }

    }
}
