package com.sibro.mailsender.controller;

import com.sibro.mailsender.utils.CompressUtil;
import com.sibro.mailsender.utils.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SendMailController {
    @Autowired
    private SendMailUtil mailUtil;//发送邮件工具类
    @Autowired
    private CompressUtil compressUtil;//压缩包工具类

    @GetMapping("/hello")
    public String sayHi() {
        return "hello world!";
    }


    @GetMapping("/send")
    public String Hello( Model model){
        //发送人
        String to = "******";
        List<String> ccList = new ArrayList<>();
        ccList.add("******");
        ccList.add("******");
        //抄送
        String[] cc = ccList.toArray(new String[ccList.size()]);
        //标题
        String title = "SpringBoot发送邮件功能测试!";
        //正文
        String content = "这是一场SpringBoot发送邮件功能测试!";
        //附件
        List<String> files = new ArrayList<>();
        files.add("******");
        files.add("******");
        String[] filepath =files.toArray(new String[files.size()]);
        mailUtil.sendMail(to,title,content,cc,filepath);
        //压缩文件测试
//        try {
//            CompressUtil.generateFile("C:\\Users\\Administrator\\Desktop\\20190916数据","zip");
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        return "index";
    }
}
