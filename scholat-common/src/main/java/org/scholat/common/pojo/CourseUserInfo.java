package org.scholat.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseUserInfo {

    private Integer courseId;

    private Integer userId;

    private String className;//班级名

    private String sno;//学号

    private Date createTime;


}
