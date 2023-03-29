package com.mapers.signUp;

import java.util.UUID;

//라이센스 키 생성
public class LisencekeyCreate {

	public String LicenceKey(){
		 UUID uuid = UUID.randomUUID();
		 String key = uuid.toString();
		
		return key;
	}
	
	    }
	
		

