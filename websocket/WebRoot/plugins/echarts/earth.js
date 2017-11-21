/**
 * Created by admin on 2016/10/8.
 */
$(function(){
    var _data=[];
    _data=[
        {name: '北京',value: 70000},
        {name: '天津',value: 800},
        {name: '上海',value: 2000},
        {name: '重庆',value: 300},
        {name: '河北',value: 800},
        {name: '河南',value: 2000},
        {name: '云南',value: 2},
        {name: '辽宁',value: 70000},
        {name: '黑龙江',value:70000},
        {name: '湖南',value: 2000},
        {name: '安徽',value: 800},
        {name: '山东',value: 2000},
        {name: '新疆',value: 1},
        {name: '江苏',value: 70000},
        {name: '浙江',value: 700},
        {name: '江西',value: 300},
        {name: '湖北',value: 70000},
        {name: '广西',value: 150},
        {name: '甘肃',value: 2000},
        {name: '山西',value: 150},
        {name: '内蒙古',value:2000},
        {name: '陕西',value: 2000},
        {name: '吉林',value: 650},
        {name: '福建',value: 2000},
        {name: '贵州',value: 1},
        {name: '广东',value: 2000},
        {name: '青海',value: 2},
        {name: '西藏',value: 3},
        {name: '四川',value: 2},
        {name: '宁夏',value: 560},
        {name: '海南',value: 1},
        {name: '台湾',value: 3},
        {name: '香港',value: 2},
        {name: '澳门',value: 1},
    ];
    var chart = echarts.init(document.getElementById('map-wrapper'));

    /*chart.setOption({
     series: [{
     name:'中国',
     type: 'map',
     map: 'china'
     }]
     });*/
    option = {
        title : {
            text: '中国职业足球体系土壤指标',
            subtext: '',
            x:'left',
            textStyle:{
                color:'#43caee',//字体颜色
                fontSize:25,//字体大小
            },

        },
        /*tooltip : {
         trigger: 'item'
         },*/
        /*legend: {
         orient: 'vertical',
         x:'left',
         data:['订单量']
         },*/
        dataRange: {
            x: 'left',
            y: 'bottom',
            splitList: [
                {start: 50000, end: 100000, label: '赛事数量 50000-100000',color: '#19bee7'},
                {start: 1000, end: 5000, label: '赛事数量 1000-5000',color: '#43caee'},
                {start: 500, end: 1000, label: '赛事数量 500-1000',color: '#71d6f0'},
                {start: 100, end: 500, label: '赛事数量 100-500',color: '#9cecff'},
                {end: 10,label: '赛事数量 0',color: '#A9A9A9'}
            ],
            color: ['#E0022B', '#E09107', '#A3E00B']
        },
        /*toolbox: {
         show: true,
         orient : 'vertical',
         x: 'right',
         y: 'center',
         feature : {
         mark : {show: true},
         dataView : {show: true, readOnly: false},
         restore : {show: true},
         saveAsImage : {show: true}
         }
         },*/
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
                itemStyle:{
                    normal:{
                        label:{
                            show:false,
                            textStyle: {
                                color: "rgb(249, 249, 249)"
                            }
                        }
                    },
                    emphasis:{label:{show:false}}
                },
                data:_data,
            }
        ]
    };
    var currentIndex=-1;
    var timeTicket = setInterval(function () {
        var dataLen = option.series[0].data.length;
//            alert(dataLen);
        // 取消之前高亮的图形
        chart.dispatchAction({
            type: 'downplay',
            seriesIndex: 0,
            dataIndex: currentIndex
        });
        currentIndex = (currentIndex + 1) % dataLen;
        // 高亮当前图形
        chart.dispatchAction({
            type: 'highlight',
            seriesIndex: 0,
            dataIndex: currentIndex
        });
        // 显示 tooltip
        chart.dispatchAction({
            type: 'showTip',
            seriesIndex: 0,
            dataIndex: currentIndex
        });

    },1000);
    chart.setOption(option);
});
