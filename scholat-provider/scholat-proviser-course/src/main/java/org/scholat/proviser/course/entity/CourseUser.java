package org.scholat.proviser.course.entity;

import lombok.Data;

import java.util.Date;

/**
 * 课程用户表对应实体类
 */
@Data
public class CourseUser {

    private Integer courseId;

    private Integer userId;

    private String className;//班级名

    private String sno;//学号

    private Date createTime;
}
