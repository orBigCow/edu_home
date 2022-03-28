package com.bcs.service;

import com.bcs.domain.Course;
import com.bcs.domain.CourseLesson;
import com.bcs.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    //根据课程ID查询对应的章节以及关联的课时信息
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    //回显章节对应的课程信息
    public Course findCourseByCourseId(Integer courseId);

    //新增章节信息
    public void saveSection(CourseSection courseSection);

    //更新章节信息
    public void updateSection(CourseSection courseSection);

    //修改章节状态
    public void updateSectionStatus(Integer status, Integer id);

    //新建课时信息
    public void saveLesson(CourseLesson courseLesson);

}
