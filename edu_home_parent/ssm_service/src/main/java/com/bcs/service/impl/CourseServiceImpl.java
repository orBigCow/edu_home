package com.bcs.service.impl;

import com.bcs.dao.CourseMapper;
import com.bcs.domain.Course;
import com.bcs.domain.CourseVO;
import com.bcs.domain.Teacher;
import com.bcs.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        List<Course> list = courseMapper.findCourseByCondition(courseVO);
        return list;
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) {
        try {
            Course course = new Course();
            //封装course实体类
            BeanUtils.copyProperties(course,courseVO);
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);
            //保存课程信息
            courseMapper.saveCourse(course);
            //获取课程id
            int id = course.getId();
            Teacher teacher = new Teacher();
            //封装teacher实体
            BeanUtils.copyProperties(teacher,courseVO);
            teacher.setCourseId(id);
            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            //保存讲师信息
            courseMapper.saveTeacher(teacher);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    //课程信息回显(根据id查询对应的课程以及讲师信息)
    @Override
    public CourseVO findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    //修改课程和讲师信息
    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) {
        try {
            Course course = new Course();
            //封装course信息
            BeanUtils.copyProperties(course,courseVO);
            Date date = new Date();
            //补全信息
            course.setUpdateTime(date);
            //更新课程信息
            courseMapper.updateCourse(course);

            Teacher teacher = new Teacher();
            //封装teacher信息
            BeanUtils.copyProperties(teacher,courseVO);
            //补全信息
            teacher.setCourseId(course.getId());
            teacher.setUpdateTime(date);
            //更新讲师信息
            courseMapper.updateTeacher(teacher);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //修改课程状态
    @Override
    public void updateCourseStatus(Integer status, Integer id) {
        Course course = new Course();
        //封装course对象
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());
        //修改课程状态
        courseMapper.updateCourseStatus(course);
    }

}
