package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.vo.CgvMemberVO;
import com.spring.service.MemberServiceImpl;

@Controller
public class JoinController {
	
	@Autowired
	private MemberServiceImpl memberService;
	
	/**
	 * id_check.do : 아이디 중복체크 처리 - Ajax 호출
	 */
	@ResponseBody
	@RequestMapping(value="/id_check.do", method=RequestMethod.GET)
	public String idCheck(String id) {
		int result = memberService.getIdCheck(id);
		return String.valueOf(result);
	}
	
	
	/**
	 * joinCheck.do : 회원가입 처리
	 */
	@RequestMapping(value="/joinCheck.do", method=RequestMethod.POST)
	public ModelAndView joinCheck(CgvMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		
		int result = memberService.getJoinResult(vo);
		
		if(result == 1){
			mv.addObject("join_result","ok");
			mv.setViewName("/login/login");
		}else{
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
	/**
	 * join.do : 회원가입 폼
	 */
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String join() {
		return "/join/join";
	}
}
