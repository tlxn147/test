package kr.co.board.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.board.dto.EtcBoard_Dto;

public class EtcBoard_Dao extends SqlSessionDaoSupport {
	//Etc�Խ��� list��� + ����¡ +�˻�
			public List<EtcBoard_Dto> getEtcCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
				
				HashMap<String, Object> data = new HashMap<String, Object>();
				
				data.put("displayPost",displayPost);
				data.put("postNum",postNum);
				data.put("searchType", searchType);
				data.put("keyword", keyword);
				
				return getSqlSession().selectList("board.etc_board",data);
			}
			
			//Etc�� �ۼ�
			public int etcCategoryWrite(EtcBoard_Dto dto) throws Exception{
				return getSqlSession().insert("board.etc_write",dto);
			}
			
			//Etc�Խ��� ��ȸ
			public EtcBoard_Dto etcCategoryView(int postNo) throws Exception{
				return getSqlSession().selectOne("board.etc_view",postNo);
			}
			
			//Etc�� ��ȸ�� ����
		    public int etcViewCountUpdate(int postNo) throws Exception{
		    	return getSqlSession().update("board.etcViewCnt_update",postNo);
		    }
			
			//Etc �� ����
			public int etcCategoryUpdate(EtcBoard_Dto dto) throws Exception {
				return getSqlSession().update("board.etc_update",dto);
			}
			
			//Etc �� ����
			public int etcCategoryDelete(int postNo) throws Exception {
				return getSqlSession().delete("board.etc_delete",postNo);
			}
			
			//Etc �Խù� �� ���� + �˻�
			public int etcCategoryCount(String searchType, String keyword) throws Exception{
				
				HashMap<String,Object> data =new HashMap<>();
				
				data.put("searchType",searchType);
				data.put("keyword", keyword);
				
				return getSqlSession().selectOne("board.etc_count",data);
			}
			//Etc�Խ��� ��� ���� ����
			public int etcCommentsCountUpdate(int postNo, int commentsCount) throws Exception {
				
				HashMap<String,Object> data =new HashMap<>();
				data.put("postNo", postNo);
				data.put("commentsCount", commentsCount);
				
				return getSqlSession().update("board.etc_commentsCount_update",data);
				
			}
			
			//Main�������� ������ �ֱ� Etc�Խñ� 10�� ��ȸ
				public List<EtcBoard_Dto> getMainEtcCategory() throws Exception {
					return getSqlSession().selectList("board.etc_mainPage");
				}

}