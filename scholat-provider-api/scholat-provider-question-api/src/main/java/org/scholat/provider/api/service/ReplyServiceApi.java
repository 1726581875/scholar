package org.scholat.provider.api.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.scholat.provider.question.pojo.PageQuestionAndReply;
import org.scholat.provider.question.pojo.Reply;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/*
 * @author lsx
 * Reply Api
 */
@FeignClient(value = "SCHOLAT-QUESTION")
public interface ReplyServiceApi {
	
	//根据问题Id查找该问题的所有回复
		@GetMapping("/reply/findReplyList/{questionId}/{page}")
		public Page<Reply> queReply(@PathVariable Integer questionId,@PathVariable Integer page);
		
		//删除回复
		@DeleteMapping("/reply/deleteReply/{replyId}")
		public void deleteReply(@PathVariable Integer replyId);
		
		//查找个人的所有回复并显示回复的问题
		@GetMapping("/reply/replyWithQues/{userId}/{page}")
		public PageQuestionAndReply findReplyWithQue(@PathVariable Integer userId,@PathVariable Integer page,HttpServletRequest request);
		
		//回复问题
	    @PutMapping("/reply/addReply")
	    public void addReply(@PathVariable Reply reply);
}
