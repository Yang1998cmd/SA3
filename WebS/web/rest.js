REST_sendEmailBatch = function (data) {
    var response;

    $.ajax({
        "async": true,
        "crossDomain": true,
        "url": "http://localhost:8080/rest/rest_send/sendEmailBatch?url=" + data._url + "&payload=" + data._payload,
        "method": "GET",
        "headers": {
            "content-type": "application/x-www-form-urlencoded",
            "cache-control": "no-cache",
        },
        success: function (res) {
            //璇锋眰鎴愬姛鏃跺鐞�

            response = res;
            if (res === "true") {
                alert("邮件发送成功！");
            }
            else {
                alert("邮件发送失败！");
            }

        }
    });

    return response;
};

REST_validateEmailAddress = function (data) {
    var response;

    $.ajax({
        "async": false,
        "crossDomain": true,
        "url": "http://localhost:8080/rest/rest_send/validateEmailAddress?url=" + data._url,
        "method": "GET",
        "headers": {
            "content-type": "application/x-www-form-urlencoded",
            "cache-control": "no-cache",
        },
        success: function (data) {
            //璇锋眰鎴愬姛鏃跺鐞�

            response = data;

            if(response==="true")
            {
                alert("邮箱正确！");
            }
            else
            {
                alert("邮箱错误！");
            }

        }
    });

    return response;
};