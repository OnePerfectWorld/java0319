package com.atguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.manage.mapper.*;
import com.atguigu.gmall.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;


import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {


    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;
    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;
    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;
    @Autowired
    private SpuInfoMapper spuInfoMapper;
    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;
    @Autowired
    private SpuImageMapper spuImageMapper;
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;



    //查询一级分类信息
    @Override
    public List<BaseCatalog1> getBaseCatalog1() {
        List<BaseCatalog1> baseCatalog1List = baseCatalog1Mapper.selectAll();

        return baseCatalog1List ;
    }

    //查询二级分类信息
    @Override
    public List<BaseCatalog2> getBaseCatalog2(String baseCatalog1Id) {
        //Example example = new Example(BaseCatalog2.class);
        //example.createCriteria().andEqualTo("catalog1Id",baseCatalog1Id);
        //List<BaseCatalog2> baseCatalog2List = baseCatalog2Mapper.selectByExample(example);
        BaseCatalog2 baseCatalog2 =new BaseCatalog2();

        baseCatalog2.setCatalog1Id(baseCatalog1Id);
        List<BaseCatalog2> baseCatalog2List = baseCatalog2Mapper.select(baseCatalog2);
        return baseCatalog2List;
    }

    //查询三级分类信息
    @Override
    public List<BaseCatalog3> getBaseCatalog3(String baseCatalog2Id) {
        BaseCatalog3 baseCatalog3 =new BaseCatalog3();
        baseCatalog3.setCatalog2Id(baseCatalog2Id);
        List<BaseCatalog3> baseCatalog3List = baseCatalog3Mapper.select(baseCatalog3);
        return baseCatalog3List;
    }


    //根据三级分类id查询频台属性信息
    @Override
    public List<BaseAttrInfo> getBaseAttrInfoList(String baseCatalog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(baseCatalog3Id);
        List<BaseAttrInfo> baseAttrInfoList = baseAttrInfoMapper.select(baseAttrInfo);
        return baseAttrInfoList;
    }


    //添加和更新
    @Override
    public void saveBasbaseAttrInfo(BaseAttrInfo baseAttrInfo) {
        if(baseAttrInfo.getId()!=null&&baseAttrInfo.getId().length()>0){
            baseAttrInfoMapper.updateByPrimaryKey(baseAttrInfo);
        }else{
            baseAttrInfo.setId(null);
            baseAttrInfoMapper.insert(baseAttrInfo);
        }

        //修改时，要把属性所对应的属性值全部删除
        BaseAttrValue attrValue = new BaseAttrValue();
        attrValue.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(attrValue);

        List<BaseAttrValue> baseAttrValueList = baseAttrInfo.getBaseAttrValueList();
        if(baseAttrValueList!=null&&baseAttrValueList.size()>0){
            for (BaseAttrValue baseAttrValue : baseAttrValueList) {
                if(baseAttrValue.getId()==null||baseAttrValue.getId().length()==0){
                    baseAttrValue.setId(null);
                }
                baseAttrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insert(baseAttrValue);
            }
        }



    }

    //根据主键id查询BaseAttrInfo
    @Override
    public BaseAttrInfo getBaseAttrInfo(String attrId) {
        //查询平台属性
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectByPrimaryKey(attrId);
        //查询频台属性值
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(attrId);//? baseAttrInfo.getId()==attrId????
        List<BaseAttrValue> baseAttrValueList = baseAttrValueMapper.select(baseAttrValue);

        //组装baseAttrInfo
        baseAttrInfo.setBaseAttrValueList(baseAttrValueList);
        return baseAttrInfo;
    }


    //级联删除属性名信息和属性值信息
    @Override
    public void deleteAttrInfo(String baseAttrInfoId) {
       //删除属性名信息
        baseAttrInfoMapper.deleteByPrimaryKey(baseAttrInfoId);
        //删除属性值信息

        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(baseAttrInfoId);
        Example example = new Example(BaseAttrValue.class);
        example.createCriteria().andEqualTo("attrId",baseAttrValue);

        baseAttrValueMapper.deleteByExample(example);


    }

    //查询spu_info信息
    @Override
    public List<SpuInfo> getSpuInfoList(String baseCatalog3Id) {
        SpuInfo spuInfo = new SpuInfo();

        spuInfo.setCatalog3Id(baseCatalog3Id);
        List<SpuInfo> spuInfoList = spuInfoMapper.select(spuInfo);
        return spuInfoList;
    }

    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        //保存spu_info表里的信息
        if(spuInfo.getId()==null||spuInfo.getId().length()==0){
            //插入
            spuInfo.setId(null);
            spuInfoMapper.insertSelective(spuInfo);
        }else{

            spuInfoMapper.updateByPrimaryKey(spuInfo);
        }
        //删除图片
        SpuImage spuImage = new SpuImage();
        spuImage.setSpuId(spuInfo.getId());
        spuImageMapper.delete(spuImage);

        //保存图片信息
        List<SpuImage> imageList = spuInfo.getSpuImageList();
        for (SpuImage image : imageList) {
            if(image.getId()!=null && image.getId().length()==0){
                image.setId(null);
            }
            image.setSpuId(spuInfo.getId());
            spuImageMapper.insertSelective(image);

        }
        //删除销售属性
        SpuSaleAttr spuSaleAttr = new SpuSaleAttr();
        spuSaleAttr.setSpuId(spuInfo.getId());
        spuSaleAttrMapper.delete(spuSaleAttr);

        //删除销售属性值
        SpuSaleAttrValue spuSaleAttrValue = new SpuSaleAttrValue();
        spuSaleAttrValue.setSpuId(spuInfo.getId());
        spuSaleAttrValueMapper.delete(spuSaleAttrValue);


        //保存销售属性
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        for (SpuSaleAttr saleAttr : spuSaleAttrList) {
            if(saleAttr.getId()!=null && saleAttr.getId().length()==0){
                saleAttr.setId(null);
            }
            saleAttr.setSpuId(spuInfo.getId());
            spuSaleAttrMapper.insertSelective(saleAttr);
            // 保存属性值
            List<SpuSaleAttrValue> spuSaleAttrValueList = saleAttr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue saleAttrValue : spuSaleAttrValueList) {
                if(saleAttrValue.getId()!=null && saleAttrValue.getId().length()==0){
                    saleAttrValue.setId(null);
                }
                saleAttrValue.setSpuId(spuInfo.getId());
                spuSaleAttrValueMapper.insertSelective(saleAttrValue);

            }


        }







    }
}
