package com.force4us.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.force4us.entity.Page;
import com.force4us.service.QueryService;

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
		System.out.println(command);
		String description = req.getParameter("description");
		String currentPage = req.getParameter("currentPage");

		Page page = new Page();
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if (currentPage == null || !pattern.matcher(currentPage).matches()) {
			page.setCurrentPage(1);
		} else {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		System.out.println("---------------->开始查询");
		QueryService queryService = new QueryService();

		// 查询消息列表并传给页面

		req.setAttribute("messageList", queryService.queryMessageList(command, description, page));
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		req.setAttribute("page", page);
		// 跳转
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
