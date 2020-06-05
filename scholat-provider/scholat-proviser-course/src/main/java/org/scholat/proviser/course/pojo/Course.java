package org.scholat.proviser.course.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class Course {

	private Integer courseId;
	
	private String courseImage;
	
	private String courseName;
	
	private Integer userId;
	
	private String courseDesc;
	
	private Date createTime;
	
	
}
