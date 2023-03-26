package com.mapers.myPageLike.model;

import java.util.ArrayList;

public class ListDTO {
	private ArrayList<LikeDTO> kList;
	private PagingBean paginbean;
	
	public ListDTO(ArrayList<LikeDTO> kList, PagingBean pagingbean) {
		super();
		this.kList = kList;
		this.paginbean = pagingbean;
	}
	
	public ArrayList<LikeDTO> getList(){
		return kList;
	}
	
	public void setRList(ArrayList<LikeDTO> kList) {
		this.kList = kList;
	}

	public PagingBean getPaginbean() {
		return paginbean;
	}

	public void setPaginbean(PagingBean paginbean) {
		this.paginbean = paginbean;
	}
}
