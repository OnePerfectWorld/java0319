package com.atguigu.gmall.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class BaseAttrInfo implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String attrName;
    @Column
    private String catalog3Id;
    @Transient
    private List<BaseAttrValue> baseAttrValueList;


    public List<BaseAttrValue> getBaseAttrValueList() {
        return baseAttrValueList;
    }

    public void setBaseAttrValueList(List<BaseAttrValue> baseAttrValueList) {
        this.baseAttrValueList = baseAttrValueList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getCatalog3Id() {
        return catalog3Id;
    }

    public void setCatalog3Id(String catalog3Id) {
        this.catalog3Id = catalog3Id;
    }

    @Override
    public String toString() {
        return "BaseAttrInfo{" +
                "id='" + id + '\'' +
                ", attrName='" + attrName + '\'' +
                ", catalog3Id='" + catalog3Id + '\'' +
                ", baseAttrValueList=" + baseAttrValueList +
                '}';
    }
}