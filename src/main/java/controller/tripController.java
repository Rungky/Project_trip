package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.TripDAO;
import dto.*;



@WebServlet("/trip")
public class tripController extends HttpServlet {
	TripDAO tripdao = new TripDAO();
	
	


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
		System.out.println(request.getParameter("action"));

		System.out.println("get");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
		System.out.println(request.getParameter("action"));
		
		System.out.println("post");
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
				long miliseconds = System.currentTimeMillis();
				Date start = new Date(miliseconds);
				Date end = new Date(miliseconds);
				int wifi = 0;
				int park = 0;
				int air = 0;
				int dry = 0;
				int port = 0;
				int room_person = 1;
				request.setAttribute("date_s", start);
				request.setAttribute("date_e", end);
				
				
				
				int cat_no = Integer.parseInt(request.getParameter("dorm_category_no"));

				try {
					
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
					
					TripDAO dao = new TripDAO();
					dormList = dao.getDormList(cat_no, start, end, wifi, park, air, dry, port, room_person);
					request.setAttribute("dormList", dormList);
					
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				nextPage = "/reservation.jsp";
				
			} else if (action.equals("detail.do")) {
				int dormno = Integer.parseInt(request.getParameter("dormno"));
				DormDTO dormdto = tripdao.selectDorm(dormno);
				List<RoomDTO> roomsList = tripdao.selectRoomsList(dormno);
				List<ReviewDTO> reviewsList = tripdao.selectReviewsList(dormno);
				request.setAttribute("dormdto", dormdto);
				request.setAttribute("roomsList", roomsList);
				request.setAttribute("reviewsList", reviewsList);
				
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
				
				File currentDirPath = new File("C:\\Users\\jin58\\eclipse-workspace\\project_trip\\src\\main\\webapp\\image\\review");
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
				System.out.println(title);
				System.out.println(contents);
				System.out.println(score);
				System.out.println(date);
				System.out.println(picture);
				System.out.println(reservNo);
				System.out.println(memberId);
				
				tripdao.insertReview(title, contents, score, date, picture, reservNo, memberId);
				
				nextPage = "/trip?action=detail.do&dormno="+dormno+"";
			} else if(action.equals("history.do")) {
				System.out.println("히스토리 들어옴");
				try {
					// session.member_id 담기
					List<ReservationDTO> reserList = tripdao.selectReservationsList("admin");
					request.setAttribute("reserList",reserList );
					//객실 코드 받을 수 있음 
					
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				nextPage = "/trip01/history.jsp";
			} else if (action.equals("page8.do")) {
				//가져와야 하는 값 숙소이름, 객실 이름, 체크인 체크아웃 금액 룸 컨텐츠..? 세션..?
				//member_id member_tel
				//String dorm_no = request.getParameter("dorm_no");
				//String dorm_name = request.getParameter("dorm_name");
				
				//String room_name = request.getParameter("room_name");
				
				
				nextPage = "/page8.jsp";
			} else if(action.equals("review.do")) {
				System.out.println("액션 리뷰 들어옴");
				//reservationDTO 넘겨주기
				try {
					int reserve_no = Integer.parseInt(request.getParameter("reserve_no"));
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}

				nextPage = "/review.jsp";
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
