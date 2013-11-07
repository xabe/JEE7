var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var wsUri = "ws://" + window.location.host + webCtx + "/websocket";
var output;

function init() {
	output = $("#output");
	testWebSocket();
}

function testWebSocket() {
	websocket = new WebSocket(wsUri);
	websocket.onopen = function(){  
        console.log("Abre session");  
    };
    websocket.onclose = function(evt){  
    	console.log("Cierrar session "+evt);
    };
    websocket.onerror = function (error) {
    	  console.log('WebSocket Error ' + error);
    };
    websocket.onmessage = function (event) {
        var messagesArea = $("#messages");
        var jsonObj = JSON.parse(event.data);
        var message = jsonObj.user + " " +printDate(new Date(parseInt(jsonObj.fecha))) +" : " + jsonObj.message + "\r\n";
        messagesArea.html(messagesArea.html() + message);
        messagesArea.animate({
        	scrollTop : messagesArea.height()	
        },1000);
    };
}

function printDate(temp) {
    var dateStr = temp.getFullYear().toString() + "/" +
                  temp.getMonth().toString() + "/" +
                  temp.getDate().toString() + "   " +
                  temp.getHours().toString() + ":" +
                  temp.getMinutes().toString() + ":" +
                  temp.getSeconds().toString();
    return dateStr;

}

function closeSession(){
	if(websocket != null)
	{
		websocket.close();
	}
}

function sendMessage() {
    var user = $("#userInput").val().trim();
    if (user === "")
        alert ("¡por favor introduzca tu nombre!");
    
    var inputElement = $("#messageInput");
    var message = inputElement.val().trim();
    if (message !== "") {
        var jsonObj = {"user" : user, "message" : message, "fecha" : new Date().getTime()};
        websocket.send(JSON.stringify(jsonObj));
        inputElement.val("");
    }
    inputElement.focus();
}