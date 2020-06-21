package org.scholat.provider.question.controller;

import org.scholat.provider.question.pojo.PageQuestion;
import org.scholat.provider.question.pojo.Question;
import org.scholat.provider.question.pojo.SingleQueAndRepList;
import org.scholat.provider.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/*
 * @author lsx
 * 提问Controller
 */
@RestController
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	//所以问题
	@GetMapping("/question/allQuestion/{page}")
	public PageQuestion findAllQuestion(@PathVariable Integer page){
		PageQuestion pageQuestion = new PageQuestion();
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的8条记录
		pageQuestion.setQuestionList(questionService.allQuestion(pageReques).getContent());    //PageRequest是Pageable的实现类
		pageQuestion.setPageCount(questionService.allQuestion(pageReques).getTotalPages());    //总页数
		pageQuestion.setSize(questionService.allQuestion(pageReques).getTotalElements());      //总记录数
		return pageQuestion;
		
	}
	
	//删除问题
	@DeleteMapping("/question/deleteQuestion/{questionId}")
	public void deleteQuestion(@PathVariable Integer questionId){
		questionService.deleteQuestion(questionId);
	}
	
	//问题详情及该问题的回复
	@GetMapping("/question/singleQuestion/{questionId}/{page}")
	public SingleQueAndRepList findSingleQuestion(@PathVariable Integer questionId,@PathVariable Integer page){
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的8条记录
		return questionService.findSingleQuestion(questionId,pageReques);
	}
	
	//个人的全部提问的问题
	@GetMapping("/question/questionListByUser/{userId}/{page}")
	public PageQuestion findQuestionByUser(@PathVariable Integer userId,@PathVariable Integer page){
		PageQuestion pagePersonQuestion = new PageQuestion();
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的8条记录		
		pagePersonQuestion.setQuestionList(questionService.findQuestionByTeacher(userId,pageReques).getContent());   //此参数2仅作为测试数据，其实应是cookie的userId
		pagePersonQuestion.setPageCount(questionService.findQuestionByTeacher(userId,pageReques).getTotalPages());
		pagePersonQuestion.setSize(questionService.findQuestionByTeacher(userId,pageReques).getTotalElements());
		return pagePersonQuestion;
	}
	
	//发布新的提问
	@PutMapping("/question/addQuestion")
	public void addQuestion(Question question){
		questionService.addQuestion(question);
	}
	

}
