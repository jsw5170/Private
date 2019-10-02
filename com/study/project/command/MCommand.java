package com.study.project.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MCommand {
	
	void execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
