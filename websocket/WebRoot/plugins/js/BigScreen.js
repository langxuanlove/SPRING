var chinaMap = echarts.getMap("china");
var mainMapData;
$(function(){
/**
 * 俱乐部排行
 */
	(function(){
		$.ajax({
			url:'page/displayController/getClubRank',
			type:'post',
			data:{
				pageIndex:0,
				pageSize:99

			},
			dataType:'json',
			success:function(data){
				//console.log(data);
				$('#toplist').empty();
				var _dataCS=[];// 中超
				var _dayaCA=[];// 中甲
				for(var i=0;i<data.length;i++){
					if(data[i].asso_name=="中超俱乐部"){
						var _numCS=data[i].name.indexOf("俱乐部");
						var _nameCS=data[i].name.substring(0,_numCS+3);
						_dataCS.push(_nameCS);
					}
					if(data[i].asso_name=="中甲俱乐部"){
						var _numCA=data[i].name.indexOf("俱乐部");
						var _nameCA=data[i].name.substring(0,_numCA+3);
						_dayaCA.push(_nameCA);
					}
				};

				var _num=0;
				$.each(_dataCS,function(i,_each){	
					if(_num<20){
						_num++;
					var _liCS='<li><span class="number">'+_num+'</span>'+
							'<img src="images/logo1.jpg" alt=""/>'+
							'<p>'+_each+'</p>';
					$('#toplist').append(_liCS);
					}
				});
				$.each(_dayaCA,function(i,_each){
					if(_num<20){
					_num++;
					var _liCA='<li><span class="number">'+_num+'</span>'+
							'<img src="images/logo1.jpg" alt=""/>'+
							'<p>'+_each+'</p>';
					$('#toplist').append(_liCA);
					}
				});				
				/* 俱乐部排名列表（上下） */
				var numCount=$("#toplist li").length;	
			    $("#toplist").rollGallery({
			        direction:"top",
			        speed:2000,
			        noStep:false,
			        rollNum:1,
			        showNum:numCount
			    });
			}
			
			
		});
	})();
	
/**
 * 中超裁判
 */	
	(function(){
		$.ajax({
			url:'page/displayController/getRefereeInfo',
			type:'post',
			data:{
				pageIndex:0,
				pageSize:99

			},
			dataType:'json',
			success:function(data){
// console.log(data);
				$('#slides-cp').empty();
				$.each(data,function(i,_each){
					if(_each.name!=undefined){
					var _liReferee='<li><img src="images/judge1.jpg" alt="裁判" width="90" height="90"/>'+
									'<p>'+_each.name+'<i>'+_each.level+'</i></p></li>';
					$('#slides-cp').append(_liReferee);
					}
					
				});
				/* 中超裁判轮播（左右） */
			    $('#judge-list').flexslider({
			        animation: "slide",
			        direction:"horizontal",
			        easing:"swing"
			    });
			},	
		});	
	})();
	
/**
 * 职业足球俱乐部信息
 */	
	(function(){
		$.ajax({
			url:'page/displayController/getChinaInfo',
			type:'post',
			data:{
				pageIndex:0,
				pageSize:99

			},
			dataType:'json',
			success:function(data){
// console.log(data);
				$('.i-szpm').text(data[0].lists);
				$('.i-jlb').text(data[0].clubTotal);
				$('.i-cfzs').text(data[0].punishTotal);
				$('.i-szrs').text(data[0].list_num);				
			},
		});
	})();
	
/**
 * 世界上座排行
 */	
	(function(){
		$.ajax({
			url:'page/displayController/getWorldRankInfo',
			type:'post',
			data:{
				pageIndex:0,
				pageSize:99

			},
			dataType:'json',
			success:function(data){
// console.log(data);
				 data.sort(function(a,b){return b.list_num-a.list_num});
				var _denominator=data[0].list_num;				
				  for(var i=0;i<5;i++){
					$('.no-'+i).children('.skillbar-title').find('span').text(data[i].country);
					$('.no-'+i).children('.skillbar').data('percent',Math.floor(data[i].list_num*100/_denominator)+'%');
					$('.no-'+i).children('.skill-bar-percent').text(data[i].list_num);
					
				}
				/* 进度条 */
			    $('.skillbar').each(function(){
// console.log($(this).data('percent'))
			        $(this).find('.skillbar-bar').animate({
			            width:$(this).data('percent')
			        },3000);
			    });
				
				
			},
			
		});
		
	})();
	
/**
 * 中国职业足球体系土壤指标、K值对比、当地体系指标值
 */	
	(function(){
		
		$.ajax({
			url:'page/displayController/getRegionSoilBased',
			type:'post',
			data:{
				pageIndex:0,
				pageSize:99

			},
			dataType:'json',
			success:function(data){
//				console.log(data);
			/*
			 * K值对比
			 */
				var _data_K=[];
				var _data_P=[];
				$.each(data,function(i,_each){
					_data_K.push(_each.K);
					_namek=_each.province.substring(0,2);
					switch (_namek)
					{
					case "内蒙":
						_namek="内蒙古"
						break;
					case "黑龙":
						_namek="黑龙江"
						break;
					default:
						break;
					};
					_data_P.push(_namek);	
				});
				KContrast(_data_K,_data_P);// K值对比
			
			/*
			 * 2016中国职业足球体系土壤指标
			 */	
				var _data_sys = "";
				_data_sys += "["
				$.each(data,function(i,_each){
					_nameP=_each.province.substring(0,2);
					switch (_nameP)
					{
					case "内蒙":
						_nameP="内蒙古"
						break;
					case "黑龙":
						_nameP="黑龙江"
						break;
					default:
						break;
					};
					_data_sys += '{"name":"'+_nameP+'","value":"'+Math.round(_each.K)+'","A":"'+_each.A+'","B":"'+_each.B+'","C":"'+_each.C+'","T":"'+_each.T+'","E":"'+_each.E+'","F":"'+_each.F+'"},';
					
				});
				_data_sys = _data_sys.substring(0, _data_sys.length-1);
				_data_sys += "]"
				_data_sys=JSON.parse(_data_sys)
				mainMapData = _data_sys;
//				console.info(_data_sys);
				SysIndex(_data_sys);
				
			},		
		});
	
	})();

/**
 * 中超射手榜
 */	
	(function(){
		$.ajax({
			url:'page/displayController/getProPlayerInfo',
			type:'post',
			data:{
				pageIndex:0,
				pageSize:99

			},
			dataType:'json',
			success:function(data){
				data.sort(function(a,b){return b.point-a.point});
//				console.log(data);
				var _ssPiont=[];
				var _ssName=[];
				$.each(data,function(i,_each){
					if(i<7){
					_ssPiont.push(_each.point);
					_ssName.push(_each.name);	
					};
				});
//				console.info(_ssPiont);
//				console.info(_ssName);
				supershooter(_ssPiont,_ssName);
				
				},
			});
		
		
	})();
	
	
});

/**
 * 2016中国职业足球体系土壤指标
 */	
	function SysIndex(arrSys){
	    var _data=arrSys;
	    var Syschart = echarts.init(document.getElementById('map-wrapper'));
	    var Sysoption = {
	        title : {
	            text: '2016中国职业足球体系土壤指标',
	            subtext: 'China professional soccer system edaphic indicator in 2016',
	            x:'left',
	            textStyle:{
	                color:'#02cc89',//字体颜色
	                fontSize:32,//字体大小
	                fontWeight:'bolder'
	            },
	            subtextStyle:{
	            	color:'#02cc89',
	            	fontSize:16
	            }

	        },
	        tooltip : {
	         trigger: 'item'
	         },
	        dataRange: {
	            x: 'left',
	            y: 'bottom',
	            splitList: [
	                {start: 35, label: '职业体系指标 50000-100000',color: '#00cc89'},
	                {start: 31, end: 35, label: '职业体系指标  1000-5000',color: '#02dd96'},
	                {start: 26, end: 30, label: '职业体系指标  500-1000',color: '#56e689'},
	                {start: 21, end: 25, label: '职业体系指标  100-500',color: '#94f6b6'},
	                {end: 20,label: '职业体系指标  0',color: '#e5e5e5'}
	            ],
	            textStyle:{
	            	fontSize:14,
	            	color:'#6e7a89'
	            }
	        },
	        roamController: {
	            show: true,
	            x: 'right',
	            mapTypeControl: {
	                'china': true
	            }
	        },
	        series : [
	            {
	                name: '赛事数量',
	                type: 'map',
	                mapType: 'china',
	                roam: false,
	                center: [91, 40],
	                width:560,
	                height:440,
	                top:-35,
	                itemStyle:{
	                    normal:{
	                        label:{
	                            show:false,
	                            textStyle: {
	                                color: "rgb(249, 249, 249)"
	                            }
	                        }
	                    },
	                    emphasis:{
	                    	areaColor:'#4ee7b7', //高亮颜色
	                    	label:{show:false}
	                    }
	                },
	                data:_data
	            }
	        ]
	    };
	    var currentIndex=0;
	    
	    var timeTicket = setInterval(function () {
	        var dataLen = Sysoption.series[0].data.length;
//	            console.info(dataLen);
	        // 取消之前点亮的图形
	        Syschart.dispatchAction({
	            type: 'downplay',
	            seriesIndex: 0,
	            dataIndex: currentIndex
	        });
	        currentIndex = (currentIndex + 1) % dataLen;
	        // 点亮当前图形
	        Syschart.dispatchAction({
	            type: 'highlight',
	            seriesIndex: 0,
	            dataIndex: currentIndex,
	            selected: LocalIndex(_data,currentIndex)
     
	        }); 
	        
	        
	    },1000);
	    Syschart.setOption(Sysoption);
	    Syschart.on("highlight", function(param){
	    	var index =  param.dataIndex;
	    	var selectMap = mainMapData[index];
	    	features = chinaMap.geoJson.features;
	    	province = features.find(function(item){
	    		if(item.properties.name == selectMap.name){
	    			return item;
	    		}
	    	});
	    	var xy = province.properties.cp;
//	    	console.log(province.properties.name)
	    	var map = Syschart.getModel().getSeriesByIndex(0);
	    	xy = map.coordinateSystem.dataToPoint(xy);
	    	var myCanvas = document.getElementById("canvasForLine");
    	   var context = myCanvas.getContext("2d");
    	   
    	   context.clearRect(0, 0, 1000, 1000);//清空画布
    	   
    	   context.strokeStyle ='rgba(255,255,255,.3)';//设置连线颜色
    	   //画大端点
    	   context.beginPath();
    	   context.arc(xy[0], xy[1], 22, 0, 360, false);
    	   context.fillStyle ='rgba(255,255,255,.3)';
    	   context.fill();
    	   context.closePath();
    	   
    	   //画小端点
    	   context.beginPath();
    	   context.arc(xy[0], xy[1], 14, 0, 360, false);
    	   context.fillStyle ='rgba(255,255,255,.4)';
    	   context.fill();
    	   context.closePath();
    	   
    	   //画折线
    	   context.beginPath();
    	   context.lineWidth = 5;//设置线宽
    	   context.strokeStyle ='rgb(0,203,138)';//设置连线颜色
    	   context.moveTo(xy[0] + 11, xy[1]);
    	   context.lineTo(800, xy[1]);
    	   context.moveTo(800 - 2, xy[1]);
    	   context.lineTo(858, 100);
    	   context.closePath();//可以把这句注释掉再运行比较下不同
    	   context.stroke();//画线框
    	   context.fill();//填充颜色
    	   
	    });
	    Syschart.dispatchAction({
            type: 'highlight',
            seriesIndex: 0,
            dataIndex: 0,
            selected: LocalIndex(_data,currentIndex)
 
        }); 
//	    console.log(Syschart)
};
	
/**
 * K值对比
 */	
	function KContrast(arr_k,arr_p){
	    var KChart = echarts.init(document.getElementById("container"));
	    var app = {};
	    var xAxisData = [];
	    var data_K = arr_k; // 数据
	    var data_P = arr_p;	// 地区
	    var Koption = {
            tooltip: {
                trigger: 'axis',
                position: function (pt) {
                    return [pt[0], '10%'];
                }
            },
            grid: {
                left: '2%',
                right: '2%',
                top:'5%',
                bottom: '20%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: data_P,
                axisLabel:{
                	margin: 5,
                    interval:0,
                    formatter:function(val){  
                        return val.split("").join("\n");  
                      },
                    textStyle:{
                        fontSize:12,
                        color:"#546d7d"
                    }
                },
                axisTick:{
                    show:false  
                  }
            },
            yAxis: {
                type: 'value',
                boundaryGap: [0, '100%'],
                axisLabel:{
                    interval:0,
                    margin:2,
                    textStyle:{
                        fontSize:12,
                        color:"#546d7d"
                    }
                },
                axisLine:{
                    lineStyle:{
                        opacity:0
                    }
                },
                axisTick:{
                    lineStyle:{
                        opacity:0
                    }
                }
            },
            dataZoom: [{
                type: 'inside',
            }],
            series: [
                {
                    name:'各地区综合指标值 ',
                    type:'line',
                    smooth:true,
                    symbol: 'none',
                    sampling: 'average',
                    itemStyle: {
                        normal: {
                            color: 'rgba(202,241,217,0.1)',
                            show:false,
                            shadowColor: 'rgba(11,3,6, 1)',
                            shadowBlur: 10,
                            
                        }
                    },
                    /*itemStyle: {
                        normal: {
                            color: 'rgba(202,241,217,1)',
                            show:false,
                            type:'solid',
                            shadowColor: 'rgba(11,3,6, 1)',
                              shadowBlur:1,
                              shadowOffsetY:-10,
                                            
                      }},*/
                      /*lineStyle: {normal: {
                          color:'rgba(202,241,217,1)',
                          width:3,
                          type:'solid',
                            shadowColor: 'rgba(11,3,6, 0.5)',
                              shadowBlur: 1,
                              shadowOffsetX:10,
                              shadowOffsetY:-100,
                              opacity:1                                                  
                      }},*/
                    lineStyle: {
                    	normal: {                    
                    		color:'rgba(149,245,183,0.8)',
                    		width:1,
                    		type:'solid',
                    		shadowColor: 'rgba(0, 0, 0,1)',
                            	shadowBlur: 15,

                            	shadowOffsetY:-2,
                            	opacity:1
                    	}
                    },

                      areaStyle: {
                          normal: {
                              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                  offset: 0,
                                  color: 'rgba(149,245,183,1)'
                              }, {
                                  offset: 1,
                                  color: 'rgba(3,220,151,1)'
                              }]),
                          }
                      },

                    data: data_K
                }
            ]
        };
        KChart.setOption(Koption);
	};

/**
 * 当地体系指标值
 */
	function LocalIndex(Local,num){
		var Local_A=Local[num].A,
			Local_C=Local[num].C,
			Local_T=Local[num].T,
			Local_F=Local[num].F,
			Local_K=Local[num].value,
			Local_N=Local[num].name
		var chartData = [
            {value:Local_T, name:'其他'},
            {value:Local_C, name:'顶级裁判'},
            {value:Local_F, name:'名师分布'},
            {value:Local_A, name:'俱乐部'},
        ];
//		console.log(chartData)
		var chartNewData = [];
		for(var index in chartData){
			var item = chartData[index];
			if(item.value != 0){
				chartNewData.push(item);
			}
		}
		if(0 == chartNewData.length){
			chartNewData.push({
				name: "无",
				value: 1
			});
		}
		var LocalChart = echarts.init(document.getElementById('pie-chart'));
		Localoption = {
            title: {
                text:Local_N,
                x: 'center',
                y: 'center',       
                textStyle:{
                    color:"#ffffff",
                    fontSize:26,
                    fontWeight:'normal'
                },
               
                top:130
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)",
            },
            color:['#03dc97','#95f5b7','#ffffff',"#00cb8a"],
            textStyle:{
                color:"#ffffff"
            },
            series: [
                {
                    name:'访问来源',
                    type:'pie',
                    radius: ['35%', '50%'],
                    /*itemStyle : {
                        normal : {
                            label : {
                                position : 'inner',
                                formatter : '{b} \n {d}%',
                                	function (params){
                                				if(params.percent!=0){
                                					
                                					return (params.percent - 0) + '%'+params.name;
                                				}
                                			},
                                textStyle:{
                                    color:"#000",
                                    fontSize:10,
                                },
                            },

                        }
                    },*/
                    data:chartNewData,
                    rotate:-90
                },
            ]
        };
        LocalChart.setOption(Localoption);
//        console.log(Local_K);
        $('.area-info').children('.pie-counts').find('i').text(Local_K);
	};
/**
 * 中超射手榜
 */	
	function supershooter(_piont,_name){
		// 基于准备好的dom，初始化echarts实例
	    var ssChart = echarts.init(document.getElementById('punishGraphic'));
	    // 指定图表的配置项和数据
	    var SSoption = {
	        tooltip: {
	            trigger: 'axis'
	        },
	        legend: {},
	        grid: {
	        	left: '0',
	            right: '3%',
	            bottom: '3%',
	            containLabel: true
	        },
	        toolbox: {},
	        xAxis: {
	            type: 'category',
	            boundaryGap: false,
	            data: _name,
	            axisLine:{
	                show:false,
	            },
	            axisLabel:{
	                textStyle:{
	                    color:'#fff',
	                    fontSize:5
	                },
	                formatter:function(val){  
                        return val.split("").join("\n");  
                      },
	            },
	            axisTick:{
	                show:true,
	                lineStyle:{
	                    opacity:0
	                },
	            },
	            splitLine:{
	                interval:0
	            }
	        },
	        yAxis: {
	            type: 'value',
	            axisLine:{
	                show:false,
	            },
	            nameTextStyle:{
	                color:"#fff",
	            },
	            axisLabel:{
	                textStyle:{
	                    color:'#fff',
	                    fontSize:10,
	                },
	            },
	            axisTick:{
	                show:true,
	                lineStyle:{
	                    opacity:0
	                },
	            }
	        },
	        series: [
	            {
	                name:'',
	                type:'line',
	                stack: '',
	                data:_piont,
	                itemStyle : {
	                    normal : {
	                        lineStyle:{
	                            color:'#ffffff'
	                        },
	                        textStyle:{
	                            color:"#ffffff"
	                        },
	                        opacity:1,
	                        borderColor:"#ffffff"
	                    }
	                },
	            }
	        ]
	    };
	    // 使用刚指定的配置项和数据显示图表。
	    ssChart.setOption(SSoption);
		
		
	};
