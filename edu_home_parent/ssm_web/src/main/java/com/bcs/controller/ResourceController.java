package com.bcs.controller;

import com.github.pagehelper.PageInfo;
import com.bcs.domain.Resource;
import com.bcs.domain.ResourceVO;
import com.bcs.domain.ResponseResult;
import com.bcs.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    //资源分页&多条件查询
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVO resourceVO){
        PageInfo<Resource> pageInfo = resourceService.findAllResource(resourceVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "资源分页&多条件查询成功", pageInfo);
        return responseResult;
    }

    //添加or修改资源信息
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        if (resource.getId() == null){
            //添加资源信息
            resourceService.saveResource(resource);
            ResponseResult responseResult = new ResponseResult(true, 200, "添加资源信息成功", "");
            return responseResult;
        } else {
            //修改资源信息
            resourceService.updateResource(resource);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改资源信息成功", "");
            return responseResult;
        }
    }

    //删除资源信息
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(int id){
        resourceService.deleteResource(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除资源成功", "");
        return responseResult;
    }


}
