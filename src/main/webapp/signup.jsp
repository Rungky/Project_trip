<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/signup.css">
<title>회원가입</title>
</head>
<body>
	<div class="wrap_top">
        <section>
	        <div class="signup_main">
				<a href="./main.jsp">
					<img src="https://image.goodchoice.kr/images/web_v3/ic_bi_yeogi_250px.png" alt="로고">
				</a>
				<h2>회원가입</h2>
	            <form action="./main.jsp" method="post">
	                <div class="signup_top">
                        <p>이메일 아이디</p>
	                    <input type="text" name="id" placeholder="이메일 주소를 입력해주세요" />
                        <p>비밀번호</p>
	                    <input type="password" name="password" placeholder="비밀번호를 입력해 주세요" />
                        <p>닉네임</p>
	                    <input type="password" name="password" placeholder="닉네임을 입력해 주세요" />
                        <p>전화번호</p>
	                    <input type="password" name="password" placeholder="전화번호를 입력해 주세요" />
	                </div>
	                <div>
	                    <input class="signup_btn_box" type="submit" value="회원가입" onclick="alert('회원가입 완료!')" />
	                </div>
	            </form>
	        </div>
	    </section>
    </div>
</body>
</html>