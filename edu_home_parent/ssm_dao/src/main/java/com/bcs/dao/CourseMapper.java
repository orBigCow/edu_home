package com.bcs.dao;

import com.bcs.domain.Course;
import com.bcs.domain.CourseVO;
import com.bcs.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    //根据条件查询课程信息
    public List<Course> findCourseByCondition(CourseVO courseVO);

    //保存课程信息
    public void saveCourse(Course course);

    //保存讲师信息
    public void saveTeacher(Teacher teacher);

    //课程信息回显(根据id查询对应的课程以及讲师信息)
    public CourseVO findCourseById(Integer id);

    //修改课程信息
    public void updateCourse(Course course);

    //修改讲师信息
    public void updateTeacher(Teacher teacher);

    //修改课程状态
    public void updateCourseStatus(Course course);


}
