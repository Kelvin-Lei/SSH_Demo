package com.kelvin.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.kelvin.dao.HelloDao;

@Controller
public class HelloAction {

	@Resource
	private HelloDao dao;
	
	private String msg;
	
	public String execute() {
		//通过dao获取输出消息
		msg = dao.say();
		return "success";
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
