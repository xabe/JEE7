var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
//var wsUri = "ws://" + window.location.host + webCtx + "/websocket";
var type;
var port = ":8000";
var chatClient = null;
var endPointURL = null;
var output;

function init() {
  if( typeof(WebSocket) != "function" ) {
      $('body').html("<h1>Error</h1><p>Your browser does not support HTML5 Web Sockets. Try Google Chrome instead.</p>");
  }
  if (window.location.protocol == 'http:') 
  {
    type = 'ws://';
  } 
  else 
  {
    type = 'wss://';
    port = ":8443";
  }
  endPointURL = "ws://" + window.location.host + webCtx + "/notification";
  var state = $("#state").val();
  if(state == "Development")
  {
     endPointURL = type + window.location.host + webCtx + "/notification";
  }
  else
  {
     endPointURL = type + window.location.host + port + webCtx + "/notification";
  }
  console.log(endPointURL);
  
	output = $("#output");
	testWebSocket();
}

function testWebSocket() {
	websocket = new WebSocket(endPointURL);
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