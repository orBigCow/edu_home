package com.bcs.controller;

import com.github.pagehelper.PageInfo;
import com.bcs.domain.Menu;
import com.bcs.domain.ResponseResult;
import com.bcs.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    //查询所有菜单信息
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(int currentPage, int pageSize){
        PageInfo allMenu = menuService.findAllMenu(currentPage, pageSize);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有菜单信息成功", allMenu);
        return responseResult;
    }

    //回显菜单信息
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(int id){
        if (id == -1){
            //新增菜单信息  回显父菜单名称
            List<Menu> menuList = menuService.findSubMenuListByPid(id);
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", null);
            map.put("parentMenuList", menuList);
            ResponseResult responseResult = new ResponseResult(true, 200, "添加菜单回显信息成功",map);
            return responseResult;
        }else {
            //修改菜单信息  回显父菜单名称及当前菜单信息
            Menu menu = menuService.findMenuById(id);
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", menu);
            map.put("parentMenuList", menuList);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改菜单回显信息成功",map);
            return responseResult;
        }
    }

    //新增or修改菜单信息
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        if (menu.getId() == null){
            //新增菜单信息
            menuService.saveMenu(menu);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增菜单信息成功", "");
            return responseResult;
        } else {
            //修改菜单信息
            menuService.updateMenu(menu);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改菜单信息成功", "");
            return responseResult;
        }
    }

    //删除菜单
    @RequestMapping("/deleteMenu")
    public ResponseResult deleteMenu(int id){
        menuService.deleteMenu(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除菜单成功", "");
        return responseResult;
    }

}
