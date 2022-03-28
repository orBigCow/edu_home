package com.bcs.controller;

import com.github.pagehelper.PageInfo;
import com.bcs.domain.PromotionAd;
import com.bcs.domain.PromotionAdVO;
import com.bcs.domain.ResponseResult;
import com.bcs.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    //分页查询所有广告信息
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO){
        PageInfo pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有广告成功", pageInfo);
        return responseResult;
    }

    //图片上传接口
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult PromotionAdUpload(MultipartFile file, HttpServletRequest request) throws IOException {
        //判断文件是否为空
        if (file.isEmpty()){
            throw new RuntimeException();
        }
        //2.获取项目部署路径 // D:\apache-tomcat-8.5.56\webapps\ssm-web\
        String realPath = request.getServletContext().getRealPath("/");
        // D:\apache-tomcat-8.5.56\webapps\
        String webappPath = realPath.substring(0, realPath.indexOf("ssm-web"));
        //原始文件名
        String originalFilename = file.getOriginalFilename();
        //新文件名
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String uploadPath = webappPath + "upload/";
        File filePath = new File(uploadPath, newFileName);
        //如果目录不存在，就创建目录
        if (!filePath.exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录:" + filePath);
        }
        file.transferTo(filePath);
        Map<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);
        ResponseResult responseResult = new ResponseResult(true, 200,"图片上传成功",map);
        return responseResult;
    }

    //新建or修改广告信息
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        System.out.println("test");
        if (promotionAd.getId() == null){
            //新建广告信息
            promotionAdService.savePromotionAd(promotionAd);
            ResponseResult responseResult = new ResponseResult(true, 200, "新建广告信息成功", null);
            return responseResult;
        }else {
            //修改广告信息
            promotionAdService.updatePromotionAd(promotionAd);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改广告信息成功", null);
            return responseResult;
        }
    }

    //根据ID回显广告信息
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "回显广告信息成功", promotionAd);
        return responseResult;
    }

    //广告状态上下线
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(int id, int status){
        promotionAdService.updatePromotionAdStatus(id, status);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status", status);
        ResponseResult responseResult = new ResponseResult(true, 200, "广告状态上下线成功", map);
        return responseResult;
    }


}
