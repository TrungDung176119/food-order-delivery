/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
var wsUrl;
if (window.location.protocol === 'http:') {
    wsUrl = 'ws://';
} else {
    wsUrl = 'wss://';
}
var ws = new WebSocket(wsUrl + window.location.host + "/HolaFood/chat");

ws.onmessage = function (event) {
    var chatMessages = document.getElementById("chat-messages");
    chatMessages.innerHTML += "<div>" + event.data + "</div>";
};

ws.onerror = function (event) {
    console.log("Error ", event);
};

function sendMsg() {
    var msg = document.getElementById("msg").value;
    if (msg)
    {
        ws.send(msg);
    }
    document.getElementById("msg").value = "";
}

document.addEventListener("DOMContentLoaded", function () {
    var chatBox = document.getElementById("chat-box");
    var chatToggle = document.getElementById("chat-toggle");
    var closeButton = document.getElementById("close-chat");

    // Toggle chat box visibility
    chatToggle.addEventListener("click", function () {
        chatBox.style.display = chatBox.style.display === "none" ? "flex" : "none";
    });

    // Close chat box
    closeButton.addEventListener("click", function () {
        chatBox.style.display = "none";
    });
});

