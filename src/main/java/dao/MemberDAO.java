package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dao.LoggableStatement;
import dto.*;

public class MemberDAO {
	private DataSource dataFactory;
	private Connection con;
	private PreparedStatement pstmt;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	  //회원가입
	  public void join(MemberDTO memberDTO){
	      
	      try {
	    	  con = dataFactory.getConnection();
	    	  System.out.println("커넥션풀 성공");
	    	  
	    	  String query = "";
		      query += "insert into tb_member(member_id, member_pw, member_names, member_tel)";
		      query += "values (?,?,?,?)";
	    	  
		      pstmt = new LoggableStatement(con, query);
		      System.out.println(((LoggableStatement)pstmt).getQueryString());

		      pstmt = con.prepareStatement(query);
	          pstmt.setString(1, memberDTO.getMember_id());
	          pstmt.setString(2, memberDTO.getMember_pw());
	          pstmt.setString(3, memberDTO.getMember_name());
	          pstmt.setString(4, memberDTO.getMember_tel());
	          
	          int result = pstmt.executeUpdate();
	          System.out.println("join 성공: "+ result);
	          
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }finally {
				try {
					
					if (con != null) con.close(); 
					if (pstmt != null) pstmt.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
	      }
	  }
	  
	  //로그인 기능 
	  public MemberDTO login(MemberDTO memberDTO){
		  MemberDTO m = new MemberDTO();
		  
		  try {
	    	  con = dataFactory.getConnection();
	    	  System.out.println("커넥션풀 성공");
	    	  
	    	  String query = "";
		      query += " select * from tb_member ";
		      query += " where member_id = ? and member_pw = ? ";
		      System.out.println(query);
		      
		      pstmt = new LoggableStatement(con, query);
		      pstmt.setString(1, memberDTO.getMember_id());
		      pstmt.setString(2, memberDTO.getMember_pw());
		      
		      System.out.println("DAO id : "+ memberDTO.getMember_id());
		      System.out.println("DAO pw : "+memberDTO.getMember_pw());

		      System.out.println(((LoggableStatement)pstmt).getQueryString());
	    	  
	          ResultSet rs = pstmt.executeQuery();
	          
	          if(rs.next()) {
	        	 String id = rs.getString("member_id");
	        	 String pw = rs.getString("member_pw");
	        	 System.out.println("if문 id : "+id);
	        	 System.out.println("if문 pw : "+pw);

//	        	 memberDTO = new MemberDTO();
	        	 m.setMember_id(id);
	        	 m.setMember_pw(pw);
	          
	          } if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}	
		  
		  return m;
	  }
}