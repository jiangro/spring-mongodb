package com.jr.controller;

import com.jr.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * @author jiangrong
 */
@RestController
@RequestMapping("/test")
public class TestControlller {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void saveUser(){
        UserEntity userEntity1 = new UserEntity();
        UserEntity userEntity2 = new UserEntity();
        UserEntity userEntity3 = new UserEntity();
        userEntity1.setUid("111");
        userEntity1.setUsername("用户1");
        userEntity1.setPassword("密码1");
        userEntity2.setUid("222");
        userEntity2.setUsername("用户2");
        userEntity2.setPassword("密码2");
        userEntity3.setUid("333");
        userEntity3.setUsername("用户3");
        userEntity3.setPassword("密码3");
        mongoTemplate.save(userEntity1);
        mongoTemplate.save(userEntity2);
        mongoTemplate.save(userEntity3);
    }

    @GetMapping("/select")
    public String test(String userId){
        Query query=new Query(Criteria.where("uid").is(userId));
        List<UserEntity> list = mongoTemplate.find(query, UserEntity.class);
        return list.toString();
    }


}
