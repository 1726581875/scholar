package org.scholat.provider.question.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.scholat.provider.question.pojo.PageQuestionAndReply;
import org.scholat.provider.question.pojo.QuestionAndReply;
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
/*
 * @author lsx
 */
@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	//根据问题Id查找该问题的所有回复
	@GetMapping("/reply/findReplyList/{questionId}/{page}")
	public Page<Reply> queReply(@PathVariable Integer questionId,@PathVariable Integer page){
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的2条记录	
		return replyService.findReply(questionId,pageReques);
	}
	
	//删除回复
	@DeleteMapping("/reply/deleteReply/{replyId}")
	public void deleteReply(@PathVariable Integer replyId){
		replyService.deleteReply(replyId);
	}
	
	//查找个人的所有回复并显示回复的问题
	@GetMapping("/reply/replyWithQues/{userId}/{page}")
	public PageQuestionAndReply findReplyWithQue(@PathVariable Integer userId,@PathVariable Integer page,HttpServletRequest request){
		PageQuestionAndReply pageQuestionAndReply = new PageQuestionAndReply();
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的2条记录		
//		List<QuestionAndReply> ReplyByUser = replyService.findReplyByUser(userId);
		pageQuestionAndReply.setQuestionAndReply(replyService.findReplyByUser(userId,pageReques).getContent());
		pageQuestionAndReply.setPageCount(replyService.findReplyByUser(userId,pageReques).getTotalPages());
		pageQuestionAndReply.setSize(replyService.findReplyByUser(userId,pageReques).getTotalElements());
		return pageQuestionAndReply;
	}
	
	//回复问题
    @PutMapping("/reply/addReply")
    public void addReply(@PathVariable Reply reply){
    	replyService.addReply(reply);
    }
}
