package com.bcs.service;

import com.bcs.domain.Course;
import com.bcs.domain.CourseVO;

import java.util.List;

public interface CourseService {

    //根据条件查询课程信息
    public List<Course> findCourseByCondition(CourseVO courseVO);

    //保存课程和讲师信息
    public void saveCourseOrTeacher(CourseVO courseVO);

    //课程信息回显(根据id查询对应的课程以及讲师信息)
    public CourseVO findCourseById(Integer id);

    //修改课程以及讲师信息
    public void updateCourseOrTeacher(CourseVO courseVO);

    //修改课程状态
    public void updateCourseStatus(Integer status, Integer id);

}
