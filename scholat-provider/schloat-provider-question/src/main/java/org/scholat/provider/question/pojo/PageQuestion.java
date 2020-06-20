package org.scholat.provider.question.pojo;

import java.util.List;

import lombok.Data;

@Data
public class PageQuestion {

	//总页数
	private Integer pageCount;
	
	//记录总数
	private Long size;	
	
	//当前该页的数据集
	private List<Question> questionList;
	
}
