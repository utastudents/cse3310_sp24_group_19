var connection = null;
var uuid = null;
var serverUrl;
serverUrl = "ws://" + window.location.hostname + ":9880";
// Create the connection with the server
connection = new WebSocket(serverUrl);

var nicknameStyle = document.getElementById("nick_name");
nicknameStyle.style.display = "none";

connection.onopen = function (evt) {
    console.log("open");
}

//when user closes client(tab)
connection.onclose = function (evt) {
    console.log("close");
}

connection.onmessage = function (evt) {
    if (uuid == null) {
        uuid = evt.data;
        var uuidDiv = document.getElementById("player_uuid");
        uuidDiv.innerHTML = "Player: " + uuid;
    }
}

// Get references to the play button and nickname container
var playButton = document.getElementById("play_button");
var nicknameContainer = document.querySelector("nickname");

// Add click event listener to the play button
playButton.addEventListener("click", function() {
    // Hide the body element
    document.body.style.display = "none";
    
    // Show the nickname section
    nicknameContainer.style.display = "block";
});