<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/login.css">
<title>로그인</title>

<script>
function loginChk() {
    var form = document.f1;
    if (!form.id.value) {
        alert("아이디를 입력해 주십시오. ");
        form.id.focus();
        return;
    }
 
    if (!form.password.value) {
        alert("비밀번호를 입력해 주십시오.");
        form.password.focus(); 
        return;
    }
}
</script>

</head>
<body>
	<div class="wrap_top">
        <section>
	        <div class="login_main">
	            <a href="${contextPath}/trip">
					<img src="https://image.goodchoice.kr/images/web_v3/ic_bi_yeogi_250px.png" alt="로고">
				</a><p></p>
	            <form action="${ contextPath}/trip" method="post" name="f1">
	            	<input type="hidden" name="action" value="login.do">
	                <div class="login_top">
	                    <input type="text" name="id" placeholder="아이디" />
	                    <input type="password" name="password" placeholder="비밀번호" />
	                </div>
	                <div>
	                    <input class="login_btn_box" type="submit" value="로그인" onclick="loginChk()"/>
	                </div>
	            </form>
                <p>
                    <a href="trip?action=joinForm.do">|회원가입|</a>
                </p>
	        </div>
	    </section>
    </div>
</body>
</html>