$(document).ready(function() {
    $('#login').on('submit', function(event) {
        event.preventDefault(); // フォームのデフォルト送信を防ぐ

        var formData = {
            user_id: $('#user_id').val(),
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
	    const apiUrl = "http://localhost:8080/get_friend"; // APIのURLを指定
	    // Ajaxリクエストを送信
	    fetch(apiUrl, {
	        method: "GET",
	    })
	    .then(response => response.json())
	    .then(data => {
	        // 受け取ったデータをHTMLリストに表示
	        const friendList = document.getElementById("friendlist");
	        $.each(data.friends, function(key, value) {
				const listItem = document.createElement("li");
	            listItem.textContent = `ID: ${value.user_nm}`;
	            friendList.appendChild(listItem);
			})
	    })
	    .catch(error => {
	        console.error("Error fetching data:", error);
	    });
	}
});

document.addEventListener("DOMContentLoaded", function() {
	const chatlist = document.getElementById("chatlist");
	if (chatlist) {
	    const apiUrl = "http://localhost:8080/chat/get_chatgrouplist"; // APIのURLを指定
	    // Ajaxリクエストを送信
	    fetch(apiUrl, {
	        method: "GET",
	    })
	    .then(response => response.json())
	    .then(data => {
	        // 受け取ったデータをHTMLリストに表示
	        const chatlist = document.getElementById("chatlist");
	        $.each(data.list_chat_group, function(key, value) {
				const listItem = document.createElement("li");
	            listItem.textContent = `ID: ${value.chat_group_id} + ":" + ${value.chat_name}`;
	            chatlist.appendChild(listItem);
			})
	    })
	    .catch(error => {
	        console.error("Error fetching data:", error);
	    });
	}
});