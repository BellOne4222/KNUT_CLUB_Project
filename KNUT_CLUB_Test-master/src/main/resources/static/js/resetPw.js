/*
var npw1 = document.querySelector('#nPw');
var npw2 = document.querySelector('#npPw');

npw2.addEventListener("focusout", comparePw);

function comparePw() {
    if(npw2.value === npw1.value && npw2.value != "") {
        error[0].style.display = "none";
    } else if(npw2.value !== npw1.value) {
        error[0].innerHTML = "비밀번호가 일치하지 않습니다.";
        error[0].style.display = "block";
    }

    if(npw2.value === "") {
        error[0].innerHTML = "필수 정보입니다.";
        error[0].style.display = "block";
    }
}*/

var npw1 = document.querySelector('#nPw');
var npw2 = document.querySelector('#npPw');
var error = document.querySelectorAll('.error_next_box');

npw2.addEventListener("focusout", comparePw);

function comparePw() {
    if(npw2.value === npw1.value) {
        error[0].innerHTML = "비밀번호가 일치합니다.";
        error[0].style.display = "block";
    }
    else if(npw2.value !== npw1.value) {
        error[0].innerHTML = "비밀번호가 일치하지 않습니다.";
        error[0].style.display = "block";
    }

    if(npw2.value === "") {
        error[0].innerHTML = "필수 정보입니다.";
        error[0].style.display = "block";
    }
}
