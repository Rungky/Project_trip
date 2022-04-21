<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="dao.*"
    import="dto.*"
    import="java.util.*"
    import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="TripDAO" class="dao.TripDAO" />
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
	function count(type)  {
	  // 결과를 표시할 element
	  const resultElement = document.getElementById('result');
	  
	  // 현재 화면에 표시된 값
	  let number = resultElement.innerText;
	  	  
		  if(type === 'plus') {
		    number = parseInt(number) + 1;
		  }else if(type === 'minus')  {
		    if(number == 1){
		    	number = 1;
		    } else if(number > 1){		    	
		    	number = parseInt(number) - 1;
		    }
		  }
	  
	  // 결과 출력
	  resultElement.innerText = number;
	}
</script>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>목록 페이지</title>
    <link rel="stylesheet" href="css/reser.css">
</head>

<body topmargin="0" bottommargin="0" leftmargin="0" rightmargin="0">
    <%@ include file="./header.jsp" %>

<section>
	<div style="height:15px">
	</div>
    <div class="container">
        <div class="con1">
        	<form method="get" action="${contextPath}/reservation.do" >
	            <div class="ner1">
	                <h3>날짜</h3>
	                check In>> <input type="date" id="start" name="start" value="${date_s}"><br>
	                check Out>> <input type="date" id="end" name="end" value="${date_e}">
	            </div>
	            <div class="ner2">
	                <h3>상세조건</h3>
	                <div class="boxx">
	                    <button type="reset" class="bu re" onClick="resetBtn()">초기화</button>
	                    <button type="submit" class="bu che" onClick="compleBtn()">적용</button>
	                </div>
	            </div>
	            <div class="ner3">
	                <h3>인원</h3>
	                	<input type="button" class="p minus" value="-" onClick='count("minus")' />
	                	<span id="result" name="room_person">2</span>
	                	<input type="hidden" name="room_person" value="2"/>
	                	<input type="button" class="p plus" value="+" onClick='count("plus")' />
	            </div>
	            <div class="ner4">
	                <div id="bom">
	                    <h3>객실 내 시설</h3>
	                </div>
	                <div>
	                   <ul style="list-style-type: none;">
	                        <li class="tum">
	                            <input type="checkbox" id="check_1" class="choice" name="wifi" value="1">
	                            <label for="check_1" class="label_check">와이파이</label>
	                        </li>
	                        <li  class="tum">
	                            <input type="checkbox" id="check_2" class="choice" name="parking" value="1">
	                            <label for="check_2" class="label_check">주차장</label>
	                        </li>
	                        <li  class="tum">
	                            <input type="checkbox" id="check_3" class="choice" name="aircon" value="1">
	                            <label for="check_3" class="label_check">에어컨</label>
	                        </li>
	                        <li  class="tum">
	                            <input type="checkbox" id="check_4" class="choice" name="dryer" value="1">
	                            <label for="check_4" class="label_check">드라이기</label>
	                        </li>
	                        <li  class="tum">
	                            <input type="checkbox" id="check_5" class="choice" name="port" value="1">
	                            <label for="check_5" class="label_check">커피포트</label>
	                        </li>
	                   </ul>
	                </div>
	                
	            </div>
	            <!--
	            <div class="ner5">
	                <h3>최대가격</h3>
	                <div class="dropdown">
	                    <button class="dropbtn">가격선택</button>
						
						<div class="dropdown-content">  
						 <a href="">2만원~3만원</a>
						 <a href="">4만원~5만원</a>     
						 <a href="">8만원이상~</a>     
						 <a href="">9만원 이상~</a>          
						 </div>
					 </div>
	            </div>
	            -->
            </form>
        </div>
        <div class="con2">
            <div class="order"> 
                <button class="button button4">낮은 가격 순</button>
                <button class="button button4">높은 가격 순</button>
            </div>
        
            <div class="towroom">
            	<c:forEach var="i" items="${dormList }" step="1" >

               		<a class="" href="trip?action=detail.do&dormno=${i.dorm_no }&reserve_checkin=${date_s}&reserve_checkout=${date_e}">
                    <div class="romm" style="background-image:url(${i.getDorm_picture()})">
                        <div class="ggumim">
                            <p>
                                <strong>
                                    ${i.dorm_name }
                                </strong>
                            </p>
                            <p>
                                <span>리뷰</span>
                                <span>(</span>
                                <span>${i.count_reserve_no }</span>
                                <span>)</span>
                            </p>
                            <p>
                                <strong>${i.dorm_addr}</strong>
                            </p>
                        </div>
                        <div class="price">
                            <p><strong>
                                ${i.min_pay_night}원
                            </strong></p>
                        </div>
                    </div>
                    </a>
				</c:forEach>
			
            </div>
       </div>

    </div>

</section>
<%@ include file="./footer.jsp" %>
</body>
</html>