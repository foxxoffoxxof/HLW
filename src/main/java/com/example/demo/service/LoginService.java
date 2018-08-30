package com.example.demo.service;

import com.example.demo.entity.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    public UserMapper userMapper;

    //判断用户登录
    public User login(User users){
        users=this.userMapper.login(users.getNumber(),users.getPassword());
        return users;
    }

    //注册时判断账号是否已存在
    public User register1(String number){
        return userMapper.register1(number);
    }

    //账号注册
    public void register2(User users){
        userMapper.register2(users);
    }

    //普通用户查询
    public List<User> findByName(String number) {
        return userMapper.findUserByName(number);
    }

    //普通用户删除信息
    public void delete(String number){
        userMapper.delete(number);
    }

    //修改信息
    public void upname(String name,String number){userMapper.upname(name,number);}
    public void upsex(String sex,String number){userMapper.upsex(sex,number);}
    public void update(String date,String number){userMapper.update(date,number);}
    public void uptel(String tel,String number){userMapper.uptel(tel,number);}
    public void uppwd(String password,String number){userMapper.uppwd(password,number);}

    //管理员查询

    public Page<User> getAllComponent(Page<User> page) {
        List<User> list = userMapper.findAll(page);
        int queryCount = userMapper.queryCount();
        page.setTotalDataCount(queryCount);
        page.setList(list);
        return page;
    }

}
