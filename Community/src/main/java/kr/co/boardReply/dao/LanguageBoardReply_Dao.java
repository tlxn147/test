package kr.co.boardReply.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.boardReply.dto.LanguageBoardReply_Dto;

public class LanguageBoardReply_Dao extends SqlSessionDaoSupport {
	
	//Language댓글 조회
		public List<LanguageBoardReply_Dto> languageReplyList(int postNo) throws Exception{
			
			return getSqlSession().selectList("boardReply.language_commentsList",postNo);
		}
		
		//Language댓글 작성
		public int languageReplyWrite(LanguageBoardReply_Dto dto) throws Exception {
			return getSqlSession().insert("boardReply.language_commentWrite",dto);
		}
		
		//Language댓글 수정
		public int languageReplyUpdate(LanguageBoardReply_Dto dto) throws Exception {
			return getSqlSession().update("boardReply.language_commentUpdate",dto);
		}
		
		//Language댓글 삭제
		public int languageReplyDelete(int postNo, int commentsNo) throws Exception {
			
			HashMap<String,Integer> data = new HashMap<>();
			
			data.put("postNo", postNo);
			data.put("commentsNo", commentsNo);
			
			return getSqlSession().delete("boardReply.language_commentDelete",data);
		}
		
		//Language 하나의 글에 대한 답글 수  조회
		public int languageReplyCount(int postNo) throws Exception{
			return getSqlSession().selectOne("boardReply.language_comments_count",postNo);
		}

}
