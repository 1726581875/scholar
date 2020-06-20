package org.scholat.provider.question.controller.client;


import org.scholat.common.ResultMsg;
import org.scholat.provider.question.pojo.PageQuestion;
import org.scholat.provider.question.pojo.Question;
import org.scholat.provider.question.pojo.SingleQueAndRepList;
import org.scholat.provider.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
/*
 * @author lsx
 * 
 */
@RestController
@CrossOrigin(allowCredentials = "true" ,allowedHeaders = "*")
public class QuestionClient {
	
	@Autowired
	private QuestionService questionService;
	

	@GetMapping("/questions/allQuestion/{page}")
	public Object findAllQuestion(@PathVariable Integer page){
		PageQuestion pageQuestion = new PageQuestion();
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的8条记录
		pageQuestion.setQuestionList(questionService.allQuestion(pageReques).getContent());    //PageRequest是Pageable的实现类
		pageQuestion.setPageCount(questionService.allQuestion(pageReques).getTotalPages());    //总页数
		pageQuestion.setSize(questionService.allQuestion(pageReques).getTotalElements());      //总记录数
		return new ResultMsg<Object>(1,pageQuestion,"success");
	}
	
	@DeleteMapping("/questions/deleteQuestion/{questionId}")
	public ResultMsg<Object> deleteQuestion(@PathVariable Integer questionId){
		questionService.deleteQuestion(questionId);
		return new ResultMsg<Object>(1, null,"success");
	}
	
	@GetMapping("/questions/singleQuestion/{questionId}/{page}")
	public ResultMsg<Object> findSingleQuestion(@PathVariable Integer questionId,@PathVariable Integer page){
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的8条记录
		SingleQueAndRepList singleQuestion = questionService.findSingleQuestion(questionId,pageReques);
		return new ResultMsg<Object>(1,singleQuestion,"success");
	}
	
	@GetMapping("/questions/questionListByUser/{page}")
	public Object findQuestionByUser(@PathVariable Integer page){
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
		return new ResultMsg<Object>(1,pagePersonQuestion,"success");
		
	}
	
	@PutMapping("/questions/addQuestion")
	public ResultMsg<Object> addQuestion(@RequestBody Question question){
		questionService.addQuestion(question);
	    return new ResultMsg<Object>(1,null,"success");
	}
}
