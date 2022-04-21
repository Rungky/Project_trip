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

				
					<table>
						<tr>
							<td>예약내역이 없습니다</td>
						</tr>
						<tr>
							<td><a href="/project_trip/reservation.jsp">예약하러 가기</a></td>
						</tr>
					</table>
			</div>
		</article>
	</section>
	<footer>
		고객 행복 센터 041-111-1111<br> ㈜ 사적모임<br> 주소 : 천안시 서북구 대흥로 256<br>
		전자우편주소 | human@email.com
	</footer>
</body>

</html>