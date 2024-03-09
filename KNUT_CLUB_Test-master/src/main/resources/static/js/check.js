/*약관 동의 체크*/
window.onload = function () {
    const checkAll = document.getElementById('chkAll');
    const chks = document.querySelectorAll('.chk');
    const chkBoxLength = chks.length;

    checkAll.addEventListener('click', function(event) {
        if(event.target.checked)  {
            chks.forEach(function(value){
                value.checked = true;
            })
        }else {
            chks.forEach(function(value){
                value.checked = false;
            })
        }
    });
    for (chk of chks){
        chk.addEventListener('click', function() {
            let count = 0;
            chks.forEach(function(value){
                if(value.checked == true){
                    count++;
                }
            })
            if(count !== chkBoxLength){
                checkAll.checked = false;
            }else{
                checkAll.checked = true;
            }
        })
    }
}