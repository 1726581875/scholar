package org.scholat.provider.api.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.scholat.provider.question.pojo.PageQuestionAndReply;
import org.scholat.provider.question.pojo.Reply;
import org.springframework.cloud.openfeign.FeignClient;
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
	@GetMapping("/reply/findReplyList/{questionId}")
	public List<Reply> queReply(@PathVariable Integer questionId);
	
	@DeleteMapping("/reply/deleteReply/{replyId}")
	public void deleteReply(@PathVariable Integer replyId);
	
	@GetMapping("/reply/replyWithQues/{page}")
	public PageQuestionAndReply findReplyWithQue(@PathVariable Integer page,HttpServletRequest request);
	
    @PutMapping("/reply/addReply")
    public void addReply(Reply reply);
}
