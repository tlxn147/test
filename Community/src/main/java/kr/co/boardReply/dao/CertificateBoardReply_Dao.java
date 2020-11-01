package kr.co.boardReply.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.boardReply.dto.CertificateBoardReply_Dto;

public class CertificateBoardReply_Dao extends SqlSessionDaoSupport {
	//Certificate댓글 조회
	public List<CertificateBoardReply_Dto> certificateReplyList(int postNo) throws Exception{
		
		return getSqlSession().selectList("boardReply.certificate_commentsList",postNo);
	}
	
	//Certificate댓글 작성
	public int certificateReplyWrite(CertificateBoardReply_Dto dto) throws Exception {
		return getSqlSession().insert("boardReply.certificate_commentWrite",dto);
	}
	
	//Certificate댓글 수정
	public int certificateReplyUpdate(CertificateBoardReply_Dto dto) throws Exception {
		return getSqlSession().update("boardReply.certificate_commentUpdate",dto);
	}
	
	//Certificate댓글 삭제
	public int certificateReplyDelete(int postNo, int commentsNo) throws Exception {
		
		HashMap<String,Integer> data = new HashMap<>();
		
		data.put("postNo", postNo);
		data.put("commentsNo", commentsNo);
		
		return getSqlSession().delete("boardReply.certificate_commentDelete",data);
	}
	
	//Certificate 하나의 글에 대한 답글 수  조회
	public int certificateReplyCount(int postNo) throws Exception{
		return getSqlSession().selectOne("boardReply.certificate_comments_count",postNo);
	}	

}
