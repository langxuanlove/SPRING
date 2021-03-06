<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>Testing websockets</title>
</head>
<body>
  <div>
      <input type="text" id="yourName"/>
    <button id="start">click to start</button>
  </div>
  <div style="width:100%;">
      <div id="messages" style="border:5px solid red;width:50%;height:300px;margin-top:20px;"></div>
  </div>
  <div style="width:100%;margin-top:20px;">
      <div id="sends" style="float:left;width:50%;height:50px;margin-right:20px;">
          <input type="text"  id="MessageUN" style="width:100%;height:48px;"/>
      </div>
      <button style="float:left;width:10%;height:50px;" id="sendMessage">发送</button>
  </div>
  
  <script type="text/javascript">
  var button=document.getElementById("start");
  button.onclick=function(){
      
      
      
      
      var name=document.getElementById("yourName");
      console.log(name.value);
      var websocketAdd="ws://localhost:8080/gnet_websocket_1/ws/chat/"+name.value;
      var webSocket=new WebSocket(websocketAdd); 

      
      function onMessage(event) {
            document.getElementById('messages').innerHTML 
              += '<br />' + event.data;
      }

      function onOpen(event) {
            document.getElementById('messages').innerHTML 
              = 'Connection established';
          //一旦链接开始，尝试发出一条通讯消息
             start();
          alert("消息通道开启，可以发送消息了");
          //确认链接开始，就可以开始消息的发送了
          //首先绑定一个点击事件
          var sendMessage=document.getElementById("sendMessage");
          sendMessage.onclick=function(){
              var message= document.getElementById('MessageUN').value;
              //我们之前会有一个唯一的标识符，就是在click to start之前的标识符
              webSocket.send(message);
              //上边一部完成之后，会自动触发onmessage事件
          }
          
      }
      
      
       function start() {
         webSocket.send(name.value+" : "+'hello');
       }

      function onError(event) {
            alert(event.data);
      }
      
      webSocket.onerror = function(event) {
            onError(event)
      };

      webSocket.onopen = function(event) {
            onOpen(event)
      };

      webSocket.onmessage = function(event) {
            onMessage(event)
      };      
        
  }
  
  </script>
</body>
</html>