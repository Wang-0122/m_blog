package com.mtons.mblog.modules.repository;

import com.mtons.mblog.modules.entity.PushMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wang
 * @title: aaaa
 * @projectName mblog
 * @description: TODO
 * @date 2019/4/13  21:49
 */
@Repository
public interface PushMailRepository extends JpaRepository<PushMail, Long>, JpaSpecificationExecutor<PushMail> {

    List<PushMail> findByStatus(Integer status);
}
