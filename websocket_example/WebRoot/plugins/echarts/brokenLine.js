/**
 * Created by admin on 2016/10/8.
 */
$(function(){
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('punishGraphic'));

    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {},
        grid: {
            /* left: '3%',
             right: '4%',*/
            bottom: '3%',
            containLabel: true
        },
        toolbox: {},
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['新疆维吾尔','甘肃','青海','西藏','内蒙古','山东','天津',],
            axisLine:{
                show:false,
            },
            axisLabel:{
                textStyle:{
                    color:'#fff',
                    fontSize:5
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
                data:[10, 15, 100, 25, 30,20,60],
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
    myChart.setOption(option);
});
