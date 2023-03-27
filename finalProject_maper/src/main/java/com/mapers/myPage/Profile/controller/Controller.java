package com.mapers.myPage.Profile.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	// execute(request, response): String -> url
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
