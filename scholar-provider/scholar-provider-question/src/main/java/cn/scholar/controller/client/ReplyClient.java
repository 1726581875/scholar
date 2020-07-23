package cn.scholar.controller.client;

import javax.servlet.http.HttpServletRequest;

import cn.scholar.common.RespBean;
import cn.scholar.pojo.Reply;
import cn.scholar.service.ReplyService;
import cn.scholar.pojo.PageQuestionAndReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author lsx
 * 
 */
@RestController
@CrossOrigin(allowCredentials = "true" ,allowedHeaders = "*")
public class ReplyClient {
	
	@Autowired
	private ReplyService replyService;
	
	//根据问题的Id查找该问题的所有回复
	@GetMapping("/replys/findReplyList/{questionId}/{page}")
	public Object queReply(@PathVariable Integer questionId,@PathVariable Integer page){
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的8条记录
		   Page<Reply> ReplyList = replyService.findReply(questionId,pageReques);
		   return new RespBean<Object>(1,ReplyList,"success");
	}
	
	//删除回复
	@DeleteMapping("/replys/deleteReply/{replyId}")
	public Object deleteReply(@PathVariable Integer replyId){
		replyService.deleteReply(replyId);
		return new RespBean<Object>(1,null,"success");
	}
	
	//查找个人的所有回复并显示回复的问题
	@GetMapping("/replys/replyWithQues/{userId}/{page}")
	public Object findReplyWithQue(@PathVariable Integer userId,@PathVariable Integer page,HttpServletRequest request){
		PageQuestionAndReply pageQuestionAndReply = new PageQuestionAndReply();
		PageRequest pageReques=PageRequest.of(page,8);  //第page+1页的8条记录		
//		List<QuestionAndReply> ReplyByUser = replyService.findReplyByUser(userId);
		pageQuestionAndReply.setQuestionAndReply(replyService.findReplyByUser(userId,pageReques).getContent());
		pageQuestionAndReply.setPageCount(replyService.findReplyByUser(userId,pageReques).getTotalPages());
		pageQuestionAndReply.setSize(replyService.findReplyByUser(userId,pageReques).getTotalElements());
		return new RespBean<Object>(1,pageQuestionAndReply,"success");
	}
	
    
	//回复问题
    @PutMapping("/replys/addReply")
    public Object addReply(@RequestBody Reply reply){
    	System.out.println(reply);
    	replyService.addReply(reply);
    	return new RespBean<Object>(1,null,"success");
    }
}
