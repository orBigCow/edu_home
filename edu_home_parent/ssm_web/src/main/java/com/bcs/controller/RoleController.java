package com.bcs.controller;

import com.bcs.domain.*;
import com.bcs.service.MenuService;
import com.bcs.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //查询所有角色
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有角色成功", allRole);
        return responseResult;
    }

    @Autowired
    private MenuService menuService;

    //查询所有父子菜单信息
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findSubMenuListByPid(-1);
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList",allMenu);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有菜单成功", map);
        return responseResult;
    }

    //添加or修改角色信息
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){
        if (role.getId() == null){
            //添加角色
            roleService.saveRole(role);
            ResponseResult responseResult = new ResponseResult(true, 200, "添加角色成功", "");
            return responseResult;
        }else {
            //修改角色
            roleService.updateRole(role);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改角色成功", "");
            return responseResult;
        }
    }

    //根据角色ID查询对应的菜单ID
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(int roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        ResponseResult responseResult = new ResponseResult(true, 200, "根据角色ID查询对应的菜单信息成功", menuByRoleId);
        return responseResult;
    }

    //为角色分配菜单列表
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVO roleMenuVO){
        System.out.println(roleMenuVO);
        roleService.RoleContextMenu(roleMenuVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "为用户分配菜单成功", "");
        return responseResult;
    }

    //删除角色
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(int id){
        roleService.deleteRole(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色成功", "");
        return responseResult;
    }

    //获取当前角色拥有的拥有的资源分类以及对应的资源信息
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(int roleId){
        List<ResourceCategory> list = roleService.findResourceByRoleId(roleId);
        ResponseResult responseResult = new ResponseResult(true, 200, "获取当前角色拥有的资源信息成功", list);
        return responseResult;
    }

    //为角色分配资源
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVO roleResourceVO){
        roleService.roleContextResource(roleResourceVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "为角色分配资源成功", null);
        return responseResult;
    }

}
