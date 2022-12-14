package com.spring.service;

import java.util.ArrayList;

import com.mycgv.vo.CgvNoticeVO;

public interface NoticeService {
	int getTotalCount();	//�������� ��ü �ο��
	ArrayList<CgvNoticeVO> getList(int startCount, int endCount);  //�������� ��ü ����Ʈ
	CgvNoticeVO getContent(String nid);	//�������� �󼼺���
	void getUpdateHits(String nid);     //�������� ��ȸ�� ������Ʈ
	int getWriteResult(CgvNoticeVO vo);		//�������� �۾��� ó��
	int getUpdate(CgvNoticeVO vo);		//�������� ���� ó��
	int getDelete(String nid);		//�������� ���� ó��
}
