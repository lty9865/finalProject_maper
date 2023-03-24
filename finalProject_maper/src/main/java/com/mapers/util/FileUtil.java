package com.mapers.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

public class FileUtil {
	
	public static MultipartRequest uploadFile(HttpServletRequest req, String saveDirectory, int maxPostSize) {
		// ������ ���ٸ� ����
		try {
			File folder = new File(saveDirectory);
			if(!folder.exists()) {
				try {
					folder.mkdirs();
					System.out.println("���� ���� �Ϸ�");
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("���� ���� ���� ���� �߻�");
				}
			}
			return new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("���� ���ε� �� ���� �߻�");
			return null;
		}
	}
	
	public static void deleteFile(HttpServletRequest req, String directory, String fileName) {
		String sDirectory = req.getServletContext().getRealPath(directory);
		File file = new File(sDirectory + File.separator + fileName);
		if(file.exists()) {
			file.delete();
		}
	}
	
}
