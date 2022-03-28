package com.bcs.controller;

import com.bcs.domain.ResourceCategory;
import com.bcs.domain.ResponseResult;
import com.bcs.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    //查询所有资源分类
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true,200,"查询所有资源分类成功", allResourceCategory);
    }

    //添加&修改资源分类
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){
        if (resourceCategory.getId() == null){
            //添加资源分类信息
            resourceCategoryService.saveResourceCategory(resourceCategory);
            ResponseResult responseResult = new ResponseResult(true, 200, "添加资源分类信息成功", null);
            return responseResult;
        }else {
            //修改资源分类信息
            resourceCategoryService.updateResourceCategory(resourceCategory);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改资源分类信息成功", null);
            return responseResult;
        }
    }

    //删除资源分类信息
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(int id){
        resourceCategoryService.deleteResourceCategory(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除资源分类成功", null);
        return responseResult;
    }

}
