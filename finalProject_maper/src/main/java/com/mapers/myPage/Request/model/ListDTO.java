package com.mapers.myPage.Request.model;

import java.util.ArrayList;

import com.mapers.common.PagingBean;

public class ListDTO {
	private ArrayList<RequestDTO> rList;
	private PagingBean pagingbean;
	
	public ListDTO(ArrayList<RequestDTO> rList, PagingBean pagingbean) {
		super();
		this.rList = rList;
		this.pagingbean = pagingbean;
	}
	
	public ArrayList<RequestDTO> getRlist(){
		return rList;
	}
	
	public void setRlist(ArrayList<RequestDTO> rList) {
		this.rList = rList;
	}

	public PagingBean getPagingbean() {
		return pagingbean;
	}

	public void setPagingbean(PagingBean pagingbean) {
		this.pagingbean = pagingbean;
	}
}
