package com.bcs.service.impl;

import com.bcs.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bcs.dao.MenuMapper;
import com.bcs.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    //查询所有父子菜单信息
    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> allMenu = menuMapper.findSubMenuListByPid(pid);
        return allMenu;
    }

    //查询所有菜单信息
    @Override
    public PageInfo findAllMenu(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Menu> allMenu = menuMapper.findAllMenu();
        PageInfo<Menu> menuPageInfo = new PageInfo<>(allMenu);
        return menuPageInfo;
    }

    //根据id查询菜单信息
    @Override
    public Menu findMenuById(int id) {
        Menu menu = menuMapper.findMenuById(id);
        return menu;
    }

    //新增菜单
    @Override
    public void saveMenu(Menu menu) {
        //补全信息
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        if (menu.getParentId() == -1){
            menu.setLevel(0);
        } else {
            menu.setLevel(1);
        }
        //调用mapper
        menuMapper.saveMenu(menu);
    }

    //修改菜单
    @Override
    public void updateMenu(Menu menu) {
        //补全信息
        menu.setUpdatedTime(new Date());
        menu.setUpdatedBy("system");
        if (menu.getParentId() == -1){
            menu.setLevel(0);
        } else {
            menu.setLevel(1);
        }
        //调用mapper
        menuMapper.updateMenu(menu);
    }

    //删除菜单
    @Override
    public void deleteMenu(int id) {
        menuMapper.deleteMenu(id);
    }

}
