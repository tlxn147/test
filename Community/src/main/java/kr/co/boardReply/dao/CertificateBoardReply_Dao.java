package kr.co.boardReply.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.boardReply.dto.CertificateBoardReply_Dto;

public class CertificateBoardReply_Dao extends SqlSessionDaoSupport {
	//Certificate��� ��ȸ
	public List<CertificateBoardReply_Dto> certificateReplyList(int postNo) throws Exception{
		
		return getSqlSession().selectList("boardReply.certificate_commentsList",postNo);
	}
	
	//Certificate��� �ۼ�
	public int certificateReplyWrite(CertificateBoardReply_Dto dto) throws Exception {
		return getSqlSession().insert("boardReply.certificate_commentWrite",dto);
	}
	
	//Certificate��� ����
	public int certificateReplyUpdate(CertificateBoardReply_Dto dto) throws Exception {
		return getSqlSession().update("boardReply.certificate_commentUpdate",dto);
	}
	
	//Certificate��� ����
	public int certificateReplyDelete(int postNo, int commentsNo) throws Exception {
		
		HashMap<String,Integer> data = new HashMap<>();
		
		data.put("postNo", postNo);
		data.put("commentsNo", commentsNo);
		
		return getSqlSession().delete("boardReply.certificate_commentDelete",data);
	}
	
	//Certificate �ϳ��� �ۿ� ���� ��� ��  ��ȸ
	public int certificateReplyCount(int postNo) throws Exception{
		return getSqlSession().selectOne("boardReply.certificate_comments_count",postNo);
	}	

}