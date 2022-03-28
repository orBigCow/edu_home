package com.bcs.service;

import com.bcs.domain.ResourceCategory;
import com.bcs.domain.Role;
import com.bcs.domain.RoleMenuVO;
import com.bcs.domain.RoleResourceVO;

import java.util.List;

public interface RoleService {

    //查询所有角色
    public List<Role> findAllRole(Role role);

    //添加角色信息
    public void saveRole(Role role);

    //修改角色信息
    public void updateRole(Role role);

    //根据角色ID查询关联菜单ID
    public List<Integer> findMenuByRoleId(int roleId);

    //为角色分配菜单列表
    public void RoleContextMenu(RoleMenuVO roleMenuVO);

    //删除角色
    public void deleteRole(int roleId);

    //获取当前角色拥有的资源分类以及对应的资源信息
    public List<ResourceCategory> findResourceByRoleId(int roleId);

    //为角色分配资源
    public void roleContextResource(RoleResourceVO roleResourceVO);

}
