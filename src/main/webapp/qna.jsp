<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                    <div id="my_qna"> 나의 문의내역 </div> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div id="new_qna"><a href="questionWrite.jsp">새 문의 작성</a> </div>
                </div>
                
                <div id="qna_main">
                    <div class="qna_title">
                        <div>
                            <div class="fs_m fw_6">현금영수증 관련 문의드립니다</div>
                            <div class="fs_s margin_top">작성일 2022.03.01</div>
                        </div>
                        <div class="answer_button">
                            <button class="btn_doAnswer">답변하기</button>
                            <button class="btn_open">▼</button>
                        </div>
                    </div>
                    <!--/<hr>-->
                    <div class="qna_contents">
                        <div class="qna_contents2" class="fs_m"> 결제이후에 현금영수증을 발급받고 싶은데 가능한가요? </div>
                        <div class="qna_answer fs_s2">
                            <br>
                            <div><span class="fw_6 fs_m2">답변 &nbsp;</span><span class="fs_s">작성일 2022.03.02</span></div>
                            <div>
                                현금영수증은 현금성 결제수단으로 결제 시 발급이 가능합니다.
                                <br>
                                결제 단계에서 현금영수증을 신청하면 자동으로 발행되지만,
                                신청을 누락했거나 발행받지 못한 경우라면 영수증 확인 후 국세청에서 자진발급분을 등록해 주시길 바랍니다.

                            </div>
                        </div>
                    </div>
                    <div class="qna_title">
                        <div>
                            <div class="fs_m fw_6">체크인 관련 문의드립니다</div>
                            <div class="fs_s margin_top">작성일 2022.03.01</div>
                        </div>
                        <div class="answer_button">
                            <button class="btn_doAnswer">답변하기</button>
                            <button class="btn_open">▼</button>
                        </div>
                    </div>
                    <!--/<hr>-->
                    <div class="qna_contents">
                        <div class="qna_contents2" class="fs_m"> 안녕하세요 예약번호 1022872 입니다. 체크인 날짜 변경 가능한가요? </div>
                        <div class="qna_answer fs_s">
                            <br>
                            <div><span class="fw_6 fs_m2">답변 &nbsp;</span><span class="fs_s">작성일 2022.03.02</span></div>
                            <div>
                                예약 결제 완료 후 날짜 및 객실타입 등 부분 변경은 불가합니다.
                                <br>
                                예약취소와 동일하게 취소 및 환불 규정에 따라 처리되므로 예약취소가 가능한 기간에는 예약취소 후 재결제 하셔서 이용 부탁드립니다.
                                만약, 예약취소가 불가하거나 수수료가 발생하는 경우라면 고객행복센터로 문의해주시길 바랍니다.
                            </div>
                        </div>
                    </div>
                    <div class="qna_title">
                        <div>
                            <div class="fs_m fw_6">예약취소 관련 문의드립니다</div>
                            <div class="fs_s margin_top">작성일 2022.03.01</div>
                        </div>
                        <div class="answer_button">
                            <button class="btn_doAnswer">답변하기</button>
                            <button class="btn_open">▼</button>
                        </div>
                    </div>
                    <!--/<hr>-->
                    <div class="qna_contents">
                        <div class="qna_contents2" class="fs_m"> 결제이후에 예약을 취소하고 싶은데 가능한가요? </div>
                        <div class="qna_answer fs_s2">
                            <br>
                            <div><span class="fw_6 fs_m2">답변 &nbsp;</span><span class="fs_s">작성일 2022.03.02</span></div>
                            <div>
                                예약취소는 앱/웹 > 내정보 > 예약/구매내역에서 직접 가능합니다.
                                <br>
                                예약/결제 진행 당시 안내된 취소/환불 규정에 따라 처리되며, 취소수수료가 발생할 경우 취소수수료를 차감한 금액으로 환불 처리됩니다.
                                일부 숙소에 한해 취소가 가능한 시점이나 앱/웹에서 취소가 불가할 수 있으니 이 경우에는 고객행복센터로 요청해 주시길 바랍니다.
                            </div>
                        </div>
                    </div>

                </div>
                <div id="qna_page">
                    <div>
                        <a href>[이전]</a>&nbsp;
                        <a href>[1]</a>
                        <a href>[2]</a>
                        <a href>[3]</a>&nbsp;
                        <a href>[다음]</a>
                    </div>
                </div>
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
        list_answer[i].addEventListener("click", function(){
            window.open("q&a_answer.jsp","answer","width: 600px");
        })
    }
</script>

</html>