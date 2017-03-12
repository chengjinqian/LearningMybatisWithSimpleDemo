package com.force4us.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.force4us.service.ListService;

/**
 * 
 * 列表页面初始化控制
 *
 */
public class ListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置接收参数的编码格式
		req.setCharacterEncoding("UTF-8");
		// 接收请求参数
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		// 像页面传值
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		// 查询消息列表并传给页面
		ListService listService = new ListService();
		req.setAttribute("messageList", listService.queryMessageList(command, description));
		// 跳转
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
