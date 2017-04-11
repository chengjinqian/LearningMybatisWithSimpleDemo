package com.force4us.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.force4us.bean.Message;
import com.force4us.config.sqlxml.PageMapper;
import com.force4us.db.DBAccess;

public class MessageDao {
	/*
	 * mybatis方式： 根据查询条件查询消息列表
	 */
	public List<Message> queryMessageList(Map<String, Object> parameter) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		List<Message> messageList = new ArrayList<Message>();
		try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句
			// messageList = sqlSession.selectList("Message.queryMessageList",
			// message);// 这个Message是User.xml里面mapping标签的namespace
			PageMapper mapper = sqlSession.getMapper(PageMapper.class);
			messageList = mapper.queryMessageList(parameter);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return messageList;
	}

	/**
	 * 根据查询条件查询消息列表的条数
	 */
	public int count(Message message) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句
			PageMapper mapper = sqlSession.getMapper(PageMapper.class);
			result = mapper.count(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}

	/**
	 * 根据查询条件分页查询消息列表
	 */
	public List<Message> queryMessageListByPage(Map<String, Object> parameter) {
		DBAccess dbAccess = new DBAccess();
		List<Message> messageList = new ArrayList<Message>();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句
			PageMapper imessage = sqlSession.getMapper(PageMapper.class);
			messageList = imessage.queryMessageListByPage(parameter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return messageList;
	}

	public void deleteOne(int id) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			sqlSession.delete("com.force4us.config.sqlxml.MessageMapper.deleteOne", id);
			sqlSession.commit();// 注意提交事务
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}

	}

	public void deleteBatch(List<Integer> ids) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			sqlSession.delete("MessageMapper.deleteBatch", ids);
			sqlSession.commit();// 注意提交事务
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}

	}

	// public static void main(String[] args) {
	// MessageDao messageDao = new MessageDao();
	// messageDao.queryMessageList("", "");
	// }
	/*
	 * 传统jdbc方式：根据查询条件查询消息列表
	 */
	// public List<Message> queryMessageList(String command, String description)
	// {
	// List<Message> messageList = new ArrayList<Message>();
	// try {
	// Class.forName("com.mysql.jdbc.Driver");
	// try {
	// Connection conn = (Connection)
	// DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/micro_message",
	// "root", "2011210847");
	// StringBuilder sql = new StringBuilder("select
	// ID,COMMAND,DESCRIPTION,CONTENT from message where 1=1");
	// List<String> paramList = new ArrayList<String>();
	// if (command != null && !"".equals(command.trim())) {
	// sql.append(" and COMMAND=?");
	// paramList.add(command);
	// }
	// if (description != null && !"".equals(description.trim())) {
	// sql.append(" and DESCRIPTION like '%' ? '%'");
	// paramList.add(description);
	// }
	// PreparedStatement statement = (PreparedStatement)
	// conn.prepareStatement(sql.toString());
	// for (int i = 0; i < paramList.size(); i++) {
	// statement.setString(i + 1, paramList.get(i));
	// }
	// ResultSet rs = statement.executeQuery();
	//
	// while (rs.next()) {
	// Message message = new Message();
	// messageList.add(message);
	// message.setId(rs.getString("ID"));
	// message.setCommand(rs.getString("COMMAND"));
	// message.setDescription(rs.getString("DESCRIPTION"));
	// message.setContent(rs.getString("CONTENT"));
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// } catch (ClassNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return messageList;
	// }
}
