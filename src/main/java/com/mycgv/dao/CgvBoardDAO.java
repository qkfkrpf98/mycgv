package com.mycgv.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv.vo.CgvBoardVO;

@Repository
public class CgvBoardDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * totalCount : 전체 로우수 출력-mapper.board.totalcount
	 */
	public int totalCount() {
		return sqlSession.selectOne("mapper.board.totalcount");
	}
	
	/**
	 * delete : 게시글 삭제-mapper.board.delete
	 */
	public int delete(String bid) {
		return sqlSession.delete("mapper.board.delete",bid);
	}
	
	
	/**
	 * update : 게시글 수정-mapper.board.update
	 */
	public int update(CgvBoardVO vo) {		
		return sqlSession.update("mapper.board.update",vo);
	}
	
	/**
	 * updateHits : 조회수 업데이트 - mapper.board.updatehits
	 */
	public int updateHits(String bid) {
		return sqlSession.update("mapper.board.updatehits",bid);
	}
	
	/**
	 * select : 게시글 상세 보기-mapper.board.content
	 */
	public CgvBoardVO select(String bid) {
		return sqlSession.selectOne("mapper.board.content",bid);
	}
	
	/**
	 * select : 게시글 전체 리스트(페이징처리) - mapper.board.list
	 */
	public ArrayList<CgvBoardVO> select(int startCount, int endCount){
		
		//파라미터를 Map으로 정의하기
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("start", startCount);	
		param.put("end", endCount);
		
		List<CgvBoardVO> list = sqlSession.selectList("mapper.board.list", param);
		
		
		return (ArrayList<CgvBoardVO>)list;
	}
	
	
	/**
	 * insert : 게시글 등록 - mapper.board.insert
	 */
	public int insert(CgvBoardVO vo) {
		return sqlSession.insert("mapper.board.insert", vo);
	}
}






