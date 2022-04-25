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
<link rel="stylesheet" href="./css/signup.css">
<title>회원가입</title>

<script>
function joinChk() {
    var form = document.f1;
    if (!form.id.value) {
        alert("아이디를 입력해 주십시오.");
        return;
    }
 
    if (!form.password.value) {
        alert("비밀번호를 입력해 주십시오.");
        return;
    }
    
    if (!form.name.value) {
        alert("닉네임을 입력해 주십시오.");
        return;
    }
    
    if (!form.tel.value) {
        alert("전화번호를 입력해 주십시오.");
        return;
    }
    
    if (form.id.value && form.password.value && form.name.value && form.tel.value ) {
        alert("회원가입 완료!");
        return;
    }
}
</script>

</head>
<body>
	<div class="wrap_top">
        <section>
	        <div class="signup_main">
				<a href="${contextPath}/trip">
					<img src="./image/loginLogo.png" alt="로고">
				</a>
				<h2>회원가입</h2>
	            <form action="${contextPath}/trip" method="post" name="f1">
	            	<input type="hidden" name="action" value="join.do">
	                <div class="signup_top">
                        <p>이메일 아이디</p>
	                    <input type="text" name="id" id="id" placeholder="이메일 주소를 입력해주세요" />
                        <p>비밀번호</p>
	                    <input type="password" name="password" placeholder="비밀번호를 입력해 주세요" />
                        <p>닉네임</p>
	                    <input type="text" name="name" placeholder="닉네임을 입력해 주세요" />
                        <p>전화번호</p>
	                    <input type="text" name="tel" placeholder="전화번호를 입력해 주세요" />
	                </div>
	                <div>
	                    <input class="signup_btn_box" type="submit" value="회원가입" onclick="joinChk()"/>
	                </div>
	            </form>
	        </div>
	    </section>
    </div>
</body>
</html>