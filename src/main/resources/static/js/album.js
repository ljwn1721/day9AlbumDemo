// 앨범 수정 버튼 클릭
function editAlbum(event, button) {
    event.stopPropagation(); // 이제 정상적으로 동작
    const albumId = button.getAttribute("data-id");
    window.location.href = `/albums/${albumId}/edit`;
}

// 앨범 삭제 버튼 클릭
function deleteAlbum(event, button) {
    event.stopPropagation();
    const albumId = button.getAttribute("data-id");

    if (!confirm("정말 이 앨범을 삭제하시겠습니까?")) {
        return;
    }

    fetch(`/albums/${albumId}/delete`, {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" }
    })
        .then(response => {
            if (response.ok) {
                alert("앨범이 삭제되었습니다.");
                location.reload();
            } else {
                alert("앨범 삭제에 실패했습니다.");
            }
        })
        .catch(error => {
            console.error("삭제 오류:", error);
            alert("서버 오류가 발생했습니다.");
        });
}

// 새 앨범 추가 버튼 클릭
function addAlbum() {
    // 앨범 생성 페이지로 이동
    window.location.href = "/albums/new";
}
