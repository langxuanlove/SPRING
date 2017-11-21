$(function() {
    SyntaxHighlighter.all();
    $.each($(".slides").find("video"), function(i, item) {
        item.removeEventListener("ended", function() {}, false);
        item.addEventListener("ended", function() {
            var slider = $('.flexslider').data('flexslider');
            var count = slider.count;
            var currentSlide = slider.currentSlide;
            var nextSlide = (currentSlide + 1) >= count ? 0 : (currentSlide + 1);
            slider.flexAnimate(nextSlide);

            var p = $("ol.flex-control-nav li").eq(currentSlide).find('p');
            p.html($(p).attr("data-thumb-title"));

        }, false);
    });
});
$(window).load(function() {
    $('.flexslider').flexslider({
        animation: "slide",
        controlNav: "thumbnails",
        slideshow: false,
        directionNav: false,
        start: function(slider) {

           // playVideo(0);
        },
        before: function(slider) {}, //Callback: function(slider) - Fires asynchronously with each slider animation
        after: function(slider) {
            if(currentSlide!=slider.currentSlide){
               playVideo(slider.currentSlide);                
            }
            currentSlide=slider.currentSlide;
//            console.log('--------------------<<<<<>>>');
        }, //Callback: function(slider) - Fires after each slider animation completes
    });
});

var currentSlide=0;
$(function(){
	var index=0;
	var action = {
			mark: 0
		};		
		var timer = setInterval(function(){
			$.ajax({
				url: 'page/displayController/selectPageInfo',
				type: 'get',
				dataType: 'json',
				success: function(data){
					data = data[0];
//					console.log(data);
					if(data.mark == action.mark){
						return;
					}
					action = data;
					if(data.page_num == '5' && data.action_type == '1' && data.sub_page_flag == 'true'){
						var _index = data.sub_page_num-1;
						olli(_index);
					}
					
					//location.href = "http://" + location.host + "/Big_Screen/Big_Screen.jsp#page" + action.page_num;
				}
			});
		}, 1000);    

//按钮切换视频
function olli(index){
	var _img=$('section.slider ol.flex-control-nav li').eq(index).find('img');
		_img.click();
}				
});

function playVideo(index) {
    //  
	

    $.each($(".slides").find("video"), function(i, item) {
//        console.log('----------------->>>' + i);

        item.pause();
       // item.currentTime = 0;

    });
    $.each($("ol.flex-control-thumbs li").find('p'), function(i, item) {

        $(item).html($(item).attr("data-thumb-title"));
    });
    var video = $("#v_" + index);
   
    video.get(0).loop=true;
    video.get(0).currentTime = 0;
    video.get(0).play(); //播放
    
   
    //////////////////////////
   /*   var myPlayer = _V_("#v_" + index);
    _V_("#v_" + index).ready(function(){
        var myPlayer = this;
        myPlayer.loop=true;
        myPlayer.play();
    });*/
    
    
    
    
    //////////////////
    
    $(".video-item").hide();
    $(".video-item").eq(index).show();
    var p = $("ol.flex-control-thumbs li").eq(index).find('p');

    p.html($(p).attr("playing-txt"));
}
function pauseEvevyVideo(){
    $.each($(".slides").find("video"), function(i, item) {
//        console.log('----------------->>>' + i);

        item.pause();
       // item.currentTime = 0;

    });    
}
function showMessage(index) {
    $(".video-item").hide();
    $(".video-item").eq(index).show();
}
