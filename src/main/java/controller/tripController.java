package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

import dao.MemberDAO;
import dao.TripDAO;
import dto.CheckDTO;
import dto.DormDTO;
import dto.DormVO;
import dto.MemberDTO;
import dto.QuestionDTO;
import dto.ReservationDTO;
import dto.ReviewDTO;
import dto.RoomDTO;
import service.MemberService;
import service.QnaService;

@WebServlet("/trip")
public class tripController extends HttpServlet {
	TripDAO tripdao = new TripDAO();
	MemberDAO memberDAO = new MemberDAO();
	QnaService qnaservice = new QnaService();
	MemberService memberService = new MemberService();

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
				
				nextPage = "/trip?action=main.do";
			}  else if (action.equals("reservation.do")) {
				
				List<DormVO> dormList = new ArrayList<DormVO>();
				
				// category_no를 이용해 dorm 정보 조회
				int cat_no =0;
				
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date1 = newDtFormat.format(cal.getTime());
				Date start = new Date(newDtFormat.parse(date1).getTime());

				cal.add(cal.DATE, +1);
				String date2 = newDtFormat.format(cal.getTime());
				
				Date end = new Date(newDtFormat.parse(date2).getTime());
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
					
					
					
					if(request.getParameter("start") != null) {
					
						start = new Date(newDtFormat.parse(request.getParameter("start")).getTime());
						
						request.setAttribute("date_s", start);
					}
					
					if(request.getParameter("end") != null) {
						
						end = new Date(newDtFormat.parse(request.getParameter("end")).getTime());
						
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
				String id = "";
				if (session.getAttribute("id") != null) {
					id = (String) session.getAttribute("id");
				}
				
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
				
				if(checkindate.after(checkoutdate) || checkin.equals(checkout)) {
					checkin = sdf.format(today);
					checkout = tomorrow;
					checkindate = sdf.parse(checkin);
					checkoutdate = sdf.parse(checkout);
					request.setAttribute("dateerror", "error");
				}
				
				long reserveday = (checkoutdate.getTime() - checkindate.getTime()) / (24*60*60*1000);
				DormDTO dormdto = tripdao.selectDorm(dormno);
				Date checkIn = new Date(checkindate.getTime());
				Date checkOut = new Date(checkoutdate.getTime());
				List<RoomDTO> roomsList = tripdao.selectRoomsList(dormno);
				List<RoomDTO> reservedroomsList = tripdao.reservedRoomsList(dormno, checkIn, checkOut);
				boolean like_tg = tripdao.checkLike(dormno, id);
				for(int i = 0 ; i <roomsList.size();i++) {
					for(int j = 0; j <reservedroomsList.size();j++) {
						if (roomsList.get(i).getRoom_no()==reservedroomsList.get(j).getRoom_no()) {
							roomsList.get(i).setReserved(1);
							break;
						} else {
							roomsList.get(i).setReserved(0);
						}
					} 
				}
				
				List<ReviewDTO> reviewsList = tripdao.selectReviewsList(dormno);
				request.setAttribute("dormdto", dormdto);
				request.setAttribute("roomsList", roomsList);
				request.setAttribute("reviewsList", reviewsList);
				request.setAttribute("roomday", reserveday);
				request.setAttribute("tomorrow", tomorrow);
				request.setAttribute("checkin", checkin);
				request.setAttribute("checkout", checkout);
				request.setAttribute("like_tg", like_tg);
				
				nextPage = "/trip01/detail.jsp";
				
			} else if (action.equals("like.do")) {
				int dormno = Integer.parseInt(request.getParameter("dormno"));
				String id = (String) session.getAttribute("id");
				boolean like_tg = Boolean.parseBoolean(request.getParameter("like"));
				System.out.println(like_tg);
				if(like_tg) {
					tripdao.deleteLike(dormno,id);
					tripdao.changeLike(dormno, -1);
				} else {
					tripdao.insertLike(dormno,id);
					tripdao.changeLike(dormno, 1);
				}
				like_tg = !like_tg;
				PrintWriter out = response.getWriter();
				out.print("{\"param\":\""+like_tg+"\"}");
				System.out.println(like_tg);
				return;
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
				System.out.println("");
				
				
				if (title.equals("") || contents.equals("")) {
					request.setAttribute("textnull", "textnull");
					System.out.println("텍스트 null오류");
					nextPage = "/trip?action=review.do&reserve_no="+reservNo+"";
				} else {
					System.out.println("INSERT");
					tripdao.insertReview(title, contents, score, date, picture, reservNo, memberId);
					response.sendRedirect("/project_trip/trip?action=detail.do&dormno="+dormno+"");
					return;
				}
					
				
				
			} else if(action.equals("result.do")) {
				System.out.println("result 들어옴");
				String member = (String)session.getAttribute("id");
				
				try {
					//받아온 값 가지고 인서트 해주고, 그거를 history.do로 가서 결과값 출력해주기
					System.out.println("인서트 해주는 부분");
					int dorm_no = Integer.parseInt( request.getParameter("dorm_no"));
					System.out.println(dorm_no);
					int room_no = Integer.parseInt( request.getParameter("room_no"));
					System.out.println(room_no);
					Date reserve_checkin = Date.valueOf(request.getParameter("reserve_checkin"));
					Date reserve_checkout = Date.valueOf(request.getParameter("reserve_checkout"));
					int reserve_pay = Integer.parseInt(request.getParameter("reserve_pay"));
					System.out.println(reserve_pay);
					tripdao.insertReservation(member,reserve_checkin, reserve_checkout,reserve_pay,room_no,dorm_no );
					System.out.println("인서트 성공");
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				response.sendRedirect("/project_trip/trip?action=history.do&member_id=" + member);
				return;
//				nextPage = "/trip?action=history.do&member_id=" + member + "";
			} else if(action.equals("reserDelete.do")) {
				System.out.println("예약취소부분");
				int reserve_no = Integer.parseInt(request.getParameter("reserve_no"));
				tripdao.reserDelete(reserve_no);
				
				response.sendRedirect("/project_trip/trip?action=history.do");
				return;
//				nextPage = "/trip?action=history.do";
			}
			else if(action.equals("history.do")) {
				String member = (String)session.getAttribute("id");
				System.out.println("히스토리 들어옴");
				try {
					MemberDTO dto = tripdao.memberDto(member);
					request.setAttribute("dto", dto);
					
					List<ReservationDTO> reserList = tripdao.selectReservationsList(member);
					request.setAttribute("reserList",reserList );
					System.out.println(reserList.size());
					
					if(reserList != null && reserList.size()> 0) {
						System.out.println("List내용있음, 예약내역 출력");
						nextPage = "/trip01/history.jsp";
					
						
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
				String member = (String)session.getAttribute("id");
				MemberDTO dto = tripdao.memberDto(member);
				request.setAttribute("dto", dto);
				
				//예약하기전 출력
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
				
				
				nextPage = "/page8.jsp";;
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
							nextPage = "/trip?action=main.do";
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
					nextPage = "/trip?action=main.do";
			}else if(action.equals("qna.do")) {
				System.out.println("qna 페이지");
				//questionDTO 담기
				List<QuestionDTO> QuestionList = new ArrayList<QuestionDTO>();
				
				String strPageNum  =request.getParameter("pageNum");
				String strCountPerPage  =request.getParameter("countPerPage");
				int pageNum = 1;
				int countPerPage = 5;
				if(strPageNum != null) {
					pageNum = Integer.parseInt(strPageNum);
				}if(strCountPerPage != null) {
					countPerPage = Integer.parseInt(strCountPerPage);
				}
				
				QuestionList=qnaservice.listArticles(pageNum,countPerPage);
				System.out.println("size : "+QuestionList.size());
				request.setAttribute("questionList", QuestionList);
				
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("countPerPage", countPerPage);
				nextPage = "/qna.jsp";
			}else if(action.equals("qnaForm.do")) {
				nextPage = "/questionWrite.jsp";
			}else if(action.equals("addqna.do")) {
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				String id= (String)session.getAttribute("id");
				
				QuestionDTO qdto = new QuestionDTO();
				qdto.setQuestion_title(title);
				qdto.setQuestion_contents(content);
				qdto.setQuestion_parentno(0);
				
				
				long miliseconds = System.currentTimeMillis();
		        Date date = new Date(miliseconds);
				qdto.setQuestion_date(date);
				qdto.setQuestion_picture("sss");
				qdto.setQuestion_view(0);
				qdto.setMember_id(id);
				
				qnaservice.addArticle(qdto);
				response.sendRedirect("/project_trip/trip?action=qna.do");
				return;
//				nextPage = "/trip?action=qna.do";
				
			}else if(action.equals("replyqna.do")) {
				String recontent = request.getParameter("recontent");
				String parentNO = request.getParameter("parentNO");
				
				QuestionDTO qdto = new QuestionDTO();
				qdto.setQuestion_contents(recontent);
				qdto.setQuestion_parentno(Integer.parseInt(parentNO));
				qdto.setQuestion_title("retitle");
				
				long miliseconds = System.currentTimeMillis();
		        Date date = new Date(miliseconds);
				qdto.setQuestion_date(date);
				qdto.setQuestion_picture("picture");
				qdto.setQuestion_view(0);
				qdto.setMember_id("admin");
				
				qnaservice.addReply(qdto);
				response.sendRedirect("/project_trip/trip?action=replylist.do");
				return;
//				nextPage = "/trip?action=replylist.do";
			}else if(action.equals("answerqna.do")) {
				List<QuestionDTO> QuestionList = new ArrayList<QuestionDTO>();
				
				int product_no = Integer.parseInt(request.getParameter("product_no"));
				
				QuestionList=qnaservice.listQna(product_no);
				System.out.println("size : "+QuestionList.size());
				request.setAttribute("questionList", QuestionList);
				nextPage = "/qna_answer.jsp";
			}else if(action.equals("replylist.do")) {
				List<QuestionDTO> QuestionList = new ArrayList<QuestionDTO>();
				
				QuestionList=qnaservice.ReplyQna();
				System.out.println("size : "+QuestionList.size());
				request.setAttribute("questionList", QuestionList);
				nextPage = "/trip?action=qna.do";
			
			}else if (action.equals("main.do")) {
				
				List<DormDTO> dormList = new ArrayList(); 
				dormList = memberDAO.selectDormList();
				request.setAttribute("dormList", dormList);
				
				Calendar cal = Calendar.getInstance();
				String format = "yyyy-MM-dd";
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				Calendar calendar = new GregorianCalendar();
				SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
				
				String reserve_checkin = SDF.format(calendar.getTime());		
				calendar.add(Calendar.DATE, +1);	
				String reserve_chekout = SDF.format(calendar.getTime());		
				
				request.setAttribute("reserve_checkin",reserve_checkin);
				request.setAttribute("reserve_checkout", reserve_chekout);
				nextPage = "/main.jsp";
				
			}else if (action.equals("mypage.do")){
				// 로그인 미구현으로 인한 임시코드
				//session.setAttribute("member_id","co9382");
				//================
				
				String member_id = (String) session.getAttribute("id");
				MemberDTO memberDTO = memberService.selectMember(member_id);
				request.setAttribute("member",memberDTO);
				nextPage = "/mypage.jsp";
			} else if (action.equals("modify_name.do")) {

				String member_id = (String) request.getParameter("member_id");
				String member_name = (String) request.getParameter("member_name");
				System.out.println("받은 아이디와 새 닉네임:"+member_id+"&"+member_name);
				
				if (member_id != null && !("".equals(member_id)) && member_name != null && !("".equals(member_name))) {
					memberService.modifyMemberName(member_id, member_name);
					System.out.println("닉네임 수정완료");
				}		
				nextPage="/trip?action=mypage.do";  
			}else if (action.equals("modify_pw.do")) {
				
				String member_id = request.getParameter("member_id");
				String member_pw = request.getParameter("member_pw");
				System.out.println("받은 아이디와 새비밀번호:"+member_id+"&"+member_pw);
				if(member_id !=null &&!("".equals(member_id))&& member_pw !=null &&!("".equals(member_pw))) {
					memberService.modifyMemberPw(member_id, member_pw);
					System.out.println("비밀번호 수정완료");
				}
				nextPage="/trip?action=mypage.do";  // 수정된정보를 포함하여 마이페이지로 이동 
				
			}else if(action.equals("logout.do")) {
				session.invalidate();
				nextPage="/trip?action=main.do";   //메인으로 이동
				
			}else if(action.equals("removeMember.do")) {
				
				String member_id = (String) request.getParameter("member_id");
				//System.out.println(member_id);
				memberService.removeMember(member_id);
				session.invalidate();
				nextPage="/trip?action=main.do";   //메인으로 이동
			}else {
				System.out.println("잘못들어옴");
			}
			
			System.out.println("1 >> nextPage:  "+ nextPage);
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		
		} catch (Exception e ) {
			e.printStackTrace();
		}
		
	}

}
