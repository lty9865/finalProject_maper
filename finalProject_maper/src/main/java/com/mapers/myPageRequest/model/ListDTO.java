package com.mapers.myPageRequest.model;

import java.util.ArrayList;

public class ListDTO {
	private ArrayList<RequestDTO> rList;
	private PagingBean paginbean;
	
	public ListDTO(ArrayList<RequestDTO> rList, PagingBean pagingbean) {
		super();
		this.rList = rList;
		this.paginbean = pagingbean;
	}
	
	public ArrayList<RequestDTO> getList(){
		return rList;
	}
	
	public void setRList(ArrayList<RequestDTO> rList) {
		this.rList = rList;
	}

	public PagingBean getPaginbean() {
		return paginbean;
	}

	public void setPaginbean(PagingBean paginbean) {
		this.paginbean = paginbean;
	}
}
