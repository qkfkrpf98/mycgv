package com.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycgv.dao.CgvMovieDAO;
import com.mycgv.vo.CgvMovieVO;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private CgvMovieDAO movieDao;
	
	/**
	 * ��ȭ ��ü �ο��
	 */
	@Override
	public int getTotalCount() {
		return movieDao.totalCount();
	}
	
	/**
	 * ��ȭ ��ü ����Ʈ
	 */
	@Override
	public ArrayList<CgvMovieVO> getList(int startCount, int endCount){
		return movieDao.select(startCount, endCount);
	}
	
	/**
	 * ��ȭ ���
	 */
	@Override
	public int getInsert(CgvMovieVO vo) {
		return movieDao.insert(vo);
	}
	
	/**
	 * ��ȭ ��� �� mid ��������
	 */
	@Override
	public String getMid() {
		return movieDao.selectMid();
	}
	
	/**
	 * ��ȭ ���� ���
	 */
	@Override
	public int getInsertFile(CgvMovieVO vo) {
		return movieDao.insert_file(vo);
	}
	
	/**
	 * ��ȭ �󼼺���
	 */
	@Override
	public CgvMovieVO getContent(String mid) {
		return movieDao.select(mid);
	}
	
	@Override
	public ArrayList<CgvMovieVO> selectList() {
		return movieDao.selectAll();
	}

}









