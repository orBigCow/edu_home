package com.bcs.service.impl;

import com.bcs.dao.CourseContentMapper;
import com.bcs.domain.Course;
import com.bcs.domain.CourseLesson;
import com.bcs.domain.CourseSection;
import com.bcs.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    //根据课程ID查询对应的章节以及关联的课时信息
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> list = courseContentMapper.findCourseAndLessonByCourseId(courseId);
        return list;
    }

    //回显章节对应的课程信息
    @Override
    public Course findCourseByCourseId(Integer courseId) {
        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    //新增章节信息
    @Override
    public void saveSection(CourseSection courseSection) {
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);
        courseContentMapper.saveSection(courseSection);
    }

    //更新章节信息
    @Override
    public void updateSection(CourseSection courseSection) {
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateSection(courseSection);
    }

    //修改章节状态
    @Override
    public void updateSectionStatus(Integer status, Integer id) {
        CourseSection courseSection = new CourseSection();
        //补全章节信息
        courseSection.setUpdateTime(new Date());
        courseSection.setId(id);
        courseSection.setStatus(status);
        //修改章节状态
        courseContentMapper.updateSectionStatus(courseSection);
    }

    //新建课时信息
    @Override
    public void saveLesson(CourseLesson courseLesson) {
        Date date = new Date();
        //补全课时信息
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);
        //保存课时信息
        courseContentMapper.saveLesson(courseLesson);
    }
}
