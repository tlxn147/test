package kr.co.boardReply.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;


import kr.co.boardReply.dto.PublicServantBoardReply_Dto;

public class PublicServantBoardReply_Dao extends SqlSessionDaoSupport {

	//PublicServant��� ��ȸ
			public List<PublicServantBoardReply_Dto> publicServantReplyList(int postNo) throws Exception{
				
				return getSqlSession().selectList("boardReply.publicServant_commentsList",postNo);
			}
			
			//PublicServant��� �ۼ�
			public int publicServantReplyWrite(PublicServantBoardReply_Dto dto) throws Exception {
				return getSqlSession().insert("boardReply.publicServant_commentWrite",dto);
			}
			
			//PublicServant��� ����
			public int publicServantReplyUpdate(PublicServantBoardReply_Dto dto) throws Exception {
				return getSqlSession().update("boardReply.publicServant_commentUpdate",dto);
			}
			
			//PublicServant��� ����
			public int publicServantReplyDelete(int postNo, int commentsNo) throws Exception {
				
				HashMap<String,Integer> data = new HashMap<>();
				
				data.put("postNo", postNo);
				data.put("commentsNo", commentsNo);
				
				return getSqlSession().delete("boardReply.publicServant_commentDelete",data);
			}
			
			//PublicServant �ϳ��� �ۿ� ���� ��� ��  ��ȸ
			public int publicServantReplyCount(int postNo) throws Exception{
				return getSqlSession().selectOne("boardReply.publicServant_comments_count",postNo);
			}	
	
}