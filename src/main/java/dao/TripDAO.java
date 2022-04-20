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
import service.*;

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
		DormDTO dtoTemp = scoreAverage(dormNo);
		dto.setReview_count(dtoTemp.getReview_count());
		dto.setScoreAvr(dtoTemp.getScoreAvr());
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
	
	public DormDTO scoreAverage(int dormNo){
		DormDTO dto = new DormDTO();
		double scoreArv = 0;
		int count = 0;
		List<ReviewDTO> reviewdto = selectReviewsList(dormNo);
		try {
			
			for(int i = 0; i<reviewdto.size();i++) {
				scoreArv += reviewdto.get(i).getReview_score();
				count++;
			}
			scoreArv = scoreArv / count;
			scoreArv = Math.round(scoreArv*10)/10.0;
			dto.setReview_count(count);
			dto.setScoreAvr(scoreArv);

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
			query += " ORDER BY review_no desc";
			
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
				dto.setScore((int)(rs.getDouble("review_score")));
				
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
	
	
	public List<ReservationDTO> selectReservationsList(String member_id){
		List<ReservationDTO> list = new ArrayList<ReservationDTO>();
		
		try {
			con = dataFactory.getConnection();
			System.out.println("진입");
			String query = "";
			query += " SELECT reserve_no, member_id, reserve_date, reserve_checkin, reserve_checkout, ";
			query += " reserve_pay,reserve_person, room.room_name, dorm.dorm_name ";
			query += " FROM tb_reservation reser , tb_room room, tb_dorm dorm ";
			query += " WHERE ";
			query += "    reser.room_no = room.room_no ";
			query += " AND ";
			query += "    reser.dorm_no = dorm.dorm_no ";
			query += " AND ";
			query += "    member_id = ? ";
			query += " ORDER BY reserve_no desc";
			System.out.println("사이2");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member_id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ReservationDTO dto = new ReservationDTO();
				dto.setReserve_no(rs.getInt("reserve_no"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setReserve_date(rs.getDate("reserve_date"));
				dto.setReserve_checkin(rs.getDate("reserve_checkin"));
				dto.setReserve_checkout(rs.getDate("reserve_checkout"));
				dto.setReserve_pay(rs.getInt("reserve_pay"));
				dto.setReserve_person(rs.getInt("reserve_person"));
				dto.setRoom_name(rs.getString("room_name"));
				dto.setDorm_name(rs.getString("dorm_name"));
				list.add(dto);
				System.out.println("값 다 넣음");
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
			query += " tb_review_seq.nextval,";
			query += " ?,";
			query += " ?,";
			query += " ?,";
			query += " TO_DATE(?, 'YY/MM/DD'),";
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
	
	//수정필요 qna1
	public int selectTotalQuestion() {
		int total=0;
		try {
			con = dataFactory.getConnection();
			String query = "select count(*) as total from tb_question";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void insertReservation(
			String member_id,
			Date reserve_checkin,
			Date reserve_checkout,
			int reserve_pay,
			int reserve_person,
			int room_no,
			int dorm_no
			)
	{
		
		try {
			con = dataFactory.getConnection();
			System.out.println("커넥션풀 성공");
			
			String query = "";
			query += " INSERT INTO tb_reservation ";
			query += " VALUES(";
			query += " tb_reservation_seq.nextval,";
			query += " ?,";
			query += " sysdate";
			query += " ?,";
			query += " ?,";
			query += " ?,";
			query += " ?,";
			query += " ?";
			query += " )";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member_id);
			pstmt.setDate (2, reserve_checkin);
			pstmt.setDate(3, reserve_checkout);
			pstmt.setInt(4, reserve_pay);
			pstmt.setInt(5, reserve_person);
			pstmt.setInt(6, room_no);
			pstmt.setInt(7, dorm_no);
			
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
	
	
	//수정필요qna2
	public List<QuestionDTO> selectAllQuestion(int pageNum, int countPerPage) {
		List<QuestionDTO> QuestionList = new ArrayList();
		
		try {
			con = dataFactory.getConnection();

			String query = "";
			query += " select tmp.* from (";
			query += " 	select"; 
			query += " 	 rownum as rnum,"; 
			query += "     level,"; 
			query += "     articleno,"; 
			query += "     parentno, ";
			query += "     title,";
			query += "     content,";
			query += "     id,";
			query += "     writedate,";
			query += "     e.ename,";
			query += "     view_count";
			query += " from t_board t, emp2 e";
			query += " where t.id = e.empno";
			query += " start with parentno = 0";
			query += " connect by prior articleno = parentno";
			query += " order siblings by articleno desc";
			query += " ) tmp"; 
			query += " where rnum > ? and rnum <= ?"; 
			
			int offset = (pageNum-1)*countPerPage;
			int to = offset+countPerPage;
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, to);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QuestionDTO article = new QuestionDTO();
				article.setQuestion_no(rs.getInt("question_no"));
				article.setQuestion_parentno(rs.getInt("question_parentno"));
				article.setQuestion_title(rs.getString("question_title"));
				article.setQuestion_contents(rs.getString("question_contents"));
				article.setQuestion_picture(rs.getString("question_picture"));
				article.setQuestion_date(rs.getDate("question_date"));
				article.setQuestion_view(rs.getInt("question_view"));
				article.setMember_id(rs.getString("member_id"));
			
				
				QuestionList.add(article);
			}

			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return QuestionList;
	}
	
	//수정필요3 qna
	public void insertNewQuestion (QuestionDTO questionDTO) {
		try {
			con = dataFactory.getConnection();
			String query = "";
			query += " INSERT INTO tb_question (";
			query += " 		question_no, question_parentno, question_title, question_contents, ";
			query += " 		question_picture, question_date,question_view,member_id )";
			query += " values (";
			query += " 		t_board_seq.nextval, ?, ?, ?, ";
			query += " 		?, ?, ?, ?, ? ";
			query += " )";
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, questionDTO.getQuestion_no());
			pstmt.setInt(2, questionDTO.getQuestion_parentno());
			pstmt.setString(3, questionDTO.getQuestion_title());
			pstmt.setString(4, questionDTO.getQuestion_contents());
			pstmt.setString(5, questionDTO.getQuestion_picture());
			
			int result = pstmt.executeUpdate();
			System.out.println("새글등록 : result : "+ result);
			
//			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
