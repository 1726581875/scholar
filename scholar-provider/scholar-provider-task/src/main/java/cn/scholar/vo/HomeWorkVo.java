package cn.scholar.vo;

import cn.scholar.common.pojo.CourseTask;
import lombok.Data;

import java.util.List;

@Data
public class HomeWorkVo {

    private String courseName;

    private String userName;

    private List<CourseTask> taskList;


}
