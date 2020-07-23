package cn.scholar.service;

import cn.scholar.pojo.Question;
import cn.scholar.pojo.SingleQueAndRepList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
 * @author lsx
 * 提问service接口
 */
public interface QuestionService {
	
	//发布提问
	void addQuestion(Question question);
	
	//删除评论
	void deleteQuestion(Integer questionId);
	
	//查看提问并分页显示回复
	SingleQueAndRepList findSingleQuestion(Integer questionId,Pageable pageable);
	
	//所有提问(分页)
	Page<Question> allQuestion(Pageable pageable);
	
	//根据教师号查找所发布的评论(分页)
	Page<Question> findQuestionByTeacher(Integer userId,Pageable pageable);
	
	//查找提问
	Question findQuestion(Integer questionId);
	
}
