package com.square.mall.common.util;

import com.square.mall.common.constant.SymbolConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Objects;

/**
 * 邮件工具类
 *
 * @author Gencent
 * @date 2019/8/20
 */
@Slf4j
@Component
public class MailUtil {

    @Resource
    private JavaMailSender mailSender;

    private static JavaMailSender staticMailSender = null;

    /**
     * 静态方法初始化
     */
    @PostConstruct
    public void init(){
        staticMailSender = mailSender;
    }


    /**
     * @param from 发件人 (与yml配置名称一致)
     * @param to  收件人，多个以逗号隔开
     * @param subject 标题
     * @param text 文本内容
     * @param cc 抄送，多个以逗号隔开
     * @param attachment 附件全路径名
     * @return 是否发送成功
     */
    public static Boolean sendEmail(String from , String to, String subject, String text, String cc, String attachment){

        // 解决附件名称过长导致的附件名称乱码问题
        System.setProperty("mail.mime.splitlongparameters", "false");
        MimeMessage message = staticMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            if (StringUtil.isNotBlank(to)) {
                String[] toArr = to.split(SymbolConstant.COMMA);
                helper.setTo(toArr);
            }
            helper.setSubject(subject);
            helper.setText(text,true);
            helper.setSentDate(new Date());
            if (StringUtil.isNotBlank(cc)) {
                String[] ccArr = cc.split(SymbolConstant.COMMA);
                helper.setCc(ccArr);
            }

            // 添加附件
            if(StringUtil.isNotBlank(attachment)){
                FileSystemResource file = new FileSystemResource(attachment);
                helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            }
            staticMailSender.send(message);
        }catch (Exception e){
            log.error("sendEmail 发送邮件错误 e: ", e);
            return false;
        }
        return true ;
    }

}
