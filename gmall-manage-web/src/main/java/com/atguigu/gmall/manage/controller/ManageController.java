package com.atguigu.gmall.manage.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.service.ManageService;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class ManageController {


    @Reference
    private ManageService manageService;


    @RequestMapping("/index")
    public String toIndex(){


        return "index";
    }


    @RequestMapping("/attrListPage")
    public String toAttrListPage(){

        return "attrListPage";
    }


    //查询一级分类信息
    @RequestMapping("/getBaseCatalog1")
    @ResponseBody
    public List<BaseCatalog1> getBaseCatalog1(){
        List<BaseCatalog1> baseCatalog1List = manageService.getBaseCatalog1();
        return baseCatalog1List;

    }


    //查询二级分类信息
    @RequestMapping("/getBaseCatalog2")
    @ResponseBody
    public List<BaseCatalog2> getBaseCatalog2(@RequestParam String baseCatalog1Id){

        List<BaseCatalog2> baseCatalog2List = manageService.getBaseCatalog2(baseCatalog1Id);
        return baseCatalog2List;

    }


    //查询三级分类信息
    @RequestMapping("/getBaseCatalog3")
    @ResponseBody
    public List<BaseCatalog3> getBaseCatalog3(@RequestParam String baseCatalog2Id){
        List<BaseCatalog3> baseCatalog3List = manageService.getBaseCatalog3(baseCatalog2Id);
        return baseCatalog3List;
    }

    //
    //根据三级分类getBaseCatalog3Id查询平台属性信息
    @RequestMapping("/getBaseAttrInfoList")
    @ResponseBody
    public List<BaseAttrInfo> getBaseAttrInfoList(@RequestParam("catalog3Id") String baseCatalog3Id){
        List<BaseAttrInfo> baseAttrInfoList = manageService.getBaseAttrInfoList(baseCatalog3Id);
        return baseAttrInfoList;
    }


    //保存BaseAttrInfo的信息
    @RequestMapping(value = "/saveBasbaseAttrInfo",method = RequestMethod.POST)
    @ResponseBody
    public String saveBasbaseAttrInfo(BaseAttrInfo baseAttrInfo){

        manageService.saveBasbaseAttrInfo(baseAttrInfo);
        return "success";
    }

    //查询属性值列表
    @RequestMapping("/getBaseAttrValueList")
    @ResponseBody
    public List<BaseAttrValue> getBaseAttrValueList(String attrId){
        BaseAttrInfo baseAttrInfo = manageService.getBaseAttrInfo(attrId);
        List<BaseAttrValue> baseAttrValueList = baseAttrInfo.getBaseAttrValueList();
        return baseAttrValueList;
    }

    //删除属性的方法
    @RequestMapping(value = "/deleteAttrInfo",method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAttrInfo(@RequestBody String baseAttrInfoId ){

        manageService.deleteAttrInfo(baseAttrInfoId);

        return "success";
    }


}
