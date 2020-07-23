package cn.scholar.common.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 14:39
 * 教师布置的教学任务（作业）
 */
@Data
@Accessors(chain = true)
public class CourseTask implements Serializable {

    private int taskId;

    private int courseId;

    private int userId;  //教师的id

    private String userName;  //教师的名字

    private String courseName; //课程的名字

    private List<String> className; //班级
//    private String className;
    private String taskTitle;

    private String taskContent;

    private String annexPath;

    private Date endTime;

    private Date createTime;

    private String isDelaySubmit;

    /*private int taskId;

    private int courseId;

    private String taskTitle;

    private String taskContent;

    private String annexPath;

    private Date endTime;

    private Date createTime;*/
}
