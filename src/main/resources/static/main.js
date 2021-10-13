var stompClient = null;

var urlParams = new URLSearchParams(window.location.href.split('?')[1]);

var userId = urlParams.get('userId');
var username = urlParams.get('username');

function connect() {
    var socket = new SockJS("http://192.168.1.6:8081/ws-chat");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/message', function (data) {
            data = JSON.parse(data.body);
            var from  = data.user_id;
            if(from != userId) {
                showMessage(data.message, false, data.username);
            }
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

const input = $('#input');
const output = $('#chat-content');

input.on('keypress', function(e) {
    if(e.code === 'Enter') {
        sendMessage();
    }
})

$('.publisher-btn').click(function() {
    sendMessage();
})

function sendMessage() {
    const message = input.val();

    var settings = {
        "url": "/message",
        "method": "POST",
        "timeout": 0,
        "headers": {
        "Content-Type": "application/json"
        },
        "data": JSON.stringify({
        "user_id": userId,
        "username": username,
        "message": message
        }),
    };

    $.ajax(settings).done(function (response) {
        showMessage(message, true, username);
        input.val('');
    });
}

function showMessage(message, isMe, senderName) {
    var messagePositionClass = isMe ? 'media-chat-reverse' : ''; 
    var content = "<div class='media media-chat " + messagePositionClass + " '>" +
        "   <div class='media-body'>" +
        "       <p class='meta'><time>" + senderName + "</time></p>" +
        "       <p>" + message + "</p>" +
        "    </div>" +
        "</div>";

    $('.ps-container').animate({ scrollTop: $('.ps-container')[0].scrollHeight }, "slow");

    output.append(content);
}

function validate() {
    if(userId == null || username == null) {
        $('#page-content').hide();
        $('#username-input-div').show();
    }
}

$('#username-input').on('keypress', function(e) {
    if(e.code === 'Enter') {
        window.location.href = 'user/create?username=' + $(this).val();
    }
})

validate();
connect();