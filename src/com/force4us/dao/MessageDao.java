package com.force4us.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.force4us.bean.Message;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class MessageDao {
	List<Message> messageList = new ArrayList<Message>();

	public List<Message> queryMessageList(String command, String description) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/micro_message",
						"root", "2011210847");
				StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from message where 1=1");
				List<String> paramList = new ArrayList<String>();
				if (command != null && !"".equals(command.trim())) {
					sql.append(" and COMMAND=?");
					paramList.add(command);
				}
				if (description != null && !"".equals(description.trim())) {
					sql.append(" and DESCRIPTION like '%' ? '%'");
					paramList.add(description);
				}
				PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql.toString());
				for (int i = 0; i < paramList.size(); i++) {
					statement.setString(i + 1, paramList.get(i));
				}
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					Message message = new Message();
					messageList.add(message);
					message.setId(rs.getString("ID"));
					message.setCommand(rs.getString("COMMAND"));
					message.setDescription(rs.getString("DESCRIPTION"));
					message.setContent(rs.getString("CONTENT"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return messageList;
	}
}
