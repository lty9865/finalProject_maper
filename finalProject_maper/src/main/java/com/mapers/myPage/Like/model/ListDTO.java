package com.mapers.myPage.Like.model;

import java.util.ArrayList;

import com.mapers.common.PagingBean;

public class ListDTO {
	private ArrayList<LikeDTO> kList;
	private PagingBean paginbean;
	
	public ListDTO(ArrayList<LikeDTO> kList, PagingBean pagingbean) {
		super();
		this.kList = kList;
		this.paginbean = pagingbean;
	}
	
	public ArrayList<LikeDTO> getKList(){
		return kList;
	}
	
	public void setKList(ArrayList<LikeDTO> kList) {
		this.kList = kList;
	}

	public PagingBean getPaginbean() {
		return paginbean;
	}

	public void setPaginbean(PagingBean paginbean) {
		this.paginbean = paginbean;
	}
}
