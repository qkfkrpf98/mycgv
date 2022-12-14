package com.spring.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvMovieDAO;
import com.mycgv.vo.CgvMovieVO;
import com.spring.service.MovieServiceImpl;

@Controller
public class MainController {
	@Autowired
	private MovieServiceImpl  movieService;
	/**
	 * footer.do
	 */
	@RequestMapping(value="/footer.do", method=RequestMethod.GET)
	public String footer() {
		return "footer";
	}
	
	/**
	 * header.do
	 */
	@RequestMapping(value="/header.do", method=RequestMethod.GET)
	public String header() {
		return "header";
	}
	
	
	/**
	 * index.do
	 */
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String index() {
		
		return "/index";
	}

	
}
