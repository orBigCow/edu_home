package com.bcs.dao;

import com.bcs.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    //查询所有角色(条件)
    public List<Role> findAllRole(Role role);

    //添加角色
    public void saveRole(Role role);

    //修改角色
    public void updateRole(Role role);

    //根据角色ID查询关联菜单ID
    public List<Integer> findMenuByRoleId(int roleId);

    //根据角色ID清空角色菜单中间表数据
    public void deleteRoleContextMenu(int roleId);

    //为角色重新分配菜单
    public void RoleContextMenu(Role_menu_relation role_menu_relation);

    //删除角色
    public void deleteRole(int roleId);

    //根据角色id查询对应的资源分类信息
    public List<ResourceCategory> findResourceCategoryByRoleId(int roleId);

    //根据角色id查询对应的资源分类下的资源信息
    public List<Resource> findResourceListByRoleIdAndCategoryId(@Param("roleId") int roleId, @Param("categoryId") int categoryId);

    //根据角色ID清空角色资源中间表数据
    public void deleteRoleContextResource(int roleId);

    //重新建立角色资源之间的关系
    public void RoleContextResource(RoleResourceRelation resourceRelation);

}
