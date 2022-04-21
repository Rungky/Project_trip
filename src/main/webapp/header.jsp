<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<c:set var="contextPath" value="${ pageContext.request.contextPath}" />
<header>
	<div class="hd">
		<div>
			<form action="${contextPath}/trip">
				<button class="bt">
					<img class="rogo" src="/image/logo.png">
				</button>
			</form>
		</div>
		<nav>
			<ul>
				<li><a href="">예약 내역</a></li>
				<li><a href="">Q&A</a></li>
				
				<c:if test="${empty id}">
					<li><a href="trip?action=loginForm.do">로그인</a></li>
				</c:if>
				<c:if test="${!empty id}">
					<li><a href="trip?action=loginOut.do">로그아웃</a></li>
				</c:if>
			</ul>
		</nav>
	</div>
</header>

