package com.mapers.myPage.Request.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mapers.common.DataSourceManager;

public class ConversationDAO {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	// singleton pattern
	private static ConversationDAO instance = new ConversationDAO();
	private DataSource dataSource;
	
	private ConversationDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	
	public static ConversationDAO getInstance() {
		return instance;
	}
	
	// Connection을 dbcp에 반납
	public void closeAll() {
		
		try {
			
			if(rs != null) rs.close();
			if(psmt !=null) psmt.close();
			if(conn !=null) conn.close();
			
			System.out.println("DB Connection Pool Resource Dismissed!");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB Connection Pool 자원 반납 Error");
		}
	}

    public List<ConversationDTO> getConversationsByPostId(String postId) {
        List<ConversationDTO> convList = new ArrayList<>();
        
        String sql = "SELECT * FROM conversations WHERE post_id = ? ORDER BY cycle, cycle_id";
        
        try {

        	if (conn != null) {
        		conn.close();
        	}
        	
        	conn = dataSource.getConnection();
        	
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, postId);
            
            rs = psmt.executeQuery();
            while (rs.next()) {
                ConversationDTO conv = new ConversationDTO();
                
                conv.setId(rs.getInt("id"));
                conv.setTitle(rs.getString("title"));
                conv.setContent(rs.getString("content"));
                conv.setCycle(rs.getInt("cycle"));
                conv.setCycleId(rs.getInt("cycle_id"));
                // Set other fields if necessary

                convList.add(conv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return convList;
    }
    
    public boolean insert(ConversationDTO convDTO) {
        boolean success = false;

        String sql = "INSERT INTO conversations (post_id, cycle, title, content) VALUES (?, ?, ?, ?)";

        try {
        	
        	if (conn != null) {
        		conn.close();
        	}
        	
            conn = dataSource.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, convDTO.getId());
            psmt.setInt(2, convDTO.getCycle());
            psmt.setString(3, convDTO.getTitle());
            psmt.setString(4, convDTO.getContent());

            int result = psmt.executeUpdate();
            success = result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return success;
    }
    
    public int getNumberOfRepliesForTitle(String title) {
        int count = 0;
        
        String sql = "SELECT COUNT(*) FROM conversations WHERE title LIKE ?";

        try {
            if (conn != null) {
                conn.close();
            }

            conn = dataSource.getConnection();

            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "RE%: " + title);

            rs = psmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return count;
    }
}

