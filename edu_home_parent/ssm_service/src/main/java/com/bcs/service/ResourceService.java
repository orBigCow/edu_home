package com.bcs.service;

import com.github.pagehelper.PageInfo;
import com.bcs.domain.Resource;
import com.bcs.domain.ResourceVO;

public interface ResourceService {

    //资源分页&多条件查询
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO);

    //添加资源信息
    public void saveResource(Resource resource);

    //修改资源信息
    public void updateResource(Resource resource);

    //删除资源信息
    public void deleteResource(int id);

}
