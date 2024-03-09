/*로그인 탭*/
function openTab(evt, tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}

/* 수정필요 */
function findPw(){
    var find = window.open("", "new window", "width=400,height=250");
    find.document.write(
        "<html>" +
        "<head>" +
            "<title>비밀번호 찾기</title>" +
        "</head>" +
        "<body>" +
            "<form action='/findPassword' method='post'>" +
                "<h1>비밀번호 찾기</h1><br>" +
                "<p>학번 : </p>" +
                "<input style='height: 20px' name='studentId' type='text'>\n" +
                "<p>이름 : </p>" +
                "<input style='height: 20px' name='name' type='text'>" +
                "<button  style='margin-left: 15px' id='findBtn' type='submit' value='찾기' >" +
            "</form>" +
        "</body>" +
        "</html>");
}
