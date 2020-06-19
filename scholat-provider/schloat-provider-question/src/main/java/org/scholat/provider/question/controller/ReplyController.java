package org.scholat.provider.question.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.scholat.provider.question.pojo.PageQuestionAndReply;
import org.scholat.provider.question.pojo.QuestionAndReply;
import org.scholat.provider.question.pojo.Reply;
import org.scholat.provider.question.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
/*
 * @author lsx
 */
@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@GetMapping("/reply/findReplyList/{questionId}")
	public List<Reply> queReply(@PathVariable Integer questionId){
		return replyService.findReply(questionId);
	}
	
	@DeleteMapping("/reply/deleteReply/{replyId}")
	public void deleteReply(@PathVariable Integer replyId){
		replyService.deleteReply(replyId);
	}
	
	@GetMapping("/reply/replyWithQues/{page}")
	public PageQuestionAndReply findReplyWithQue(@PathVariable Integer page,HttpServletRequest request){
		PageQuestionAndReply pageQuestionAndReply = new PageQuestionAndReply();
		PageRequest pageReques=PageRequest.of(page,2);  //第page+1页的2条记录		
//		// Cookie cookie=new Cookie("sessionId","CookieTestInfo");
//		Cookie[] cookies = (Cookie[]) request.getCookies();
//		if(cookies != null){
//		for(Cookie cookie : cookies){
//		if(cookie.getName().equals("sessionId")){
//			userId =  cookie.getValue();
//		}
		Integer userId = 2;    //测试数据:仅作测试使用
//		List<QuestionAndReply> ReplyByUser = replyService.findReplyByUser(userId);
		pageQuestionAndReply.setQuestionAndReply(replyService.findReplyByUser(userId,pageReques).getContent());
		pageQuestionAndReply.setPageCount(replyService.findReplyByUser(userId,pageReques).getTotalPages());
		pageQuestionAndReply.setSize(replyService.findReplyByUser(userId,pageReques).getTotalElements());
		return pageQuestionAndReply;
	}
	
    @PutMapping("/reply/addReply")
    public void addReply(@PathVariable Reply reply){
    	replyService.addReply(reply);
    }
}
