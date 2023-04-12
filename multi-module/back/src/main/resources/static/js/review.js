/**
 * 리뷰저장
 */
function submitReview() {
    // 별점과 리뷰 내용을 가져옵니다.
    const rating = document.querySelector('input[name="rating"]:checked').value;
    const reviewContent = document.querySelector('input[name="reviewContent"]').value;

    // 요청 본문을 설정합니다.
    const requestBody = {
        rating: rating,
        reviewContent: reviewContent
    };
    const requestBodyJSON = JSON.stringify(requestBody);

    // fetch API를 사용하여 요청을 보냅니다.
    fetch('/hotel/review/'+hotelId, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: requestBodyJSON
    })
        .then(data => {
            console.log(data);
            window.location.reload();
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
$('.review-delete-btn').on('click', function(e) {
    const reviewId = this.dataset.reviewId;
    $.ajax({
        url: '/hotel/review/'+reviewId,
        method: 'DELETE',
        success: function() {
            // 리뷰 목록 새로고침
            location.reload();
        }
    });
});


/**
 * 리뷰 수정(modal창)
 */
// 리뷰 수정 버튼 클릭 이벤트 핸들러
$('.review-update-btn').on('click', function(e) {
    const reviewId = this.dataset.reviewId;
    e.preventDefault();
    // 리뷰 데이터 로드
    $.get('/hotel/review/' + reviewId, function(data) {
        // 모달창 폼에 데이터 바인딩
        $('#reviewContent').val(data.reviewContent);
        $('#id').text(reviewId); // 리뷰 아이디 출력
        $('input[name="update-rating"][value="' + data.rating + '"]').prop('checked', true); //rating 체크
        // 모달창 열기
        $('#reviewModal').modal('show');
    });
});

// 폼 서밋 핸들러
$('#reviewForm').on('submit', function(e) {
    e.preventDefault();
    // 폼 데이터를 JavaScript 객체로 변환
    const id = document.querySelector('#id').innerText; // id 값을 가져옴
    const reviewContent = document.querySelector('#reviewContent').value; // 리뷰 내용을 가져옴
    const rating = document.querySelector('input[name="update-rating"]:checked').value;

    const formData = {
        id: id,
        reviewContent: reviewContent,
        rating: rating
    };

    // AJAX 요청 보내기
    $.ajax({
        url: '/hotel/review',
        method: 'PUT',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function() {
            // 모달창 닫기
            $('#reviewModal').modal('hide');
            // 리뷰 목록 새로고침
            location.reload();
        }
    });
});