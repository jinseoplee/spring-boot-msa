var SERVER_URL = "http://127.0.0.1:8000/api";

function updateMultiplication() {
  $.ajax({
    url: SERVER_URL + "/multiplication/random",
  }).then(function (data) {
    // 정답 비우기
    $("#answer").val("");

    // 무작위 문제를 API로 가져와서 추가하기
    $("#multiplication-a").empty().append(data.factorA);
    $("#multiplication-b").empty().append(data.factorB);
  });
}

function getUserRecentAttempts(nickname) {
  $.ajax({
    url: SERVER_URL + "/multiplication/attempt?nickname=" + nickname,
  }).then(function (data) {
    $("#attempt-body").empty();
    data.forEach(function (row) {
      $("#attempt-body").append(
        "<tr><td>" +
          row.multiplication.factorA +
          " X " +
          row.multiplication.factorB +
          "</td>" +
          "<td>" +
          row.answer +
          "</td>" +
          "<td>" +
          (row.correct === true ? "정답" : "오답") +
          "</td></tr>"
      );
    });
  });
}

$(document).ready(function () {
  // 곱셈 문제 갱신
  updateMultiplication();

  $("#attempt-form").submit(function (event) {
    // 폼 기본 제출 막기
    event.preventDefault();

    // 페이지에서 값 가져오기
    var a = $("#multiplication-a").text();
    var b = $("#multiplication-b").text();
    var answer = $("#answer").val();
    var nickname = $("#nickname").val();

    // 정답 또는 닉네임을 입력하지 않은 경우
    if (answer == "" || nickname == "") {
      alert("정답과 닉네임 모두 입력해 주세요.");
      return;
    }

    // API에 맞게 데이터 조합하기
    var data = {
      nickname: nickname,
      multiplication: {
        factorA: a,
        factorB: b,
      },
      answer: answer,
    };

    // POST를 이용해 데이터 보내기
    $.ajax({
      url: SERVER_URL + "/multiplication/attempt",
      type: "POST",
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: function (result) {
        if (result.correct) {
          $("#result-message")
            .empty()
            .append(
              "<p class='alert alert-info text-center mt-3'>정답입니다.</p>"
            );
        } else {
          $("#result-message")
            .empty()
            .append(
              "<p class='alert alert-danger text-center mt-3'>오답입니다.</p>"
            );
        }
      },
    });

    updateMultiplication();

    setTimeout(() => {
      getUserRecentAttempts(nickname);
      updateStatistics(nickname);
    }, 300);
  });
});
