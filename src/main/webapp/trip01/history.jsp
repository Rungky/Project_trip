<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="dao.TripDAO" import="dto.*"
	import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>history</title>

<link rel="stylesheet" href="../css/history.css">
<link rel="stylesheet" href="./css/header_footer.css">
<style>
.atc {
    font-size: 25px;
    font-weight: bold;
    padding-bottom: 10px;
    margin-bottom: 10px;
    border-bottom: 1px solid black;
}

table {
    margin-bottom: 20px;
    background-color: antiquewhite;
    padding: 10px 80px 10px 10px;
    width: 1000px;
}
</style>
</head>

<body>

	<header>
		<div class="hd">
			<div>
				<a href="/project_trip/main.jsp">
					<button class="bt">
						<img class="rogo" src="image/logo.png">
					</button>
				</a>
			</div>
			<nav>
				<ul>
					<li><a href="/project_trip/trip?action=history.do">예약 내역</a></li>
					<li><a href="/project_trip/qna.jsp">Q&A</a></li>
					<li><a href="/project_trip/mypage.jsp">${'member_name'}</a></li>
					<!-- 사용자: session에 담긴(value 아마도 member_name  session.member_name 회원 닉네임 -->
				</ul>
			</nav>
		</div>
	</header>
	<section>
		<article style='display:flex; justify-content:center;'>
				<div class="pd">
					<div class="atc">예약 내역</div>

				<c:forEach var="result" items="${reserList}">
					<table>
						<tr>
							<td colspan="3">숙소 예약번호 ${result.reserve_no}</td>
						</tr>
					<tr>
							<td rowspan="4" class="img"><img class="img2" style="width:150px; height: 100px; padding: 10px"
								src="${'https://image.goodchoice.kr/resize_490x348/adimg_new/54325/174064/ab3aa6fb6b901e33c6c6e5bf6c2d7b75.jpg'}"></td>
							<td>${'room.room_name' }</td>
							<td rowspan="5" class="rv"><a
								href="/project_trip/trip?action=review.do&reserve_no=${result.reserve_no}&member_id=${result.member_id}"><button class="rvbt"
										name="action" value="review">리뷰</button></a></td>
						</tr>
						<tr>
						
							<td> ${ '룸 컨텐츠'}</td>
						</tr>
						<tr>
							<td>예약 날짜 : ${result.reserve_date}</td>
						</tr>
						<tr>
							<td>체크인 : ${result.reserve_checkin} / 체크아웃 : ${result.reserve_checkout}</td>
						</tr>
						<tr>
							<td colspan="3" class="right" style="text-align:right; font-weight:bold;">금액 <span class="pri">${result.reserve_pay}</span></td>
						</tr>
					</table>
				</c:forEach>
			</div>
		</article>
	</section>
	<footer>
		고객 행복 센터 041-111-1111<br> ㈜ 사적모임<br> 주소 : 천안시 서북구 대흥로 256<br>
		전자우편주소 | human@email.com
	</footer>
</body>

</html>