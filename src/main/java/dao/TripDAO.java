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

	public DormDTO selectDorm(int dormNo) {
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
			while (rs.next()) {
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
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	public DormDTO scoreAverage(int dormNo) {
		DormDTO dto = new DormDTO();
		double scoreArv = 0;
		int count = 0;
		List<ReviewDTO> reviewdto = selectReviewsList(dormNo);
		try {

			for (int i = 0; i < reviewdto.size(); i++) {
				scoreArv += reviewdto.get(i).getReview_score();
				count++;
			}
			scoreArv = scoreArv / count;
			scoreArv = Math.round(scoreArv * 10) / 10.0;
			dto.setReview_count(count);
			dto.setScoreAvr(scoreArv);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	public List<RoomDTO> selectRoomsList(int dormNo) {
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
			while (rs.next()) {

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
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<ReviewDTO> selectReviewsList(int dormNo) {
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
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setReview_no(rs.getInt("review_no"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_contents(rs.getString("review_contents"));
				dto.setReview_score(rs.getDouble("review_score"));
				dto.setReview_date(rs.getDate("review_date"));
				dto.setReview_picture(rs.getString("review_picture"));
				dto.setReserve_no(rs.getInt("reserve_no"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setScore((int) (rs.getDouble("review_score")));

				list.add(dto);
			}
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ReservationDTO selectReservation(int reserveno) {
		ReservationDTO dto = new ReservationDTO();
		try {
			con = dataFactory.getConnection();
			String query = "";
			query += " SELECT * ";
			query += " FROM tb_reservation";
			query += " WHERE reserve_no = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, reserveno);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				dto.setReserve_no(rs.getInt("reserve_no"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setReserve_date(rs.getDate("reserve_date"));
				dto.setReserve_checkin(rs.getDate("reserve_checkin"));
				dto.setReserve_checkout(rs.getDate("reserve_checkout"));
				dto.setReserve_pay(rs.getInt("reserve_pay"));
				dto.setReserve_person(rs.getInt("reserve_person"));
				dto.setRoom_no(rs.getInt("room_no"));
				dto.setDorm_no(rs.getInt("dorm_no"));
			}
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	public List<ReservationDTO> selectReservationsList(String member) {
		List<ReservationDTO> list = new ArrayList<ReservationDTO>();
		try {
			con = dataFactory.getConnection();
			System.out.println("진입");
			String query = "";
			query += " select reser.reserve_no, dorm.dorm_name, ";
			query += " 		  room.room_name, reser.member_id, ";
			query += " 		  reser.reserve_checkin, reser.reserve_date, ";
			query += " 		  reser.reserve_checkout, reser.reserve_pay, ";
			query += " 		  room.room_picture, reser.reserve_person ";
			query += " from tb_reservation reser , tb_room room, tb_dorm dorm ";
			query += " where reser.room_no = room.room_no ";
			query += " 		and reser.dorm_no = room.dorm_no ";
			query += " 		and room.dorm_no = dorm.dorm_no ";
			query += " 		and reser.member_id = ? ";
			query += " order by reserve_no desc ";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ReservationDTO dto = new ReservationDTO();
				dto.setReserve_no(rs.getInt("reserve_no"));
				dto.setDorm_name(rs.getString("dorm_name"));
				dto.setRoom_name(rs.getString("room_name"));
				dto.setMember_id(rs.getString("member_id"));
				System.out.println("사이3");
				dto.setReserve_date(rs.getDate("reserve_date"));
				dto.setReserve_checkin(rs.getDate("reserve_checkin"));
				System.out.println("사이4");
				dto.setReserve_checkout(rs.getDate("reserve_checkout"));
				dto.setReserve_pay(rs.getInt("reserve_pay"));
				dto.setReserve_person(rs.getInt("reserve_person"));
				System.out.println("사이5");
				System.out.println("사이6");
				dto.setRoom_picture(rs.getString("room_picture"));
				list.add(dto);
				System.out.println("값 다 넣음");
			}
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	public void reserDelete(int reserve_no) {
		try {
			con = dataFactory.getConnection();
			String query=" ";
			query+="DELETE FROM TB_RESERVATION ";
			query += "WHERE reserve_no= ? ";
			query += "";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, reserve_no);
		
			ResultSet rs = pstmt.executeQuery();
			System.out.println("예약삭제완료");
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

	public CheckDTO checkList(
			int dorm_no, 
			int room_no, 
			String dorm_name, 
			String room_name, 
			Date reserve_checkin,
			Date reserve_checkout, 
			int reserve_pay) 
	{
		CheckDTO dto = new CheckDTO();

		dto.setDorm_no(dorm_no);
		dto.setRoom_no(room_no);
		dto.setDorm_name(dorm_name);
		dto.setRoom_name(room_name);
		dto.setReserve_checkin(reserve_checkin);
		dto.setReserve_checkout(reserve_checkout);
		dto.setReserve_pay(reserve_pay);
		return dto;
	}

	public void insertReview(String title, String contents, double reviewScore, Date date, String picture,
			int reserveNo, String memberId) {

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
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public int selectTotalQuestion() {
		int total = 0;
		try {
			con = dataFactory.getConnection();
			String query = "select count(*) as total from tb_question";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	// 예약 인서트
	public void insertReservation(
			String member, 
			Date reserve_checkin, 
			Date reserve_checkout, 
			int reserve_pay,
			int room_no, 
			int dorm_no) {

		try {
			con = dataFactory.getConnection();
			System.out.println("커넥션풀 성공");

			String query = "";
			query += " INSERT INTO tb_reservation ";
			query += " VALUES(";
			query += " tb_reservation_seq.nextval,";
			query += " ?,";
			query += " sysdate,";
			query += " ?,";
			query += " ?,";
			query += " ?,";
			query += " 0,";
			query += " ?,";
			query += " ?";
			query += " )";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member);
			pstmt.setDate(2, reserve_checkin);
			pstmt.setDate(3, reserve_checkout);
			pstmt.setInt(4, reserve_pay);
			pstmt.setInt(5, room_no);
			pstmt.setInt(6, dorm_no);
			System.out.println("인서트 완료");
			
			pstmt.executeUpdate();
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// member
	public MemberDTO memberDto(String member_id) {
		MemberDTO dto = new MemberDTO();
		try {
			con = dataFactory.getConnection();
			System.out.println("memberDTO");
			String query = "";
			query += " select * from tb_member ";
			query += " where member_id = ? ";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member_id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				dto.setMember_id(rs.getString("member_id"));
				dto.setMember_pw(rs.getString("member_pw"));
				dto.setMember_name(rs.getString("member_names"));
				dto.setMember_tel(rs.getString("member_tel"));
				
				System.out.println("memberDto메소드 완료");
			}
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	//글조회
		public List<QuestionDTO> selectAllQuestion(int pageNum, int countPerPage) {
			List<QuestionDTO> QuestionList = new ArrayList();
			
			try {
				con = dataFactory.getConnection();

				String query = "";
				query += " select * from ";
				query += " 	tb_question ";
				
				
				//페이징 구현
				int offset = (pageNum-1)*countPerPage;
				int to = offset+countPerPage;
				
				pstmt = con.prepareStatement(query);

//				pstmt.setInt(1, offset);
//				pstmt.setInt(2, to);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					QuestionDTO question = new QuestionDTO();
					question.setQuestion_no(rs.getInt("question_no"));
					question.setQuestion_parentno(rs.getInt("question_parentno"));
					question.setQuestion_title(rs.getString("question_title"));
					question.setQuestion_contents(rs.getString("question_contents"));
					question.setQuestion_picture(rs.getString("question_picture"));
					question.setQuestion_date(rs.getDate("question_date"));
					question.setQuestion_view(rs.getInt("question_view"));
					question.setMember_id(rs.getString("member_id"));
				
					
					QuestionList.add(question);
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
		//글쓰기
		public void insertNewQuestion (QuestionDTO questionDTO) {
			try {
				con = dataFactory.getConnection();
				String query = "";
				query += " INSERT INTO tb_question (";
				query += " 		question_no, question_parentno, question_title, question_contents, ";
				query += " 		question_picture, question_date,question_view,member_id )";
				query += " values (";
				query += " 		tb_question_seq.nextval, ?, ?, ?, ";
				query += " 		?, ?, ?, ? ";
				query += " )";
				pstmt = con.prepareStatement(query);
				
				pstmt.setInt(1, questionDTO.getQuestion_parentno());
				pstmt.setString(2, questionDTO.getQuestion_title());
				pstmt.setString(3, questionDTO.getQuestion_contents());
				pstmt.setString(4, questionDTO.getQuestion_picture());
				pstmt.setDate(5, questionDTO.getQuestion_date());
				pstmt.setInt(6, questionDTO.getQuestion_view());
				pstmt.setString(7, questionDTO.getMember_id());
				
				int result = pstmt.executeUpdate();
				System.out.println("새글등록 : result : "+ result);
				
//				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public List<DormVO> getDormList(int dorm_category_no, Date start, Date end, int opt_wifi, int opt_parking,
			int opt_aircon, int opt_dryer, int opt_port, int room_person, int order, int price) {

		List<DormVO> dormList = new ArrayList<DormVO>();
		try {
			con = dataFactory.getConnection();
			System.out.println("커넥션풀 성공");

			String query = "";
			query += " SELECT d.dorm_no, ";
			query += " 		d.dorm_name, ";
			query += " 		d.dorm_addr, ";
			query += " 	    d.opt_wifi, ";
			query += " 	    d.opt_parking, ";
			query += " 	    d.opt_aircon, ";
			query += " 	    d.opt_dryer, ";
			query += " 	    d.opt_port, ";
			query += " 	    d.dorm_picture, ";
			query += " 	    d.dorm_category_no, ";
			query += " 		Min(m.room_pay_night) as min_pay_night, ";
			query += " 	    count(vo.reserve_no) as count_reserve_no ";
			query += " FROM tb_dorm d, ";
			query += " 	    tb_room m left join (tb_reservation r left join tb_review vo on r.reserve_no = vo.reserve_no) ";
			query += " 	    on m.dorm_no = r.dorm_no ";
			query += " WHERE ";
			if (dorm_category_no != 0) {
				query += " 		d.dorm_category_no = " + dorm_category_no + " and ";
			}
			query += "     	d.dorm_no = m.dorm_no ";
			query += " 		and (to_date(?, 'yy/MM/dd') <= r.reserve_checkin or to_date(?, 'yy/MM/dd') >= r.reserve_checkout or r.reserve_checkin is null) ";
			if (opt_wifi == 1) {
				query += " 		and d.opt_wifi >= " + opt_wifi + " ";
			}
			if (opt_parking == 1) {
				query += " 		and d.opt_parking >= " + opt_parking + " ";
			}
			if (opt_aircon == 1) {
				query += " 		and d.opt_aircon >= " + opt_aircon + " ";
			}
			if (opt_dryer == 1) {
				query += " 		and d.opt_dryer >= " + opt_dryer + " ";
			}
			if (opt_port == 1) {
				query += " 		and d.opt_port >= " + opt_port + " ";
			}
			query += " 		and m.room_person >= ? ";

			if (price == 1) {
				query += " 		and room_pay_night <= 50000 ";
			}
			if (price == 2) {
				query += " 		and (room_pay_night <= 100000 and room_pay_night > 50000 ) ";
			}
			if (price == 3) {
				query += " 		and (room_pay_night <= 200000 and room_pay_night > 100000 ) ";
			}
			if (price == 4) {
				query += " 		and (room_pay_night > 200000 ) ";
			}

			query += " GROUP BY d.dorm_no, ";
			query += " 		d.dorm_name, ";
			query += " 		d.dorm_addr, ";
			query += " 	    d.opt_wifi, ";
			query += " 	    d.opt_parking, ";
			query += " 	    d.opt_aircon, ";
			query += " 	    d.opt_dryer, ";
			query += " 	    d.opt_port, ";
			query += " 	    d.dorm_picture, ";
			query += " 	    d.dorm_category_no ";
			if (order == 1) {
				query += " ORDER BY min_pay_night ";
			} else if (order == 2) {
				query += " ORDER BY min_pay_night DESC ";
			}

			pstmt = con.prepareStatement(query);
			pstmt.setDate(1, end);
			pstmt.setDate(2, start);
			pstmt.setInt(3, room_person);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				DormVO dto = new DormVO();
				dto.setDorm_no(rs.getInt("dorm_no"));
				dto.setDorm_name(rs.getString("dorm_name"));
				dto.setDorm_addr(rs.getString("dorm_addr"));
				dto.setDorm_picture(rs.getString("dorm_picture"));
				dto.setOpt_wifi(rs.getInt("opt_wifi"));
				dto.setOpt_parking(rs.getInt("opt_parking"));
				dto.setOpt_aircon(rs.getInt("opt_aircon"));
				dto.setOpt_dryer(rs.getInt("opt_dryer"));
				dto.setOpt_wifi(rs.getInt("opt_wifi"));
				dto.setOpt_port(rs.getInt("opt_port"));
				dto.setDorm_category_no(rs.getInt("dorm_category_no"));
				dto.setMin_pay_night(rs.getInt("min_pay_night"));
				dto.setCount_reserve_no(rs.getInt("count_reserve_no"));

				dormList.add(dto);
			}
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dormList;
	}

	public List<QuestionDTO> selectQuestion(int question_no) {
		List<QuestionDTO> QuestionList = new ArrayList();
		
		try {
			con = dataFactory.getConnection();

			String query = "";
			query += " select * from ";
			query += " 	tb_question ";
			query += " where question_no = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,question_no);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QuestionDTO question = new QuestionDTO();
				question.setQuestion_no(rs.getInt("question_no"));
				question.setQuestion_parentno(rs.getInt("question_parentno"));
				question.setQuestion_title(rs.getString("question_title"));
				question.setQuestion_contents(rs.getString("question_contents"));
				question.setQuestion_picture(rs.getString("question_picture"));
				question.setQuestion_date(rs.getDate("question_date"));
				question.setQuestion_view(rs.getInt("question_view"));
				question.setMember_id(rs.getString("member_id"));
			
				
				QuestionList.add(question);
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
	

	public void plusViewCount(int articleNo) {
		try {
			
			con = dataFactory.getConnection();
			String query = "";
			query += " update tb_question";
			query += " set question_view = question_view + 1";
			query += " where question_no = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
			
//			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
