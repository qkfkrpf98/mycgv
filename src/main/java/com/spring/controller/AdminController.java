package com.spring.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycgv.vo.CgvMemberVO;
import com.mycgv.vo.CgvMovieVO;
import com.mycgv.vo.CgvNoticeVO;
import com.spring.service.FileServiceImpl;
import com.spring.service.MemberServiceImpl;
import com.spring.service.MovieServiceImpl;
import com.spring.service.NoticeServiceImpl;
import com.spring.service.PageServiceImpl;

@Controller
public class AdminController {
	
	@Autowired
	private MemberServiceImpl memberService;
	
	@Autowired
	private NoticeServiceImpl noticeService;
	
	@Autowired
	private MovieServiceImpl  movieService;
	
	@Autowired
	private PageServiceImpl  pageService;
	
	@Autowired
	private FileServiceImpl  fileService;
	
	/**
	 * admin_movie_content.do : ��ȭ �� ����
	 */
	@RequestMapping(value="/admin_movie_content.do", method=RequestMethod.GET)
	public ModelAndView admin_movie_content(String mid) {
		ModelAndView mv = new ModelAndView();
		
		CgvMovieVO vo = movieService.getContent(mid);
		
		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_movie/admin_movie_content");
		
		
		return mv;
	}
	
	
	
	/**
	 * admin_movie_regist_check.do : ��ȭ ��� ó��
	 */
	@RequestMapping(value="/admin_movie_regist_check.do", method=RequestMethod.POST)
	public ModelAndView admin_movie_regist_check(CgvMovieVO vo, HttpServletRequest request) 
													throws Exception {
		ModelAndView mv = new ModelAndView();
		
		//������ �����ߴ��� üũ
		vo = fileService.multiFileCheck(vo);
		
		//DB ����
		//1. cgv_movie ���̺� ���� --> mid ����
		int result = movieService.getInsert(vo);
		
		if(result == 1) {
			//2. mid���� ��������
			String mid = movieService.getMid();
			
			//3. mid�� ���۷����ϴ� cgv_movie_file ���̺� ����
			vo.setMid(mid);
			int result2 = movieService.getInsertFile(vo);
			
			if(result2 ==1) {
				//������ upload�� ����
				fileService.multiFileSave(vo, request);
			}
			
		}		
		
		mv.setViewName("redirect:/admin_movie_list.do");
		
		return mv;
	}
	
	/**
	 * admin_movie_regist.do : ��ȭ ��� ȭ��
	 */
	@RequestMapping(value="/admin_movie_regist.do", method=RequestMethod.GET)
	public ModelAndView admin_movie_regist() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/admin/admin_movie/admin_movie_regist");
		
		return mv;
	}
	
	/**
	 * admin_movie_list.do : ��ü ��ȭ ����Ʈ
	 */
	@RequestMapping(value="/admin_movie_list.do", method=RequestMethod.GET)
	public ModelAndView admin_movie_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		
		Map<String, Integer> param = pageService.getPageResult(rpage, "movie", movieService);
		ArrayList<CgvMovieVO> list = movieService.getList(param.get("startCount"),param.get("endCount"));
		
		mv.addObject("list",list);
		mv.addObject("dbCount", param.get("dbCount"));
		mv.addObject("rpage", param.get("rpage"));
		mv.addObject("pageSize", param.get("pageSize"));
		mv.setViewName("/admin/admin_movie/admin_movie_list");
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/movie_list_json.do", method=RequestMethod.GET
			, produces="text/plain;charset=UTF-8")
	public String movie_list_json() {
		System.out.println("controller");
		ArrayList<CgvMovieVO> list = movieService.selectList();
		
		JsonObject jobject = new JsonObject(); //CgvNoticeVO
		JsonArray jarray = new JsonArray();  //ArrayList
		Gson gson = new Gson();
		
		for(CgvMovieVO vo:list) {
			JsonObject jo = new JsonObject();
			jo.addProperty("mid", vo.getMid());
			jo.addProperty("mname", vo.getMname());
			jo.addProperty("msfile", vo.getMsfile1());
			
			jarray.add(jo);
		}
		
		jobject.add("list", jarray);
		System.out.println(jobject);
		return gson.toJson(jobject);
	}
	
	
	
	/**
	 * admin_notice_delete_check.do : �������� ���� ó��
	 */
	@RequestMapping(value="/admin_notice_delete_check.do", method=RequestMethod.POST)
	public ModelAndView admin_notice_delete_check(String nid, HttpServletRequest request)
															throws Exception {
		ModelAndView mv = new ModelAndView();
	
		CgvNoticeVO vo = noticeService.getContent(nid);
		int result = noticeService.getDelete(nid);
		
		if(result == 1){	
			fileService.fileDelete(vo, request);
			mv.setViewName("redirect:/admin_notice_list.do");
		}else{

			mv.setViewName("error_page");
		}		
		
		return mv;
	}
	
	
	/**
	 * admin_notice_update_check.do : �������� ���� ó��
	 */
	@RequestMapping(value="/admin_notice_update_check.do", method=RequestMethod.POST)
	public ModelAndView admin_notice_update_check(CgvNoticeVO vo, HttpServletRequest request)
																throws Exception {
		ModelAndView mv = new ModelAndView();

		String old_filename = vo.getNsfile();	//����ȭ�鿡�� hidden���� �Ѿ���� ���� upload ������ ����� ���ϸ�
		
		vo = fileService.update_fileCheck(vo);
		int result = noticeService.getUpdate(vo);
		
		if(result == 1){
			//���ο� ������ upload ������ ������ �� ������ ������ ����
			fileService.update_filesave(vo, request, old_filename);
			mv.setViewName("redirect:/admin_notice_list.do");
			
		}else{

			mv.setViewName("error_page");
		}		
		
		return mv;
	}
	
	
	/**
	 * admin_notice_write_check.do : �������� �۾��� ó��
	 */
	@RequestMapping(value="/admin_notice_write_check.do", method=RequestMethod.POST)
	public ModelAndView admin_notice_write_check(CgvNoticeVO vo, HttpServletRequest request )
															throws Exception {
		ModelAndView mv = new ModelAndView();
		
		vo = fileService.fileCheck(vo);
		int result = noticeService.getWriteResult(vo);
		
		if(result == 1){			
			fileService.fileSave(vo, request);
			mv.setViewName("redirect:/admin_notice_list.do");
		}else{

			mv.setViewName("error_page");
		}		
		
		return mv;
	}
	
	
	/**
	 * admin_notice_write.do : �������� �۾��� ȭ�� 
	 */
	@RequestMapping(value="/admin_notice_write.do", method=RequestMethod.GET)
	public String admin_notice_write() {
		return "/admin/admin_notice/admin_notice_write";
	}
	
	/**
	 * admin_notice_delete.do : �������� ���� ȭ�� 
	 */
	@RequestMapping(value="/admin_notice_delete.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_delete(String nid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("nid", nid);
		mv.setViewName("/admin/admin_notice/admin_notice_delete");
		
		return mv;
	}
	
	/**
	 * admin_notice_update.do : �������� ����ȭ�� 
	 */
	@RequestMapping(value="/admin_notice_update.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_update(String nid) {
		ModelAndView mv = new ModelAndView();
		
		CgvNoticeVO vo = noticeService.getContent(nid);
		
		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_notice/admin_notice_update");
		
		return mv;
	}
	
	/**
	 * admin_notice_content.do : �������� �� ���� 
	 */
	@RequestMapping(value="/admin_notice_content.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_content(String nid) {
		ModelAndView mv = new ModelAndView();
		
		CgvNoticeVO vo = noticeService.getContent(nid);
		
		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_notice/admin_notice_content");
		
		return mv;
	}
	
	
	/**
	 * admin_notice_list.do : �������� ��ü����Ʈ 
	 */
	@RequestMapping(value="/admin_notice_list.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		
		Map<String, Integer> param = pageService.getPageResult(rpage, "notice", noticeService);
		ArrayList<CgvNoticeVO> list = noticeService.getList(param.get("startCount"),param.get("endCount"));
		
		mv.addObject("list",list);
		mv.addObject("dbCount", param.get("dbCount"));
		mv.addObject("rpage", param.get("rpage"));
		mv.addObject("pageSize", param.get("pageSize"));
		mv.setViewName("/admin/admin_notice/admin_notice_list");
		
		return mv;
	}
	
	/**
	 * admin_member_content.do : ȸ�� �� ���� 
	 */
	@RequestMapping(value="/admin_member_content.do", method=RequestMethod.GET)
	public ModelAndView admin_member_content(String id) {
		ModelAndView mv = new ModelAndView();

		CgvMemberVO vo = memberService.getMemberContent(id);
		String address = vo.getZonecode()+" "+vo.getAddr1()+" "+ vo.getAddr2();
		
		mv.addObject("vo", vo);
		mv.addObject("address", address);
		mv.setViewName("/admin/admin_member/admin_member_content");
		
		return mv;
	}
	
	/**
	 * admin_member_list.do : ȸ�� ��ü����Ʈ 
	 */
	@RequestMapping(value="/admin_member_list.do", method=RequestMethod.GET)
	public ModelAndView admin_member_list(String rpage) {
		ModelAndView mv = new ModelAndView();
			
		Map<String, Integer> param = pageService.getPageResult(rpage, "member", memberService);
		ArrayList<CgvMemberVO> list = memberService.getMemberList(param.get("startCount"), param.get("endCount"));
		
		mv.addObject("list", list);
		mv.addObject("dbCount", param.get("dbCount"));
		mv.addObject("rpage", param.get("rpage"));
		mv.addObject("pageSize", param.get("pageSize"));
		mv.setViewName("/admin/admin_member/admin_member_list");		
		
		return mv;
	}
	
	/**
	 * admin.do : ������ ������ 
	 */
	@RequestMapping(value="/admin.do", method=RequestMethod.GET)
	public String admin() {
		return "/admin/admin";
	}
}
