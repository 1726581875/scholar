package org.scholat.privider.task.vo;

import lombok.Data;
import org.scholat.common.pojo.CourseTask;

import java.util.List;

@Data
public class HomeWorkVo {

    private String courseName;

    private String userName;

    private List<CourseTask> taskList;


}
