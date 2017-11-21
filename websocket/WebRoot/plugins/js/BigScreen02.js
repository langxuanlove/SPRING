$(function(){
	/**
	 * 视频播放（第二屏）
	 */
	/*
	 * 获取足球视频信息
	 */
	(function(){
		$.ajax({
			url:'page/displayController/getVideoInfo',
			type:'post',
			data:{
				pageIndex:0,
				pageSize:99
			},
			dataType:'json',
			success:function(data){
//				console.log(data);
//				$('#main .slides').empty();
				$('.video-con').empty();
				$.each(data,function(i,_each){
				/*var _li='<li data-thumb="source/images/sm-sp.jpg" playing-txt="正在播放" data-thumb-title="'+_each.video_name+'" data-thumb-subtitle="'+_each.video_longer+'">'+
                			'<a href="javascript:void(0);">'+
                				'<video id="v_'+i+'" controls="controls">'+
                					'<source src="'+_each.video_url+'" type="video/mp4" />'+
                				'</video>'+
                			'</a>'+
                		'</li>';            */  			
                var _div='<div class="video-item">'+
                    		'<div class="video-des total-radius-shadow">'+
                				'<h2 class="video-tit">'+_each.video_news_name+'</h2>'+
                				'<p class="source">来源: <i>114NBA</i>|<i>'+_each.video_news_time+'</i></p>'+
                				'<div class="video-on">'+
                					'<p style="text-indent:2em;">'+_each.video_news_dimension+'</p>'+
                				'</div>'+
                			'</div>'+
                			'<div class="video-time total-radius-shadow">'+
                				'<p>场均有效比赛时间</p>'+
                				'<i>'+_each.video_game_time+'</i>'+
                			'</div>'+
                		'</div>';
                /*$('#main .slides').append(_li);	*/
                $('.video-con').append(_div);
                $('.video-con').children('.video-item').eq(0).addClass('cur');

				})
			},
		});	
	})();	
});

