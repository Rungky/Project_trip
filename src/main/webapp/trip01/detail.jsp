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
</head>

<body>
    <header>
        <div class="hd">
            <div>
                <form action=" ">
                    <button class="bt"><img class="rogo" src="./image/logo.png"></button>
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
        <div class="center">
            <div class="st">
                <aside>
                    <img class="image" src="./image/room.jpg">
                </aside>
                <article>
                    <div>
                        ${dormdto.dorm_name }
                    </div>
                    <div>
                        숙소정보
                    </div>
                    <div>
                        ${dormdto.dorm_addr }
                    </div>
                    <div>
						<ul>
							<c:forTokens var="item" items="${dormdto.dorm_contents }" delims=",">
								<li>${item}</li>
							</c:forTokens>
						</ul>
					</div>
                    <div>
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
    <section>
        <div class="center">
            <div class="st2">
                <input id="tab1" type="radio" name="tabs" checked>
                <label for="tab1">객실 안내/예약</label>
                <input id="tab2" type="radio" name="tabs">
                <label for="tab2">리뷰</label>
                <c:forEach var="item" items="${roomsList }">
	                <table id="tb1">
	                    <tr>
	                        <td rowspan="3"><img class="image2" src="${item.room_picture }"></td>
	                        <td rowspan="3">
	                        	<ul>
	                        		<c:forTokens var="item2" items="${item.room_contents }" delims=",">
                            			<li>${item2}</li>
                            		</c:forTokens>
                        		</ul>
	                        </td>
	                        <td colspan="2">${item.room_name }</td>
	                        
	                    </tr>
	                    <tr>
	                        <td>가격</td>
	                        <td class="pr">${item.room_pay_night }</td>
	                    </tr>
	                    <tr>
	                        <td colspan="2">
	                            <form action="">
	                                <button class="rsv">예약</button>
	                            </form>
	                        </td>
	                    </tr>
	                </table>
                </c:forEach>    
                <c:forEach var="item" items="${reviewsList}">       
	                <table id="tb2">
	                    <tr>
	                        <td colspan="2" class="title">${item.review_title}</td>
	                    </tr>
	                    <tr>
	                        <td class="star${item.score}"></td>
	                        <td class="score">${item.review_score}</td>
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
	                    <tr colspan="2">
	                    	<td><img class="rev_pt" src="image/review/${item.review_picture}"></td>
	                    </tr>
	                </table>
                </c:forEach>   
            </div>
        </div>
    </section>
    <footer>
        고객 행복 센터 041-111-1111<br>
        ㈜ 사적모임<br>
        주소 : 천안시 서북구 대흥로 256<br>
        전자우편주소 | human@email.com
    </footer>
</body>

</html>