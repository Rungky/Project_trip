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
<title>Q&A</title>
<link href="css/q&a.css" rel="stylesheet">
<link href="css/header_footer.css" rel="stylesheet">
</head>
<body>
     <%@include file="header.jsp" %>
    <section>
        <div id="wrap_qna">
            <div id="qna">
                 <div id="qna_head" class="fs_m2 fw_7">
                    <div id="my_qna"> 문의내역 </div> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div id="new_qna"><a href="${contextPath}/trip?action=qnaForm.do">새 문의 작성</a> </div>
                </div>
                
               <c:choose>
					<c:when test="${ empty questionList}">
						<div>등록된 글이 없습니다</div>
					</c:when>
                
	                <c:when test="${! empty questionList}">
		                <c:forEach var="question" items="${questionList}" varStatus="questionNum">
		                <c:choose>
		                	<c:when test="${question.question_parentno == 0 }">
			                <div id="qna_main">
			                    <div class="qna_title">
			                        <div>
			                            <div class="fs_m fw_6">${question.question_title}</div>
			                            <div class="fs_s margin_top">작성일 ${question.question_date}</div>
			                        </div>
			                        <div class="answer_button">
			                            <button class="btn_doAnswer" data-id="${question.question_no}">답변하기</button>
			                            <button class="btn_open">▼</button>
			                        </div>
			                    </div>
			             		
			                    <!--/<hr>-->
			                    <div class="qna_contents">
			                    	<div>${question.member_id}</div>
			                        <div class="qna_contents2" class="fs_m"> ${question.question_contents} </div>
			                        <c:set var="questionListsize" value="${fn:length(questionList)}"/>
			                    	<c:forEach var="question2" items="${questionList}"> 
			                    	
				                    	<c:if test="${question.question_no == question2.question_parentno}">
			                        	<div class="qna_answer fs_s2">
				                            <br>
				                            <div><span class="fw_6 fs_m2">답변 &nbsp;</span><span class="fs_s">작성일 ${question2.question_date}</span></div>
				                            <div>
				                            	${question2.question_contents}
				                            </div>
			                        	</div>
			                        	</c:if>
		                        	</c:forEach>
			                    </div>
			       			
			                </div>
			                </c:when>
		                </c:choose>
		                </c:forEach>
	                </c:when>
                </c:choose>
               
            </div>
        </div>
    </section>

    <!--include-->
    <%@include file="footer.jsp"%>


</body>

<script>
    var open_button = document.querySelectorAll(".btn_open");

    var contents = document.querySelectorAll(".qna_contents");

    for (let i = 0; i < open_button.length; i++) {
        open_button[i].addEventListener("click", function () {


            if (contents[i].style.display == ("" || "none")) {
                open_button[i].innerHTML = "▲";
                contents[i].style.display = "flex";
            } else {
                open_button[i].innerHTML = "▼";
                contents[i].style.display = "none";
            }
        });
    }

    var list_answer = document.querySelectorAll(".btn_doAnswer");
    console.log(list_answer.length);
    for(let i=0; i<list_answer.length; i++){
        list_answer[i].addEventListener("click", function(event){
        	let product_no = event.target.getAttribute("data-id");
            window.open("http://localhost:8080/project_trip/trip?action=answerqna.do&product_no="+product_no,"answer","width: 600px");
        })
    }
</script>

</html>