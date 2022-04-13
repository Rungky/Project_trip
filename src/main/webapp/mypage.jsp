<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mypage</title>
	<link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/mypage-style.css">

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.0.min.js" ></script>
    <script type="text/javascript">
        $(function(){
            //닉네임
            $('.input1').click(function(){
                $('.re_input').show();
                $('.re_input1').show();
                $('.re_input1').show();
            });
            $('.re_input1').click(function(){
                $('.re_input').hide();
                $('.re_input1').hide();
                $('.re_input1').hide();
            });
            //전화번호
            $('.input2').click(function(){
                $('.re_input2').show();
                $('.re_input2_1').show();
                $('.re_input2_1').show();
            });
            $('.re_input2_1').click(function(){
                $('.re_input2').hide();
                $('.re_input2_1').hide();
                $('.re_input2_1').hide();
            });

        });
    </script>

</head>
<body>
    <%@ include file="comm/header.jsp"%>
    <section>
        <div class="mypage_main">
            <div class="mypage_header">
                <div>
                    <img src="https://image.goodchoice.kr/profile/ico/ico_25.png" alt="이미지">
                </div>
                <div>
                    <h4>마이페이지</h4>
                    <!--로그인한 회원 정보에 맞게 출력되야함-->
                    <p>human@google.com</p><br>
                </div>
            </div>
            <hr>
             <%-- 백단에서 조건걸어서 수정하기 --%>
            <form id="test" class="mypage_form1" action="">
                <span>닉네임  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rungky</span><input id="tab" class="input1" type="button" value="수정"><br>
                <input style="display: none;" class="re_input" type="text">
                <input style="display: none;" class="re_input1" type="submit" value="수정하기"><input style="display: none;" class="re_input1" type="button" value="취소">
                <br>
                <span>전화번호  &nbsp;&nbsp;010-1234-5678</span><input id="tab" class="input2" type="button" value="수정"><br>
                <input style="display: none;" class="re_input2" type="text">
                <input style="display: none;" class="re_input2_1" type="submit" value="수정하기"><input style="display: none;" class="re_input2_1" type="button" value="취소">
                <br>
                <a href="#">내가 예약한 숙소보기</a>
            </form>
            <br>
            <hr>
            <form class="mypage_form2" action="">
                <p>사적모임 사이트를 더이상 이용하고 싶지 않으신가요?</p>
                <input type="button" value="로그아웃"><input type="button" value="회원탈퇴">
            </form>
        </div>
    </section>
    <%@ include file="comm/footer.jsp"%>
</body>
</html>