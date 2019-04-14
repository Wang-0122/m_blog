package com.mtons.mblog.modules.service;

import com.mtons.mblog.modules.entity.PushMail;

import java.util.List;

/**
 * @author wang
 * @title: aaaa
 * @projectName mblog
 * @description: TODO
 * @date 2019/4/13  21:49
 */
public interface PushMailService {

    Long save(PushMail pushMail);

    Long pushMail(List<PushMail> list,String path);

    List<PushMail> findByStaus(Integer status);
}
