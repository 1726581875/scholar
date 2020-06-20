package org.scholat.provider.api.service;

import java.util.List;

import org.scholat.common.ResultMsg;
import org.scholat.common.message.enums.CommonEnum;
import org.scholat.provider.question.pojo.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

/*
 * @author lsx
 * Question Api
 */
@FeignClient(value = "SCHOLAT-QUESTION")
public interface QuestionServiceApi {

	@GetMapping("/question/allQuestion")
	public ResultMsg<Object> findAllQuestion();
	
	@DeleteMapping("/question/deleteQuestion")
	public ResultMsg<Object> deleteQuestion(Integer questionId);
	
	@GetMapping("/question/singleQuestion")
	public ResultMsg<Object> findSingleQuestion(Integer questionId);
	
	@GetMapping("/question/questionListByUser")
	public ResultMsg<Object> findQuestionByUser(Integer userId);
	
	@PutMapping("/question/addQuestion")
	public ResultMsg<Object> addQuestion(Question question);
}
