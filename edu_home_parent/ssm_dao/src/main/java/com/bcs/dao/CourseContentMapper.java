package com.bcs.dao;

import com.bcs.domain.Course;
import com.bcs.domain.CourseLesson;
import com.bcs.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    //根据courseId查询相应的章节信息以及关联的课时信息
    public List<CourseSection> findCourseAndLessonByCourseId(Integer courseId);

    //回显章节对应的课程信息
    public Course findCourseByCourseId(Integer courseId);

    //新建章节信息
    public void saveSection(CourseSection courseSection);

    //更新章节信息
    public void updateSection(CourseSection courseSection);

    //修改章节状态
    public void updateSectionStatus(CourseSection courseSection);

    //新建课时信息
    public void saveLesson(CourseLesson courseLesson);

}
