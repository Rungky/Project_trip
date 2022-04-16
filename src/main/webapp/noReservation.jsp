<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>예약 페이지</title>
    <link rel="stylesheet" href="css/reser.css"> <!-- css파일 reser2.css였어요!-->
</head>

<body topmargin="0" bottommargin="0" leftmargin="0" rightmargin="0">
    <header>
        <div class="hd">
            <div>
                <form action="review.html">
                    <button class="bt">
                        <img class="rogo" src="image/logo.png">
                    </button>
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
    <div class="container">
        <div class="con1">
            <div class="ner1">
                <h3>날짜</h3>
                check In>> <input type="date" name="birthday"><br>
                check Out>> <input type="date" name="birthday">
            </div>
            <div class="ner2">
                <h3>상세조건</h3>
                <div class="boxx">
                    <button class="bu re">초기화</button>
                    <button class="bu che">적용</button>
                </div>

            </div>
            <div class="ner3">
                <h3>인원</h3>
                    <button class="p minus">-</button>
                    <span>2</span>
                    <button class="p plus">+</button>
            </div>
            <div class="ner4">
                <div id="bom">
                    <h3>객실 내 시설</h3>
                </div>
                <div>
                   <ul style="list-style-type: none;">
                        <li class="tum">
                            <input type="checkbox" id="check_1" class="choice" value="와이파이">
                            <label for="check_1" class="label_check">와이파이</label>
                        </li>
                        <li  class="tum">
                            <input type="checkbox" id="check_2" class="choice" value="와이파이">
                            <label for="check_2" class="label_check">냉장고</label>
                        </li>
                        <li  class="tum">
                            <input type="checkbox" id="check_3" class="choice" value="와이파이">
                            <label for="check_3" class="label_check">드라이기</label>
                        </li>
                        <li  class="tum">
                            <input type="checkbox" id="check_4" class="choice" value="와이파이">
                            <label for="check_4" class="label_check">에어컨</label>
                        </li>
                   </ul>
                </div>
                
            </div>
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
        </div>
        <div class="con2">
            <div class="order">
                <button class="button button4">추천순</button>  
                <button class="button button4">낮은 가격 순</button>
                <button class="button button4">높은 가격 순</button>
            </div>
            <div class="towroom">
               <strong>현재 조건에 맞는 숙소가 없습니다</strong>
            
            </div>
       </div>

    </div>

</section>
<footer>
	<p style="font-weight:600;">고객 행복 센터 | 041-111-1111<br>
	 ㈜ 사적모임</p>
	 주소 : 천안시 서북구 대흥로 256<br>
	전자우편주소 | human@email.com<br>
	(주) 사적모임은 통신판매중개자로서 통신판매의 당사자가 아니며, 상품의 예약, 이용 및 환불 등과 관련한 의무와 책임은 각 판매자에게 있습니다.
</footer>
</body>
</html>