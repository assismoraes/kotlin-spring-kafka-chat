var stompClient = null;

var urlParams = new URLSearchParams(window.location.href.split('?')[1]);

var userId = urlParams.get('userId');
var username = urlParams.get('username');



function connect() {
    var socket = new SockJS("http://192.168.1.6:8081/ws-chat");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/message', function (data) {
            console.log('received data >>>>>>>>>>')
            console.log(data);
            data = JSON.parse(data.body);
            var from  = data.user_id;
            if(from != userId) {
                showMessage(data.message, false, data.username);
                // output.append(data.username + ': ' + data.message, document.createElement('br'));
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

        // showMessage(message, false, username);
        // input.val('');
        

        $.ajax(settings).done(function (response) {
            console.log('sent data >>>>>>>>>>>>>>>>>>> ')
            console.log(settings);
            // output.append('Eu: ' + message, document.createElement('br'));
            showMessage(message, true, username);
            input.val('');
            // input.val('');
            console.log(response);
        });

        // var data = {
        //     user_id: userId,
        //     username: username,
        //     message: message
        // };
        // console.log('sent data >>>>>>>>>>>>>>>>>>> ')
        // console.log(data);
        // output.append('Eu: ' + message, document.createElement('br'));
        // input.val('');
        // stompClient.send('/topic/message', {}, JSON.stringify(data));
    }
})

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

function ok() {
    const ws = new WebSocket('ws://127.0.0.1:8081/ws-chat/');
    const input = document.querySelector('input');
    const output = document.querySelector('output');

    ws.addEventListener('open', console.log);
    ws.addEventListener('message', console.log);

    ws.addEventListener('message', message => {
        const dados = JSON.parse(message.data);
        if(dados.type === 'chat') {
            output.append('Outro: ' + dados.text + createElement('br'));
        }
    })

    input.addEventListener('keypress', e => {
        if(e.code === 'Enter') {
            const valor = input.value;
            output.append('Eu: ' + valor, document.createElement('br'));
            input.value = "";
            ws.send(valor);
        }
    })
}