package com.mapers.util;

public class ListPage {
	public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNums, String reqUrl) {
		String pagingStr ="";
		
		int totalPages = (int)(Math.ceil(((double)totalCount / pageSize)));
		
		int pageTemp = (((pageNums -1)/blockPage)*blockPage)+1;
		if(pageTemp != 1) {
			pagingStr += "<a href='../" + reqUrl + "&pageNums=1'>[첫페이지]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='../" + reqUrl + "&pageNums=" + (pageTemp - 1) + "'>[이전 블록]</a>";
		}
		int blockCount = 1;
		while(blockCount <= blockPage && pageTemp <= totalPages) {
			if(pageTemp == pageNums) {
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
			}else {
				pagingStr += "&nbsp;<a href='../" + reqUrl + "&pageNums=" + pageTemp + "'>" + pageTemp + "</a>&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}
		
		if(pageTemp <= totalPages) {
			pagingStr += "<a href='../" + reqUrl + "&pageNums=" + pageTemp + "'>[다음 블록]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='../" + reqUrl + "&pageNums=" +totalPages + "'>[마지막 페이지]</a>";
		}
		return pagingStr;
	}

}
