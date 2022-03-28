package com.bcs.dao;

import com.bcs.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    //查询所有广告信息
    public List<PromotionAd> findAllPromotionAdByPage();

    //新建广告信息
    public void savePromotionAd(PromotionAd promotionAd);

    //修改广告信息
    public void updatePromotionAd(PromotionAd promotionAd);

    //回显广告信息
    public PromotionAd findPromotionById(int id);

    //广告状态上下线
    public void updatePromotionAdStatus(PromotionAd promotionAd);

}
