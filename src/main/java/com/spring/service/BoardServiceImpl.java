package com.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycgv.dao.CgvBoardDAO;
import com.mycgv.vo.CgvBoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private CgvBoardDAO  boardDao;
	
	/**
	 * ���� ó��
	 */
	@Override
	public int getDelete(String bid) {
		return boardDao.delete(bid);
	}
	
	/**
	 * ����ó��
	 */
	@Override
	public int getUpdate(CgvBoardVO vo) {
		return boardDao.update(vo);
	}
	
	/**
	 * ��ȸ�� ������Ʈ
	 */
	@Override
	public void getUpdateHits(String bid) {
		boardDao.updateHits(bid);
	}
	
	/**
	 * �Խñ� ����
	 */
	@Override
	public int getWriteResult(CgvBoardVO vo) {
		return boardDao.insert(vo);
	}
	
	/**
	 * �Խñ� �󼼺���
	 */
	@Override
	public CgvBoardVO getContent(String bid) {
		return boardDao.select(bid);
	}		
	
	/**
	 * �Խñ� ��ü �ο�
	 */
	@Override
	public int getTotalCount() {
		return boardDao.totalCount();
	}
	
	/**
	 * �Խñ� ��ü ����Ʈ
	 */
	@Override
	public ArrayList<CgvBoardVO> getList(int startCount, int endCount){
		ArrayList<CgvBoardVO> list = boardDao.select(startCount, endCount);
		return list;
	}

}
