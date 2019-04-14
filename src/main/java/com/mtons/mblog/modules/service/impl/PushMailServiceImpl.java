package com.mtons.mblog.modules.service.impl;

import com.mtons.mblog.modules.entity.PushMail;
import com.mtons.mblog.modules.repository.PushMailRepository;
import com.mtons.mblog.modules.service.PushMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wang
 * @title: aaaa
 * @projectName mblog
 * @description: TODO
 * @date 2019/4/13  21:49
 */
@Service
@Transactional
public class PushMailServiceImpl implements PushMailService {


    @Autowired
    private PushMailRepository pushMailRepository;

    @Autowired
    private JavaMailSender mailSender;

    /**
     *
     * @param pushMail
     * @return
     */
    @Override
    public Long save(PushMail pushMail) {
        pushMailRepository.save(pushMail);
        return pushMail.getId();
    }

    /**
     * 更博异步推送邮件
     * @param list
     * @param path
     * @return
     */
    @Override
    @Async
    public Long pushMail(List<PushMail> list,String path) {
        if (list != null && !list.isEmpty()){
            for (PushMail mail : list) {

                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("1021558365@qq.com");
                message.setSubject("博客更新通知!");

                message.setText("亲爱的"+mail.getName()+"您好！您关注的博客更新啦！请点击链接  https://www.yilukkk.com"+path+"  访问吧!");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        message.setTo(mail.getEmail());
                        mailSender.send(message);
                    }
                }).start();

            }
        }
        return null;
    }

    @Override
    public List<PushMail> findByStaus(Integer status) {
        return pushMailRepository.findByStatus(status);
    }
}
