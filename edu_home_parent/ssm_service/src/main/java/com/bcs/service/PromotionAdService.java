package com.bcs.service;

import com.github.pagehelper.PageInfo;
import com.bcs.domain.PromotionAd;
import com.bcs.domain.PromotionAdVO;

public interface PromotionAdService {

    //查询所有广告信息
    public PageInfo findAllPromotionAdByPage(PromotionAdVO promotionAdVO);

    //新建广告信息
    public void savePromotionAd(PromotionAd promotionAd);

    //修改广告信息
    public void updatePromotionAd(PromotionAd promotionAd);

    //根据ID回显广告信息
    public PromotionAd findPromotionAdById(int id);

    //广告状态上下线
    public void updatePromotionAdStatus(int id, int status);

}
