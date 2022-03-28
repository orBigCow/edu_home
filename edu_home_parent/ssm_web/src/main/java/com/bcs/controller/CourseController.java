package com.bcs.controller;

import com.bcs.domain.Course;
import com.bcs.domain.CourseVO;
import com.bcs.domain.ResponseResult;
import com.bcs.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    //根据条件查询课程接口
    @RequestMapping("/findAllCourse")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        List<Course> list = courseService.findCourseByCondition(courseVO);
        ResponseResult result = new ResponseResult(true, 200, "查询成功", list);
        return result;
    }

    //图片上传接口
    @RequestMapping("/courseUpload")
    public ResponseResult courseUpload(MultipartFile file, HttpServletRequest request) throws IOException {
        //判断文件是否为空
        if (file.isEmpty()){
            throw new RuntimeException();
        }
        //2.获取项目部署路径 // D:\apache-tomcat-8.5.56\webapps\ssm-web\
        String realPath = request.getServletContext().getRealPath("/");
        // D:\apache-tomcat-8.5.56\webapps\
        String webappPath = realPath.substring(0, realPath.indexOf("ssm-web"));
        //原始文件名
        String originalFilename = file.getOriginalFilename();
        //新文件名
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String uploadPath = webappPath + "upload/";
        File filePath = new File(uploadPath, newFileName);
        //如果目录不存在，就创建目录
        if (!filePath.exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录:" + filePath);
        }
        file.transferTo(filePath);
        Map<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);
        ResponseResult responseResult = new ResponseResult(true, 200,"图片上传成功",map);
        return responseResult;
    }

    //新建&修改课程接口
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO){
        if (courseVO.getId() == null){
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
            return responseResult;
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }
    }

    //课程信息回显(根据id查询对应的课程以及讲师信息)
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVO courseVO = courseService.findCourseById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "回显课程信息成功", courseVO);
        return responseResult;
    }

    //修改课程状态
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer status, Integer id){
        courseService.updateCourseStatus(status, id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", status);
        ResponseResult responseResult = new ResponseResult(true, 200, "课程状态更新成功", map);
        return responseResult;
    }




}
