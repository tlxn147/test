package kr.co.boardReply.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.boardReply.dto.EtcBoardReply_Dto;

public class EtcBoardReply_Dao extends SqlSessionDaoSupport {
	//Etc��� ��ȸ
		public List<EtcBoardReply_Dto> etcReplyList(int postNo) throws Exception{
			
			return getSqlSession().selectList("boardReply.etc_commentsList",postNo);
		}
		
		//Etc��� �ۼ�
		public int etcReplyWrite(EtcBoardReply_Dto dto) throws Exception {
			return getSqlSession().insert("boardReply.etc_commentWrite",dto);
		}
		
		//Etc��� ����
		public int etcReplyUpdate(EtcBoardReply_Dto dto) throws Exception {
			return getSqlSession().update("boardReply.etc_commentUpdate",dto);
		}
		
		//Etc��� ����
		public int etcReplyDelete(int postNo, int commentsNo) throws Exception {
			
			HashMap<String,Integer> data = new HashMap<>();
			
			data.put("postNo", postNo);
			data.put("commentsNo", commentsNo);
			
			return getSqlSession().delete("boardReply.etc_commentDelete",data);
		}
		
		//Etc �ϳ��� �ۿ� ���� ��� ��  ��ȸ
		public int etcReplyCount(int postNo) throws Exception{
			return getSqlSession().selectOne("boardReply.etc_comments_count",postNo);
		}	

}