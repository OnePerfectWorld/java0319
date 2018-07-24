package com.atguigu.gmall.usermanage.controller;



import com.atguigu.gmall.bean.UserInfo;
import com.atguigu.gmall.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    //查找全部员工信息
    @RequestMapping(value = "/findAllUser",method = RequestMethod.GET)
    @ResponseBody
    public List<UserInfo> findAllUser(){
        List<UserInfo> allUser = userInfoService.findAllUser();
        return allUser;
    }


    //添加员工信息
    @RequestMapping(value = "/addUser",method = RequestMethod.PUT)
    public void addUserInfo(UserInfo userInfo){
        System.out.print(userInfo);
        userInfoService.addUserInfo(userInfo);

    }

    //修改用户信息
    @RequestMapping(value = "/updateUser",method = RequestMethod.PUT)
    public void updateUserInfo(UserInfo userInfo){
        userInfoService.updateUserInfo(userInfo);

    }


    //删除用户
    @RequestMapping(value = "deleteUser",method = RequestMethod.DELETE)
    public void deleteUser(String id){
        userInfoService.deleteUer(id);


    }


}
