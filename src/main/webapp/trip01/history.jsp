<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>history</title>

    <link rel="stylesheet" href="../css/history.css">
</head>

<body>
<%
TripDAO resortdao = new TripDAO();
	List<RoomDTO> roomsList = dao.selectRoomsList(1);
	List<ReservationDTO> reservationsList = dao.selectReservationsList("asd");
%>

    <header>
        <div class="hd">
            <div>
                <form action="">
                    <button class="bt"><img class="rogo" src="../image/logo.png"></button>
                </form>
            </div>
            <nav>
                <ul>
                    <li><a href="">예약 내역</a></li>
                    <li><a href="">Q&A</a></li>
                    <li><a href="">로그아웃</a></li>

                </ul>
            </nav>
        </div>
    </header>
    <section>
        <article>
            <div class="pd">
                <div class="atc">
                    예약 내역
                </div>
                
                <%for(int i = 0 ; i<reservationsList.size();i++) { %>
                <table>
                    <form method="post" action="review.do">
                        <tr>
                            <td colspan="3">숙소 예약번호 ${reservation.reserve_no }</td>
                        </tr>
                        <tr>
                            <td rowspan="4" class="img"><img class="img2" src="../image/${reservation.reserve_picture }"></td>
                            <td>신라스테이 역삼</td>
                            <td rowspan="5" class="rv"><button class="rvbt" name="action" value="review">리뷰</button></td>
                        </tr>
                        <tr>
                            <td>[내맘대로 8시간 STAY-숙박불가] 체크인 시 배정 (15시 체크인) / DayUse</td>
                        </tr>
                        <tr>
                            <td>${reservation.reserve_checkin} ~ ${reservation.reserve_checkout} | 1박</td>
                        </tr>
                        <tr>
                            <td>체크인 15:00 | 체크아웃 14:00</td>
                        </tr>
                        <tr>
                            <td colspan="3" class="right">금액 <span class="pri">87,010원</span></td>
                        </tr>
                    </form>
                </table>
                <%} %>
            </div>
        </article>
    </section>
    <footer>
        고객 행복 센터 041-111-1111<br>
        ㈜ 사적모임<br>
        주소 : 천안시 서북구 대흥로 256<br>
        전자우편주소 | human@email.com
    </footer>
</body>

</html>