<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Question page</title>
	<link rel="stylesheet" href="./css/header_footer.css">
    <link rel="stylesheet" href="./css/questionWrite-style.css">
</head>
<body>
    <%@ include file="header.jsp"%>
    <section>
        <div>
            <div class="question_head">
                <a href=""><h1>Q & A 게시판</h1></a>
                <h1>새 문의 작성</h1>
            </div>
            <hr><br>
            <div class="question_text">
                <p>이용 중 불편하신 점을 문의주시면 최대한 빠른 시일내에 답변 드리겠습니다.</p>
            </div>
            <div class="question_input">
                <form action="">
                    <p>문의 제목</p>
                    <input class="question_input_title" type="text" placeholder="제목을 입력해주세요.">
                    <p>문의 내용</p>
                    <textarea type="text" placeholder="내용을 입력해주세요."></textarea><br>
                    <input class="question_input_btn" type="reset" value="취소"><input class="question_input_btn" type="submit" value="작성완료">
                </form>    
            </div>
        </div>
    </section>
    <%@ include file="footer.jsp"%>
</body>
</html>