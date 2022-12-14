package com.mycgv.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv.vo.CgvMemberVO;
import com.mycgv.vo.SessionVO;

@Repository
public class CgvMemberDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * totalCount : ��ü �ο�� ���
	 */
	public int totalCount() {		
		return sqlSession.selectOne("mapper.member.total_count");
	}
	
	/**
	 * idCheck : ���̵� �ߺ�üũ
	 */
	public int idCheck(String id) {	
		return sqlSession.selectOne("mapper.member.idcheck", id);
	}
	
	/**
	 * selectContent : ȸ�� �� ����
	 */
	public CgvMemberVO selectContent(String id) {
		return sqlSession.selectOne("mapper.member.content", id);	
	}
	
	/**
	 * selectAll : ȸ�� ��ü ����Ʈ
	 */
	public ArrayList<CgvMemberVO> selectAll(int startCount, int endCount){		
		//Map Ÿ���� ��ü�� �����ϰ� start, end��� Ű�̸����� �Ķ���͸� �ѱ��.
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		List<CgvMemberVO> list = sqlSession.selectList("mapper.member.listAll", param);
		
		return (ArrayList<CgvMemberVO>)list;
	}
	
	/**
	 * select : �α���
	 */
	public SessionVO select(CgvMemberVO vo) {
		return sqlSession.selectOne("mapper.member.login",vo);
	}	
	
	
	/**
	 * insert : ȸ������ 
	 */
	public int insert(CgvMemberVO vo) {
		//sqlSession ��ü�� �޼ҵ带 ȣ���Ͽ� �������� ������		
		return sqlSession.insert("mapper.member.join", vo);
	}
}