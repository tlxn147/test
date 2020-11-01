package kr.co.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.board.dao.CertificateBoard_Dao;
import kr.co.board.dao.EtcBoard_Dao;
import kr.co.board.dao.ItBoard_Dao;
import kr.co.board.dao.LanguageBoard_Dao;
import kr.co.board.dao.PublicServantBoard_Dao;
import kr.co.board.dto.CertificateBoard_Dto;
import kr.co.board.dto.EtcBoard_Dto;
import kr.co.board.dto.ItBoard_Dto;
import kr.co.board.dto.LanguageBoard_Dto;
import kr.co.board.dto.PublicServantBoard_Dto;
import kr.co.boardReply.dao.CertificateBoardReply_Dao;
import kr.co.boardReply.dao.EtcBoardReply_Dao;
import kr.co.boardReply.dao.ItBoardReply_Dao;
import kr.co.boardReply.dao.LanguageBoardReply_Dao;
import kr.co.boardReply.dao.PublicServantBoardReply_Dao;
import kr.co.boardReply.dto.CertificateBoardReply_Dto;
import kr.co.boardReply.dto.EtcBoardReply_Dto;
import kr.co.boardReply.dto.ItBoardReply_Dto;
import kr.co.boardReply.dto.LanguageBoardReply_Dto;
import kr.co.boardReply.dto.PublicServantBoardReply_Dto;
import lombok.Setter;

@Service
public class BoardService {
	
	@Setter
	@Autowired
	private ItBoard_Dao itDao; 
	
	@Setter
	@Autowired
	private LanguageBoard_Dao languageDao;
	
	@Setter
	@Autowired
	private PublicServantBoard_Dao publicServantDao;
	
	@Setter
	@Autowired
	private CertificateBoard_Dao certificateDao;
	
	@Setter
	@Autowired
	private EtcBoard_Dao etcDao;
	
	@Setter
	@Autowired
	private ItBoardReply_Dao itReplyDao;
	
	@Setter
	@Autowired
	private LanguageBoardReply_Dao languageReplyDao;
	
	@Setter
	@Autowired
	private PublicServantBoardReply_Dao publicServantReplyDao;
	
	@Setter
	@Autowired
	private CertificateBoardReply_Dao certificateReplyDao;
	
	@Setter
	@Autowired
	private EtcBoardReply_Dao etcReplyDao;
	
	
	
	//IT게시판 list출력 + 페이징
	public List<ItBoard_Dto> getItCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
		
		
		return itDao.getItCategoryList(displayPost, postNum, searchType, keyword);
	}
	
	//It 게시판 글 작성
	public int itCategoryWrite(ItBoard_Dto dto) throws Exception {
		return itDao.itCategoryWrite(dto);
	}
	
	//It 게시판 글 조회
	public ItBoard_Dto itCategoryView(int postNo) throws Exception {
		return itDao.itCategoryView(postNo);
	}
	
	//IT글 조회수 변경
    public int itViewCountUpdate(int postNo) throws Exception{
    	return itDao.itViewCountUpdate(postNo);
    }
	
	//It 글 수정
	public int itCategoryUpdate(ItBoard_Dto dto) throws Exception{
		return itDao.itCategoryUpdate(dto);
	}
	
	//It 글 삭제
	public int itCategoryDelete(int postNo) throws Exception{
		return itDao.itCategoryDelete(postNo);
	}
	
	//It 게시물 총 갯수 + 검색
	public int itCategoryCount(String searchType, String keyword) throws Exception{
		return itDao.itCategoryCount(searchType, keyword);
	}
	
	//IT게시판 답글 갯수 수정
	public int itCommentsCountUpdate(int postNo, int commentsCount) throws Exception{
		return itDao.itCommentsCountUpdate(postNo, commentsCount);
	}
	
	//Main페이지에 보여줄 최근 it게시글 10개 조회
  	public List<ItBoard_Dto> getMainItCategory() throws Exception{
  		return itDao.getMainItCategory();
  	}
	
  	
	//IT댓글 조회
    public List<ItBoardReply_Dto> itReplyList(int postNo) throws Exception{
    	return itReplyDao.itReplyList(postNo);
    }
    
    //IT댓글 작성
  	public int itReplyWrite(ItBoardReply_Dto dto) throws Exception {
  		return itReplyDao.itReplyWrite(dto);
  	}
  	
  	//IT댓글 수정
  	public int itReplyUpdate(ItBoardReply_Dto dto) throws Exception {
  		return itReplyDao.itReplyUpdate(dto);
  	}
  	
  	//IT댓글 삭제
  	public int itReplyDelete(int postNo, int commentsNo) throws Exception {
  		return itReplyDao.itReplyDelete(postNo, commentsNo);
  	}
  	
    //IT 하나의 글에 대한 답글 수  조회
  	public int itReplyCount(int postNo) throws Exception {
  		return itReplyDao.itReplyCount(postNo);
  	}
  	
    
  	
  //--------------------------------------------------------------------------------------------------------------------------
  	
  //Language게시판 list출력 + 페이징
  		public List<LanguageBoard_Dto> getLanguageCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
  			
  			
  			return languageDao.getLanguageCategoryList(displayPost, postNum, searchType, keyword);
  		}
  		
  		//Language 게시판 글 작성
  		public int languageCategoryWrite(LanguageBoard_Dto dto) throws Exception {
  			return languageDao.languageCategoryWrite(dto);
  		}
  		
  		//Language 게시판 글 조회
  		public LanguageBoard_Dto languageCategoryView(int postNo) throws Exception {
  			return languageDao.languageCategoryView(postNo);
  		}
  		
  	    //Language글 조회수 변경
  	    public int languageViewCountUpdate(int postNo) throws Exception{
  	    	return languageDao.languageViewCountUpdate(postNo);
  	    }
  		
  		//Language 글 수정
  		public int languageCategoryUpdate(LanguageBoard_Dto dto) throws Exception{
  			return languageDao.languageCategoryUpdate(dto);
  		}
  		
  		//Language 글 삭제
  		public int languageCategoryDelete(int postNo) throws Exception{
  			return languageDao.languageCategoryDelete(postNo);
  		}
  		
  		//Language 게시물 총 갯수 + 검색
  		public int languageCategoryCount(String searchType, String keyword) throws Exception{
  			return languageDao.languageCategoryCount(searchType, keyword);
  		}
  		
  		//Language게시판 답글 갯수 수정
  		public int languageCommentsCountUpdate(int postNo, int commentsCount) throws Exception{
  			return languageDao.languageCommentsCountUpdate(postNo, commentsCount);
  		}
  		
  	    //Main페이지에 보여줄 최근 language게시글 10개 조회
  	  	public List<LanguageBoard_Dto> getMainLanguageCategory() throws Exception{
  	  		return languageDao.getMainLanguageCategory();
  	  	}
  		
  		//Language댓글 조회
  	    public List<LanguageBoardReply_Dto> languageReplyList(int postNo) throws Exception{
  	    	return languageReplyDao.languageReplyList(postNo);
  	    }
  	    
  	    //Language댓글 작성
  	  	public int languageReplyWrite(LanguageBoardReply_Dto dto) throws Exception {
  	  		return languageReplyDao.languageReplyWrite(dto);
  	  	}
  	  	
  	  	//Language댓글 수정
  	  	public int languageReplyUpdate(LanguageBoardReply_Dto dto) throws Exception {
  	  		return languageReplyDao.languageReplyUpdate(dto);
  	  	}
  	  	
  	  	//Language댓글 삭제
  	  	public int languageReplyDelete(int postNo, int commentsNo) throws Exception {
  	  		return languageReplyDao.languageReplyDelete(postNo, commentsNo);
  	  	}
  	  	
  	    //Language 하나의 글에 대한 답글 수  조회
  	  	public int languageReplyCount(int postNo) throws Exception {
  	  		return languageReplyDao.languageReplyCount(postNo);
  	  	}
  	
  	  //--------------------------------------------------------------------------------------------------------------------------
  	  	
  	  //PublicServant게시판 list출력 + 페이징
  	  		public List<PublicServantBoard_Dto> getPublicServantCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
  	  			
  	  			
  	  			return publicServantDao.getPublicServantCategoryList(displayPost, postNum, searchType, keyword);
  	  		}
  	  		
  	  		//PublicServant 게시판 글 작성
  	  		public int publicServantCategoryWrite(PublicServantBoard_Dto dto) throws Exception {
  	  			return publicServantDao.publicServantCategoryWrite(dto);
  	  		}
  	  		
  	  		//PublicServant 게시판 글 조회
  	  		public PublicServantBoard_Dto publicServantCategoryView(int postNo) throws Exception {
  	  			return publicServantDao.publicServantCategoryView(postNo);
  	  		}
  	  		
  	  	    //PublicServant글 조회수 변경
  	  	    public int publicServantViewCountUpdate(int postNo) throws Exception{
  	  	    	return publicServantDao.publicServantViewCountUpdate(postNo);
  	  	    }
  	  		
  	  		//PublicServant 글 수정
  	  		public int publicServantCategoryUpdate(PublicServantBoard_Dto dto) throws Exception{
  	  			return publicServantDao.publicServantCategoryUpdate(dto);
  	  		}
  	  		
  	  		//PublicServant 글 삭제
  	  		public int publicServantCategoryDelete(int postNo) throws Exception{
  	  			return publicServantDao.publicServantCategoryDelete(postNo);
  	  		}
  	  		
  	  		//PublicServant 게시물 총 갯수 + 검색
  	  		public int publicServantCategoryCount(String searchType, String keyword) throws Exception{
  	  			return publicServantDao.publicServantCategoryCount(searchType, keyword);
  	  		}
  	  		
  	  		//PublicServant게시판 답글 갯수 수정
  	  		public int publicServantCommentsCountUpdate(int postNo, int commentsCount) throws Exception{
  	  			return publicServantDao.publicServantCommentsCountUpdate(postNo, commentsCount);
  	  		}
  	  		
  	  	    //Main페이지에 보여줄 최근 PublicServant게시글 10개 조회
  	  	  	public List<PublicServantBoard_Dto> getMainPublicServantCategory() throws Exception{
  	  	  		return publicServantDao.getMainPublicServantCategory();
  	  	  	}
  	  		
  	  		//PublicServant댓글 조회
  	  	    public List<PublicServantBoardReply_Dto> publicServantReplyList(int postNo) throws Exception{
  	  	    	return publicServantReplyDao.publicServantReplyList(postNo);
  	  	    }
  	  	    
  	  	    //PublicServant댓글 작성
  	  	  	public int publicServantReplyWrite(PublicServantBoardReply_Dto dto) throws Exception {
  	  	  		return publicServantReplyDao.publicServantReplyWrite(dto);
  	  	  	}
  	  	  	
  	  	  	//PublicServant댓글 수정
  	  	  	public int publicServantReplyUpdate(PublicServantBoardReply_Dto dto) throws Exception {
  	  	  		return publicServantReplyDao.publicServantReplyUpdate(dto);
  	  	  	}
  	  	  	
  	  	  	//PublicServant댓글 삭제
  	  	  	public int publicServantReplyDelete(int postNo, int commentsNo) throws Exception {
  	  	  		return publicServantReplyDao.publicServantReplyDelete(postNo, commentsNo);
  	  	  	}
  	  	  	
  	  	    //PublicServant 하나의 글에 대한 답글 수  조회
  	  	  	public int publicServantReplyCount(int postNo) throws Exception {
  	  	  		return publicServantReplyDao.publicServantReplyCount(postNo);
  	  	  	}

  	  	  //--------------------------------------------------------------------------------------------------------------------------
  	  	  	
  	  	  //Certificate게시판 list출력 + 페이징
  	  	  		public List<CertificateBoard_Dto> getCertificateCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
  	  	  			
  	  	  			
  	  	  			return certificateDao.getCertificateCategoryList(displayPost, postNum, searchType, keyword);
  	  	  		}
  	  	  		
  	  	  		//Certificate 게시판 글 작성
  	  	  		public int certificateCategoryWrite(CertificateBoard_Dto dto) throws Exception {
  	  	  			return certificateDao.certificateCategoryWrite(dto);
  	  	  		}
  	  	  		
  	  	  		//Certificate 게시판 글 조회
  	  	  		public CertificateBoard_Dto certificateCategoryView(int postNo) throws Exception {
  	  	  			return certificateDao.certificateCategoryView(postNo);
  	  	  		}
  	  	  		
  	  	  	    //Certificate글 조회수 변경
  	  	  	    public int certificateViewCountUpdate(int postNo) throws Exception{
  	  	  	    	return certificateDao.certificateViewCountUpdate(postNo);
  	  	  	    }
  	  	  		
  	  	  		//Certificate 글 수정
  	  	  		public int certificateCategoryUpdate(CertificateBoard_Dto dto) throws Exception{
  	  	  			return certificateDao.certificateCategoryUpdate(dto);
  	  	  		}
  	  	  		
  	  	  		//Certificate 글 삭제
  	  	  		public int certificateCategoryDelete(int postNo) throws Exception{
  	  	  			return certificateDao.certificateCategoryDelete(postNo);
  	  	  		}
  	  	  		
  	  	  		//Certificate 게시물 총 갯수 + 검색
  	  	  		public int certificateCategoryCount(String searchType, String keyword) throws Exception{
  	  	  			return certificateDao.certificateCategoryCount(searchType, keyword);
  	  	  		}
  	  	  		
  	  	  		//Certificate게시판 답글 갯수 수정
  	  	  		public int certificateCommentsCountUpdate(int postNo, int commentsCount) throws Exception{
  	  	  			return certificateDao.certificateCommentsCountUpdate(postNo, commentsCount);
  	  	  		}
  	  	  		
  	  	  	    //Main페이지에 보여줄 최근 Certificate게시글 10개 조회
  	  	  	  	public List<CertificateBoard_Dto> getMainCertificateCategory() throws Exception{
  	  	  	  		return certificateDao.getMainCertificateCategory();
  	  	  	  	}
  	  	  		
  	  	  		//Certificate댓글 조회
  	  	  	    public List<CertificateBoardReply_Dto> certificateReplyList(int postNo) throws Exception{
  	  	  	    	return certificateReplyDao.certificateReplyList(postNo);
  	  	  	    }
  	  	  	    
  	  	  	    //Certificate댓글 작성
  	  	  	  	public int certificateReplyWrite(CertificateBoardReply_Dto dto) throws Exception {
  	  	  	  		return certificateReplyDao.certificateReplyWrite(dto);
  	  	  	  	}
  	  	  	  	
  	  	  	  	//Certificate댓글 수정
  	  	  	  	public int certificateReplyUpdate(CertificateBoardReply_Dto dto) throws Exception {
  	  	  	  		return certificateReplyDao.certificateReplyUpdate(dto);
  	  	  	  	}
  	  	  	  	
  	  	  	  	//Certificate댓글 삭제
  	  	  	  	public int certificateReplyDelete(int postNo, int commentsNo) throws Exception {
  	  	  	  		return certificateReplyDao.certificateReplyDelete(postNo, commentsNo);
  	  	  	  	}
  	  	  	  	
  	  	  	    //Certificate 하나의 글에 대한 답글 수  조회
  	  	  	  	public int certificateReplyCount(int postNo) throws Exception {
  	  	  	  		return certificateReplyDao.certificateReplyCount(postNo);
  	  	  	  	}  

  	  	  	  //--------------------------------------------------------------------------------------------------------------------------
  	  	  	  	
  	  	  	  //Etc게시판 list출력 + 페이징
  	  	  	  		public List<EtcBoard_Dto> getEtcCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
  	  	  	  			
  	  	  	  			
  	  	  	  			return etcDao.getEtcCategoryList(displayPost, postNum, searchType, keyword);
  	  	  	  		}
  	  	  	  		
  	  	  	  		//Etc 게시판 글 작성
  	  	  	  		public int etcCategoryWrite(EtcBoard_Dto dto) throws Exception {
  	  	  	  			return etcDao.etcCategoryWrite(dto);
  	  	  	  		}
  	  	  	  		
  	  	  	  		//Etc 게시판 글 조회
  	  	  	  		public EtcBoard_Dto etcCategoryView(int postNo) throws Exception {
  	  	  	  			return etcDao.etcCategoryView(postNo);
  	  	  	  		}
  	  	  	  		
  	  	  	  	    //Etc글 조회수 변경
  	  	  	  	    public int etcViewCountUpdate(int postNo) throws Exception{
  	  	  	  	    	return etcDao.etcViewCountUpdate(postNo);
  	  	  	  	    }
  	  	  	  		
  	  	  	  		//Etc 글 수정
  	  	  	  		public int etcCategoryUpdate(EtcBoard_Dto dto) throws Exception{
  	  	  	  			return etcDao.etcCategoryUpdate(dto);
  	  	  	  		}
  	  	  	  		
  	  	  	  		//Etc 글 삭제
  	  	  	  		public int etcCategoryDelete(int postNo) throws Exception{
  	  	  	  			return etcDao.etcCategoryDelete(postNo);
  	  	  	  		}
  	  	  	  		
  	  	  	  		//Etc 게시물 총 갯수 + 검색
  	  	  	  		public int etcCategoryCount(String searchType, String keyword) throws Exception{
  	  	  	  			return etcDao.etcCategoryCount(searchType, keyword);
  	  	  	  		}
  	  	  	  		
  	  	  	  		//Etc게시판 답글 갯수 수정
  	  	  	  		public int etcCommentsCountUpdate(int postNo, int commentsCount) throws Exception{
  	  	  	  			return etcDao.etcCommentsCountUpdate(postNo, commentsCount);
  	  	  	  		}
  	  	  	  		
  	  	  	  	    //Main페이지에 보여줄 최근 Etc게시글 10개 조회
  	  	  	  	  	public List<EtcBoard_Dto> getMainEtcCategory() throws Exception{
  	  	  	  	  		return etcDao.getMainEtcCategory();
  	  	  	  	  	}
  	  	  	  		
  	  	  	  		//Etc댓글 조회
  	  	  	  	    public List<EtcBoardReply_Dto> etcReplyList(int postNo) throws Exception{
  	  	  	  	    	return etcReplyDao.etcReplyList(postNo);
  	  	  	  	    }
  	  	  	  	    
  	  	  	  	    //Etc댓글 작성
  	  	  	  	  	public int etcReplyWrite(EtcBoardReply_Dto dto) throws Exception {
  	  	  	  	  		return etcReplyDao.etcReplyWrite(dto);
  	  	  	  	  	}
  	  	  	  	  	
  	  	  	  	  	//Etc댓글 수정
  	  	  	  	  	public int etcReplyUpdate(EtcBoardReply_Dto dto) throws Exception {
  	  	  	  	  		return etcReplyDao.etcReplyUpdate(dto);
  	  	  	  	  	}
  	  	  	  	  	
  	  	  	  	  	//Etc댓글 삭제
  	  	  	  	  	public int etcReplyDelete(int postNo, int commentsNo) throws Exception {
  	  	  	  	  		return etcReplyDao.etcReplyDelete(postNo, commentsNo);
  	  	  	  	  	}
  	  	  	  	  	
  	  	  	  	    //Etc 하나의 글에 대한 답글 수  조회
  	  	  	  	  	public int etcReplyCount(int postNo) throws Exception {
  	  	  	  	  		return etcReplyDao.etcReplyCount(postNo);
  	  	  	  	  	}    	  	  	  	
  	  	  	  	
}
