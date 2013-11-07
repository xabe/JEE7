var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var wsUri = "ws://" + window.location.host + webCtx + "/notification";
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
    	onMessage(event);
    };
}

function onMessage(evt)
{
  writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data+'</span>');
}

function writeToScreen(message)
{
  var pre = document.createElement("p");
  pre.style.wordWrap = "break-word";
  pre.innerHTML = message;
  output.append(pre);
}

function closeSession(){
	if(websocket != null)
	{
		websocket.close();
	}
}