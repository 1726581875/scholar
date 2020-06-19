package org.scholat.provider.question.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.scholat.provider.question.pojo.Question;
import org.scholat.provider.question.pojo.QuestionAndReply;
import org.scholat.provider.question.pojo.Reply;
import org.scholat.provider.question.reporitory.ReplyReporitory;
import org.scholat.provider.question.service.QuestionService;
import org.scholat.provider.question.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyReporitory replyReporitory;
	
	@Autowired
	private QuestionService questionService;

	@Override
	public List<Reply> findReply(Integer questionId) {
		// TODO Auto-generated method stub
		return replyReporitory.findByQuestionId(questionId);
	}

	@Override
	public void deleteReply(Integer replyId) {
		// TODO Auto-generated method stub
		replyReporitory.deleteById(replyId);
	}

	@Override
	public Page<QuestionAndReply> findReplyByUser(Integer userId,Pageable pageable) {
		// TODO Auto-generated method stub		
		List<Reply> ReplyList = replyReporitory.findByUserId(userId);
		List<QuestionAndReply> QueAndRepList = new ArrayList<>();
		for (Reply reply : ReplyList) {
			QuestionAndReply questionAndReply = new QuestionAndReply();   
			Integer questionId = reply.getQuestionId();	
			Question question = questionService.findQuestion(questionId);
//			System.out.println(question);
			questionAndReply.setQuestion(question);	
//			System.out.println(reply);
			questionAndReply.setReply(reply);					
			QueAndRepList.add(questionAndReply);
		}
		int start = (int)pageable.getOffset();
		int end = (start + pageable.getPageSize()) > QueAndRepList.size() ? QueAndRepList.size() : ( start + pageable.getPageSize());
		return new PageImpl<QuestionAndReply>(QueAndRepList.subList(start, end), pageable, QueAndRepList.size());
	}

	@Override
	public void addReply(Reply reply) {
		// TODO Auto-generated method stub
	    replyReporitory.save(reply);
	}

}
