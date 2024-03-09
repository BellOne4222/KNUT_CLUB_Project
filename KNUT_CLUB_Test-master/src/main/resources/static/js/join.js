/*변수 선언*/
var id = document.querySelector('#id');
var pw1 = document.querySelector('#pswd1');
var pwMsg = document.querySelector('#alertTxt');
var pwImg1 = document.querySelector('#pswd1_img1');
var pw2 = document.querySelector('#pswd2');
var pwImg2 = document.querySelector('#pswd2_img1');
var pwMsgArea = document.querySelector('.int_pass');
var userName = document.querySelector('#name');
var mm = document.querySelector('#mm');
var dd = document.querySelector('#dd');
var department = document.querySelector('#department');
var gender = document.querySelector('#gender');
// var email = document.querySelector('#email');
var mobile = document.querySelector('#mobile');
var address = document.querySelector('#address');
var address2 = document.querySelector('#address2');
var error = document.querySelectorAll('.error_next_box');



/*이벤트 핸들러 연결*/


id.addEventListener("focusout", checkId);
pw1.addEventListener("focusout", checkPw);
pw2.addEventListener("focusout", comparePw);
userName.addEventListener("focusout", checkName);
yy.addEventListener("focusout", isBirthCompleted);
mm.addEventListener("focusout", isBirthCompleted);
dd.addEventListener("focusout", isBirthCompleted);
gender.addEventListener("focusout", function() {
	if(gender.value === "성별") {
		error[6].style.display = "block";
		error[6].innerHTML = "필수 정보입니다.";
	} else {
		error[6].style.display = "none";
	}
})
// email.addEventListener("focusout", isEmailCorrect);
mobile.addEventListener("focusout", checkPhoneNum);
address.addEventListener("focusout", checkAddress);
address2.addEventListener("focusout", addAddress);
department.addEventListener("focusout",checkDepartment);




/*콜백 함수*/


function checkId() {
	var idPattern = /[0-9]{5,20}/;
	if(id.value === "") {
		error[0].innerHTML = "필수 정보입니다.";
		error[0].style.display = "block";
	} else if(!idPattern.test(id.value)) {
		error[0].innerHTML = "숫자만 사용가능합니다.";
		error[0].style.display = "block";
	} else {
		error[0].innerHTML = "사용가능한 학번입니다!";
		error[0].style.color = "#08A600";
		error[0].style.display = "block";
	}
}

function checkPw() {
	var pwPattern = /[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,16}/;
	if(pw1.value === "") {
		error[1].innerHTML = "필수 정보입니다.";
		error[1].style.display = "block";
	} else if(!pwPattern.test(pw1.value)) {
		error[1].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
		pwMsg.innerHTML = "사용불가";
		pwMsgArea.style.paddingRight = "93px";
		error[1].style.display = "block";

		pwMsg.style.display = "block";
		pwImg1.src = "/img/join/m_icon_not_use.png";
	} else {
		error[1].style.display = "none";
		pwMsg.innerHTML = "안전";
		pwMsg.style.display = "block";
		pwMsg.style.color = "#03c75a";
		pwImg1.src = "/img/join/m_icon_safe.png";
	}
}

function comparePw() {
	if(pw2.value === pw1.value && pw2.value != "") {
		pwImg2.src = "/img/join/m_icon_check_enable.png";
		error[2].style.display = "none";
	} else if(pw2.value !== pw1.value) {
		pwImg2.src = "/img/join/m_icon_check_disable.png";
		error[2].innerHTML = "비밀번호가 일치하지 않습니다.";
		error[2].style.display = "block";
	}

	if(pw2.value === "") {
		error[2].innerHTML = "필수 정보입니다.";
		error[2].style.display = "block";
	}
}

function checkName() {
	var namePattern = /[a-zA-Z가-힣]/;
	if(userName.value === "") {
		error[3].innerHTML = "필수 정보입니다.";
		error[3].style.display = "block";
	} else if(!namePattern.test(userName.value) || userName.value.indexOf(" ") > -1) {
		error[3].innerHTML = "한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)";
		error[3].style.display = "block";
	} else {
		error[3].style.display = "none";
	}
}

function checkDepartment(){
	var departmentPattern = /[가-힣]/;

	if(department.value === ""){
		error[4].innerHTML = "필수 정보입니다.";
		error[4].style.display = "block";
	} else if(!departmentPattern.test(department.value)){
		error[4].innerHTML = "한글로만 입력가능합니다.";
		error[4].style.display = "block";
	} else{
		error[4].style.display = "none";
	}
}

function isBirthCompleted() {
	var yearPattern = /[0-9]{4}/;

	if(!yearPattern.test(yy.value)) {
		error[5].innerHTML = "태어난 년도 4자리를 정확하게 입력하세요.";
		error[5].style.display = "block";
	} else {
		isMonthSelected();
	}


	function isMonthSelected() {
		if(mm.value === "월") {
			error[5].innerHTML = "태어난 월을 선택하세요.";
		} else {
			isDateCompleted();
		}
	}

	function isDateCompleted() {
		if(dd.value === "") {
			error[5].innerHTML = "태어난 일(날짜) 2자리를 정확하게 입력하세요.";
		} else {
			isBirthRight();
		}
	}
}



function isBirthRight() {
	var datePattern = /\d{1,2}/;
	if(!datePattern.test(dd.value) || Number(dd.value)<1 || Number(dd.value)>31) {
		error[5].innerHTML = "생년월일을 다시 확인해주세요.";
	} else {
		checkAge();
	}
}

function checkAge() {
	if(Number(yy.value) < 1920) {
		error[5].innerHTML = "정말이세요?";
		error[5].style.display = "block";
	} else if(Number(yy.value) > 2020) {
		error[5].innerHTML = "미래에서 오셨군요. ^^";
		error[5].style.display = "block";
	} else if(Number(yy.value) > 2005) {
		error[5].innerHTML = "만 14세 미만의 어린이는 보호자 동의가 필요합니다.";
		error[5].style.display = "block";
	} else {
		error[5].style.display = "none";
	}
}


// function isEmailCorrect() {
// 	var emailPattern = /[a-z0-9]{2,}@[a-z0-9-]{2,}\.[a-z0-9]{2,}/;
//
// 	if(email.value === ""){
// 		error[4].style.display = "block";
// 		error[4].innerHTML = "필수 정보입니다.";
// 	} else if(!emailPattern.test(email.value)) {
// 		error[4].style.display = "block";
// 		error[4].innerHTML = "형식에 맞지 않는 이메일입니다.";
// 	} else {
// 		error[4].style.display = "none";
// 	}
//
// }

function checkPhoneNum() {
	var isPhoneNum = /([01]{2})([01679]{1})-([0-9]{3,4})-([0-9]{4})/;

	if(mobile.value === "") {
		error[7].innerHTML = "필수 정보입니다.";
		error[7].style.display = "block";
	} else if(!isPhoneNum.test(mobile.value)) {
		error[7].innerHTML = "형식에 맞지 않는 번호입니다.";
		error[7].style.display = "block";
	} else {
		error[7].style.display = "none";
	}


}

function checkAddress(){
	var isAddress = /[가-힣0-9]/;

	if(address.value === ""){
		error[8].innerHTML = "필수 정보입니다.";
		error[8].style.display = "block";
	} else if(!isAddress.test(address.value)){
		error[8].innerHTML = "형식에 맞지 않은 주소입니다.";
		error[8].style.display = "block";
	} else{
		error[8].style.display = "none";
	}
}

function addAddress(){
	var addressPattern = /[가-힣0-9~!@#$%^&*()_+|<>?:{}]/;

	if(address2.value === ""){
		error[9].innerHTML = "필수 정보입니다.";
		error[9].style.display = "block";
	} else if(!addressPattern.test(address2.value)){
		error[9].innerHTML = "형식에 맞지 않은 주소입니다.";
		error[9].style.display = "block";
	} else{
		error[9].style.display = "none";
	}
}


/* 전화번호 자동완성 */
var mobile = document.getElementById('mobile');


mobile.onkeyup = function() {
	console.log(this.value);
	this.value = autoHypenPhone(this.value);
}
var autoHypenPhone = function(str) {
	str = str.replace(/[^0-9]/g, '');
	var tmp = '';
	if (str.length < 4) {
		return str;
	} else if (str.length < 7) {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3);
		return tmp;
	} else if (str.length < 11) {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 3);
		tmp += '-';
		tmp += str.substr(6);
		return tmp;
	} else {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 4);
		tmp += '-';
		tmp += str.substr(7);
		return tmp;
	}
	return str;
}


/*주소찾기*/
function findAddr() {
	new daum.Postcode({
		oncomplete: function(data) {
			console.log(data);

			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var roadAddr = data.roadAddress; // 도로명 주소 변수
			var jibunAddr = data.jibunAddress; // 지번 주소 변수
			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			if (roadAddr !== '') {
				document.getElementById("address").value = roadAddr;
			} else if (jibunAddr !== '') {
				document.getElementById("address").value = jibunAddr;
			}
		}
	}).open();
}


