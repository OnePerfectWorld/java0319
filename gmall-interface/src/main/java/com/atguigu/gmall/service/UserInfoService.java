package com.atguigu.gmall.service;


import com.atguigu.gmall.bean.UserInfo;

import java.util.List;

public interface UserInfoService {


    List<UserInfo>  findAllUser();


    void addUserInfo(UserInfo userInfo);


    void updateUserInfo(UserInfo userInfo);

    void deleteUer(String id);

}
