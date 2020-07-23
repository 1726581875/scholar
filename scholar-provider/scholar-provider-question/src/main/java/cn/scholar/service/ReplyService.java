package cn.scholar.service;

import cn.scholar.pojo.QuestionAndReply;
import cn.scholar.pojo.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
 * @author lsx
 * 回复Reply的service接口
 */
public interface ReplyService {

	//查找提问对应的评论
	Page<Reply> findReply(Integer questionId,Pageable pageable);
	
	//删除评论
	void deleteReply(Integer replyId);
	
	//查找个人评论并显示提问的问题信息
	Page<QuestionAndReply> findReplyByUser(Integer userId,Pageable pageable);
	
	
	//发布评论
	void addReply(Reply reply);
	
}
