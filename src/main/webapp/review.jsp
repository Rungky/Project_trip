<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>

<title>리뷰 페이지</title>

 <link href="./css/header_footer.css" rel="stylesheet">
  <link href="./css/review.css" rel="stylesheet">
</head>
<body>
   <%@include file="header.jsp" %>

    <section>
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
                <div id="main_contents">
                    <form id="review_form">
                        <div class="review_title">
                            <div class="fs_m">리뷰제목</div>
                            <input class="input_title" type="text" id="">
                        </div>
                        <div class="review_contents">
                            <div class="fs_m" style="margin-top: 5px;">리뷰내용 작성</div>
                            <input class="input_contents" type="text" id="" placeholder="사용하신 숙소의 평가를 남겨주세요!">
                        </div>
                        <div class="margin_auto">
                            <br>
                            <input class="input_submit" type="submit" value="리뷰등록">
                            <input class="input_reset" type="reset" value="취소하기">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

     <%@include file="footer.jsp" %>

</body>
</html>