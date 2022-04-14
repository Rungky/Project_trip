<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변작성 페이지</title>
 <link href="./css/review.css" rel="stylesheet">
</head>
<body>
	<section>
        <div id="main_wrap">
            <div id="main">
                <div id="main_title">
                    <div class="margin_auto"><img class="review_logo"
                            src="https://image.goodchoice.kr/images/web_v3/ic_bi_yeogi_250px.png" alt="로고"> </div>
                    <div style="margin-top: 5px;">
                        <div class="fs_m2 fw_6">문의에 답변을 남겨주세요!</div>
                        <div class="fs_s">
                            답변작성시 운영정책을 지켜주세요.<br>
                            친절하고 명확한 답변은 여기어때의 발전에 도움이 됩니다.
                        </div>
                        <hr>
                    </div>
                </div>
                <div id="main_contents">
                    <form id="review_form">
                        <div class="review_title">
                            <!--데이터 받아와서 표시하기-->
                            <div class="fs_m2 fw_6">질문제목 : 예약취소 문의드립니다.</div>
                            <div class="fs_m2">안녕하세요 예약번호 1022872 입니다. 체크인 날짜 변경 가능한가요? </div>
                        </div> <br>
                        <div class="review_contents">
                            <div class="fs_m fw_6" style="margin-top: 5px;">답변내용 작성</div>
                            <input class="input_contents" type="text" id="" placeholder="질문에 대한 답변을 남겨주세요!">
                        </div>
                        <div class="margin_auto">
                            <br>
                            <input class="input_submit" type="submit" value="답변등록">
                            <input class="input_reset" type="reset" value="취소하기">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>