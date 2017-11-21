/**
 * Created by lizhuo on 2016/10/17.
 */
var timerBigScreen4 = null;
var indexBigScreen4 = 1;
var autorollBigScreen4;
$(function(){
	autorollBigScreen4 = autoroll;
	var hotList;
    var offset = 15000; //轮换时间
    var chart = echarts.init(document.getElementById('heat-map'));
    var chinaMap = echarts.getMap("china").geoJson.features;
    var context = document.getElementById("canvasForHotPin").getContext("2d");
    var timerDrawMap = null;
    var timer = null;
    var map = [];
    var chinaCityMap = {
            "海门":[121.15,31.89],
            "鄂尔多斯":[109.781327,39.608266],
            "招远":[120.38,37.35],
            "舟山":[122.207216,29.985295],
            "齐齐哈尔":[123.97,47.33],
            "盐城":[120.13,33.38],
            "赤峰":[118.87,42.28],
            "青岛":[120.33,36.07],
            "乳山":[121.52,36.89],
            "金昌":[102.188043,38.520089],
            "泉州":[118.58,24.93],
            "莱西":[120.53,36.86],
            "日照":[119.46,35.42],
            "胶南":[119.97,35.88],
            "南通":[121.05,32.08],
            "拉萨":[91.11,29.97],
            "云浮":[112.02,22.93],
            "梅州":[116.1,24.55],
            "文登":[122.05,37.2],
            "上海":[121.48,31.22],
            "攀枝花":[101.718637,26.582347],
            "威海":[122.1,37.5],
            "承德":[117.93,40.97],
            "厦门":[118.1,24.46],
            "汕尾":[115.375279,22.786211],
            "潮州":[116.63,23.68],
            "丹东":[124.37,40.13],
            "太仓":[121.1,31.45],
            "曲靖":[103.79,25.51],
            "烟台":[121.39,37.52],
            "福州":[119.3,26.08],
            "瓦房店":[121.979603,39.627114],
            "即墨":[120.45,36.38],
            "抚顺":[123.97,41.97],
            "玉溪":[102.52,24.35],
            "张家口":[114.87,40.82],
            "阳泉":[113.57,37.85],
            "莱州":[119.942327,37.177017],
            "湖州":[120.1,30.86],
            "汕头":[116.69,23.39],
            "昆山":[120.95,31.39],
            "宁波":[121.56,29.86],
            "湛江":[110.359377,21.270708],
            "揭阳":[116.35,23.55],
            "荣成":[122.41,37.16],
            "连云港":[119.16,34.59],
            "葫芦岛":[120.836932,40.711052],
            "常熟":[120.74,31.64],
            "东莞":[113.75,23.04],
            "河源":[114.68,23.73],
            "淮安":[119.15,33.5],
            "泰州":[119.9,32.49],
            "南宁":[108.33,22.84],
            "营口":[122.18,40.65],
            "惠州":[114.4,23.09],
            "江阴":[120.26,31.91],
            "蓬莱":[120.75,37.8],
            "韶关":[113.62,24.84],
            "嘉峪关":[98.289152,39.77313],
            "广州":[113.23,23.16],
            "延安":[109.47,36.6],
            "太原":[112.53,37.87],
            "清远":[113.01,23.7],
            "中山":[113.38,22.52],
            "昆明":[102.73,25.04],
            "寿光":[118.73,36.86],
            "盘锦":[122.070714,41.119997],
            "长治":[113.08,36.18],
            "深圳":[114.07,22.62],
            "珠海":[113.52,22.3],
            "宿迁":[118.3,33.96],
            "咸阳":[108.72,34.36],
            "铜川":[109.11,35.09],
            "平度":[119.97,36.77],
            "佛山":[113.11,23.05],
            "海口":[110.35,20.02],
            "江门":[113.06,22.61],
            "章丘":[117.53,36.72],
            "肇庆":[112.44,23.05],
            "大连":[121.62,38.92],
            "临汾":[111.5,36.08],
            "吴江":[120.63,31.16],
            "石嘴山":[106.39,39.04],
            "沈阳":[123.38,41.8],
            "苏州":[120.62,31.32],
            "茂名":[110.88,21.68],
            "嘉兴":[120.76,30.77],
            "长春":[125.35,43.88],
            "胶州":[120.03336,36.264622],
            "银川":[106.27,38.47],
            "张家港":[120.555821,31.875428],
            "三门峡":[111.19,34.76],
            "锦州":[121.15,41.13],
            "南昌":[115.89,28.68],
            "柳州":[109.4,24.33],
            "三亚":[109.511909,18.252847],
            "自贡":[104.778442,29.33903],
            "吉林":[126.57,43.87],
            "阳江":[111.95,21.85],
            "泸州":[105.39,28.91],
            "西宁":[101.74,36.56],
            "宜宾":[104.56,29.77],
            "呼和浩特":[111.65,40.82],
            "成都":[104.06,30.67],
            "大同":[113.3,40.12],
            "镇江":[119.44,32.2],
            "桂林":[110.28,25.29],
            "张家界":[110.479191,29.117096],
            "宜兴":[119.82,31.36],
            "北海":[109.12,21.49],
            "西安":[108.95,34.27],
            "金坛":[119.56,31.74],
            "东营":[118.49,37.46],
            "牡丹江":[129.58,44.6],
            "遵义":[106.9,27.7],
            "绍兴":[120.58,30.01],
            "扬州":[119.42,32.39],
            "常州":[119.95,31.79],
            "潍坊":[119.1,36.62],
            "重庆":[106.54,29.59],
            "台州":[121.420757,28.656386],
            "南京":[118.78,32.04],
            "滨州":[118.03,37.36],
            "贵阳":[106.71,26.57],
            "无锡":[120.29,31.59],
            "本溪":[123.73,41.3],
            "克拉玛依":[84.77,45.59],
            "渭南":[109.5,34.52],
            "马鞍山":[118.48,31.56],
            "宝鸡":[107.15,34.38],
            "焦作":[113.21,35.24],
            "句容":[119.16,31.95],
            "北京":[116.46,39.92],
            "徐州":[117.2,34.26],
            "衡水":[115.72,37.72],
            "包头":[110,40.58],
            "绵阳":[104.73,31.48],
            "乌鲁木齐":[87.68,43.77],
            "枣庄":[117.57,34.86],
            "杭州":[120.19,30.26],
            "淄博":[118.05,36.78],
            "鞍山":[122.85,41.12],
            "溧阳":[119.48,31.43],
            "库尔勒":[86.06,41.68],
            "安阳":[114.35,36.1],
            "开封":[114.35,34.79],
            "济南":[117,36.65],
            "德阳":[104.37,31.13],
            "温州":[120.65,28.01],
            "九江":[115.97,29.71],
            "邯郸":[114.47,36.6],
            "临安":[119.72,30.23],
            "兰州":[103.73,36.03],
            "沧州":[116.83,38.33],
            "临沂":[118.35,35.05],
            "南充":[106.110698,30.837793],
            "天津":[117.2,39.13],
            "富阳":[119.95,30.07],
            "泰安":[117.13,36.18],
            "诸暨":[120.23,29.71],
            "郑州":[113.65,34.76],
            "哈尔滨":[126.63,45.75],
            "聊城":[115.97,36.45],
            "芜湖":[118.38,31.33],
            "唐山":[118.02,39.63],
            "平顶山":[113.29,33.75],
            "邢台":[114.48,37.05],
            "德州":[116.29,37.45],
            "济宁":[116.59,35.38],
            "荆州":[112.239741,30.335165],
            "宜昌":[111.3,30.7],
            "义乌":[120.06,29.32],
            "丽水":[119.92,28.45],
            "洛阳":[112.44,34.7],
            "秦皇岛":[119.57,39.95],
            "株洲":[113.16,27.83],
            "石家庄":[114.48,38.03],
            "莱芜":[117.67,36.19],
            "常德":[111.69,29.05],
            "保定":[115.48,38.85],
            "湘潭":[112.91,27.87],
            "金华":[119.64,29.12],
            "岳阳":[113.09,29.37],
            "长沙":[113,28.21],
            "衢州":[118.88,28.97],
            "廊坊":[116.7,39.53],
            "菏泽":[115.480656,35.23375],
            "合肥":[117.27,31.86],
            "武汉":[114.31,30.52],
            "大庆":[125.03,46.58]
        };
    //chinaMap = chinaMap.concat(chinaCityMap);//数据结构不一样
    for(var i in chinaMap){
    	map[chinaMap[i].properties.name] = chinaMap[i];
    }
    var currentData = [];
    var option = {
    		title: {
                text: '中国热门业余足球赛事',
                subtext: 'The popular amateur football game in China.',
                left: 'center',
                textStyle: {
                    color: '#fff',
                    fontSize:30,
                    fontWeight:'bolder'
                },
                subtextStyle:{
                    color:'#fff',
                    fontSize:24
                }
            },
            tooltip: {
            	alwaysShowContent: true
            },
            visualMap: {
            	show: false,
                min: 0,
                max: 150,
                splitNumber: 10,
                //splitNumber: 10,
                inRange: {
                	//color: ['#ff0000','#ff8000','#ffff00','#00ff00']
                    color: ['#ff0000','#ff8000','#ffff00','#00ff00','#00ffff','#0000ff','#8000ff'].reverse()
                	//color: ['#ffff00','#ff0000','#ffffff'].reverse()
                },
                textStyle: {
                    color: '#fff'
                }
            },
            geo: {
                map: 'china',
                label: {
                    emphasis: {
                        show: false
                    }
                },
                roam: true,
                itemStyle: {
                    normal: {
                        areaColor: '#d7ffeb',
                        borderColor: '#1fb488'
                    },
                    emphasis: {
                        areaColor: '#1eb487'
                    }
                },
                top:112,
            },
            series: [{
            	pointSize: 35,
                name: 'AQI',
                type: 'heatmap',
                coordinateSystem: 'geo',
                data: convertData(currentData)
            }]
        };
        chart.setOption(option);
	$.ajax({
		'url': 'page/displayController/getHotInformation',
		'type': 'post',
		'dataType': 'json',
		'async': false,
		'success': function(data){
			hotList = data;
			for(var index in hotList){
				var item = hotList[index];
				getNews(item.game_ID);
			}
		}
	});
	
    
	$('.hot-event-logo-list li:first').addClass('curLi');
    autoroll();
    hookThumb();
   
    function autoroll(){
    	if(timer != null){
    		clearTimeout(timer);
    	}
    	clearTimeout(timerDrawMap);
        n = 7;
        if(action.sub_page_flag == "false"){
        	indexBigScreen4++;	
        }
        
        if(indexBigScreen4 > n || indexBigScreen4 < 1){
        	indexBigScreen4 = 1;
        }
        
        //显示
        slide(indexBigScreen4 - 1);
        
        var hotNews = hotList[indexBigScreen4 - 1];
        $(".hot-event-des h3").html(hotNews.game_name);
        $(".hot-event-des .team_num").html(hotNews.team_num + "支队伍");
        $(".hot-event-des .people_num").html(hotNews.people_num + "人赛制");
        $(".hot-event-des .hot-con-des-con").html(hotNews.game_introduction);
        $('#comments-list').removeData("flexslider");
        $('#comments-list').before($("<div>").addClass("comments-list").attr("id", "comments-list-new"));
        $("#comments-list").remove();
        $("#comments-list-new").append($("<ul>").addClass("slides").css("height", "311"));
        currentData = [];
        option = chart.getOption();
        option.series[0].data = [];
        chart.setOption(option);
        drawMap(hotNews.game_news_array, 0)();
        for(var index in hotNews.game_news_array){
        	var item = hotNews.game_news_array[index];
        	var option = chart.getOption();
        	if(item["NO."] == 1){
        		$("#comments-list-new ul").append(
        			$("<li>")
        			.append(
        				$("<h3>").html(item.gamenews_name).addClass("comments-tit").css("display", "none")
        			)
        			.append(
        				$("<i>").html(formatDate(new Date(item.gamenews_time))).addClass("comments-time").css("display", "none")
        			)
        			.append(
        				$("<p>").html(item.gamenews_dimension).addClass("comment-con").css("display", "none")
        			)
        		)
        	}
        	
        }
        $('#comments-list-new').flexslider({
            animation: "fade",
            slideshowSpeed: 3000,
            direction:"horizontal",
            easing:"swing",
            start: function(){
                $('#comments-list h3').fadeIn();
                $('#comments-list i').fadeIn();
                $('#comments-list p').fadeIn();
            }
        });
        
        $("#comments-list-new").attr("id", "comments-list");

        if(action.sub_page_flag == "true"){
        	timer = window.setTimeout(autoroll, offset * 3);
        }else{
        	timer = window.setTimeout(autoroll, offset);
        }
        
    }
    function slide(i){
        $('.hot-event-logo-list li').eq(i).addClass('curLi').siblings().removeClass('curLi');
    }
    function hookThumb(){
        $('.hot-event-logo-list li').hover(
            function () {
                if (timer) {
                    clearTimeout(timer);
                    i = $(this).prevAll().length;
                    slide(i);
                }
            },
            function () {
                timer = window.setTimeout(autoroll, offset);
                this.blur();
                return false;
            }
        );
    }
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
	} 
	function getNews(game_ID){
		$.ajax({
    		'url': 'page/displayController/getHotInformationNews',
    		'type': 'post',
    		'data': {
    			game_ID: game_ID
    		},
    		'async': false,
    		'dataType': 'json',
    		'success': function(data){
    			hotList.find(function(item){
    				if(item.game_ID == game_ID){
    					item['game_news_array'] = data;
    				}
    			});
    		}
    	});
	}
	
	

    //concat连接数组方法
    function convertData(dataList) {
        var res = [];
        
        
        for (var i = 0; i < dataList.length; i++) {
            var data = dataList[i];
            data.name = data.name
            				.replace("省", "")
            				.replace("市", "")
            				.replace("自治区", "")
            				.replace("回族", "")
            				.replace("壮族", "")
            				.replace("维吾尔", "");
            data.name = $.trim(data.name);
            var province = map[data.name];
            if(province == null){
            	console.log(data.name);
            	console.log(map);
            }
            var value = data.value * 25;
            if(value < 30){
            	value = 35
            }
            res.push({
              name: province.properties.name,
              //value: province.properties.cp.concat([Math.atan(data.value) * 83])
              value: province.properties.cp.concat([value])
            })
        }
        return res;
    };
    function drawMap(game_news_array, index){
    	return function(){
    		var showList = [];
    		for(var i = 0; i < game_news_array.length; i++){
    			var game_news = game_news_array[i];
    			if(game_news.show == 1){
    				showList.push(game_news);
    				game_news_array.splice(i,1);
    				i--;
    			}
    		}
    		var length = game_news_array.length / showList.length;
    		for(var i = 0; i < showList.length; i++){
    			game_news_array.splice(i * length + i, 0, showList[i]);
    		}
    		if(index < game_news_array.length){
    			var news = game_news_array[index]
    			var provinceName = news.province;
    			
    			var option = chart.getOption();
    			var province = currentData.find(function(item){
    				if(item.name == provinceName){
    					return item;
    				}
    			});
    			if(province != null){
    				province.value++;
    			}else{
    				currentData.push({
    					name: provinceName,
    					value: 1
    				});
    			}
    			option.series[0].data = convertData(currentData);
    			chart.setOption(option);
    			
    	    	if(news.show == 1){
    	    		var xy;
    	    		chinaMap.find(function(item, i){
    	                if(news.province.indexOf(item.properties.name) > -1){
    	                	xy = chart.getModel().getSeriesByIndex(0).coordinateSystem.dataToPoint(item.properties.cp)
    	                  return item;
    	                }
    	            });
    	    		var panelW = 220;
    	    		var panelH = 140;
        	    	context.clearRect(0, 0, 1200, 1258);
    	    		
    	    		context.beginPath();
    	    		context.lineWidth = 2;//设置线宽
    	    		context.strokeStyle ='rgb(255,255,255)';//设置连线颜色
    	    		context.moveTo(xy[0], xy[1]);
    	    		var angle = Math.random() * 360;
    	    		var panelCenter = [Math.sin(angle) * 200 + xy[0], Math.cos(angle) * 200 + xy[1]];
    	    		context.lineTo(panelCenter[0], panelCenter[1]);
    	    		context.closePath();//
    	    		context.stroke();//画线框
    	    		
    	    		context.lineWidth = 3;//设置线宽
    	    		context.strokeStyle ='rgba(255,255,255,.8)';//设置连线颜色
    	    		context.fillStyle='rgb(0, 203, 139)';  //填充的颜色
    	    		context.clearRect(panelCenter[0] - panelW/2, panelCenter[1] - panelH/2, panelW, panelH);
    	    		context.fillRect(panelCenter[0] - panelW/2, panelCenter[1] - panelH/2, panelW, panelH);  //填充颜色 x y坐标 宽 高
    	    		context.strokeRect(panelCenter[0] - panelW/2, panelCenter[1] - panelH/2, panelW, panelH);  //填充边框 x y坐标 宽 高
    	    		context.beginPath();
    	    		context.lineWidth = 1.5;//设置线宽
    	    		context.font = '23px Courier New';
    	    		context.textAlign = 'left';
    	            context.textBaseline = 'top';
    	            var length = 9; 
    	            var lineHeight = 50;
    	            for(var i = 0; i < 3; i++){
    	            	if(null == news.gamenews_name){
    	            		news.gamenews_name = "";
    	            	}
    	            	context.strokeText(news.gamenews_name.substring(length * (i), length * (i+1)), panelCenter[0] -  panelW/2 + 10, panelCenter[1] - panelH/2 + i * lineHeight + 10);
    	            }
    	    	}
    	    	
    	    	
    			timerDrawMap = setTimeout(drawMap(game_news_array, ++index), 1);
    		}
    	}
	}

    
});