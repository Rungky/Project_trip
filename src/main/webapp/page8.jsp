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
<link rel="stylesheet" href="./css/header_footer.css">
<script>

function alarm() {
	alert("예약이 완료되었습니다!");
}


</script>
</head>
<body>
	<%@ include file="./header.jsp"%>
	<section>
		<div class="sec">
			<div class="info">예약자 정보</div>
			<br>
			<div>
				<div>예약자 이름</div>
				<div>${id}</div>
			</div>
			<br>
			<div>
				<div>휴대폰 번호</div>
				<div>${dto.member_tel}</div>
			</div>
			<br>
			<!-- 상세페이지에서 넘겨온 내용들 session에 담아서 여기다가 출력 -->
			<!-- session.member_tel -->


			<div class="name">
				<div class="name2">숙소 이름</div>
				<div class="c1">${check.dorm_name}</div>
				<br>
				<div class="name2">객실 이름</div>
				<div class="c2">${check.room_name}</div>
				<div class="c3"></div>
				<br>
				<div>
					<div class="name2">체크인</div>
					<div class="c4">${check.reserve_checkin}</div>
				</div>
				<br>
				<div>
					<div class="name2">체크아웃</div>
					<div class="c5">${check.reserve_checkout}</div>
				</div>
				<br>
				<div class="c6">
					<div>총 결제금액(VAT포함)</div>
					<div>${check.reserve_pay}</div>
				</div>
			</div>
			<!--  member_id 가져가야함-->
			<form action="trip">
				<button class="box" name="action" value="result.do">예약</button>
				<input type="hidden" name="dorm_no" value="${check.dorm_no}">
				<input type="hidden" name="room_no" value="${check.room_no}">
				<input type="hidden" name="reserve_checkin" value="${check.reserve_checkin}">
				<input type="hidden" name="reserve_checkout" value="${check.reserve_checkout}">
				<input type="hidden" name="reserve_pay" value="${check.reserve_pay}">
			</form>
		</div>
	</section>
	<%@ include file="./footer.jsp"%>
</body>
</html>