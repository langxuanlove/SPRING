/**
 * Created by admin on 2016/10/8.
 */
$(function(){
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    var xAxisData = [];
    var data = [['1','8','3','4','5','6','7','10','4','2','4','3','9','7'],['7','6','5','4','3','2','1'],['6','5','4','3','2','1','7'],['5','4','3','2','1','6','7']];   //数据
    var date = [['北京','上海','深圳','天津','河南','河北','四川','重庆','云南','成都','宁夏','湖北','湖南','山东'],['北京','上海','深圳','天津','河南','河北','四川','重庆','云南','成都','宁夏','湖北','湖南','山东'],['大连','青岛','山西','西安','重庆','广州','深圳','重庆','云南','成都','宁夏','湖北','湖南','山东'],['北京','上海','深圳','天津','河南','河北','四川','重庆','云南','成都','宁夏','湖北','湖南','山东']];// 地区
    var q = 0;

    option = {
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        title: {},
        legend: {},
        toolbox: {
            /*feature: {
             dataZoom: {
             yAxisIndex: 'none'
             },
             restore: {},
             saveAsImage: {}
             }*/
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
            data: date[q],
            axisLabel:{
                interval:0,
                margin:2,
                textStyle:{
                    fontSize:12,
                    color:"#546d7d"
                }
            },
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
            /*start: 0,
             end: 10*/
        }, /*{
         start: 0,
         end: 10,
         handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
         handleSize: '80%',
         handleStyle: {
         color: '#fff',
         shadowBlur: 3,
         shadowColor: 'rgba(0, 0, 0, 0.6)',
         shadowOffsetX: 2,
         shadowOffsetY: 2
         }
         }*/],
        series: [
            {
                name:'模拟数据',
                type:'line',
                smooth:true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: 'rgb(255, 70, 131)'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(255, 158, 68)'
                        }, {
                            offset: 1,
                            color: 'rgb(255, 70, 131)'
                        }])
                    }
                },
                data: data[q]
            }
        ]
    };
    myChart.setOption(option);
    $(document).ready(function(){
        $('dt:first').addClass('active');
        $('dd:first').css('display','block');
        autoroll();
        hookThumb();
    });
    var i=-1; //第i+1个tab开始
    var offset = 5000; //轮换时间
    var timer = null;
    var colorArr = {
        0 : "#72d5f0",
        1 : "#3ae5b6",
        2 : "#ffb75b",
        3 : "#c99dff"
    };
    function autoroll(){
        n = $('dt').length-1;
        i++;
        if(i > n){
            i = 0;
        }
        slide(i);
        q = i;	//取索引
        //更改option参数
        option.series[0].data = data[q];
        option.xAxis.data = date[q];
        option.series[0].itemStyle.normal.color = colorArr[q];
        option.series[0].areaStyle.normal.color = colorArr[q];
        //重新加载图表
        myChart.setOption(option);
        timer = window.setTimeout(autoroll, offset);
    }

    function slide(i){
        $('dt').eq(i).addClass('active').siblings().removeClass('active');
        $('dd').eq(i).css('display','block').siblings('dd').css('display','none');
        $('dt').eq(i).css({'border-bottom':'5px solid'+ colorArr[i],'color':colorArr[i]}).siblings('dt').css({'color':'#ccc','border-bottom':'none'});
    }
    function hookThumb(){
        $('dt').hover(
            function () {
                if (timer) {
                    clearTimeout(timer);
                    var i = $(this).prevAll().length;
                    slide(i);
                }

                q = $(this).index();	//取索引
                //更改option参数
                option.series[0].data = data[q];
                option.xAxis.data = date[q];
                option.series[0].itemStyle.normal.color = colorArr[q];
                option.series[0].areaStyle.normal.color = colorArr[q];
                //重新加载图表
                myChart.setOption(option);
            },
            function () {
                timer = window.setTimeout(autoroll, offset);
                this.blur();
                return false;
            });
    }
});