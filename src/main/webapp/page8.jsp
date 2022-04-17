<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>예약페이지</title>
<link rel="stylesheet" href="css/page8.css">

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
		<div class="sec">
			<div class="info">예약자 정보</div>
			<br>
			<div>
				<div>예약자 이름</div>
				<!-- session.member_name -->
				<div>${'member_name'}</div>
			</div>
			<br>
			<div>
				<div>휴대폰 번호</div>
				<!-- session.member_tel -->
				<div>${'member_tel'}</div>
			</div>
			<br>
			<!-- 상세페이지에서 넘겨온 내용들 session에 담아서 여기다가 출력 -->
			<!-- session.member_tel -->
			<div class="name">
				<div class="name2">숙소 이름</div>
				<div class="c1">신라스테이 역삼</div>
				<br>
				<div class="name2">객실타입/기간</div>
				<div class="c2">[내맘대로 8시간 STAY-숙박불가]체크인 시 배정</div>
				<div class="c3">(15시 체크인) / DayUse</div>
				<br>
				<div>
					<div class="name2">체크인</div>
					<div class="c4">04.12 화 15:00</div>
				</div>
				<br>
				<div>
					<div class="name2">체크아웃</div>
					<div class="c5">04.12 화 23:59</div>
				</div>
				<br>
				<div class="c6">
					<div>총 결제금액(VAT포함)</div>
					<div>87,010원</div>
				</div>
			</div>
			<!--  예약완료 누르면 인서트 메소드 실행 -->
			<input type="button" value="예약완료" class="box">
		</div>
	</section>
	<footer>
		고객 행복 센터 | 041-111-1111<br> ㈜ 사적모임<br> 주소 : 천안시 서북구 대흥로 256<br>
		전자우편주소 | human@email.com
	</footer>
</body>
</html>