package org.scholat.provider.api.service;

import java.util.List;

import org.scholat.common.ResultMsg;
import org.scholat.common.message.enums.CommonEnum;
import org.scholat.provider.question.pojo.PageQuestion;
import org.scholat.provider.question.pojo.Question;
import org.scholat.provider.question.pojo.SingleQueAndRepList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/*
 * @author lsx
 * Question Api
 */
@FeignClient(value = "SCHOLAT-QUESTION")
public interface QuestionServiceApi {

	//所以问题
		@GetMapping("/question/allQuestion/{page}")
		public PageQuestion findAllQuestion(@PathVariable Integer page);
		
		//删除问题
		@DeleteMapping("/question/deleteQuestion/{questionId}")
		public void deleteQuestion(@PathVariable Integer questionId);
		
		//问题详情及该问题的回复
		@GetMapping("/question/singleQuestion/{questionId}/{page}")
		public SingleQueAndRepList findSingleQuestion(@PathVariable Integer questionId,@PathVariable Integer page);
		
		//个人的全部提问的问题
		@GetMapping("/question/questionListByUser/{userId}/{page}")
		public PageQuestion findQuestionByUser(@PathVariable Integer userId,@PathVariable Integer page);
		
		//发布新的提问
		@PutMapping("/question/addQuestion")
		public void addQuestion(Question question);
}
