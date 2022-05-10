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
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<script>

	$(function(){
		bind();
	});
	
	function bind(){
		jQuery("#check_all").off("click").on("click", function(){
			if($("#check_all").prop("checked")){
				$(this).parent().find('input').prop("checked", true);			
			} else {
				$(this).parent().find('input').prop("checked", false);			
			}
		});
		
		$("#btn_pay").off("click").on("click", function(){
			if(!$(".one").prop("checked")){
				alert("동의 항목을 확인해주세요!");
				return false;
			}
			if(!$(".two").prop("checked")){
				alert("동의 항목을 확인해주세요!")
				return false;
			}
		})
	}
	

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
				<div class="name2">[숙소 이름]</div>
				<div class="c1">${check.dorm_name}</div>
				<br>
				<div class="name2">[객실 이름]</div>
				<div class="c2">${check.room_name}</div>
				<div class="c3"></div>
				<br>
				<div>
					<div class="name2">[체크인]</div>
					<div class="c4">${check.reserve_checkin}</div>
				</div>
				<br>
				<div>
					<div class="name2">[체크아웃]</div>
					<div class="c5">${check.reserve_checkout}</div>
				</div>
				<br>
				<div class="c6">
					<div>[총 결제금액(VAT포함)]</div>
					<div>${check.reserve_pay}</div>
				</div>
				<br>
				<div class="c7">
					<div>[ 💷결제수단선택]</div>
					<div>
						<select name="pay">
							<option value="a" selected="selected">카카오페이</option>
							<option value="b">신용체크카드</option>
							<option value="c">네이버페이</option>
							<option value="d">휴대폰결제</option>
						</select>
					</div>
				</div>
				<br>
				<div class="c8">
					<div>
						 <input type="checkbox" class="check all" id="check_all" name="check" value="all" >
						  <label for="form">전체동의</label><br>
						 <input type="checkbox" class="check one" name="check" value="one">
						 <label for="form">숙소 이용 규칙 및 취소/환불규정 동의 (필수)</label><br>
						<input type="checkbox" class="check two" name="check" value="two">
						<label for="form">개인정보 수집 및 이용 동의 (필수)</label><br>
						<input type="checkbox" class="check thr" name="check" value="thr">
						<label for="form">광고 sns 홍보 수신 동의</label><br>
					</div>
				</div>
			</div>
			<!--  member_id 가져가야함-->
			<form action="trip">
				<button id="btn_pay" class="box" name="action" value="result.do">결제하기</button>
				<button class="box" onclick="history.back()">돌아가기</button>
				<input type="hidden" name="dorm_no" value="${check.dorm_no}">
				<input type="hidden" name="room_no" value="${check.room_no}">
				<input type="hidden" name="reserve_checkin"
					value="${check.reserve_checkin}"> <input type="hidden"
					name="reserve_checkout" value="${check.reserve_checkout}">
				<input type="hidden" name="reserve_pay" value="${check.reserve_pay}">
			</form>
		</div>
	</section>
	<%@ include file="./footer.jsp"%>
</body>
</html>