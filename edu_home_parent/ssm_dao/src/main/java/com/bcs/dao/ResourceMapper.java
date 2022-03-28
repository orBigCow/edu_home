package com.bcs.dao;

import com.bcs.domain.Resource;
import com.bcs.domain.ResourceVO;

import java.util.List;

public interface ResourceMapper {

    //资源分页&多条件查询
    public List<Resource> findAllResource(ResourceVO resourceVO);

    //添加资源信息
    public void saveResource(Resource resource);

    //修改资源信息
    public void updateResource(Resource resource);

    //删除资源信息
    public void deleteResource(int id);

}
