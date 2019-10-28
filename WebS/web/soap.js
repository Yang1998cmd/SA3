
SOAP_sendEmailBatch = function (data) {

    var response;

    var _payload = "" +
        "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
        "    <Body>\n" +
        "        <sendEmailBatch xmlns=\"http://soapserver\">\n" +
        "            <_url>" + data._url + "</_url>\n" +
        "            <_payload>" + data._payload + "</_payload>\n" +
        "        </sendEmailBatch>\n" +
        "    </Body>\n" +
        "</Envelope>";

    console.log("要输出的内容:"+data._url+data._payload);

    $.ajax({
        async: true,
        crossDomain: true,
        url: "http://localhost:8080/services/soapserver",
        method: "POST",
        headers: {
            "content-type": "text/xml",
            "soapaction": "\\\"\\\"",
            "cache-control": "no-cache",
        },
        data: _payload,
        type: "post",   //璇锋眰鏂瑰紡,

        beforeSend: function () {
            //璇锋眰鍓嶇殑澶勭悊
        },
        success: function (data) {
            //璇锋眰鎴愬姛鏃跺鐞�
            response = data.getElementsByTagName("sendEmailBatchReturn")[0].childNodes[0].textContent;

            if (response === "true") {
                alert("邮件发送成功！");
            }
            else {
                alert("邮件发送失败！");
            }


        },
        error: function () {
            //璇锋眰鍑洪敊澶勭悊
            alert("Ajax Error")
        }
    });

    return response;

};

SOAP_validateEmailAddress = function (data) {

    var response;

    var _payload = "" +
        "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
        "    <Body>\n" +
        "        <validateEmailAddress xmlns=\"http://soapserver\">\n" +
        "            <_url>" + data._url + "</_url>\n" +
        "        </validateEmailAddress>\n" +
        "    </Body>\n" +
        "</Envelope>";


    $.ajax({
        async: false,
        crossDomain: true,
        url: "http://localhost:8080/services/soapserver",
        method: "POST",
        headers: {
            "content-type": "text/xml",
            "soapaction": "\\\"\\\"",
            "cache-control": "no-cache",
        },
        data: _payload,
        type: "post",   //璇锋眰鏂瑰紡,

        beforeSend: function () {
            //璇锋眰鍓嶇殑澶勭悊
        },
        success: function (data) {

            response = data.getElementsByTagName("validateEmailAddressReturn")[0].childNodes[0].textContent;
            if(response==="true")
            {
                alert("邮箱正确！");
            }
            else
            {
                alert("邮箱错误！");
            }

        },
        error: function () {
            //璇锋眰鍑洪敊澶勭悊
            alert("Ajax Error")
        }
    });

    return response;

};