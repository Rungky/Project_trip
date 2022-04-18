package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.*;

public class TripDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "scott";
	private String pw = "tiger";
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	public TripDAO() {
		try {

			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void connDB() {
		try {
			
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("Connection 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			stmt = con.createStatement();
			System.out.println("Statement 생성 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DormDTO selectDorm(int dormNo){
		DormDTO dto = new DormDTO();
		try {
			con = dataFactory.getConnection();
			
			String query = "";
			query += " SELECT * ";
			query += " FROM tb_dorm";
			query += " WHERE dorm_no = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, dormNo);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setDorm_no(rs.getInt("dorm_no"));
				dto.setDorm_name(rs.getString("dorm_name"));
				dto.setDorm_contents(rs.getString("dorm_contents"));
				dto.setDorm_addr(rs.getString("dorm_addr"));
				dto.setDorm_picture(rs.getString("dorm_picture"));
				dto.setLike_cnt(rs.getInt("like_cnt"));
				dto.setOpt_wifi(rs.getInt("opt_wifi"));
				dto.setOpt_parking(rs.getInt("opt_parking"));
				dto.setOpt_aircon(rs.getInt("opt_aircon"));
				dto.setOpt_dryer(rs.getInt("opt_dryer"));
				dto.setOpt_port(rs.getInt("opt_port"));
				dto.setDorm_category_no(rs.getInt("dorm_category_no"));
			}
			if(rs != null) {
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
		
		return dto;
	}
	
	public List<RoomDTO> selectRoomsList(int dormNo){
		List<RoomDTO> list = new ArrayList<RoomDTO>();
		
		try {
			con = dataFactory.getConnection();
			
			String query = "";
			query += " SELECT * ";
			query += " FROM tb_room";
			query += " WHERE dorm_no = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, dormNo);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
				RoomDTO dto = new RoomDTO();
				dto.setRoom_no(rs.getInt("room_no"));
				dto.setDorm_no(rs.getInt("dorm_no"));
				dto.setRoom_name(rs.getString("room_name"));
				dto.setRoom_contents(rs.getString("room_contents"));
				dto.setRoom_picture(rs.getString("room_picture"));
				dto.setRoom_pay_day(rs.getInt("room_pay_day"));
				dto.setRoom_pay_night(rs.getInt("room_pay_night"));
				dto.setRoom_person(rs.getInt("room_person"));
				
				list.add(dto);
			}
			if(rs != null) {
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
		
		return list;
	}
	
	public List<ReviewDTO> selectReviewsList(int dormNo){
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();
		
		try {
			con = dataFactory.getConnection();
			
			String query = "";
			query += " SELECT review_no, review_title, review_contents, review_score, review_date, ";
			query += " review_picture,rev.reserve_no, rev.member_id ";
			query += " FROM tb_review rev , tb_reservation res ";
			query += " WHERE ";
			query += "    rev.reserve_no = res.reserve_no ";
			query += " AND ";
			query += "    dorm_no = ? ";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, dormNo);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setReview_no(rs.getInt("review_no"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_contents(rs.getString("review_contents"));
				dto.setReview_score(rs.getDouble("review_score"));
				dto.setReview_date(rs.getDate("review_date"));
				dto.setReview_picture(rs.getString("review_picture"));
				dto.setReserve_no(rs.getInt("reserve_no"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setScore(scoreRound(rs.getDouble("review_score")));
				
				list.add(dto);
			}
			if(rs != null) {
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
		
		return list;
	}
	
	public int scoreRound(double scoreDouble) {
		int temp = (int)(scoreDouble * 10) % 10;
		int scoreInt = (int)scoreDouble;
		if(temp >=5) {
			scoreInt++;
		}
		return scoreInt;
	}
	
	public List<ReservationDTO> selectReservationsList(String memberId){
		List<ReservationDTO> list = new ArrayList<ReservationDTO>();
		
		try {
			con = dataFactory.getConnection();
			
			String query = "";
			query += " SELECT * ";
			query += " FROM tb_reservation";
			query += " WHERE member_id = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
				ReservationDTO dto = new ReservationDTO();
				dto.setReserve_no(rs.getInt("reserve_no"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setReserve_date(rs.getDate("reserve_date"));
				dto.setReserve_checkin(rs.getDate("reserve_checkin"));
				dto.setReserve_checkout(rs.getDate("reserve_checkout"));
				dto.setReserve_pay(rs.getInt("reserve_pay"));
				dto.setRoom_no(rs.getInt("room_no"));
				dto.setDorm_no(rs.getInt("dorm_no"));
				
				list.add(dto);
			}
			if(rs != null) {
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
		
		return list;
	}
	
	public void insertReview(
			String title,
			String contents,
			double reviewScore,
			Date date,
			String picture,
			int reserveNo,
			String memberId
			)
	{
		
		try {
			con = dataFactory.getConnection();
			System.out.println("커넥션풀 성공");
			
			String query = "";
			query += " INSERT INTO tb_review ";
			query += " VALUES(";
			query += " tb_review_seq.nextval";
			query += " ?,";
			query += " ?,";
			query += " ?,";
			query += " TO_DATE(?, 'YY/MM/DD')";
			query += " ?,";
			query += " ?,";
			query += " ?";
			query += " )";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setDouble(3, reviewScore);
			pstmt.setDate(4, date);
			pstmt.setString(5, picture);
			pstmt.setInt(6, reserveNo);
			pstmt.setString(7, memberId);
			
			pstmt.executeUpdate();
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
