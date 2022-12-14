package com.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycgv.dao.CgvNoticeDAO;
import com.mycgv.vo.CgvNoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private CgvNoticeDAO noticeDao;
	
	/**
	 * �������� ���� ó��
	 */
	@Override
	public int getDelete(String nid) {
		return noticeDao.delete(nid);
	}
	
	/**
	 * �������� ���� ó��
	 */
	@Override
	public int getUpdate(CgvNoticeVO vo) {
		return noticeDao.update(vo);
	}
	
	/**
	 * �������� �۾��� ó��
	 */
	@Override
	public int getWriteResult(CgvNoticeVO vo) {
		return noticeDao.insert(vo);
	}
	
	/**
	 * �������� ��ȸ�� ������Ʈ
	 */
	@Override
	public void getUpdateHits(String nid) {
		noticeDao.updateHits(nid);
	}
	
	
	/**
	 * �������� �󼼺���
	 */
	@Override
	public CgvNoticeVO getContent(String nid) {
		return noticeDao.select(nid);
	}
	
	/**
	 * �������� ��ü �ο��
	 */
	@Override
	public int getTotalCount() {
		return noticeDao.totalCount();
	}
	
	/**
	 * �������� ��ü ����Ʈ
	 */
	@Override
	public ArrayList<CgvNoticeVO> getList(int startCount, int endCount){
		return noticeDao.select(startCount,endCount);
	}
}
