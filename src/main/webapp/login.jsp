<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/login.css">
<title>Insert title here</title>
</head>
<body>
	<div class="wrap_top">
        <section>
	        <div class="login_main">
	            <a href="./main.jsp">
					<img src="https://image.goodchoice.kr/images/web_v3/ic_bi_yeogi_250px.png" alt="로고">
				</a><p></p>
	            <form action="" method="post">
	                <div class="login_top">
	                    <input type="text" name="id" placeholder="이메일 주소" />
	                    <input type="password" name="password" placeholder="비밀번호" />
	                </div>
	                <div>
	                    <input class="login_btn_box" type="submit" value="로그인" />
	                </div>
	            </form>
                <p>
                    <a href="./signup.jsp">|회원가입|</a>
                </p>
	        </div>
	    </section>
    </div>
</body>
</html>