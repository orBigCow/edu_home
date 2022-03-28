package com.bcs.service;

import com.bcs.domain.Role;
import com.bcs.domain.User;
import com.bcs.domain.UserVO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface UserService {

    //用户分页&多条件查询
    public PageInfo findAllUserByPage(UserVO userVO);

    //修改用户状态
    public void updateUserStatus(int id, String status);

    //用户登录
    public User login(User user) throws Exception;

    //分配角色(回显)
    public List<Role> findUserRoleById(int userId);

    //分配角色
    public void userContextRole(UserVO userVO);

    //获取用户拥有的菜单权限
    public Map getUserPermissions(int userId);

}
