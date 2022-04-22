package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.*;
import dao.TripDAO;
import dto.CheckDTO;
import dto.DormDTO;
import dto.DormVO;
import dto.MemberDTO;
import dto.ReservationDTO;
import dto.ReviewDTO;
import dto.RoomDTO;

@WebServlet("/trip")
public class tripController extends HttpServlet {
	TripDAO tripdao = new TripDAO();
	MemberDAO memberDAO = new MemberDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
		System.out.println(request.getParameter("action"));

		System.out.println("get");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
		System.out.println(request.getParameter("action"));

		System.out.println("post");
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 객체 만들기
		HttpSession session = request.getSession();

		String nextPage = "";
		String action = " ";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		if(request.getParameter("action") != null)
			action = request.getParameter("action");
		
		
		try {
			
			if (action.equals(" ")) {
				
				nextPage = "main.jsp";
			}  else if (action.equals("reservation.do")) {
				
				List<DormVO> dormList = new ArrayList<DormVO>();
				
				// category_no를 이용해 dorm 정보 조회
				int cat_no =0;
				long miliseconds = System.currentTimeMillis();
				Date start = new Date(miliseconds);
				Date end = new Date(miliseconds);
				int wifi = 0;
				int park = 0;
				int air = 0;
				int dry = 0;
				int port = 0;
				int room_person = 1;
				int order = 0;
				int price = 0;
				request.setAttribute("date_s", start);
				request.setAttribute("date_e", end);
				
				

				try {
					if(request.getParameter("dorm_category_no") != null) {
						cat_no = Integer.parseInt(request.getParameter("dorm_category_no"));
					} 
					
					SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
					
					if(request.getParameter("start") != null) {
						java.util.Date utilDate = new java.util.Date();
						utilDate = newDtFormat.parse(request.getParameter("start"));
						start = new java.sql.Date(utilDate.getTime());
						request.setAttribute("date_s", start);
					}
					
					if(request.getParameter("end") != null) {
						java.util.Date utilDate = new java.util.Date();
						utilDate = newDtFormat.parse(request.getParameter("end"));
						end = new java.sql.Date(utilDate.getTime());
						request.setAttribute("date_e", end);
					}
					if(request.getParameter("wifi") != null) {
						wifi = Integer.parseInt(request.getParameter("wifi"));
					}
					if(request.getParameter("parking") != null) {
						park = Integer.parseInt(request.getParameter("parking"));
					}
					if(request.getParameter("aircon") != null) {
						air = Integer.parseInt(request.getParameter("aircon"));
					}
					if(request.getParameter("dryer") != null) {
						dry = Integer.parseInt(request.getParameter("dryer"));
					}
					if(request.getParameter("port") != null) {
						port = Integer.parseInt(request.getParameter("port"));
					}
					if(request.getParameter("room_person") != null) {
						room_person = Integer.parseInt(request.getParameter("room_person"));
					}
					if(request.getParameter("order") != null) {
						order = Integer.parseInt(request.getParameter("order"));
					}
					if(!("5".equals(request.getParameter("price"))) && request.getParameter("price") != null) {
						price = Integer.parseInt(request.getParameter("price"));
					}
					TripDAO dao = new TripDAO();
					dormList = dao.getDormList(cat_no, start, end, wifi, park, air, dry, port, room_person, order, price);
					request.setAttribute("dormList", dormList);
					
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				nextPage = "/reservation.jsp";
				
			} else if (action.equals("detail.do")) {
				int dormno = Integer.parseInt(request.getParameter("dormno"));
				
				Calendar cal = Calendar.getInstance();
				String format = "yyyy-MM-dd";
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				cal.add(cal.DATE, +1);
				String tomorrow = sdf.format(cal.getTime());
				
				java.util.Date today = new java.util.Date();
				String checkin = sdf.format(today);
				String checkout = tomorrow;
				
				if(request.getParameter("reserve_checkin") != null)
					checkin= request.getParameter("reserve_checkin");
				if(request.getParameter("reserve_checkout") != null)
					checkout = request.getParameter("reserve_checkout");
				
				java.util.Date checkindate = sdf.parse(checkin);
				java.util.Date checkoutdate = sdf.parse(checkout);
				long reserveday = (checkoutdate.getTime() - checkindate.getTime()) / (24*60*60*1000);
				DormDTO dormdto = tripdao.selectDorm(dormno);
				List<RoomDTO> roomsList = tripdao.selectRoomsList(dormno);
				List<ReviewDTO> reviewsList = tripdao.selectReviewsList(dormno);
				request.setAttribute("dormdto", dormdto);
				request.setAttribute("roomsList", roomsList);
				request.setAttribute("reviewsList", reviewsList);
				request.setAttribute("roomday", reserveday);
				request.setAttribute("tomorrow", tomorrow);
				request.setAttribute("checkin", checkin);
				request.setAttribute("checkout", checkout);
				
				nextPage = "/trip01/detail.jsp";
				
			} else if (action.equals("upload.do")) {
			
				String title = "";
				String contents = "";
				double score = 0;
				long now = System.currentTimeMillis();
				Date date = new Date(now);
				int reservNo = 0;
				String memberId = "";
				int dormno = 0;
				
				String picture = "1";
				String encoding = "utf-8"; 
				
				File currentDirPath = new File("C:\\workstation\\project_trip\\src\\main\\webapp\\image\\review");
				DiskFileItemFactory factory = new DiskFileItemFactory();  
				factory.setRepository(currentDirPath); 
				factory.setSizeThreshold(1024 * 1024); 

				ServletFileUpload upload = new ServletFileUpload(factory); 
				try {
					List items = upload.parseRequest(request); 
					System.out.println(items);
					for (int i = 0; i < items.size(); i++) {
						FileItem fileItem = (FileItem) items.get(i); 
						if (fileItem.isFormField()) {
							System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
							
							if (fileItem.getFieldName().equals("reviewtitle")) {
								title = fileItem.getString(encoding);
							}
							if (fileItem.getFieldName().equals("reviewcontents")) {
								contents = fileItem.getString(encoding);
							}
							if (fileItem.getFieldName().equals("reviewscore")) {
								score = Double.parseDouble(fileItem.getString(encoding));
							}
							if (fileItem.getFieldName().equals("reserveno")) {
								reservNo = Integer.parseInt(fileItem.getString(encoding));
							}
							if (fileItem.getFieldName().equals("memberid")) {
								memberId = fileItem.getString(encoding);
							}
							if (fileItem.getFieldName().equals("dormno")) {
								dormno = Integer.parseInt(fileItem.getString(encoding));
							}
						} else { 
							System.out.println("param:" + fileItem.getFieldName());
							System.out.println("file name:" + fileItem.getName());
							System.out.println("file size:" + fileItem.getSize() + "bytes");

							if (fileItem.getSize() > 0) { 
								int idx = fileItem.getName().lastIndexOf("\\"); 
								if (idx == -1) {
									idx = fileItem.getName().lastIndexOf("/");
								}
								String fileName = fileItem.getName().substring(idx + 1); 
								long timestamp = System.currentTimeMillis(); 
								String temp = "";
								temp = temp + timestamp;
								temp = temp.substring(1, temp.length());
								fileName = temp + "_" + fileName;
								picture = fileName;
								System.out.println("fileName = "+fileName);
								File uploadFile = new File(currentDirPath + "\\" + fileName);
								fileItem.write(uploadFile); 
							} 
						} 
					} 
				} catch (Exception e) {
					e.printStackTrace();
				}
				memberId = (String) session.getAttribute("id");
				System.out.println(title);
				System.out.println(contents);
				System.out.println(score);
				System.out.println(date);
				System.out.println(picture);
				System.out.println(reservNo);
				System.out.println(memberId);
				
				tripdao.insertReview(title, contents, score, date, picture, reservNo, memberId);
				
				nextPage = "/trip?action=detail.do&dormno="+dormno+"";
			} else if(action.equals("result.do")) {
				System.out.println("result 들어옴");
				String member = request.getParameter("member_id");
				
				try {
					//받아온 값 가지고 인서트 해주고, 그거를 history.do로 가서 결과값 출력해주기
					System.out.println("인서트 해주는 부분");
					//tripdao.insertReservation
					String dorm_name = request.getParameter("dormname");
					System.out.println(dorm_name);
					//insertReservation
					
					System.out.println(member);
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				nextPage = "/trip?action=history.do&member_id=" + member + "";
			}else if(action.equals("history.do")) {
				System.out.println("히스토리 들어옴");
				try {
					//(String) session.getAttribute("member_id");
					// session.member_id 담기
					String member = "admin";
					List<ReservationDTO> reserList = tripdao.selectReservationsList(member);
					request.setAttribute("reserList",reserList );
					System.out.println(reserList.size());
					
					if(reserList != null && reserList.size()> 0) {
						System.out.println("List내용있음, 예약내역 출력");
						nextPage = "/trip01/history.jsp";
					//session.member_id값과 비교하기
						
					} else if(reserList.size() == 0 && member == null) {
						nextPage = "/trip01/nohistory2.jsp";
					} else if(reserList.size() == 0 && member != null) {
						System.out.println("예약내역 없음");
						nextPage = "/trip01/nohistory.jsp";
					}
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			} else if (action.equals("page8.do")) {
				System.out.println("page8 서블릿 들어옴");
				//가져와야 하는 값 숙소이름, 객실 이름, 체크인 체크아웃 금액 룸 컨텐츠..? 세션..?
				//member_id member_tel 처음 받는 값 
				//String member = (String)session.getAttribute("member_id");
				//String tel = (String)session.getAttribute("member_tel");
				//String member_id = (String)session.getAttribute("id");
				int dorm_no = Integer.parseInt( request.getParameter("dormno"));
				int room_no = Integer.parseInt( request.getParameter("roomno"));
				String dorm_name = request.getParameter("dormname");
				String room_name = request.getParameter("roomname");
				int reserve_pay = Integer.parseInt(request.getParameter("roompay"));
				Date reser_checkin = Date.valueOf(request.getParameter("reserve_checkin"));
				Date reserve_checkout = Date.valueOf(request.getParameter("reserve_checkout"));
		
				CheckDTO checkDto = new CheckDTO();
				checkDto = tripdao.checkList(dorm_no,room_no,dorm_name,room_name,reser_checkin,reserve_checkout,reserve_pay);
				request.setAttribute("check", checkDto);
				nextPage = "/page8.jsp";
			} else if(action.equals("review.do")) {
				System.out.println("액션 리뷰 들어옴");
				try {
					int reserveno = Integer.parseInt(request.getParameter("reserve_no"));
					System.out.println(session.getAttribute("id"));
					ReservationDTO reservationdto = tripdao.selectReservation(reserveno);
					
					request.setAttribute("reserveno", reserveno);
					request.setAttribute("reservationdto", reservationdto);					
					
				}catch (Exception e) {
					e.printStackTrace();
				}

				nextPage = "/review.jsp";
			}else if (action.equals("loginForm.do")) {
					nextPage = "/login.jsp";

				} else if (action.equals("joinForm.do")) {
					nextPage = "/signup.jsp";

				} else if (action.equals("join.do")) { // 회원가입
					String id = request.getParameter("id");
					String password = request.getParameter("password");
					String name = request.getParameter("name");
					String tel = request.getParameter("tel");

					MemberDTO memberDTO = new MemberDTO();
					try {
						memberDTO.setMember_id(id);
						memberDTO.setMember_pw(password);
						memberDTO.setMember_name(name);
						memberDTO.setMember_tel(tel);

						memberDAO.join(memberDTO);
						System.out.println(id);
						if (id.equals("") || password.equals("") || name.equals("") || tel.equals("")) {
							nextPage = "/signup.jsp";
						} else {
							nextPage = "/login.jsp";
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (action.equals("login.do")) {
					String id = request.getParameter("id");
					String password = request.getParameter("password");

					MemberDTO memberDTO = new MemberDTO();

					try {
						memberDTO.setMember_id(id);
						memberDTO.setMember_pw(password);
						MemberDTO mem = memberDAO.login(memberDTO);

						if (id.equals(mem.getMember_id()) && password.equals(mem.getMember_pw())) {
							session.setAttribute("id", mem.getMember_id());
							nextPage = "/main.jsp";
						} else {
							nextPage = "/login.jsp";
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (action.equals("loginOut.do")) {
					// session을 초기화
					session.invalidate();

					// 메인 페이지로 되돌아감
					nextPage = "/main.jsp";
			}else {
				System.out.println("잘못들어옴");
			}
			
			
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e ) {
			e.printStackTrace();
		}
		
	}

}
