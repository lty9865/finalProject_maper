package com.mapers.myPage.Admin.model;

import java.util.ArrayList;

import com.mapers.common.PagingBean;

public class ListDTO {
	private ArrayList<AdminDTO> aList;
	private PagingBean paginbean;
	
	public ListDTO(ArrayList<AdminDTO> aList, PagingBean pagingbean) {
		super();
		this.aList = aList;
		this.paginbean = pagingbean;
	}
	
	public ArrayList<AdminDTO> getList(){
		return aList;
	}
	
	public void setRList(ArrayList<AdminDTO> aList) {
		this.aList = aList;
	}

	public PagingBean getPaginbean() {
		return paginbean;
	}

	public void setPaginbean(PagingBean paginbean) {
		this.paginbean = paginbean;
	}
}
