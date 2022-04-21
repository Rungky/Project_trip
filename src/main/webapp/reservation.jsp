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
	  document.getElementById('result2').value = number;
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
        	<form method="get" action="${contextPath}/trip" >
	            <div class="ner1">
	            	<input type="hidden" name="dorm_category_no" value="${param.dorm_category_no}">
	            	<input type="hidden" name="action" value="reservation.do">
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
	                	<span id="result">2</span>
	                	<input id="result2" type="hidden" name="room_person" value="2"/>
	                	<input type="button" class="p plus" value="+" onClick='count("plus")' />
	            </div>
	            <div class="ner4">
	                <div id="bom">
	                    <h3>객실 내 시설</h3>
	                </div>
	                <div>
	                   <ul style="list-style-type: none;">
	                        <li class="tum">
		                        <c:choose>
		                        	<c:when test="${empty param.wifi}">
			                            <input type="checkbox" id="check_1" class="choice" name="wifi" value="1">
			                            <label for="check_1" class="label_check">와이파이</label>
		                        	</c:when>
		                        	<c:otherwise>
		                        		<input type="checkbox" id="check_1" class="choice" name="wifi" value="1" checked>
			                            <label for="check_1" class="label_check">와이파이</label>
		                        	</c:otherwise>
		                        </c:choose>
	                        </li>
	                        <li  class="tum">
	                        	<c:choose>
		                        	<c:when test="${empty param.parking}">
		                        		<input type="checkbox" id="check_2" class="choice" name="parking" value="1">
	                            		<label for="check_2" class="label_check">주차장</label>
		                        	</c:when>
			                        <c:otherwise>
			                        	<input type="checkbox" id="check_2" class="choice" name="parking" value="1" checked>
	                            		<label for="check_2" class="label_check">주차장</label>
			                        </c:otherwise>
		                        </c:choose>
	                        </li>
	                        <li  class="tum">
	                        	<c:choose>
		                        	<c:when test="${empty param.aircon}">
		                    			<input type="checkbox" id="check_3" class="choice" name="aircon" value="1">
			                            <label for="check_3" class="label_check">에어컨</label>
		                        	</c:when>
			                        <c:otherwise>
			                            <input type="checkbox" id="check_3" class="choice" name="aircon" value="1" checked>
			                            <label for="check_3" class="label_check">에어컨</label>
			                        </c:otherwise>
		                        </c:choose>
	                        </li>
	                        <li  class="tum">
	                        	<c:choose>
		                        	<c:when test="${empty param.dryer}">
			                            <input type="checkbox" id="check_4" class="choice" name="dryer" value="1">
			                            <label for="check_4" class="label_check">드라이기</label>
		                        	</c:when>
			                        <c:otherwise>
			                        	<input type="checkbox" id="check_4" class="choice" name="dryer" value="1" checked>
			                            <label for="check_4" class="label_check">드라이기</label>
			                        </c:otherwise>
		                        </c:choose>
	                        </li>
	                        <li  class="tum">
	                        	<c:choose>
		                        	<c:when test="${empty param.port}">
			                        	<input type="checkbox" id="check_5" class="choice" name="port" value="1">
		                            	<label for="check_5" class="label_check">커피포트</label>
		                        	</c:when>
			                        <c:otherwise>
			                            <input type="checkbox" id="check_5" class="choice" name="port" value="1" checked>
			                            <label for="check_5" class="label_check">커피포트</label>
			                        </c:otherwise>
		                        </c:choose>
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
						 <a href="">1만원~5만원</a>
						 <a href="">5만원~10만원</a>     
						 <a href="">10만원~20만원</a>     
						 <a href="">20만원 이상</a>          
						 </div>
					 </div>
	            </div>
	            -->
            </form>
        </div>
        <div class="con2">
            <div class="order"> 
            	<form method="get" action="${contextPath}/trip" >
            		<input type="hidden" name="dorm_category_no" value="${param.dorm_category_no}">
	            	<input type="hidden" name="action" value="reservation.do">
	            	<c:if test="${not empty param.start}">
	            		<input type="hidden" name="start" value="${param.start}">
	            	</c:if>
	            	<c:if test="${not empty param.end}">
	            		<input type="hidden" name="end" value="${param.end}">
	            	</c:if>
	            	<c:if test="${not empty param.wifi}">
	            		<input type="hidden" name="wifi" value="${param.wifi}">
	            	</c:if>
	            	<c:if test="${not empty param.parking}">
	            		<input type="hidden" name="parking" value="${param.parking}">
	            	</c:if>
	            	<c:if test="${not empty param.aircon}">
	            		<input type="hidden" name="aircon" value="${param.aircon}">
	            	</c:if>
	            	<c:if test="${not empty param.dryer}">
	            		<input type="hidden" name="dryer" value="${param.dryer}">
	            	</c:if>
	            	<c:if test="${not empty param.port}">
	            		<input type="hidden" name="port" value="${param.port}">
	            	</c:if>
	            	<c:if test="${not empty param.room_person}">
	            		<input type="hidden" name="room_person" value="${param.room_person}">
	            	</c:if>
    	            <button type="submit" class="button button4" name="order" value="1">낮은 가격 순</button>
	                <button type="submit" class="button button4" name="order" value="2">높은 가격 순</button>
                </form>
            </div>
        
            <div class="towroom">
				<c:choose>
					<c:when test="${not empty dormList }">
		            	<c:forEach var="i" items="${dormList }" step="1" >
		               		<a href="trip?action=detail.do&dormno=${i.dorm_no }&reserve_checkin=${date_s}&reserve_checkout=${date_e}">
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
                    </c:when>
                    <c:otherwise>
                    	<h3 style="text-align:center">현재 조건에 맞는 숙소가 없습니다.</h3>
                    </c:otherwise>
				</c:choose>
			
            </div>
       </div>

    </div>

</section>
<%@ include file="./footer.jsp" %>
</body>
</html>