package com.atguigu.gmall.manage.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.BaseSaleAttr;
import com.atguigu.gmall.bean.SpuInfo;
import com.atguigu.gmall.service.ManageService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SpuManageController {

    @Reference
    private ManageService manageService;

    @RequestMapping("/spuListPage")
    public String toSpuListPage(){


        return "spuListPage";
    }

    //查询spu_info信息
    @RequestMapping("/spuList")
    @ResponseBody
    public List<SpuInfo> spuList(String baseCatalog3Id){
        List<SpuInfo> spuInfoList = manageService.getSpuInfoList(baseCatalog3Id);
        return spuInfoList;

    }

    //获取全部销售属性列表
    @RequestMapping("/baseSaleAttrList")
    @ResponseBody
    public  List<BaseSaleAttr> getBaseSaleAttrList(){
        List<BaseSaleAttr> baseSaleAttrList = manageService.getBaseSaleAttrList();
        return baseSaleAttrList;
    }

    //保存商品信息
    @RequestMapping(value ="/saveSpuInfo",method = RequestMethod.POST)
    @ResponseBody
    public String  saveSpuInfo(SpuInfo spuInfo){
        manageService.saveSpuInfo(spuInfo);


        return "success";
    }





}
