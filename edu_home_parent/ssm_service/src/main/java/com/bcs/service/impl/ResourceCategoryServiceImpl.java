package com.bcs.service.impl;

import com.bcs.dao.ResourceCategoryMapper;
import com.bcs.domain.ResourceCategory;
import com.bcs.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    //查询所有资源分类
    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> allResourceCategory = resourceCategoryMapper.findAllResourceCategory();
        return allResourceCategory;
    }

    //添加资源分类
    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {
        //补全数据
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");
        //调用mapper
        resourceCategoryMapper.saveResourceCategory(resourceCategory);
    }

    //修改资源分类
    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {
        //补全数据
        resourceCategory.setUpdatedTime(new Date());
        resourceCategory.setUpdatedBy("system");
        //调用mapper
        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    //删除资源分类
    @Override
    public void deleteResourceCategory(int id) {
        resourceCategoryMapper.deleteResourceCategoryTest(id);
    }
}
