package com.example.demo.mapper;

import com.example.demo.entity.Page;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    //普通用户登录判断
    public User login(String number,String password);
    //注册账号是否已存在判断
    public User register1(String number);
    //账号注册
    public void register2(User user);
    //普通用户查询信息
    public List<User> findUserByName(String number);
    //普通用户删除账号
    public void delete(String number);
    //修改信息
    public void upname(String name,String number);
    public void upsex(String sex,String number);
    public void update(String sex,String number);
    public void uptel(String tel,String number);
    public void uppwd(String tel,String number);
    //管理员查询
    public List<User> findAll(Page<User> page);
    public int queryCount();

}
