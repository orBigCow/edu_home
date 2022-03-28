package com.bcs.service.impl;

import com.bcs.domain.*;
import com.bcs.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bcs.dao.UserMapper;
import com.bcs.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //用户分页&多条件查询
    @Override
    public PageInfo findAllUserByPage(UserVO userVO) {
        PageHelper.startPage(userVO.getCurrentPage(), userVO.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVO);
        PageInfo<User> userPageInfo = new PageInfo<>(allUserByPage);
        return userPageInfo;
    }

    //修改用户状态
    @Override
    public void updateUserStatus(int id, String status) {
        //封装数据
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdateTime(new Date());
        //调用mapper
        userMapper.updateUserStatus(user);
    }

    //用户登录
    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.login(user);
        if (user1 != null && Md5.verify(user.getPassword(),"baochao",user1.getPassword())){
            return user1;
        }else {
            return null;
        }
    }

    //分配角色(回显)
    @Override
    public List<Role> findUserRoleById(int userId) {
        List<Role> list = userMapper.findUserRoleById(userId);
        return list;
    }

    //分配角色
    @Override
    public void userContextRole(UserVO userVO) {
        //根据用户id清空用户角色中间表
        userMapper.deleteUserContextRole(userVO.getUserId());
        //重新建立用户角色关系
        for (Integer roleId : userVO.getRoleIdList()) {
            //封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVO.getUserId());
            user_role_relation.setRoleId(roleId);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            //调用mapper
            userMapper.userContextRole(user_role_relation);
        }
            

    }

    //获取用户拥有的菜单权限
    @Override
    public Map getUserPermissions(int userId) {
        //根据用户id获取对应的角色信息
        List<Role> roleList = userMapper.findUserRoleById(userId);
        //获取用户对应的所有角色的id
        List<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }
        //根据角色id获取对应的顶级菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);
        //根据父菜单ids获取关联的子菜单并封装到顶级菜单中
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByParentId(menu.getId());
            menu.setSubMenuList(subMenu);
        }
        //根据角色ids查询对应的资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);
        //封装需要返回的信息
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);
        return map;
    }

}
