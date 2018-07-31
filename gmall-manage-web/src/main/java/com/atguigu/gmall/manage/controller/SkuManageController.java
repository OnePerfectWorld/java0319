package com.atguigu.gmall.manage.controller;



import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.SkuInfo;
import com.atguigu.gmall.bean.SpuImage;
import com.atguigu.gmall.bean.SpuSaleAttr;
import com.atguigu.gmall.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SkuManageController {

    @Reference
    private ManageService manageService;

    //查询商品图片
    @RequestMapping(value = "/spuImageList",method = RequestMethod.GET)
    @ResponseBody
    public List<SpuImage> getSpuImageList(String spuId){
        List<SpuImage> spuImageList = manageService.getSpuImageList(spuId);
        return spuImageList;

    }

    //查询商品属性
    @RequestMapping(value = "/spuSaleAttrList",method = RequestMethod.GET)
    @ResponseBody
    public List<SpuSaleAttr> getSpuSaleAttrList(String spuId){
        List<SpuSaleAttr> spuSaleAttrList = manageService.getSpuSaleAttrList(spuId);
        return spuSaleAttrList;

    }


    //保存skuInfo信息
    @RequestMapping("/saveSku")
    @ResponseBody
    public String saveSku(SkuInfo skuInfo){
        manageService.saveSku(skuInfo);

        return "success";
    }

}
