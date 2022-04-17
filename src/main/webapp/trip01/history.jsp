<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="dao.TripDAO"
    import="dto.*"
    import="java.util.List"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>history</title>

    <link rel="stylesheet" href="../css/history.css">
    <link rel="stylesheet" href="./css/header_footer.css">
</head>

<body>

    <header>
        <div class="hd">
            <div>
                <a href="/project_trip/main.jsp">
					<button class="bt"><img class="rogo" src="image/logo.png"></button>
				</a>
            </div>
            <nav>
                <ul>
                   <li><a href="/project_trip/trip01/history.jsp">예약 내역</a></li>
					<li><a href="/project_trip/qna.jsp">Q&A</a></li>
                   <li><a href="/project_trip/mypage.jsp">${'member_name'}</a></li>
					<!-- 사용자: session에 담긴(value 아마도 member_name  session.member_name 회원 닉네임 -->
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
                
                
                <c:forEach var="reservation" items="${reservationsList}" var="room" items="${roomsList}" >
                <table>
                    <form method="post" action="trip.do">
                        <tr>
                            <td colspan="3">숙소 예약번호 ${reservation.reserve_no }</td>
                        </tr>
                        <tr>
                            <td rowspan="4" class="img"><img class="img2" src="../image/${reservation.reserve_picture }"></td>
                            <td>${room.room_name }</td>
                            <td rowspan="5" class="rv">
                            	<button class="rvbt" name="action" value="review">리뷰</button>
                            </td>
                        </tr>
                        <tr>
                            <td>[내맘대로 8시간 STAY-숙박불가] 체크인 시 배정 (15시 체크인) / DayUse</td>
                        </tr>
                        <tr>
                            <td>${reservation.reserve_checkin} ~ ${reservation.reserve_checkout} | 1박</td>
                        </tr>
                        <tr>
                            <td>${reservation.reserve_checkin} | ${reservation.reserve_checkout}</td>
                        </tr>
                        <tr>
                            <td colspan="3" class="right">금액 <span class="pri">${reservation.reserve_pay}</span></td>
                        </tr>
                    </form>
                </table>
				</c:forEach>
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