package org.scholat.provider.question.pojo;

import java.util.List;

import lombok.Data;

@Data
public class PageQuestionAndReply {
	//总页数
	private Integer pageCount;
		
	//记录总数
	private Long size;
	
	//结果集
	private List<QuestionAndReply> questionAndReply;
}
