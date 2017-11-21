var mainPageIndex = 1;
var action = {
	action_type: 0,
	mark: 0
};
$(function(){
	setTimeout(changePage, 3000);
	var ws;
	ws = new WebSocket("ws://localhost:8080/Big_Screen/page/socketController.do");
	ws.onmessage = function(event) {
		console.log(event.data);
		var data = JSON.parse(event.data);
		if(data.flag_type != "0"){
			return;
		}
		action = data.msg_body;
		mainPageIndex = Number(action.page_num);
		refreshUrl();
		
		if(action.sub_page_flag == "true"){
			clearTimeout(timerBigScreen4);
			if(null != action.sub_page_num){
				indexBigScreen4 = Number(action.sub_page_num);
			}
			autorollBigScreen4();
		}
		
		/*if(mainPageIndex==5 && action.action_type=="0"){
			
		}*/
		
		
	};
	ws.onclose = function(event) {
		
	};
	// 打开WebSocket
	ws.onopen = function(event) {
		ws.send("Hello, Server!");
		console.log("success");
	};
	ws.onerror = function(event) {
		
	};
	
	/*var timer = setInterval(function(){
		$.ajax({
			url: 'page/displayController/selectPageInfo',
			type: 'get',
			dataType: 'json',
			success: function(data){
				data = data[0];
				if(data.mark == action.mark){
					return;
				}
				action = data;
				mainPageIndex = Number(action.page_num);
				refreshUrl();
				
				if(action.sub_page_flag == "true"){
					clearTimeout(timerBigScreen4);
					if(null != action.sub_page_num){
						indexBigScreen4 = Number(action.sub_page_num);
					}
					autorollBigScreen4();
				}
			}
		});
	}, 3000);*/
	
	function changePage(){
		if(action.action_type != null && action.action_type == 1){
			setTimeout(changePage, time);
			return;
		}
		mainPageIndex++;
		if(mainPageIndex > 5){
			mainPageIndex = 1;
		}
		//refreshUrl();
		$.fn.fullpage.moveSectionDown();
		//默认每个页面停留10000秒
		var time = 30000;
		//如果在第五屏，则停留60000秒
		if(mainPageIndex == 5){
			time = 30000;
		}
		setTimeout(changePage, time);
	}
	function refreshUrl(){
		//location.href = "http://" + location.host + "/Big_Screen/page/displayController/index#page" + mainPageIndex;
		$.fn.fullpage.moveTo('page'+mainPageIndex, mainPageIndex - 1);
		console.log(mainPageIndex+";");
	}
});