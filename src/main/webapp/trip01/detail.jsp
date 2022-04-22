<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>details</title>
    <link rel="stylesheet" href="./css/detail.css">
    <link rel="stylesheet" href="./css/header_footer.css">
<script>
	if(${!empty dateerror}){
		alert("날짜가 맞지않아 오늘 값으로 되었습니다.");
	}
</script>
</head>

<body>
    <%@ include file="../header.jsp" %>
    <section>
        <div class="center">
            <div class="st">
                <aside>
                    <img class="image" src="${dormdto.dorm_picture }"> 
                </aside>
                <article>
                    <div class="dormtt">
                        <h2>${dormdto.dorm_name }</h2>
                    </div>
                    <div>
                    	<sqan class="avr">${dormdto.scoreAvr}</sqan>리뷰 ${dormdto.review_count}개
                    </div>
                    <br>
                    <div class="dormaddr">
                        ${dormdto.dorm_addr }
                    </div>
                    <br>
                   	<div class="off" style="display: -webkit-box;">
	                    	<div class="top">
	                    		<c:forTokens var="item2" items="${dormdto.dorm_contents }" begin="0" end="0" delims=",">
									${item2}
								</c:forTokens>
								<button onclick="drop_contents()"class="onbutton">더보기</button>
							</div>
							<ul>
								<c:forTokens var="item" items="${dormdto.dorm_contents }" begin="1" delims=",">
									<li>${item}</li>
								</c:forTokens>
							</ul>
	                        취소 및 환불 규정<br>
	                        <ul>
	                            <li>체크인일 기준 7일 전 : 100% 환불</li>
	                            <li>체크인일 기준 4~6일 전 : 50% 환불</li>
	                            <li>체크인일 기준 3일 전 ~ 당일 및 : 환불 불가</li>
	                            <li>취소, 환불시 수수료가 발생할 수 있습니다.</li>
	                        </ul>
                    </div>
                </article>
            </div>
        </div>
    </section>
    <div id="cancel" onclick="cancel()" class="hiddenbt" style="display:none">
		&nbsp;	
	</div>
    <section>
        <div class="center">
            <div class="st2">
                <input id="tab1" type="radio" name="tabs" checked>
                <label for="tab1">객실 안내/예약</label>
                <input id="tab2" type="radio" name="tabs">
                <label for="tab2">리뷰</label>
                
			 	<form  id="tb1" class="calender_box" action="trip">
			 		<input  class="calender" type="date" name="reserve_checkin" min="${checkin}" value="${checkin}">
			 		<span style="font-size:30px;">~</span>
			 		<input class="calender" type="date" name="reserve_checkout" min="${tomorrow}" value="${checkout}">
			 		<button class="datebt" name="action" value="detail.do">적용</button>
			 		<input type="hidden" name="dormno" value="${dormdto.dorm_no}">
			 	</form>
			 	
	                <c:forEach var="item" items="${roomsList }">
                    	          
	                <c:choose>
	                <c:when test="${0 == item.room_pay_day }"> <!-- 대실 금액이 눌이아니면 둘다 표시 눌이면 하나만 표시로 두개 나눠서 조건에 맞게 출력 int라서 0이 눌 -->
		                <table id="tb1">
		                    <tr>
		                        <td rowspan="4"><img class="image2" src="${item.room_picture }"></td>
		                        <!-- 객실 세부 내용 클릭시 콘텐츠 출력 팝업 띄우기  -->
		                        <td colspan="2" class="ti">${item.room_name }</td>
		                        
		                    </tr>
		                    <tr>
		                        <td style="font-weight:bold;">가격</td>
		                        <td class="pr">${item.room_pay_night }원</td>
		                    </tr>
		                    <tr>
		                    	<td colspan="2" class="bd-top bdbt">
		                    		<button id="roombt">객실 상세</button>
		                    	</td>
		                    	<td class="roomdetail" style="display :none;">
		                    		<div class="right pd" onclick="cancel()">
		                    			<button class="closebt">X</button>
		                    		</div>
		                    		<ul>
		                        		<c:forTokens var="item2" items="${item.room_contents }" delims=",">
	                            			<li>${item2}</li>
	                            		</c:forTokens>
	                        		</ul> 
		                    	</td>
		                    </tr>
		                    <tr>
		                    	
		                        <td class="bdbt" colspan="2">
		                            <form action="trip">
		                                <button class="rsv2" name="action" value="page8.do">예약</button>
		                                <input type="hidden" name="roomno" value="${item.room_no}">
		                                <input type="hidden" name="roomname" value="${item.room_name}">
		                                <input type="hidden" name="dormno" value="${dormdto.dorm_no}">
		                                <input type="hidden" name="dormname" value="${dormdto.dorm_name}">
		                                <input type="hidden" name="roompay" value="${roomday * item.room_pay_night}">
		                                <input type="hidden" name="reserve_checkin" value="${param.reserve_checkin}">
		                                <input type="hidden" name="reserve_checkout" value="${param.reserve_checkout}">
		                            </form>
		                        </td>
		                    </tr>
		                </table>
	                </c:when>
	                <c:otherwise>
		                <table id="tb1">
		                    <tr>
		                        <td rowspan="4"><img class="image2" src="${item.room_picture }"></td>
                        		<td colspan="2" class="ti">${item.room_name }</td>
		                    </tr>
		                    <tr>
		                    	<td><span class="wd" style="font-weight:bold;">대실</span></td>
                        		<td><span class="wd" style="font-weight:bold;">숙박</span></td>
		                    </tr>
		                    <tr>
		                        <td>
			                        <div class="wd right">
				                        <span class="pr">${item.room_pay_night }원</span>
			                        </div>
		                        </td>
		                        <td>
		                        	<div class="wd right">
			                       	 	<span class="pr">${item.room_pay_night }원</span>
			                        </div>
		                        </td>
		                    </tr>
		                    <tr>
		                    	
		                        <td class="bdbt">
		                            <form class="wd" action="">
		                                <button class="rsv2" name="action" value="page8.do">예약</button>
		                                <input type="hidden" name="roomno" value="${item.room_no}">
		                                <input type="hidden" name="roomname" value="${item.room_name}">
		                                <input type="hidden" name="dormno" value="${dormdto.dorm_no}">
		                                <input type="hidden" name="dormname" value="${dormdto.dorm_name}">
		                                <input type="hidden" name="roompay" value="${item.room_pay_day}">
		                                <input type="hidden" name="reserve_checkin" value="${param.reserve_checkin}">
		                                <input type="hidden" name="reserve_checkout" value="${param.reserve_checkout}">
		                            </form>
		                        </td>
		                        <td class="bdbt">
		                            <form class="wd" action="">
		                                <button class="rsv2" name="action" value="page8.do">예약</button>
		                                <input type="hidden" name="roomno" value="${item.room_no}">
		                                <input type="hidden" name="roomname" value="${item.room_name}">
		                                <input type="hidden" name="dormno" value="${dormdto.dorm_no}">
		                                <input type="hidden" name="dormname" value="${dormdto.dorm_name}">
		                                <input type="hidden" name="roompay" value="${roomday * item.room_pay_night}">
		                                <input type="hidden" name="reserve_checkin" value="${param.reserve_checkin}">
		                                <input type="hidden" name="reserve_checkout" value="${param.reserve_checkout}">
		                            </form>
		                        </td>
		                    </tr>
		                </table>
	                </c:otherwise> 
	                </c:choose>
	                </c:forEach>
                <c:forEach var="item" items="${reviewsList}">       
	                <table id="tb2">
	                    <tr>
	                        <td colspan="2" class="title">${item.review_title}</td>
	                    </tr>
	                    <tr>
	                        <td class="star${item.score}"><span class="score">${item.review_score}</span></td>
	                        <td></td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" class="rev">${item.member_id}</td>
	                    </tr>
	                    <tr>
	                        <td colspan="2">${item.review_contents}</td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" class="date">${item.review_date}</td>
	                    </tr>
	                    <tr>
	                    	<td>
		                  		<img class="review_img" src="image/review/${item.review_picture}">
		                	</td>
	                	</tr>
	                </table>
                </c:forEach>   
            </div>
        </div>
    </section>

    <%@ include file="../footer.jsp" %>
</body>
<script>
	function drop_contents() {
		let click = document.getElementsByClassName("onbutton");
		let contents = document.getElementsByClassName("off");
		if (contents[0].style.display == ("-webkit-box")) {
			click[0].innerHTML = "접기";
			contents[0].style.display = "block";
		} else {
			click[0].innerHTML = "더보기";
			contents[0].style.display = "-webkit-box";
		}
	}
	
	var roomdetail_list = document.getElementsByClassName("roomdetail");
	var roombt_list = document.querySelectorAll("#roombt");
	var hiddenbt = document.getElementsByClassName("hiddenbt");
	console.log("1");
	for (let i = 0; i < roombt_list.length; i++) {
		console.log("2");
		roombt_list[i].addEventListener("click", function () {
			console.log("4");
	        if (hiddenbt[0].style.display == ("none")) {
	        	roomdetail_list[i].style.display = "block";
	            hiddenbt[0].style.display = "block";
	        }
	    });
	}
	function cancel() {
		for(let j = 0;j<roomdetail_list.length;j++) {
			if(roomdetail_list[j].style.display == ("block")){
				roomdetail_list[j].style.display = "none";
				hiddenbt[0].style.display = "none";
			}
		}  
	}
</script>
</html>