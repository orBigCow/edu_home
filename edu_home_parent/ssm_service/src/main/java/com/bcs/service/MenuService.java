package com.bcs.service;

import com.github.pagehelper.PageInfo;
import com.bcs.domain.Menu;

import java.util.List;

public interface MenuService {

    //查询所有父子菜单信息
    public List<Menu> findSubMenuListByPid(int pid);

    //查询所有菜单信息
    public PageInfo findAllMenu(int currentPage, int pageSize);

    //根据id查询菜单信息
    public Menu findMenuById(int id);

    //新增菜单
    public void saveMenu(Menu menu);

    //修改菜单
    public void updateMenu(Menu menu);

    //删除菜单
    public void deleteMenu(int id);

}
