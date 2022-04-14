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

    <link rel="stylesheet" href="../css/detail.css">
</head>

<body>
    <header>
        <div class="hd">
            <div>
                <form action="">
                    <button class="bt"><img class="rogo" src="../image/logo.png"></button>
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
                    <img class="image" src="../image/room.jpg">
                </aside>
                <article>
                    <div>
                        종로 연당 한옥 게스트하우스
                    </div>
                    <div>
                        숙소정보
                    </div>
                    <div>
                        서울 종로구 계동 2-45
                    </div>
                    <div>
                        숙소 이용 규칙<br>
                        <ul>
                            <li>전객실금연</li>
                            <li>애완동물 출입금지</li>
                            <li>보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)</li>
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
                <input id="tab1" type="radio" name="tabs">
                <label for="tab1">객실 안내/예약</label>
                <input id="tab2" type="radio" name="tabs" checked>
                <label for="tab2">리뷰</label>
                <c:forEach var="room" items="${roomsList }">               
                <table id="tb1">
                    <tr>
                        <td rowspan="3"><img class="image2" src="../image/${room.fileName }"></td>
                        <td colspan="2">A룸</td>
                    </tr>
                    <tr>
                        <td>가격</td>
                        <td class="pr">50,000원</td>
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
                              
                <table id="tb2">
                    <tr>
                        <td colspan="2" class="title">여기라면 다음에 또 이용할 거예요.</td>
                    </tr>
                    <tr>
                        <td class="star9"></td>
                        <td class="score">8.6</td>
                    </tr>
                    <tr>
                        <td colspan="2" class="rev">오늘의 이벤트(주차불가,객실랜덤배정) 객실 이용 · alpha0mega</td>
                    </tr>
                    <tr>
                        <td colspan="2">잠실새내 쪽에서는 유일하게 이용하는 곳이에요. 시설 적당하고 위치 너무 멀지 않고 그래서 가끔 가는 곳입니다. 물건 깨지거나 그런게 몇몇 있지만 전반적으로
                            만족합니다! 종종 또 가겠습니다!</td>
                    </tr>
                    <tr>
                        <td colspan="2" class="date">2022-04-12</td>
                    </tr>
                </table>
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