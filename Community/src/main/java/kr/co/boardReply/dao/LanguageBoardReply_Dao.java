package kr.co.boardReply.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.boardReply.dto.LanguageBoardReply_Dto;

public class LanguageBoardReply_Dao extends SqlSessionDaoSupport {
	
	//Language��� ��ȸ
		public List<LanguageBoardReply_Dto> languageReplyList(int postNo) throws Exception{
			
			return getSqlSession().selectList("boardReply.language_commentsList",postNo);
		}
		
		//Language��� �ۼ�
		public int languageReplyWrite(LanguageBoardReply_Dto dto) throws Exception {
			return getSqlSession().insert("boardReply.language_commentWrite",dto);
		}
		
		//Language��� ����
		public int languageReplyUpdate(LanguageBoardReply_Dto dto) throws Exception {
			return getSqlSession().update("boardReply.language_commentUpdate",dto);
		}
		
		//Language��� ����
		public int languageReplyDelete(int postNo, int commentsNo) throws Exception {
			
			HashMap<String,Integer> data = new HashMap<>();
			
			data.put("postNo", postNo);
			data.put("commentsNo", commentsNo);
			
			return getSqlSession().delete("boardReply.language_commentDelete",data);
		}
		
		//Language �ϳ��� �ۿ� ���� ��� ��  ��ȸ
		public int languageReplyCount(int postNo) throws Exception{
			return getSqlSession().selectOne("boardReply.language_comments_count",postNo);
		}

}