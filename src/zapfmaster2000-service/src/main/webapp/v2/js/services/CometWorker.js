var baseUrl;
self.addEventListener('message', function (e) {
    var data = e.data;

    if (data.init && data.baseUrl) {
        // importScripts(data.baseUrl+"js/libs/jquery/jquery-2.0.3.min.js");
        baseUrl = data.baseUrl;
    } else {
        getData();
    }

    //ADD YOUR SERVICE URL HERE     var ServiceUrl = host + "/GetUpdate?eMailId=" + data.email;
    var serviceUrl = baseUrl + data.url + "?token=" + data.token + "&_=" + new Date().getTime();

    function getData() {
        //debugger;
        //postMessage(data);
        //  throw serviceUrl;//JSON.stringify()
        try {

            var xhr = new XMLHttpRequest();
            xhr.open('GET', serviceUrl, false);
            xhr.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4) {
                    //Posting Back the Result
                    postMessage({
                        status: xhr.status,
                        statusText: xhr.statusText,
                        responseText: xhr.responseText
                    });
                }
            };
            xhr.send(null);
        } catch (e) {
            postMessage({
                status: e.status,
                statusText: e.statusText,
                responseText: e.responseText
            });
        }
    }
}, false);