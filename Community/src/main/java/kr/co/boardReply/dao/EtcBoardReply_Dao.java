package kr.co.boardReply.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.boardReply.dto.EtcBoardReply_Dto;

public class EtcBoardReply_Dao extends SqlSessionDaoSupport {
	//Etc댓글 조회
		public List<EtcBoardReply_Dto> etcReplyList(int postNo) throws Exception{
			
			return getSqlSession().selectList("boardReply.etc_commentsList",postNo);
		}
		
		//Etc댓글 작성
		public int etcReplyWrite(EtcBoardReply_Dto dto) throws Exception {
			return getSqlSession().insert("boardReply.etc_commentWrite",dto);
		}
		
		//Etc댓글 수정
		public int etcReplyUpdate(EtcBoardReply_Dto dto) throws Exception {
			return getSqlSession().update("boardReply.etc_commentUpdate",dto);
		}
		
		//Etc댓글 삭제
		public int etcReplyDelete(int postNo, int commentsNo) throws Exception {
			
			HashMap<String,Integer> data = new HashMap<>();
			
			data.put("postNo", postNo);
			data.put("commentsNo", commentsNo);
			
			return getSqlSession().delete("boardReply.etc_commentDelete",data);
		}
		
		//Etc 하나의 글에 대한 답글 수  조회
		public int etcReplyCount(int postNo) throws Exception{
			return getSqlSession().selectOne("boardReply.etc_comments_count",postNo);
		}	

}
