package com.atguigu.gmall.order.controller;

import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private UserAddressService userAddressService;

    @RequestMapping("/getUserAddress")
    @ResponseBody
    public List<UserAddress> getUserAddressById(HttpServletRequest request){
        String userId = request.getParameter("userId");
        List<UserAddress> userAddressList =  userAddressService.getUserAddressByUserId(userId);
        return userAddressList;
    }




}
