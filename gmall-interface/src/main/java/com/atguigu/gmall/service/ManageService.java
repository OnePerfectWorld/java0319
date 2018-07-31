package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.*;


import java.util.List;


public interface ManageService {



    List<BaseCatalog1> getBaseCatalog1();

    List<BaseCatalog2> getBaseCatalog2(String baseCatalog1Id);

    List<BaseCatalog3> getBaseCatalog3(String baseCatalog2Id);

    List<BaseAttrInfo> getBaseAttrInfoList(String baseCatalog3Id);

    void saveBasbaseAttrInfo(BaseAttrInfo baseAttrInfo);

    BaseAttrInfo getBaseAttrInfo(String attrId);

    void deleteAttrInfo(String baseAttrInfoId);


    List<SpuInfo> getSpuInfoList(String baseCatalog3Id);

    List<BaseSaleAttr> getBaseSaleAttrList();

    void saveSpuInfo(SpuInfo spuInfo);

    List<SpuImage> getSpuImageList(String spuId);

    List<SpuSaleAttr> getSpuSaleAttrList(String spuId);

    void saveSku(SkuInfo skuInfo);
}

