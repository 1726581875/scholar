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
	
	@GetMapping("/question/allQuestion/{page}")
	public PageQuestion findAllQuestion(@PathVariable Integer page){
		PageQuestion pageQuestion = new PageQuestion();
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的8条记录
		pageQuestion.setQuestionList(questionService.allQuestion(pageReques).getContent());    //PageRequest是Pageable的实现类
		pageQuestion.setPageCount(questionService.allQuestion(pageReques).getTotalPages());    //总页数
		pageQuestion.setSize(questionService.allQuestion(pageReques).getTotalElements());      //总记录数
		return pageQuestion;
		
	}
	
	@DeleteMapping("/question/deleteQuestion/{questionId}")
	public void deleteQuestion(@PathVariable Integer questionId){
		questionService.deleteQuestion(questionId);
	}
	
	@GetMapping("/question/singleQuestion/{questionId}/{page}")
	public SingleQueAndRepList findSingleQuestion(@PathVariable Integer questionId,@PathVariable Integer page){
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的8条记录
		return questionService.findSingleQuestion(questionId,pageReques);
	}
	
	@GetMapping("/question/questionListByUser/{page}")
	public PageQuestion findQuestionByUser(@PathVariable Integer page){
		PageQuestion pagePersonQuestion = new PageQuestion();
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的8条记录		
//		// Cookie cookie=new Cookie("sessionId","CookieTestInfo");
//		Cookie[] cookies = (Cookie[]) request.getCookies();
//		if(cookies != null){
//		for(Cookie cookie : cookies){
//		if(cookie.getName().equals("sessionId")){
//			userId =  cookie.getValue();
//		}
		pagePersonQuestion.setQuestionList(questionService.findQuestionByTeacher(2,pageReques).getContent());   //此参数2仅作为测试数据，其实应是cookie的userId
		pagePersonQuestion.setPageCount(questionService.findQuestionByTeacher(2,pageReques).getTotalPages());
		pagePersonQuestion.setSize(questionService.findQuestionByTeacher(2,pageReques).getTotalElements());
		return pagePersonQuestion;
	}
	
	@PutMapping("/question/addQuestion")
	public void addQuestion(Question question){
		questionService.addQuestion(question);
	}
	

}
