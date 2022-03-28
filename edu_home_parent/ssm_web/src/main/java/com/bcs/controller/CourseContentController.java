package com.bcs.controller;

import com.bcs.domain.Course;
import com.bcs.domain.CourseLesson;
import com.bcs.domain.CourseSection;
import com.bcs.domain.ResponseResult;
import com.bcs.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    //查询章节以及关联的课时信息
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(Integer courseId){
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询章节和课时信息成功", list);
        return responseResult;
    }

    //回显章节对应的课程信息
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", course.getId());
        map.put("courseName", course.getCourseName());
        ResponseResult responseResult = new ResponseResult(true, 200, "成功回显章节对应的课程信息",map);
        return responseResult;
    }

    //新增or更新章节信息
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if (courseSection.getId() == null){
            courseContentService.saveSection(courseSection);
            return new ResponseResult(true, 200, "新增章节成功",null);
        }else {
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true, 200, "修改章节成功", null);
        }
    }

    //修改章节状态
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int status, int id){
        courseContentService.updateSectionStatus(status, id);
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        ResponseResult responseResult = new ResponseResult(true, 200, "修改章节状态成功", map);
        return responseResult;
    }

    //新建课时信息
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson){
        courseContentService.saveLesson(courseLesson);
        ResponseResult responseResult = new ResponseResult(true, 200, "新增课时信息成功", null);
        return responseResult;
    }

}
