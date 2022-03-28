package com.bcs.dao;

import com.bcs.domain.*;

import java.util.List;

public interface UserMapper {

    //用户分页&多条件查询
    public List<User> findAllUserByPage(UserVO userVO);

    //修改用户状态
    public void updateUserStatus(User user);

    //用户登录
    public User login(User user);

    //根据用户id清空用户角色中间表数据
    public void deleteUserContextRole(int userId);

    //重新建立用户角色中间表关系
    public void userContextRole(User_Role_relation user_role_relation);

    //1.分配角色(回显)
    public List<Role> findUserRoleById(int userId);

    //2.根据用户对应的角色ids获取对应的顶级菜单
    public List<Menu> findParentMenuByRoleId(List<Integer> roleIds);

    //3.根据父菜单id获取关联的子菜单
    public List<Menu> findSubMenuByParentId(int parentId);

    //4.根据用户对应的角色ids查询对应的资源信息
    public List<Resource> findResourceByRoleId(List<Integer> roleIds);

}
