package com.bcs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bcs.dao.ResourceMapper;
import com.bcs.domain.Resource;
import com.bcs.domain.ResourceVO;
import com.bcs.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    //资源分页&多条件查询
    @Override
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO) {
        PageHelper.startPage(resourceVO.getCurrentPage(), resourceVO.getPageSize());
        List<Resource> allResource = resourceMapper.findAllResource(resourceVO);
        PageInfo<Resource> pageInfo = new PageInfo<>(allResource);
        return pageInfo;
    }

    //添加资源信息
    @Override
    public void saveResource(Resource resource) {
        //补全数据
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");
        //调用mapper
        resourceMapper.saveResource(resource);
    }

    //修改资源信息
    @Override
    public void updateResource(Resource resource) {
        //补全数据
        resource.setUpdatedTime(new Date());
        resource.setUpdatedBy("system");
        //调用mapper
        resourceMapper.updateResource(resource);
    }

    //删除资源信息
    @Override
    public void deleteResource(int id) {
        resourceMapper.deleteResource(id);
    }



}
