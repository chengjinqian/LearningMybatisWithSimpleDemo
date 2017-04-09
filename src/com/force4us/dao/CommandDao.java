package com.force4us.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.force4us.bean.Command;
import com.force4us.config.sqlxml.CommandMapper;
import com.force4us.db.DBAccess;

public class CommandDao {
	/*
	 * mybatis方式： 根据查询条件查询指令列表
	 */
	public List<Command> queryCommandList(String name, String description) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		List<Command> commandList = new ArrayList<Command>();
		try {
			sqlSession = dbAccess.getSqlSession();
			Command command = new Command();
			command.setName(name);
			command.setDescription(description);
			// 通过sqlSession执行SQL语句
			// messageList = sqlSession.selectList("Message.queryMessageList",
			// message);// 这个Message是User.xml里面mapping标签的namespace
			CommandMapper mapper = sqlSession.getMapper(CommandMapper.class);
			commandList = mapper.queryCommandList(name, description);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return commandList;
	}
}
