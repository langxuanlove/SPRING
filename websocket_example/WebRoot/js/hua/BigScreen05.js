var chinaMap_05 = echarts.getMap("china").geoJson.features;
var animation = {};
$(function(){
	_onload();
	//判断是蹴鞠或者其他
	window.setInterval(_onload,10000); 
});

var index_tx;
function _onload(){
	console.log("定时触发");
	$.ajax({
		url : 'page/displayController/selectPageInfo',
		type : 'post',
		dataType : 'json',
		async : false,
		data : {},
		success : function(msg){
			if(msg){
				//判断总的操作类型
				var action_type=msg[0]["action_type"];
				if(action_type=="0"){
					/*var other_tx="<div class=\"hotspot\" style=\"position:relative\">"+
									"<h1 class=\"title\">中国业余足球实时热点新闻<br/><p class=\"font18\"> The Chinese amateur football real-time news</p></h1>"+
									"  <div id=\"bigscreen05charts\" style=\"width:1200px;height:900px;\"></div>"+
									"  <div class=\"message\" style=\"display:none\">"+
									"   </div>"+
									"</div>"+
									"<div class=\"comment-area\" id=\"pinglun_div\">"+
									"   <h1 class=\"comment-title\">评论区(0)</h1>"+
									"   <div class=\"conmmentlist\">"+
									"   	<ul id=\"pinglun_ul\">"+
						             "       </ul>"+
						             "   </div>"+
						             "</div>"+
						             "<p class=\"company fr\">技术支持单位：北京凯英信业科技股份有限公司&北京五边形网络技术有限责任公司</p>";
					$("#section_id").empty();
					$("#section_id").append(other_tx);*/
					$("#pinglun_div").css("display","block");
					$("#pinglun_ul").css("display","block");
					$("#pinglun_div_cj").css("display","none");
					$("#pinglun_ul_cj").css("display","none");
					$("#code_div").css("display","none");
					index_tx=0;
					flag=true;
					//自动
					timeout=true;
					show_other_news();
				}else{
					//手动
					var page_num=msg[0]["page_num"];
					if(page_num=="4"){
						//是当有子菜单
						var sub_page_flag=msg[0]["sub_page_flag"];
						if(sub_page_flag=="true"){
							//判断操作类型
							var sub_page_num=msg[0]["sub_page_num"];
							if(sub_page_num=="1"){
//								var cj_tx="<div class=\"hotspot\" style=\"position:relative;\">"+
//						                "<h1 class=\"title\" style=\"\">中国业余足球实时热点新闻<br/><p style=\"font-size:18px;\"> The Chinese amateur football real-time news</p></h1>"+ 
//						                "<div id=\"bigscreen05charts\" style=\"width:1200px;height:750px;\"></div>"+
//						                "<div class=\"message\" style=\"display:none\">"+
//						                "    <h1 class=\"title\">国足主场0-1憾负叙利亚 <em>来源：中央日报</em><span class=\"fr time\">PM13:00</span><span class=\"fr font24\">陕西-西安</span></h1>"+
//						                "    <div class=\"vd-box\">"+
//						                "        <div class=\"list_lh\">"+
//						                "            <ul>"+
//						                "                <li>"+
//										"				<p><img src=\"images/1.png\" /></p><p>飘着细雨的西安没能再显“福地”的神奇，国足在一场非胜不可的比赛中悲剧般地输给叙利亚队，从而在前三场比赛后便早早走到了被淘汰的边缘。国足在战术指挥上的失败是显而易见的，而比结果更悲剧的是过程，因为从国足毫无章法、毫无技术含量的踢法中，我们一点也看不到希望。</p><p><img src=\"images/1.png\" /></p><p>纵观12强在前三轮的整体表现，可以说，国足是12强中踢的最没有章法的一支球队，哪怕是三战皆负的卡塔尔和另一个小组的泰国队，其比赛就内容而言也都不差，能通过有序的阵地进攻时常制威.战皆负的卡塔尔和另一个小组的泰国队，其比赛就内容而言也都不差，能通过有序的阵地进攻时常制威.战皆负的卡塔尔和另一个小组的泰国队，其比赛就内容而言也都不差，能通过有序的阵地进攻时常制威.战皆负的卡塔尔和另一个小组的泰国队，其比赛就内容而言也都不差，能通过有序的阵地进攻时常制威.</p>"+
//						                "                </li>"+
//						                "                <li> 飘着细雨的西安没能再显“福地”的神奇，国足在一场非胜不可的比赛中悲剧般地输给叙利亚队，从而在前三场比赛后便早早走到了被淘汰的边缘。国足在战术指挥上的失败是显而易见的，而比结果更悲剧的是过程，因为从国足毫无章法、毫无技术含量的踢法中，我们一点也看不到希望。纵观12强在前三轮的整体表现，可以说，国足是12强中踢的最没有章法的一支球队，哪怕是三战皆负的卡塔尔和另一个小组的泰国队，其比赛就内容而言也都不差，能通过有序的阵地进攻时常制威.战皆负的卡塔尔和另一个小组的泰国队，其比赛就内容而言也都不差，能通过有序的阵地进攻时常制威.战皆负的卡塔尔和另一个小组的泰国队，其比赛就内容而言也都不差，能通过有序的阵地进攻时常制威.战皆负的卡塔尔和另一个小组的泰国队，其比赛就内容而言也都不差，能通过有序的阵地进攻时常制威.</li>"+
//						                "            </ul>"+
//						                "        </div>"+
//						                "    </div>"+
//						                " </div>"+
//							            "</div>  "+
//										"<div class=\"discuss-area\" id=\"pinglun_div\"> "+
//							            "    <h1 class=\"discuss-title\">评论区(0)</h1>"+
//							            "    <div class=\"discusslist\">"+
//							            "        <ul id=\"pinglun_ul\">"+
//							            "            <li>"+
//							            "                <div class=\"discusslist_con\">"+
//							            "                    <div class=\"discusslist_con_base clearfix\">"+
//							            "                        <div class=\"discusslist_con_base_mask\">"+
//							            "                            <img src=\"images/person.png\" >"+
//							            "                        </div>"+
//							            "                        <span>眼红的西红柿</span>"+
//							            "                        <br />"+
//							            "                        <em>2016.10.06</em><em>14:00</em>"+
//							            "                    </div>"+
//							            "                    <p class=\"discussdetail_con_comment\">中国队加油，中国队不哭，看好你们！</p>"+
//							            "                </div>"+
//							            "            </li>"+
//							            "        </ul>"+
//							            "    </div>"+
//							            "</div>"+
//										"<div class=\"code total-radius-shadow\">"+
//										"<img src=\"images/code.png\"/>"+
//										"<p style=\"text-align:center;\">蹴鞠足球官方APP</p>"+
//										"</div>"+
//										"<p class=\"company fr\">技术支持单位：北京凯英信业科技股份有限公司&北京五边形网络技术有限责任公司</p>";
//								if(index_tx==0){
//									$("#section_id").empty();
//								}
//								$("#section_id").append(cj_tx);
								clearTimeout(time_id);
								$("#pinglun_div").css("display","none");
								$("#pinglun_ul").css("display","none");
								$("#pinglun_div_cj").css("display","block");
								$("#pinglun_ul_cj").css("display","block");
//								$("#pinglun_ul_cj").html("");
								$("#code_div").css("display","block");
								
								//蹴鞠杯新闻
								flag=false;
								timeout=false;
								index_tx=1;
								show_cj_news();
							}else{
								/*var other_tx="<div class=\"hotspot\" style=\"position:relative\">"+
									"<h1 class=\"title\">中国业余足球实时热点新闻<br/><p class=\"font18\"> The Chinese amateur football real-time news</p></h1>"+
									"  <div id=\"bigscreen05charts\" style=\"width:1200px;height:900px;\"></div>"+
									"  <div class=\"message\" style=\"display:none\">"+
									"   </div>"+
									"</div>"+
									"<div class=\"comment-area\" id=\"pinglun_div\">"+
									"   <h1 class=\"comment-title\">评论区(0)</h1>"+
									"   <div class=\"conmmentlist\">"+
									"   	<ul id=\"pinglun_ul\">"+
						            "       </ul>"+
						            "   </div>"+
						            "</div>"+
						            "<p class=\"company fr\">技术支持单位：北京凯英信业科技股份有限公司&北京五边形网络技术有限责任公司</p>";
								$("#section_id").empty();
								$("#section_id").append(other_tx);*/
								$("#pinglun_div").css("display","block");
								$("#pinglun_ul").css("display","block");
								$("#pinglun_div_cj").css("display","none");
								$("#pinglun_ul_cj").css("display","none");
								$("#code_div").css("display","none");
								index_tx=0;
								flag=true;
								timeout=true;
								//其他新闻   自动
								show_other_news();
							}
						}
					}
				}
			}
		},
		error : function(){
			console.log("查询热点新闻页操作类型失败 !");
		}
	});
}



var flag=false;
var currentIndex=0;
var timeout=false; //定时器标识，用于标识是否需要定时
var time_id;//定时器id，用于清除定时器
var imgflag=false;
function time(hotNewsChart,news_obj,mainMapData){
	if(navigator.onLine){
		imgflag=true;
	}else{
		imgflag=false;
	}
	if(!timeout)return;
    oldData = mainMapData[currentIndex];
    var oldProvinceIndex = 0;
    chinaMap_05.find(function(item, i){
        if(oldData.name.indexOf(item.properties.name)>-1){
          oldProvinceIndex = i;
          return item;
        }
    });
    
    var dataLen = 5;
    // 取消之前高亮的图形
    hotNewsChart.dispatchAction({
        type: 'downplay',
        seriesIndex: 0,
        dataIndex: currentIndex
    });
    hotNewsChart.dispatchAction({
        type: 'downplay',
        seriesIndex: 1,
        dataIndex: currentIndex
    });
    hotNewsChart.dispatchAction({
        type: 'downplay',
        seriesIndex: 2,
        dataIndex: oldProvinceIndex
    });
    currentIndex = (currentIndex + 1) % dataLen;
    currentData = mainMapData[currentIndex];
    var currentProvinceIndex = 0;
    chinaMap_05.find(function(item, i){
        if(currentData.name.indexOf(item.properties.name)>-1){
          currentProvinceIndex = i;
          return item;
        }
    });
    // 高亮当前图形
    hotNewsChart.dispatchAction({
        type: 'highlight',
        seriesIndex: 0,
        dataIndex: currentIndex
    });
    hotNewsChart.dispatchAction({
        type: 'highlight',
        seriesIndex: 1,
        dataIndex: currentIndex
    });
    hotNewsChart.dispatchAction({
        type: 'highlight',
        seriesIndex: 2,
        dataIndex: currentProvinceIndex
    });
    if(news_obj){
    	var currentArr=news_obj[currentData.name];
    	if(currentArr){
    		for(var i=0;i<currentArr.length;i++){
    			if(currentArr[i]["title"]!=null && currentArr[i]["dimension"]!=null){
    				$(".message").html("");
    				var source=currentArr[i]["source"];
    				var news_time=currentArr[i]["news_time"];
    				var province=currentArr[i]["province"];
    				var city=currentArr[i]["city"];
    				var pic=currentArr[i]["pic"];
    				$(".message").html("<h1 class=\"title\">"+currentArr[i]["title"]+" <em>来源："+(source==null?"":source)+"</em><span class=\"fr time\">"+(news_time==null?"":news_time)+"</span><span class=\"fr font24\">"+((province==null?"":(province+"-"))+(city==null?"":city))+"</span></h1>"+
    						" <div class=\"vd-box\">"+
    						"<div class=\"list_lh\">"+
    						"   <ul>"+
    						"       <li><img src=\""+(pic==null?"":pic)+"\" />"+
    						currentArr[i]["dimension"]+
    						"       </li>"+
    						"   </ul>"+
    						"  </div> "+
    				" </div>");
    				
    				
    				newsId=currentArr[i]["news_ID"];
    				if(newsId!=null){
    					$.ajax({
    						url : 'page/displayController/getCommentByNewsId',
    						type : 'post',
    						dataType : 'json',
    						async : false,
    						data : {
    							newsId : newsId
    						},
    						success : function(msg){
    							if(msg){
    								$(".comment-title").html("评论区("+msg.length+")");
    								var tx="";
    								for(var i=0;i<msg.length;i++){
    									var user_name=msg[i]["user_name"];
    									var time=msg[i]["time"];
    									var comment_diension=msg[i]["comment_diension"];
    									var pic=msg[i]["pic"];
    									console.log("pic:"+pic);
    									var reply=msg[i]["reply"];
    									var reply_name="";
    									var tx_1="";
    									if(reply==1){
    										reply_name=msg[i]["reply_name"];
    										tx_1="<span class=\"reply\">回复"+reply_name+":</span>";
    									}
    									
    									var img_url;
    									if(imgflag){
    										img_url=pic;
    									}else{
    										var img_id=Math.floor(Math.random()*60);
        									img_url="images/comment_img/"+img_id+".jpg";
    									}
    									tx+="<li>"+
    										"<div class=\"commentlist_con\">"+
    										"   <div class=\"commentlist_con_base clearfix\">"+
    										"       <div class=\"commentlist_con_base_mask\">"+//javascript:this.src='"+img_url+"'
//    										(pic==null?"":pic)+
    										"<img src='"+img_url+"'>"+
    										"       </div>"+
    										"      <p style=\"display:inline-block;\"><span>"+(user_name==null?"":user_name)+"</span></p>"+
    										"       <br />"+
    										"      <em>"+(time==null?"":time)+"</em>"+
    										"   </div>"+
    										"   <p class=\"commentdetail_con_comment\">"+tx_1+(comment_diension==null?"":comment_diension)+"</p>"+
    										" </div>"+
    										" </li>";
    								}
    								
    								$("#pinglun_ul").html("");
    								$("#pinglun_ul").html(tx);
    							}
    						},
    						error : function(){
    							console.log("根据新闻id查询所有评论信息失败");
    						}
    					});
    				}
    			}
    			if(flag){
    	        	$(".message").fadeIn(2500, function(){
    	        		if($(".list_lh li").height() > $(".message").height()){
    	        			$('.message').myScroll({
    		                 speed: 2, //数值越大，速度越慢
    		                 rowHeight: $(".list_lh li").height() //li的高度
    	        			});
    	        			}
    	        	}).delay(5000).fadeOut(2500)
    	        }
    		}
    	}
    }
	time_id=setTimeout("time("+hotNewsChart+","+news_obj+","+mainMapData+")",15000);
}

function imgFound(obj){
	var img_id=Math.floor(Math.random()*60);
	var img_url="images/comment_img/"+img_id+".jpg";
	console.log("img_url:"+img_url);
	obj.src=img_url;
	obj.onerror=null;
}

//其他新闻   	 自动 
function show_other_news(){
	var dataList=[];
	var mainMapData=[];
	var news_obj={}
	$.ajax({
		url : 'page/displayController/getNewsBaseInfo',
		type : 'post',
		dataType : 'json',
		async : false,
		data : {
			"type" : "1",
			"pageIndex" : "0",
			"pageSize" : "1000",
		},
		success : function(msg){
			if(msg){
				dataList=msg;
				var _province=[];
				for(var i=0;i<msg.length;i++){
					var newsId = msg[i]["news_ID"];
					var province = msg[i]["province"];
					var title = msg[i]["title"];
					var dimension = msg[i]["dimension"];
					if(title !=null && dimension!=null){
						if(_province.indexOf(province)==-1){
							_province.push(province);
							var provinces={
									"name" : province
							};
							mainMapData.push(provinces);
							var arr=[];
							arr.push(msg[i]);
							news_obj[province]=arr;
						}else{
							news_obj[province].push(msg[i]);
						}
					}
				}
			}
		},
		error : function(){
			console.log("查询各省市新闻失败!");
		}
	});
	
	var hotNewsChart = echarts.init(document.getElementById('bigscreen05charts'));
	//初始化地图
    init_map(chinaMap_05,hotNewsChart,mainMapData);

    animation.currentIndex = 0;
    
    	time(hotNewsChart,news_obj,mainMapData);
    	
    	$("#btn").on('click', function() {
        console.log(option.series[0].geoCoord);
        console.log(option.series[0].markPoint.data);
        console.log(option.series[0].markPoint.data[0]);
        console.log(option.series[0].markPoint.data.length);
        var mapData = [{
            "name": "大连",
            "value": 1
        }];

        //更改不闪动数据
        option.series[0].markPoint.data = mapData;
        //更改闪动数据
        option.series[1].markPoint.data = mapData;

    });
}

function init_map(chinaMap_05,hotNewsChart,mainMapData){
    mainMapData_1 = [
      {
         name: "黑龙江"
      },
      {
         name: "辽宁"
      },
      {
         name: "内蒙古"
      },
      {
         name: "上海"
      },
      {
         name: "四川"
      },
      {
         name: "甘肃"
      },
      {
         name: "广东"
      },
      {
         name: "湖南"
      },
      {
         name: "西藏"
      },
      {
         name: "青海"
      },
      {
         name: "云南"
      },
      {
    	 name: "广西"
      },
      {
         name: "贵州"
      },
      {
    	 name: "重庆"
      },
      {
    	 name: "河北"
      },
      {
    	 name: "山西"
      },
      {
         name: "河南"
	  },
	  {
	     name: "山东"
	  },
	  {
	     name: "安徽"
	  },
      {
         name: "浙江"
      },
      {
    	 name: "湖北"
      },
      {
         name: "陕西"
      },
      {
         name: "福建"
      }
    ];
    
    mainMapData=mainMapData.concat(mainMapData_1);
    
    
    
    var convertData = function(chinaMap_05,hotNewsChart,dataList) {
        var res = [];
        for (var i = 0; i < dataList.length; i++) {
            var data = dataList[i];
            var province = chinaMap_05.find(function(item, i){
                if(data.name.indexOf(item.properties.name)>-1){
                  return item;
                }
            });
            res.push({
              name: data.name,
              value: province.properties.cp
            })
        }
        return res;
    };
    mainMapData = convertData(chinaMap_05,hotNewsChart,mainMapData);
    option = {
        tooltip: {
            trigger: 'item'
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
                    areaColor: '#eaeaea',
                    borderColor: '#111'
                },
                emphasis: {
                    areaColor: '#159917'
                }
            }
        },
        series: [{
            name: 'pm2.5',
            type: 'scatter',
            coordinateSystem: 'geo',
            data: mainMapData,
            symbol: "pin",
            symbolSize: function(val) {
                return 50;
            },
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: false
                },
                emphasis: {

                }
            },
            itemStyle: {
                normal: {
                    opacity: 0,
                    color: '#10d297'
                },
                emphasis:{
                  color:'#f0f',
                  opacity: 1,
                }
            }
        }, {
            name: 'Top 5',
            type: 'effectScatter',
            coordinateSystem: 'geo',
            data: mainMapData,
            symbolSize: 10,
            showEffectOn: 'emphasis',
            rippleEffect: {
                scale: 10,
                brushType: 'fill'
            },
            hoverAnimation: true,
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    show:false
                },
                emphasis:{
                  show:false,
                  color: '#10d297',
                  shadowBlur: 10,
                  shadowColor: '#333'
                }
            },
            zlevel: 1
        },{
                name: '赛事数量',
                type: 'map',
                mapType: 'china',
                roam: false,
                itemStyle:{
                    normal:{
                    show:false,
                        label:{
                            show:false,
                            textStyle: {
                                color: "rgb(249, 249, 249)"
                            }
                        }
                    },
                    emphasis:{
                    color: '#10d297',
                    shadowBlur: 10,
                    shadowColor: '#333',
                    	areaColor:'#4ee7b7', //高亮颜色
                    	label:{show:false}
                    }
                }
            }]
    };
    hotNewsChart.setOption(option);
	
}



var cjdataList=[];
//蹴鞠杯新闻
function show_cj_news(){
	var provinceList=[];
	var provinceArr=[];
	var newmsg=[];
	$.ajax({
		url : 'page/displayController/getNewsBaseInfo',
		type : 'post',
		dataType : 'json',
		async : false,
		data : {
			"type" : "0",
			"pageIndex" : "0",
			"pageSize" : "1000",
		},
		async : false,
		success : function(msg){
			if(msg){
				newmsg=msg;
				for(var i=0;i<msg.length;i++){
					inewsId=msg[i]["news_ID"];
					iquantity=msg[i]["quantity"];
					province=msg[i]["province"];
					if(provinceArr.indexOf(province)==-1){
						provinceArr.push(province);
						//将有新闻的省市放到数组中，用于初始化地图
						var provinces={
								"name" : province
						};
						provinceList.push(provinces);
					}
				}
				mainMapData=provinceList;
//				cjdataList=msg;
			}
		}
	});
	
	var hotNewsChart = echarts.init(document.getElementById('bigscreen05charts'));
	//初始化地图
    init_map(chinaMap_05,hotNewsChart,mainMapData);
	
    changecomment(newmsg,hotNewsChart);
    cjdataList=newmsg;
}
var oldProvice="";
var oldNewsId="";
//判断是否有新评论
function changecomment(newmsg,hotNewsChart){
	if(cjdataList){
		for(var j=0;j<cjdataList.length;j++){
			jnewsId=cjdataList[j]["news_ID"];
			jquantity=cjdataList[j]["quantity"];
			if(newmsg){
				for(var i=0;i<newmsg.length;i++){
					inewsId=newmsg[i]["news_ID"];
					iquantity=newmsg[i]["quantity"];
					province=newmsg[i]["province"];
					if(jnewsId==inewsId){
						if(jquantity!=iquantity){
							//当蹴鞠新闻评论有变化时展示新评论
							if(jnewsId!=""){
								if(oldNewsId==jnewsId){
									//同一个新闻
									getComment(jnewsId);
								}else{
									showcjcomment(jnewsId,province,hotNewsChart);
									oldProvice=province;
									oldNewsId=jnewsId;
								}
							}else{
								showcjcomment(jnewsId,province,hotNewsChart);
								oldProvice=province;
								oldNewsId=jnewsId;
							}
						}
					}
				}
			}
		}
	}
}


//展示蹴鞠新闻新评论
function showcjcomment(newsId,province,hotNewsChart){
	 var animation = {}
	 animation.currentIndex = 0;
	 oldData = mainMapData[animation.currentIndex];
     var oldProvinceIndex = 0;
     chinaMap_05.find(function(item, i){
         if(oldData.name == item.properties.name){
           oldProvinceIndex = i;
           return item;
         }
     });
     
     if(oldProvice!=province){
    	 var dataLen = 5;
         // 取消之前高亮的图形
         hotNewsChart.dispatchAction({
             type: 'downplay',
             seriesIndex: 0,
             dataIndex: animation.currentIndex
         });
         hotNewsChart.dispatchAction({
             type: 'downplay',
             seriesIndex: 1,
             dataIndex: animation.currentIndex
         });
         hotNewsChart.dispatchAction({
             type: 'downplay',
             seriesIndex: 2,
             dataIndex: oldProvinceIndex
         });
         
         var currentIndex=0;
         mainMapData.find(function(item, i){
             if(item.name.indexOf(province)>-1){
            	 currentIndex = i;
            	 return item;
             }
         });
         
         animation.currentIndex = (currentIndex) % dataLen;
         currentData = mainMapData[animation.currentIndex];
         var currentProvinceIndex = 0;
         chinaMap_05.find(function(item, i){
             if(currentData.name == item.properties.name){
               currentProvinceIndex = i;
               return item;
             }
         });
         // 高亮当前图形
         hotNewsChart.dispatchAction({
             type: 'highlight',
             seriesIndex: 0,
             dataIndex: animation.currentIndex
         });
         hotNewsChart.dispatchAction({
             type: 'highlight',
             seriesIndex: 1,
             dataIndex: animation.currentIndex
         });
         hotNewsChart.dispatchAction({
             type: 'highlight',
             seriesIndex: 2,
             dataIndex: currentProvinceIndex
         });
     }
     
     //根据新闻id查询评论详情
     $.ajax({
 		url : 'page/displayController/getNewsDetail',
 		type : 'post',
 		dataType : 'json',
 		data : {
 			newsId : newsId,
 			type : "0"
 		},
 		async : false,
 		success : function(msg){
 			if(msg){
 				
 				for(var i=0;i<msg.length;i++){
 					$(".message").html("");
 					var content=msg[i]["FM_NEWS_CONTENT"];
 					if(content!=null){
 						content=$(content);
 						content.children("img").addClass("");
 					}else{
 						content="";
 					}
 					var tx="";
 					tx="<div class='getHeight'><h1 class=\"title\">"+(msg[i]["FM_NEWS_TITLE"]==null?"":msg[i]["FM_NEWS_TITLE"])+" <em>来源：中央日报</em><span class=\"fr time\">"+(msg[i]["FM_PUBLISH_DATE"]==null?"":msg[i]["FM_PUBLISH_DATE"])+"</span><span class=\"fr font24\">"+(msg[i]["_fm_news__FM_NEWS_AREA_"]==null?"":msg[i]["_fm_news__FM_NEWS_AREA_"])+"</span></h1>"+
 						" <div class=\"vd-box\">"+
 						"     <div class=\"list_lh\">"+
 						"       <ul>"+
 						"           <li>"+
 						msg[i]["FM_NEWS_CONTENT"]+
 						"           </li>"+
 						"</ul>"+
                        "</div> "+
                        "</div></div>";
                        tx = $(tx);
 					$(".message").html(tx);
 					$.each(tx.find("img"), function(i, item){
 						console.log(item);
 						$(item).attr('display', 'block');
 						$(item).parent().css('height', item.height);
 					});
 					if($(".message").css("display") != "none"){
 						if($(".list_lh li").height() > $(".message").height()){
 				    		 $('.message').myScroll({
 				                 speed: 2, //数值越大，速度越慢
 				                 rowHeight: $(".list_lh li").height() //li的高度
 				             });
 				    	 }
 					}
 					getComment(newsId);
 				}
 			}
 		},
 		error : function(){
 			console.log("查询蹴鞠新闻详情失败");
 		}
 	});
     $(".message").fadeIn(5000, function(){
    	 if($(".list_lh li").height() > $(".message").height()){
    		 $('.message').myScroll({
                 speed: 2, //数值越大，速度越慢
                 rowHeight: $(".list_lh li").height() //li的高度
             });
    	 }
     }).delay(5000);
}

function getComment(newsId){
		if(newsId){
			$.ajax({
				url : 'page/displayController/getCommentByNewsId',
				type : 'post',
				dataType : 'json',
				data : {
					newsId : newsId
				},
				async : false,
				success : function(msg){
					if(msg){
						$(".discuss-title").html("评论区("+msg.length+")");
						var tx="";
						for(var i=0;i<msg.length;i++){
							var user_name=msg[i]["user_name"];
							var time=msg[i]["time"];
							var comment_diension=msg[i]["comment_diension"];
							var pic=msg[i]["pic"];
							tx+="<li>"+
		                        "<div class=\"discusslist_con \">"+
		                        "   <div class=\"discusslist_con_base clearfix\">"+
		                        "        <div class=\"discusslist_con_base_mask\">"+
		                        (pic==null?"":"<img src=\""+pic+"\"></img>")+
		                        "        </div>"+
		                        "        <span>"+(user_name==null?"":user_name)+"</span>"+
		                        "        <br />"+
		                        "        <em>"+(time==null?"":time)+"</em>"+
		                        "    </div>"+
		                        "    <p class=\"discussdetail_con_comment\">"+(comment_diension==null?"":comment_diension)+"</p>"+
		                        "</div>"+
	                        "</li>";
						}
//						$("#pinglun_ul_cj").html("");
						$("#pinglun_ul_cj").html(tx);
					}
				},
				error : function(){
					console.log("根据新闻id查询新闻所有评论信息失败");
				}
			});
		}
}

