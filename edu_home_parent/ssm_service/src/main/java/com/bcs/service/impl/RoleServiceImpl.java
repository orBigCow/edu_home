package com.bcs.service.impl;

import com.bcs.dao.RoleMapper;
import com.bcs.domain.*;
import com.bcs.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    //查询所有角色
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    //添加角色
    @Override
    public void saveRole(Role role) {
        //封装信息
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        //调用mapper
        roleMapper.saveRole(role);
    }

    //修改角色
    @Override
    public void updateRole(Role role) {
        //封装数据
        role.setUpdatedTime(new Date());
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        //调用mapper
        roleMapper.updateRole(role);
    }

    //根据角色ID查询对应的菜单ID
    @Override
    public List<Integer> findMenuByRoleId(int roleId) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleId);
        return menuByRoleId;
    }

    //为角色分配菜单
    @Override
    public void RoleContextMenu(RoleMenuVO roleMenuVO) {
        //根据角色ID清空中间表信息
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());
        //重新分配菜单列表
        for (Integer menuId : roleMenuVO.getMenuIdList()) {
            //封装数据
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setRoleId(roleMenuVO.getRoleId());
            role_menu_relation.setMenuId(menuId);
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            //调用mapper
            roleMapper.RoleContextMenu(role_menu_relation);
        }
    }

    //删除角色
    @Override
    public void deleteRole(int roleId) {
        //清空关联关系
        roleMapper.deleteRoleContextMenu(roleId);
        //删除角色
        roleMapper.deleteRole(roleId);
    }

    //获取当前角色拥有的资源分类以及对应的资源信息
    @Override
    public List<ResourceCategory> findResourceByRoleId(int roleId) {
        //查询角色对应的资源分类信息
        List<ResourceCategory> categoryList = roleMapper.findResourceCategoryByRoleId(roleId);
        for (ResourceCategory resourceCategory : categoryList) {
            //查询每个资源分类下角色拥有的资源信息  并封装到资源分类中
            List<Resource> resourceList = roleMapper.findResourceListByRoleIdAndCategoryId(roleId,resourceCategory.getId());
            resourceCategory.setResourceList(resourceList);
        }
        return categoryList;
    }

    //为角色分配资源
    @Override
    public void roleContextResource(RoleResourceVO roleResourceVO) {
        //清空角色资源原有关系
        roleMapper.deleteRoleContextResource(roleResourceVO.getRoleId());
        //重新建立关系
        for (Integer resourceId : roleResourceVO.getResourceIdList()) {
            //封装数据
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            roleResourceRelation.setResourceId(resourceId);
            roleResourceRelation.setRoleId(roleResourceVO.getRoleId());
            Date date = new Date();
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);
            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedBy("system");
            //调用mapper
            roleMapper.RoleContextResource(roleResourceRelation);
        }
    }


}
