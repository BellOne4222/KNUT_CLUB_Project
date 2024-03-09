/* confirm */
function writeCheck() {
    if(confirm("정말 작성하시겠습니까?") == false) {
        return false;
    }
}

function updateCheck() {
    if(confirm("정말 수정하시겠습니까?") == false) {
        return false;
    }
}

function deleteCheck() {
    if (confirm("정말 삭제하시겠습니까?") == false) {
        return false;
    }
}

function joinCheck() {
    if (confirm("정말 가입하시겠습니까?") == false) {
        return false;
    }
}

function permissionCheck() {
    if (confirm("정말 승인하시겠습니까?") == false) {
        return false;
    }
}
