var SERVER_URL = "http://127.0.0.1:8000/api";

function startLeaderboardUpdates() {
  var eventSource = new EventSource(SERVER_URL + "/leaderboard");
  eventSource.onmessage = function (event) {
    var data = JSON.parse(event.data);
    updateLeaderboard(data);
  };
}

function updateLeaderboard(data) {
  var rank = 1;
  $("#leaderboard-body").empty();
  data.forEach(function (row) {
    $("#leaderboard-body").append(
      "<tr><td>" +
        rank++ +
        "</td>" +
        "<td>" +
        row.userId +
        "</td>" +
        "<td>" +
        row.totalScore +
        "</td></tr>"
    );
  });
}

function updateStatistics(userId) {
  $.ajax({
    url: SERVER_URL + "/statistics?userId=" + userId,
    success: function (data) {
      $("#statistics").show();
      $("#statistics-userId").empty().append(userId);
      $("#statistics-score").empty().append(data.score);
      $("#statistics-badges").empty().append(data.badges.join("<br>"));
    },
    error: function (data) {
      $("#statistics").show();
      $("#statistics-userId").empty().append(userId);
      $("#statistics-score").empty().append(0);
      $("#statistics-badges").empty();
    },
  });
}

$(document).ready(function () {
  startLeaderboardUpdates();
});
