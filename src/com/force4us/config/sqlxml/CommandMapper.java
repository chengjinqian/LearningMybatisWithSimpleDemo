package com.force4us.config.sqlxml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.force4us.bean.Command;

public interface CommandMapper {
	List<Command> queryCommandList(@Param("name") String name, @Param("description") String description);

}
