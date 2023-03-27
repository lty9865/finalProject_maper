package com.mapers.myPage.Request.model;

public class PagingBean {
	// 현재 페이지
	private int nowPage = 1;
	// 페이지당 게시글 수
	private int postCountPerPage = 5;
	// 페이지 그룹당 페이지 수
	private int pageCountPerPageGroup = 4;
	// database에 저장된 총 게시글 수
	private int totalPostCount;
	
	public PagingBean(int totalPostCount) {
		this.totalPostCount = totalPostCount;
	}
	
	public PagingBean(int totalPostCount, int nowPage) {
		this.totalPostCount = totalPostCount;
		this.nowPage = nowPage;
	}
	
	public int getNowPage() {
		return nowPage;
	}
	
	// 현재 페이지 번호에 해당하는 시작 게시물의 row number 리턴
	// 이전 페이지의 마지막 번호 + 1
	public int getStartRowNumber() {
		int no = (nowPage - 1) * postCountPerPage + 1;

		return no;
	}
	
	/*
	 * 현재 페이지에서 보여줄 게시물 행(row)의 마지막 번호 현재 페이지
	 * *postCountPerPage
	 * 만약 총 게시물 수보다 연산 결과의 번호가 클 경우
	 * 총 게시물 수가 마지막 번호가 되어야 함
	 * 
	 * ex) 총 게시물 수가 7개, 총 페이지는 2페이지
	 * : 1 2 3 4 5 | 6 7 |
	 * 		1 page	2 page
	 * 
	 * 현재 페이지는 2페이지고 2*5(페이지당 게시물 수)는 10이고 실제 마지막 번호는 7임
	 * -> 연산 결과가 총 게시물 수보다 클 경우, 총 게시물 수가 마지막 번호가 되어야 함
	 * 
	 */
	public int getEndRowNumber() {
		int endRowNumber = nowPage * postCountPerPage;
		
		if (endRowNumber > totalPostCount) {
			endRowNumber = totalPostCount;
		}
		
		return endRowNumber;
	}
	
	/*
	 * 총 페이지 수를 return
	 * 1. 전체 데이터(게시물) % 한 페이지 보여줄 데이터 개수
	 * 		=> 0 이면 둘을 /로 나눈 값이 총 페이지 수
	 * 2. 전체 데이터(게시물) % 한 페이지에 보여줄 데이터 개수
	 * 		=> 0보다 크면 둘을 /로 나눈 값에 +1을 한 값이 총 페이지 수 게시물 수
	 * 
	 * 1 2 3 4 5 || 6 7 8 9 10 || 11 12
	 * 1페이지 1~5 || 2페이지 6~10 || 3페이지 11, 12
	 * ex) 게시물 32개, 페이지당 게시물 수 5개 -> 7페이지
	 */
	public int getTotalPage() {
		int totalPage = totalPostCount % postCountPerPage;
		
		if (totalPage == 0) {
			totalPage = totalPostCount / postCountPerPage;
		} else {
			totalPage = totalPostCount / postCountPerPage + 1;
		}
		
		return totalPage;
	}
	
	/*
	 * 총 페이지 그룹의 수를 return
	 * 1. 총 페이지 수 % Page Group 당 Page 수
	 * 		=> 0 이면 둘을 /로 나눈 값이 총 페이지 수
	 * 2. 총 페이지 수 % Page Group 당 Page 수
	 * 		=> 0 보다 크면 둘을 /로 나눈 값에 +1을 한 값이 총 페이지 수
	 * 
	 * ex) 총 게시물 수 23개
	 * 		총 페이지 ? 페이지 1 2 3 4 5
	 * 		총 페이지 그룹 수 ? 페이지 그룹 1234(1그룹) 5(2그룹)
	 */
	public int getTotalPageGroup() {
		int totalPageGroup = getTotalPage() % pageCountPerPageGroup;
		
		if (totalPageGroup == 0) {
			totalPageGroup = getTotalPage() / pageCountPerPageGroup;
		} else {
			totalPageGroup = getTotalPage() / pageCountPerPageGroup + 1;
		}
		
		return totalPageGroup;
	}
	
	/*
	 * 현재 페이지가 속한 페이지 그룹 번호(몇 번째 페이지 그룹인지)를 ruturn
	 * 1. 현재 페이지 % 페이지 그룹 당 페이지 수
	 * 		=> 0 이면 둘을 /로 나눈 값이 현재 페이지 그룹
	 * 2. 현재 페이지 % 페이지 그룹 당 페이지 수
	 * 		=> 0 이면 둘을 /로 나눈 값에 +1을 한 값이 현재 페이지 그룹
	 * 
	 * 페이지 1 2 3 4 | 5 6 7 8 | 9 10
	 * 			1그룹		  2그룹	 3그룹
	 */
	public int getNowPageGroup() {
		int nowPageGroup = nowPage % pageCountPerPageGroup;
		
		if(nowPageGroup == 0) {
			nowPageGroup = nowPage / pageCountPerPageGroup;
		} else {
			nowPageGroup = nowPage / pageCountPerPageGroup + 1;
		}
		
		return nowPageGroup;
	}
	
	/*
	 * 현재 페이지가 속한 페이지 그룹의 시작 페이지 번호를 return 한다. 
	 * Page Group 내 Page 수*(현재 페이지 그룹 -1) + 1 을 한 값이 첫 페이지이다. 
	 * (페이지 그룹*페이지 그룹 개수, 그룹의 마지막 번호이므로) 
	 * 페이지 그룹 1 2 3 4 -> 5 6 7 8 -> 9 10
	 * 
	 */
	public int getStartPageOfPageGroup() {
		int startPageOfPageGroup = pageCountPerPageGroup * (getNowPageGroup() - 1) + 1;
		
		return startPageOfPageGroup;
	}
	
	/*
	 * 현재 페이지가 속한 페이지 그룹의 마지막 페이지 번호를 return 한다. 
	 * 1. 현재 페이지 그룹 * 페이지 그룹 개수 가 마지막 번호이다. 
	 * 2. 그 그룹의 마지막 페이지 번호가 전체 페이지의 마지막 페이지 번호보다 
	 * 큰 경우는 전체 페이지의 마지막 번호를 return한다. 
	 * 1 2 3 4 -> 5 6 7 8 -> 9 10
	 * 
	 */
	public int getEndPageOfPageGroup() {
		int endPageOfPageGroup = getNowPageGroup() * pageCountPerPageGroup;
		
		if (endPageOfPageGroup > totalPostCount) {
			endPageOfPageGroup = getTotalPage();
		}
		
		return endPageOfPageGroup;
	}
	
	/*
	 * 이전 페이지 그룹이 있는지 체크하는 메서드 
	 * 현재 페이지가 속한 페이지 그룹이 1보다 크면 true 
	 * ex ) 페이지 1 2 3 4 / 5 6 7 8 / 9 10 
	 *               1         2        3 group
	 * 
	 */
	public boolean isPreviousPageGroup() {
		boolean flag = false;
		
		if (getNowPageGroup() > 1) {
			flag = true;
		}
		
		return flag;
	}
	
	/*
	 * 다음 페이지 그룹이 있는지 체크하는 메서드
	 * 현재 페이지 그룹이 마지막 페이지 그룹( 마지막 페이지 그룹 == 총 페이지 그룹 수) 보다
	 * 작으면 true
	 * ex ) 페이지 1 2 3 4 / 5 6 7 8 / 9 10 
	 *               1         2        3 group
	 * 
	 */
	public boolean isNextPageGroup() {
		boolean flag = false;
		
		if (getTotalPageGroup() > getNowPageGroup()) {
			flag = true;
		}
		
		return flag;
	}
	
	//체크를 위한 main method
	public static void main(String args[]) {
		PagingBean p = new PagingBean(70, 6);
		// 현페이지의 시작 row number 를 조회 
		System.out.println("getBeginRowNumber:" + p.getStartRowNumber());
		// 현페이지의 마지막 row number 를 조회 
		System.out.println("getEndRowNumber:" + p.getEndRowNumber());
		// 전체 페이지 수 
		System.out.println("getTotalPage:" + p.getTotalPage());
		// 전체 페이지 그룹 수 
		System.out.println("getTotalPageGroup:" + p.getTotalPageGroup());
		System.out.println("////////////////////////////");
		p = new PagingBean(70, 6);// 게시물수 70 현재 페이지 6
		// 현페이지의 시작 row number 를 조회 
		System.out.println("getStartRowNumber:" + p.getStartRowNumber());
		// 현페이지의 마지막 row number 를 조회 
		System.out.println("getEndRowNumber:" + p.getEndRowNumber());

		// 현재 페이지 그룹 
		System.out.println("getNowPageGroup:" + p.getNowPageGroup());
		// 페이지 그룹의 시작 페이지
		System.out.println("getStartPageOfPageGroup:" + p.getStartPageOfPageGroup());
		// 페이지 그룹의 마지막 페이지 
		System.out.println("getEndPageOfPageGroup:" + p.getEndPageOfPageGroup());
		// 이전 페이지 그룹이 있는 지 
		System.out.println("isPreviousPageGroup:" + p.isPreviousPageGroup());
		// 다음 페이지 그룹이 있는 지 
		System.out.println("isNextPageGroup:" + p.isNextPageGroup());
	}
}

/*
 * 
 * 페이징 처리를 위한 비즈니스 계층의 클래스 PagingBean method 구현순서
 * getStartRowNumber() : 현재 페이지 번호에 해당하는 시작 게시물의 row number를 반환
 * getEndRowNumber() : 현재 페이지 번호에 해당하는 마지막 게시물의 row number를 반환
 * getTotalPage() : 게시글이 5개씩 묶여있는 총 페이지 수를 반환
 * getTotalPageGroup() : 게시글이 5개씩 묶여있는 페이지를 4개씩 묶은 페이지 그룹의 수를 반환
 * getNowPageGroup() : 현재 페이지가 속한 그룹이 몇 번째 페이지 그룹(페이지 그룹 번호)를 반환
 * getStartPageOfPageGroup() : 현재 페이지가 속한 페이지 그룹 번호에서 시작 페이지 번호를 반환
 * getEndPageOfPageGroup() :  : 현재 페이지가 속한 페이지 그룹 번호에서 마지막 페이지 번호를 반환
 * isPreviousPageGroup() : 이전 페이지 그룹이 있는지 여부를 체크하여 boolean 타입으로 반환
 * isNextPageGroup() : 다음 페이지 그룹이 있는지 여부를 체크하여 boolean 타입으로 반환
 * 
 */
