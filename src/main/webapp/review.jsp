<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 페이지</title>
 <link href="./css/header_footer.css" rel="stylesheet">
  <link href="./css/review.css" rel="stylesheet">
</head>
<body>
   <%@include file="header.jsp" %>

    <section>
     <form id="review_form" action="/project_trip/trip?action=upload.do" method="post" enctype="multipart/form-data">
        <div id="main_wrap">
            <div id="main">
                <div id="main_title">
                    <div class="margin_auto"><img class="review_logo"
                            src="https://image.goodchoice.kr/images/web_v3/ic_bi_yeogi_250px.png" alt="로고"> </div>
                    <div style="margin-top: 5px;">
                        <div class="fs_m2 fw_6">소중한 리뷰를 남겨주세요!</div>
                        <div class="fs_s">
                            이용하신 휴먼리조트는 어떠셨나요?<br>
                            도움이 되는 솔직한 리뷰를 남겨주시면 여기어때의 발전에 도움이 됩니다.
                        </div>
                        <hr>
                    </div>
                </div>
                <div>
						<fieldset>
							<label for="rate1"></label> 
							<label for="rate2"></label> 
							<label for="rate3"></label> 
							<label for="rate4"></label> 
							<label for="rate5"></label>
							<label for="rate6"></label> 
							<label for="rate7"></label> 
							<label for="rate8"></label> 
							<label for="rate9"></label> 
							<label for="rate10"></label>
							<input type="radio" name="reviewscore" value="1" id="rate1"  >
							<input type="radio" name="reviewscore" value="2" id="rate2"  >
							<input type="radio" name="reviewscore" value="3" id="rate3"  >
							<input type="radio" name="reviewscore" value="4" id="rate4"  >
							<input type="radio" name="reviewscore" value="5" id="rate5"  >
							<input type="radio" name="reviewscore" value="6" id="rate6"  >
							<input type="radio" name="reviewscore" value="7" id="rate7"  >
							<input type="radio" name="reviewscore" value="8" id="rate8"  >
							<input type="radio" name="reviewscore" value="9" id="rate9"  >
							<input type="radio" name="reviewscore" value="10" id="rate10" checked>
							<span class="sp_bg">
							</span>
						</fieldset>
				</div>
                <div id="main_contents">
                   
                        <div class="review_title">
                            <div class="fs_m">리뷰제목</div>
                            <input class="input_title" type="text" name="reviewtitle">
                        </div>
                        <div class="review_picture">
                        	리뷰 사진 등록 <input type="file" name="picture">
                        </div>
                        <div class="review_contents">
                            <div class="fs_m" style="margin-top: 5px;">리뷰내용 작성</div>
                            <textarea class="input_contents" type="text" name="reviewcontents" placeholder="사용하신 숙소의 평가를 남겨주세요!"></textarea>
                        </div>
                        <div class="margin_auto">
                            <br>
                            <button class="input_submit" type="submit">리뷰등록</button>
                            <button class="input_reset" type="submit">취소하기</button>
                            <input type="hidden" name="reserveno" value="<%-- ${reservationdto.reserve_no} --%>21">
                            <input type="hidden" name="memberid" value="admin<%-- ${member} --%>">
                            <input type="hidden" name="dormno" value="1001">
                        </div>
                    
                </div>
            </div>
        </div>
        </form>
    </section>

     <%@include file="footer.jsp" %>

</body>
</html>