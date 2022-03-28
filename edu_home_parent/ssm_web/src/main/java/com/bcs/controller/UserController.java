package com.bcs.controller;

import com.bcs.domain.ResponseResult;
import com.bcs.domain.Role;
import com.bcs.domain.User;
import com.bcs.domain.UserVO;
import com.github.pagehelper.PageInfo;
import com.bcs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //用户分页&多条件查询
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){
        PageInfo pageInfo = userService.findAllUserByPage(userVO);
        return new ResponseResult(true, 200, "多条件查询用户成功", pageInfo);
    }

    //修改用户状态
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(int id, String status){
        if("ENABLE".equalsIgnoreCase(status)){
            status = "DISABLE";
        }else {
            status = "ENABLE";
        }
        userService.updateUserStatus(id,status);
        return new ResponseResult(true, 200, "修改状态成功", status);
    }

    //用户登录
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);
        if (user1 != null){
            //登录成功
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token", access_token);
            session.setAttribute("user_id", user1.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("access_token", access_token);
            map.put("user_id",user1.getId());
            map.put("user", user1);
            ResponseResult responseResult = new ResponseResult(true, 1, "登录成功", map);
            return responseResult;
        }else {
            //登录失败
            ResponseResult responseResult = new ResponseResult(true, 400, "用户名或密码错误", null);
            return responseResult;
        }
    }

    //分配角色(回显)
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(int id){
        List<Role> list = userService.findUserRoleById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "分配角色回显成功", list);
        return responseResult;
    }

    //分配角色
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO){
        userService.userContextRole(userVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "分配角色成功", "");
        return responseResult;
    }

    //获取用户拥有的菜单权限
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        String header_token = request.getHeader("Authorization");
        HttpSession session = request.getSession();
        String session_token = (String) session.getAttribute("access_token");
        if (header_token.equals(session_token)){
            Integer user_id = (Integer) session.getAttribute("user_id");
            Map map = userService.getUserPermissions(user_id);
            ResponseResult responseResult = new ResponseResult(true, 200, "获取用户拥有的菜单权限成功", map);
            return responseResult;
        }else {
            ResponseResult responseResult = new ResponseResult(false, 400, "获取用户拥有的权限失败", "");
            return responseResult;
        }
    }

}
