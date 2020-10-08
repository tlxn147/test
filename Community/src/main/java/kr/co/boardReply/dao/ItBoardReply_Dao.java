package kr.co.boardReply.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.boardReply.dto.ItBoardReply_Dto;

public class ItBoardReply_Dao extends SqlSessionDaoSupport  {
	
	
	//IT댓글 조회
	public List<ItBoardReply_Dto> itReplyList(int postNo) throws Exception{
		
		return getSqlSession().selectList("boardReply.it_commentsList",postNo);
	}
	
	//IT댓글 작성
	public int itReplyWrite(ItBoardReply_Dto dto) throws Exception {
		return getSqlSession().insert("boardReply.it_commentWrite",dto);
	}
	
	//IT댓글 수정
	public int itReplyUpdate(ItBoardReply_Dto dto) throws Exception {
		return getSqlSession().update("boardReply.it_commentUpdate",dto);
	}
	
	//IT댓글 삭제
	public int itReplyDelete(int postNo, int commentsNo) throws Exception {
		
		HashMap<String,Integer> data = new HashMap<>();
		
		data.put("postNo", postNo);
		data.put("commentsNo", commentsNo);
		
		return getSqlSession().delete("boardReply.it_commentDelete",data);
	}
	
	//IT 하나의 글에 대한 답글 수  조회
	public int itReplyCount(int postNo) throws Exception{
		return getSqlSession().selectOne("boardReply.it_comments_count",postNo);
	}

}
