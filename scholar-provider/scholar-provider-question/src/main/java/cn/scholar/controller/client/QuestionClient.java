package cn.scholar.controller.client;

import cn.scholar.common.RespBean;
import cn.scholar.pojo.SingleQueAndRepList;
import cn.scholar.service.QuestionService;
import cn.scholar.pojo.PageQuestion;
import cn.scholar.pojo.Question;
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
	

	//获取全部问题
	@GetMapping("/questions/allQuestion/{page}")
	public Object findAllQuestion(@PathVariable Integer page){
		PageQuestion pageQuestion = new PageQuestion();
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的8条记录
		pageQuestion.setQuestionList(questionService.allQuestion(pageReques).getContent());    //PageRequest是Pageable的实现类
		pageQuestion.setPageCount(questionService.allQuestion(pageReques).getTotalPages());    //总页数
		pageQuestion.setSize(questionService.allQuestion(pageReques).getTotalElements());      //总记录数
		return new RespBean<Object>(1,pageQuestion,"success");
	}
	
	//删除提问的问题
	@DeleteMapping("/questions/deleteQuestion/{questionId}")
	public RespBean<Object> deleteQuestion(@PathVariable Integer questionId){
		questionService.deleteQuestion(questionId);
		return new RespBean<Object>(1, null,"success");
	}
	
	//根据问题Id查找问题详情及该问题的回复的内容
	@GetMapping("/questions/singleQuestion/{questionId}/{page}")
	public RespBean<Object> findSingleQuestion(@PathVariable Integer questionId, @PathVariable Integer page){
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的8条记录
		SingleQueAndRepList singleQuestion = questionService.findSingleQuestion(questionId,pageReques);
		return new RespBean<Object>(1,singleQuestion,"success");
	}
	
	//个人发表的全部问题
	@GetMapping("/questions/questionListByUser/{userId}/{page}")
	public Object findQuestionByUser(@PathVariable Integer userId,@PathVariable Integer page){
		PageQuestion pagePersonQuestion = new PageQuestion();
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的8条记录		
		pagePersonQuestion.setQuestionList(questionService.findQuestionByTeacher(userId,pageReques).getContent());   //此参数2仅作为测试数据，其实应是cookie的userId
		pagePersonQuestion.setPageCount(questionService.findQuestionByTeacher(userId,pageReques).getTotalPages());
		pagePersonQuestion.setSize(questionService.findQuestionByTeacher(userId,pageReques).getTotalElements());
		return new RespBean<Object>(1,pagePersonQuestion,"success");
		
	}
	
	//发新的提问
	@PutMapping("/questions/addQuestion")
	public RespBean<Object> addQuestion(@RequestBody Question question){
		questionService.addQuestion(question);
	    return new RespBean<Object>(1,null,"success");
	}
}
