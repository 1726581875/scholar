package org.scholat.provider.question.controller;

import org.scholat.provider.question.pojo.PageQuestionAndReply;
import org.scholat.provider.question.pojo.Reply;
import org.scholat.provider.question.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpServletRequest;
/*
 * @author lsx
 */
@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@GetMapping("/reply/findReplyList/{questionId}/{page}")
	public Page<Reply> queReply(@PathVariable Integer questionId,@PathVariable Integer page){
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的2条记录	
		return replyService.findReply(questionId,pageReques);
	}
	
	@DeleteMapping("/reply/deleteReply/{replyId}")
	public void deleteReply(@PathVariable Integer replyId){
		replyService.deleteReply(replyId);
	}
	
	@GetMapping("/reply/replyWithQues/{page}")
	public PageQuestionAndReply findReplyWithQue(@PathVariable Integer page,HttpServletRequest request){
		PageQuestionAndReply pageQuestionAndReply = new PageQuestionAndReply();
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的2条记录		
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
