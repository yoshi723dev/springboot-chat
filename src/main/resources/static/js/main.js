document.addEventListener("DOMContentLoaded", function() {
    $('#login').on('submit', function(event) {
        event.preventDefault(); // フォームのデフォルト送信を防ぐ

        var formData = {
            email: $('#email').val(),
            password: $('#password').val()
        };

        // JSON形式に変換
        var jsonData = JSON.stringify(formData);

        // JSONデータをサーバーにPOSTリクエストで送信
        $.ajax({
            url: '/login',
            type: 'POST',
            contentType: 'application/json',
            data: jsonData,
            success: function(response) {
                if (response.status != 200) {
                    alert("ログインエラーです");
                    return;
                }
                window.location.href = "home.html";
            },
            error: function(xhr, status, error) {
                alert('ajax error: ' + error);
            }
        });
    });
});

document.addEventListener("DOMContentLoaded", function() {
    const friendlist = document.getElementById("friendlist");
    if (friendlist) {
        // JSONデータをサーバーにPOSTリクエストで送信
        $.ajax({
            url: '/get_friend',
            type: 'GET',
            contentType: 'application/json',
            success: function(response) {
                if (response.status != 200) {
                    window.location.href = "error.html";
                }
                // 受け取ったデータをHTMLリストに表示
                $.each(response.friends, function(key, value) {
                    var pDetail = $('<p>')
                        .attr('id', 'chat_' + `${value.chat_group_id}`)
                        .attr('class', 'chathome')
                        .text(`${value.user_nm}`);
                    $('#friendlist').append(pDetail);
                    var formData = {
                        chat_group_id: `${value.chat_group_id}`,
                        friend_user_ids: `${value.user_id}`
                    };
                    // JSON形式に変換
                    var jsonData = JSON.stringify(formData);
                    var hiddenInput = $('<input>')
                        .attr('type', 'hidden')
                        .attr('name', 'chat_' + `${value.chat_group_id}` + '_value')
                        .attr('id', 'chat_' + `${value.chat_group_id}` + '_value')
                        .attr('value', `${jsonData}`);
                    
                    // Append the hidden input to the form
                    $('#friendlist').append(hiddenInput);
                })
            },
            error: function(xhr, status, error) {
                alert('ajax error: ' + error);
            }
        });
    }
});

document.addEventListener("DOMContentLoaded", function() {
    const chatlist = document.getElementById("chatlist");
    if (chatlist) {
        // JSONデータをサーバーにPOSTリクエストで送信
        $.ajax({
            url: '/chat/get_chatgrouplist',
            type: 'GET',
            contentType: 'application/json',
            success: function(response) {
                if (response.status != 200) {
                    window.location.href = "error.html";
                }
                // 受け取ったデータをHTMLリストに表示
                $.each(response.list_chat_group, function(key, value) {
                    var pDetail = $('<p>')
                        .attr('id', 'chat_' + `${value.chat_group_id}`)
                        .attr('class', 'chathome')
                        .text(`${value.chat_name}`);
                    $('#chatlist').append(pDetail);
                    var formData = {
                        chat_group_id: `${value.chat_group_id}`,
                        friend_user_ids: `${value.friend_user_ids}`
                    };
                    // JSON形式に変換
                    var jsonData = JSON.stringify(formData);
                    var hiddenInput = $('<input>')
                        .attr('type', 'hidden')
                        .attr('name', 'chat_' + `${value.chat_group_id}` + '_value')
                        .attr('id', 'chat_' + `${value.chat_group_id}` + '_value')
                        .attr('value', `${jsonData}`);
                    
                    // Append the hidden input to the form
                    $('#chatlist').append(hiddenInput);
                })
            },
            error: function(xhr, status, error) {
                alert('ajax error: ' + error);
            }
        });
    }
});

document.addEventListener("DOMContentLoaded", function() {
    $(document).on('click', '[id^="chat_"]', function() {
        var clickId = $(this).attr('id');
        var data = $('#' + `${clickId}` + "_value").val();
        $.ajax({
            url: '/chat/redirect_chat',
            method: 'POST',
            contentType: 'application/json',
            data: data,
            success: function(response) {
                window.location.href = response;
            },
            error: function(xhr, status, error) {
                alert('AJAX request failed: ' + error);
            }
        });
    });
});

document.addEventListener("DOMContentLoaded", function() {
    $(document).on('click', '#sendMessage', function() {
        if ($('#message').val() == "") {
            return;
        }
        var data = {
            message: $('#message').val(),
            chat_group_id: $('#chat_group_id').val()
        };
        // JSON形式に変換
        var jsonData = JSON.stringify(data);

        $.ajax({
            url: '/chat/regist_chatlog',
            method: 'POST',
            contentType: 'application/json',
            data: jsonData,
            success: function(response) {
                if (response.status != 200) {
                    window.location.href = "error.html";
                }
                var date = new Date();
                date.setTime(date.getTime() - date.getTimezoneOffset() * 60 * 1000);
                var str_date = date.toISOString().replace('T', ' ').substring(0,19);
                var chatClass = 'mychat';
                var infoClass = 'myinfochat';
                var message = $('#message').val();
                var pInfo = $('<p>')
                    .attr('class', infoClass)
                    .text(str_date);
                var pDetail = $('<p>')
                      .attr('class', chatClass)
                    .text(message);
                $('#chatloglist').append(pInfo);
                $('#chatloglist').append(pDetail);
                $('#message').val("");
            },
            error: function(xhr, status, error) {
                alert('AJAX request failed: ' + error);
            }
        });
    });
});


document.addEventListener("DOMContentLoaded", function() {
    const chatloglist = document.getElementById("chatloglist");
    if (chatloglist) {
        // JSONデータをサーバーにPOSTリクエストで送信
        $.ajax({
            url: '/chat/get_chatlog',
            type: 'GET',
            contentType: 'application/json',
            success: function(response) {
                if (response.status != 200) {
                    window.location.href = "error.html";
                }
                var hiddenChatGroupId = $('<input>')
                    .attr('type', 'hidden')
                    .attr('name', 'chat_group_id')
                    .attr('id', 'chat_group_id')
                    .attr('value', response.chat_group_id);
                $('#chatloglist').append(hiddenChatGroupId);
                
                // 受け取ったデータをHTMLリストに表示
                $.each(response.chatLog, function(key, value) {
                    var chatClass = 'yourchat';
                    var infoClass = 'yourinfochat';
                    var infoUser =  `${value.user_nm}`;
                    if (`${value.my_message}` == 1) {
                        chatClass = 'mychat';
                        infoClass = 'myinfochat';
                        infoUser = '';
                    }
                    var pInfo = $('<p>')
                        .attr('class', infoClass)
                        .text(`${value.chat_date}` + "　" + infoUser);
                    var pDetail = $('<p>')
                        .attr('class', chatClass)
                        .text(`${value.comment}`);
                    $('#chatloglist').append(pInfo);
                    $('#chatloglist').append(pDetail);
                })
            },
            error: function(xhr, status, error) {
                alert('ajax error: ' + error);
            }
        });
    }
});

