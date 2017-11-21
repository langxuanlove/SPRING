<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Map<String, Object>> list = (ArrayList)request.getAttribute("list");
//List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
%>

<!DOCTYPE html>
<html lang="zh-CN">
<!-- <html lang="zh-CN" manifest="/Big_Screen/main.manifest"> -->
<head>
 <base href="<%=basePath%>">
 <meta charset="UTF-8">
 <title>临淄大屏</title>
	<link rel="stylesheet" href="css/jquery.fullPage.css">
    <link rel="stylesheet" href="plugins/css/flexslider.css" />
    <!--视频播放-->
    <link rel="stylesheet" href="css/reset.css" type="text/css" />
    <link rel="stylesheet" href="css/base.css" type="text/css" />
    <link rel="stylesheet" href="css/index.css" type="text/css" />
    <link rel="stylesheet" href="css/progressbar.css" type="text/css" />
    <link rel="stylesheet" href="css/hotEvent.css" type="text/css" />
 	<link rel="stylesheet" href="css/video-js.min.css" type="text/css" />
 <style>
 .judge-list .flex-control-nav{
 	display:none;
 }
 </style>   
    
</head>

<body>
<div id="sucaihuo">
	<!-- 第一 职业健康指标 -->
    <div class="section">
			<div class="indicators-outer">
                <div class="indicators">
                    <div class="indicators-map  total-radius-shadow">
                    	<canvas id = "canvasForLine" width = '1500' height = '1500' style="position: absolute;z-index: 999;">Canvas测试</canvas>
                        <div class="map-wrapper" id="map-wrapper" style="height: 100%; width:800px;"> </div>
                        <!--<div class=""></div>-->
                    </div>
                    <div class="indicators-data total-radius-shadow mt16">
                        <ul>
                            <li>
                                <i class="map-icons cup-icon"></i>世界上座排名<i class="total i-szpm"></i>
                            </li>
                            <li>
                                <i class="map-icons flag-icon"></i>俱乐部<i class="total i-jlb"></i>
                            </li>
                            <li>
                                <i class="map-icons player-icon"></i>处罚总数<i class="total i-cfzs"></i>
                            </li>
                            <li style="margin-right:0;">
                                <i class="map-icons judge-icon"></i>平均上座人数<i class="total i-szrs"></i>
                            </li>    
                        </ul>
                    </div>
                    <div class="area-info total-radius-shadow">
                        <div id="pie-chart" style="width:100%; height:300px;">
                        </div>
                        <div class="pie-counts">
                            <p>当地体系指标值</p>
                            <i>2685</i>
                        </div>
                    </div>
                </div>
                <!--中国职业足球体系土壤指标 End-->
                <div class="rank-wrapper">
                    <div class="rank-item attendance-list total-radius-shadow">
                        <div class="rank-item-tit">
					        <h3>场均观众人数  <i>The number of the fans at scene game</i></h3>
					        <div class="progress-list" id="progress-list">
					            <ul class="slides">
					                <li class="progress-wrapper">
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>台湾</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #ff5959;"></div>
					                        </div>
					                        <div class="skill-bar-percent">20122</div>
					                    </div>
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>北京</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #51eac0;"></div>
					                        </div>
					                        <div class="skill-bar-percent">50%</div>
					                    </div>
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>上海</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #ff5959;"></div>
					                        </div>
					                        <div class="skill-bar-percent">20122</div>
					                    </div>
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>北京</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #51eac0;"></div>
					                        </div>
					                        <div class="skill-bar-percent">50%</div>
					                    </div>
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>北京</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #51eac0;"></div>
					                        </div>
					                        <div class="skill-bar-percent">50%</div>
					                    </div>
					                </li>
					                <li class="progress-wrapper">
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>上海</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #ff5959;"></div>
					                        </div>
					                        <div class="skill-bar-percent">20122</div>
					                    </div>
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>北京</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #51eac0;"></div>
					                        </div>
					                        <div class="skill-bar-percent">50%</div>
					                    </div>
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>上海</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #ff5959;"></div>
					                        </div>
					                        <div class="skill-bar-percent">20122</div>
					                    </div>
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>北京</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #51eac0;"></div>
					                        </div>
					                        <div class="skill-bar-percent">50%</div>
					                    </div>
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>北京</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #51eac0;"></div>
					                        </div>
					                        <div class="skill-bar-percent">50%</div>
					                    </div>
					                </li>
					                <li class="progress-wrapper">
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>上海</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #ff5959;"></div>
					                        </div>
					                        <div class="skill-bar-percent">20122</div>
					                    </div>
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>北京</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #51eac0;"></div>
					                        </div>
					                        <div class="skill-bar-percent">50%</div>
					                    </div>
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>上海</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #ff5959;"></div>
					                        </div>
					                        <div class="skill-bar-percent">20122</div>
					                    </div>
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>北京</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #51eac0;"></div>
					                        </div>
					                        <div class="skill-bar-percent">50%</div>
					                    </div>
					                    <div class="progress-item clearfix">
					                        <div class="skillbar-title"><span>北京</span></div>
					                        <div class="skillbar clearfix " data-percent="50%">
					                            <div class="skillbar-bar" style="background: #51eac0;"></div>
					                        </div>
					                        <div class="skill-bar-percent">50%</div>
					                    </div>
					                </li>
					            </ul>
					        </div>
					    </div>
					</div>
                    <div class="rank-item judge-list total-radius-shadow">
                        <div class="rank-item-tit">
                            <h3>中超裁判<i>The super league referee</i></h3>
                        </div>
                        <div id="judge-list" class="judge-list-con">
                            <ul class="slides" id="slides-cp">
                                <li>
                                    <img src="images/judge1.jpg" alt="裁判" width="90" height="90"/>
                                    <p>李俊<i>国家级裁判</i></p>
                                    <span><em>1</em></span>
                                </li>
                                <li>
                                    <img src="images/judge1.jpg" alt="裁判" width="90" height="90"/>
                                    <p>李俊<i>国家级裁判</i></p>
                                    <span><em>2</em></span>
                                </li>
                                <li>
                                    <img src="images/judge1.jpg" alt="裁判" width="90" height="90"/>
                                    <p>李俊<i>国家级裁判</i></p>
                                    <span><em>3</em></span>
                                </li>
                                <li>
                                    <img src="images/judge1.jpg" alt="裁判" width="90" height="90"/>
                                    <p>李俊<i>国家级裁判</i></p>
                                    <span><em>4</em></span>
                                </li>
                                <li>
                                    <img src="images/judge1.jpg" alt="裁判" width="90" height="90"/>
                                    <p>李俊<i>国家级裁判</i></p>
                                </li>
                            </ul>
                     
                        </div>
                    
                    </div>
                    <div class="rank-item publish-image total-radius-shadow">
                        <div class="rank-item-tit">
                            <h3>中超射手榜<i>The ranking of clubs</i></h3>
                        </div>
                        <div class="punish-graphic" id="punishGraphic">
                        </div>
                    </div>
					<div class="rank-item club-list total-radius-shadow">
                        <div class="rank-item-tit">
                            <h3>俱乐部排行<i>The ranking of clubs</i></h3>
                        </div>
                        <div class="club-con">
                            <div class="scrollbox">
                                <ul id="toplist">
                                    <li>
                                        <span class="number">1</span>
                                        <img src="images/logo1.jpg" alt=""/>
                                        <p>北京国安俱乐部</p>
                                    </li>
                                    <li>
                                        <span class="number">2</span>
                                        <img src="images/logo2.jpg" alt=""/>
                                        <p>长春亚泰俱乐部</p>
                                    </li>
                                    <li>
                                        <span class="number">3</span>
                                        <img src="images/logo3.jpg" alt=""/>
                                        <p>大连阿尔滨俱乐部</p>
                                    </li>
                                    <li>
                                        <span class="number">4</span>
                                        <img src="images/logo4.jpg" alt=""/>
                                        <p>广州恒大俱乐部</p>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
			
			<!--分布图-->
            <div class="tab total-radius-shadow clearfix" style="margin-bottom:5px;">
                <dl>
                    <dt>各地区综合指标值</dt>
                </dl>
                <!-- 你是获取的动态数据 所以tab可以只留一个dd 用来追加数据就行了 dd.html(data); -->
                <div style="width:100%; height: 100%;">
                    <div id="container" style="height: 100%; width:100%;"></div>  <!-- 这是一个图标 每次hover时间取一次选项的值然后更改option这个对象里面的值 就是这个动作 container已经相当于一个dd了-->
                </div>
            </div>
  		     <p class="clearfix company" >技术支持单位：北京凯英信业科技股份有限公司&北京五边形网络技术有限责任公司</p>

    </div>

    <!-- 第二 热门赛事 -->
        <div class="section">
         <div class="hot-event-wrapper mb12">
             <ul class="hot-event-logo-list">
                 <li class="curLi">
                     <div class="circle-outer">
                         <img src="images/logo1.png" alt="" />
                     </div>
                 </li>
                 <li>
                     <div class="circle-outer">
                         <img src="images/logo2.png" alt="" />
                     </div>
                 </li>
                 <li>
                     <div class="circle-outer">
                         <img src="images/logo3.png" alt="" />
                     </div>
                 </li>
                 <li>
                     <div class="circle-outer">
                         <img src="images/logo4.png" alt="" />
                     </div>
                 </li>
                 <li>
                     <div class="circle-outer">
                         <img src="images/logo5.png" alt="" />
                     </div>
                 </li>
                 <li>
                     <div class="circle-outer">
                         <img src="images/logo6.png" alt="" />
                     </div>
                 </li>
                 <li>
                     <div class="circle-outer">
                         <img src="images/logo7.png" alt="" />
                     </div>
                 </li>
             </ul>
             <div class="hot-con">
                 <div class="hot-con-l">
                     <div class="hot-event-des">
                         <h3>蹴鞠杯大学生足球冠军赛</h3>
                         <div class="hot-event-des-icons">
                             <p><i><img src="images/icon-cup.png" alt=""/></i><span class="people_num"></span></p>
                             <p class="pl50"><i><img src="images/icon-dy.png" alt=""/></i><span class="team_num"></span></p>
                         </div>
                         <p class="hot-con-des-con">蹴鞠杯大学生足球冠军赛，作为一项业足球冠军赛，作为一项业足球冠军赛，作为一项业足球冠军赛，作为一项业余联赛，以全国各地的城市为基础，建立俱乐部，参赛队伍的组织以俱乐部为余联赛，以全国各地的城市为基础，建余联赛，以全国各地的城市为基础，建立俱乐部，参赛队伍的组织以俱乐部为立俱乐部，</p>
                     </div>
                     <div class="comments-list" id="comments-list">
                         <ul class="slides">
                             <li>
                                 <h3 class="comments-tit">中央电视台CCTV-5</h3>
                                 <i class="comments-time">2016.10.06 14:00</i>
                                 <p class="comment-con">蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯大学生的福音，支持蹴鞠杯！</p>
                             </li>
                             <li>
                                 <h3 class="comments-tit">中央电视台CCTV-5</h3>
                                 <i class="comments-time">2016.10.06 14:00</i>
                                 <p class="comment-con">蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯，大学生的福音，支持蹴蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯鞠杯！支持蹴鞠杯！蹴鞠杯大学生的福音，支持蹴鞠杯！</p>
                             </li>
                             <li>
                                 <h3 class="comments-tit">中央电视台CCTV-5</h3>
                                 <i class="comments-time">2016.10.06 14:00</i>
                                 <p class="comment-con">蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯大学生的福音，支持蹴鞠蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯杯！</p>
                             </li>
                             <li>
                                 <h3 class="comments-tit">中央电视台CCTV-5</h3>
                                 <i class="comments-time">2016.10.06 14:00</i>
                                 <p class="comment-con">蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯大学生的福音，支持蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯蹴鞠杯，大学生的福音，支持蹴鞠杯！支持蹴鞠杯！蹴鞠杯蹴鞠杯！</p>
                             </li>
                         </ul>
                     </div>
                 </div>
                 <div class="hot-con-r">
                     <canvas id = "canvasForHotPin" width = '1200' height = '964' style="position: absolute;z-index: 999;">Canvas测试</canvas>
                     <div class="heat-map" id="heat-map" style="width: 1112px; height:964px;"></div>
                 </div>
             </div>
         </div>
  		     <p class="clearfix company" >技术支持单位：北京凯英信业科技股份有限公司&北京五边形网络技术有限责任公司</p>
     </div>
    <!-- 第三 球迷分析 -->
    <div class="section">
        <div class="left-box total-radius-shadow">
                <h1 class="subtitle">中国球迷分析
                <br/><p class="english">Chinese Football Fans Analysis</p>
                </h1>
                <div class="demo">
                    <div class="imgbox">
                        <img src="images/circle.png" alt="" class="" />
                    </div>
                    <div id="container2" style="height: 100%;width:100%;">
                    </div>
                    <img src="images/data.png" class="data"/>
                </div>
                <p class="details">在艾瑞统计报告中显示：中国球迷年龄分布中，以21-25岁为主要球迷生力军；占比38.4%，26-30岁为第二球迷年龄区域，占了16.6%；排名第三的年龄区域是20岁以下的人群，占比15.1%。其余年龄区域总计占比29.2%</p>
            </div>
            <div class="right-box mb12">
                <div class="right-top total-radius-shadow">
                    <h1 class="per-title">中国总球迷人数</h1>
                    <p class="describe">男球迷占到中国总球迷人数的<b>56.08%</b>,女球迷占到<b>43.92%</b></p>
                    <div class="person">
                        <div class="progress" id="progress">
                            <div id="sub_progress" class="progress-bar" style="height: 43.03%;">
                                <span class="green">56.08%</span>
                            </div>
                        </div>
                        <div class="progress ml120 bg-female">
                            <div id="sub_female" class="progress-bar female" style="height: 43.03%;">
                                <span class="purple">43.92%</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="right-bottom mt16 total-radius-shadow">
                    <h1 class="per-title">中国球迷关注较多赛事</h1>
                    <div class="progressbar" data-perc="49.8">
                        <label class="subtitle">欧冠</label>
                        <div class="bar"></div>
                        <div class="label"></div>
                    </div>
                    <div class="progressbar" data-perc="48.8">
                        <label class="subtitle">英超</label>
                        <div class="bar"></div>
                        <div class="label"></div>
                    </div>
                    <div class="progressbar" data-perc="36.2">
                        <label class="subtitle">中超</label>
                        <div class="bar"></div>
                        <div class="label"></div>
                    </div>
                    <div class="progressbar" data-perc="29.6">
                        <label class="subtitle">亚冠</label>
                        <div class="bar"></div>
                        <div class="label"></div>
                    </div>
                    <div class="progressbar" data-perc="26.4">
                        <label class="subtitle">西甲</label>
                        <div class="bar"></div>
                        <div class="label"></div>
                    </div>
                    <div class="progressbar" data-perc="21.8">
                        <label class="subtitle">意甲</label>
                        <div class="bar"></div>
                        <div class="label"></div>
                    </div>
                    <div class="progressbar" data-perc="20.4">
                        <label class="subtitle">德甲</label>
                        <div class="bar"></div>
                        <div class="label"></div>
                    </div>
                    <div class="progressbar" data-perc="16.6">
                        <label class="subtitle">欧联</label>
                        <div class="bar"></div>
                        <div class="label"></div>
                    </div>
                </div>
            </div>
		<p class="clearfix company" >技术支持单位：北京凯英信业科技股份有限公司&北京五边形网络技术有限责任公司</p>
    </div>
    <!-- 第四 热门新闻 -->
    <div class="section" id="section_id">
            <div class="hotspot" style="position:relative">
                <h1 class="title">中国业余足球实时热点新闻<br/><p class="font18"> The Chinese amateur football real-time news</p></h1>
               <!--  为ECharts准备一个具备大小（宽高）的Dom -->
                <div id="bigscreen05charts" style="width:1200px;height:900px;"></div>
                <div class="message" style="display:none">
                   
                </div>
            </div>
            <div class="comment-area" id="pinglun_div">
                <h1 class="comment-title">评论区(0)</h1>
                <div class="conmmentlist">
                	<ul id="pinglun_ul">
                       
                    </ul>
                </div>
            </div>
            <div class="discuss-area" id="pinglun_div_cj" style="display:none;"> 
				<h1 class="discuss-title">评论区(0)</h1>
				<div class="discusslist">
				<ul id="pinglun_ul_cj">
					<!-- <li>
						<div class="discusslist_con">
						<div class="discusslist_con_base clearfix">
						<div class="discusslist_con_base_mask">
						<img src="images/person.png" >
						</div>
						<span>眼红的西红柿</span>
						<br />
							<em>2016.10.06</em><em>14:00</em>
							</div>
							<p class="discussdetail_con_comment">中国队加油，中国队不哭，看好你们！</p>
							</div>
							</li> -->
				</ul>
				</div>
			</div>
			<div id="code_div" class="code total-radius-shadow" style="display:none;">
				<img src="images/code.png"/>
			<p style="text-align:center;">蹴鞠足球官方APP</p>
			</div>
						<!-- 	<p class="company fr">技术支持单位：北京凯英信业科技股份有限公司&北京五边形网络技术有限责任公司</p> -->
            <p class="company fr">技术支持单位：北京凯英信业科技股份有限公司&北京五边形网络技术有限责任公司</p>
        </div>
    <!-- 第五 足球视频 -->
    <div class="section">
            <div id="main" role="main" class="video-slider">
                <section class="slider">
                    <div class="flexslider">
                        <ul class="slides">
                        	<% for(int i = 0;i < list.size();i++){ %>
                            <li data-thumb="source/images/sm-sp.jpg" playing-txt="正在播放" data-thumb-title="<%=list.get(i).get("video_name")  %>" data-thumb-subtitle="<%=list.get(i).get("video_longer")  %>">
                                <a href="javascript:void(0);">
                                    <video id="v_<%=i %>" controls="controls" width="1354" height="720" loop="loop">
                                        <source src="<%=list.get(i).get("video_url") %>" type="video/mp4" />
                                    </video>
                                </a>
                            </li>
                            <%} %>
                            <!-- <li data-thumb="source/images/sm-sp.jpg" playing-txt="正在播放" data-thumb-title="蹴鞠杯足球冠军赛" data-thumb-subtitle="22:00">
                                <a href="javascript:void(0);">
                                    <video id="v_1" controls="controls">
                                        <source src="video/北京工商学院VS北京理工大学.mp4" type="video/mp4" />
                                    </video>
                                </a>
                            </li>
                            <li data-thumb="source/images/sm-sp.jpg" playing-txt="正在播放" data-thumb-title="蹴鞠杯足球冠军赛" data-thumb-subtitle="23:00">
                                <a href="javascript:void(0);">
                                    <video id="v_2" controls="controls">
                                        <source src="source/video/3.mp4" type="video/mp4" />
                                    </video>
                                </a>
                            </li>
                            <li data-thumb="source/images/sm-sp.jpg" playing-txt="正在播放" data-thumb-title="蹴鞠杯足球冠军赛" data-thumb-subtitle="24:00">
                                <a href="javascript:void(0);">
                                    <video id="v_3" controls="controls">
                                        <source src="source/video/4.mp4" type="video/mp4" />
                                    </video>
                                </a>
                            </li>
                            <li data-thumb="source/images/sm-sp.jpg" playing-txt="正在播放" data-thumb-title="蹴鞠杯足球冠军赛" data-thumb-subtitle="24:00">
                                <a href="javascript:void(0);">
                                    <video id="v_4" controls="controls">
                                        <source src="source/video/5.mp4" type="video/mp4" />
                                    </video>
                                </a>
                            </li> -->
                        </ul>
                    </div>
                </section>
                <div class="main-tit">中国业余足球经典赛事 <i class="english">The classic games of the Chinese amateur football</i></div>
            </div>
            <div class="video-con mb12">
                <div class="video-item cur">
                    <div class="video-des total-radius-shadow">
                        <h2 class="video-tit">中国VS叙利亚顾超后</h2>
                        <p class="source">来源: <i>114NBA</i>|<i>2016-10-08 16:25:43</i></p>
                        <div class="video-on">
                            <p>国足1-0不敌叙利亚</p>
                            <p>北京时间10月8日，于本月6日进行的世预赛亚洲12强小组赛第三轮比赛中，陕西体育中心球场，中国男足坐镇主场迎战来访的叙利亚。</p>
                            <p>比赛开始，上半场双方状态平平，下半场第53分钟，国足后防失误被叙利亚抓到机会，结果马瓦斯率先破门，紧接着，叙利亚赫利宾错失必进球，最终，全场比赛结束，国足0-1主场不敌叙利亚。下一轮对决预告：北京时间10月11日 国足VS乌兹别克</p>
                            <p>世预赛-萨利赫直传撕破防线 顾超出击扑空马瓦斯挑射破僵</p>
                        </div>
                    </div>
                    <div class="video-time total-radius-shadow">
                        <p>本赛事有效时间</p>
                        <i>62:39</i>
                    </div>
                </div>
                <div class="video-item">
                    <div class="video-des total-radius-shadow">
                        <h2 class="video-tit">萨利赫直传马瓦斯破门</h2>
                        <p class="source">来源: <i>114NBA</i>|<i>2016-10-08 16:25:43</i></p>
                        <div class="video-on">
                            <p>国足1-0不敌叙利亚</p>
                            <p>北京时间10月8日，于本月6日进行的世预赛亚洲12强小组赛第三轮比赛中，陕西体育中心球场，中国男足坐镇主场迎战来访的叙利亚。</p>
                            <p>比赛开始，上半场双方状态平平，下半场第53分钟，国足后防失误被叙利亚抓到机会，结果马瓦斯率先破门，紧接着，叙利亚赫利宾错失必进球，最终，全场比赛结束，国足0-1主场不敌叙利亚。下一轮对决预告：北京时间10月11日 国足VS乌兹别克</p>
                            <p>世预赛-萨利赫直传撕破防线 顾超出击扑空马瓦斯挑射破僵</p>
                        </div>
                    </div>
                    <div class="video-time total-radius-shadow">
                        <p>本赛事有效时间</p>
                        <i>62:39</i>
                    </div>
                </div>
                <div class="video-item">
                    <div class="video-des total-radius-shadow">
                        <h2 class="video-tit">中国VS叙利亚直传马瓦斯破门</h2>
                        <p class="source">来源: <i>114NBA</i>|<i>2016-10-08 16:25:43</i></p>
                        <div class="video-on">
                            <p>国足1-0不敌叙利亚</p>
                            <p>北京时间10月8日，于本月6日进行的世预赛亚洲12强小组赛第三轮比赛中，陕西体育中心球场，中国男足坐镇主场迎战来访的叙利亚。</p>
                            <p>比赛开始，上半场双方状态平平，下半场第53分钟，国足后防失误被叙利亚抓到机会，结果马瓦斯率先破门，紧接着，叙利亚赫利宾错失必进球，最终，全场比赛结束，国足0-1主场不敌叙利亚。下一轮对决预告：北京时间10月11日 国足VS乌兹别克</p>
                            <p>世预赛-萨利赫直传撕破防线 顾超出击扑空马瓦斯挑射破僵</p>
                        </div>
                    </div>
                    <div class="video-time total-radius-shadow">
                        <p>本赛事有效时间</p>
                        <i>62:39</i>
                    </div>
                </div>
                <div class="video-item">
                    <div class="video-des total-radius-shadow">
                        <h2 class="video-tit">中国VS叙利亚顾超后破门</h2>
                        <p class="source">来源: <i>114NBA</i>|<i>2016-10-08 16:25:43</i></p>
                        <div class="video-on">
                            <p>国足1-0不敌叙利亚</p>
                            <p>北京时间10月8日，于本月6日进行的世预赛亚洲12强小组赛第三轮比赛中，陕西体育中心球场，中国男足坐镇主场迎战来访的叙利亚。</p>
                            <p>比赛开始，上半场双方状态平平，下半场第53分钟，国足后防失误被叙利亚抓到机会，结果马瓦斯率先破门，紧接着，叙利亚赫利宾错失必进球，最终，全场比赛结束，国足0-1主场不敌叙利亚。下一轮对决预告：北京时间10月11日 国足VS乌兹别克</p>
                            <p>世预赛-萨利赫直传撕破防线 顾超出击扑空马瓦斯挑射破僵</p>
                        </div>
                    </div>
                    <div class="video-time total-radius-shadow">
                        <p>本赛事有效时间</p>
                        <i>62:39</i>
                    </div>
                </div>
                <div class="video-item">
                    <div class="video-des total-radius-shadow">
                        <h2 class="video-tit">中国V传马瓦斯破门</h2>
                        <p class="source">来源: <i>114NBA</i>|<i>2016-10-08 16:25:43</i></p>
                        <div class="video-on">
                            <p>国足1-0不敌叙利亚</p>
                            <p>北京时间10月8日，于本月6日进行的世预赛亚洲12强小组赛第三轮比赛中，陕西体育中心球场，中国男足坐镇主场迎战来访的叙利亚。</p>
                            <p>比赛开始，上半场双方状态平平，下半场第53分钟，国足后防失误被叙利亚抓到机会，结果马瓦斯率先破门，紧接着，叙利亚赫利宾错失必进球，最终，全场比赛结束，国足0-1主场不敌叙利亚。下一轮对决预告：北京时间10月11日 国足VS乌兹别克</p>
                            <p>世预赛-萨利赫直传撕破防线 顾超出击扑空马瓦斯挑射破僵</p>
                        </div>
                    </div>
                    <div class="video-time total-radius-shadow">
                        <p>本赛事有效时间</p>
                        <i>62:39</i>
                    </div>
                </div>
            </div>
  		     <p class="clearfix company" >技术支持单位：北京凯英信业科技股份有限公司&北京五边形网络技术有限责任公司</p>
        </div>
        
</div>

<script src="plugins/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="plugins/js/slider.js"></script>
<script type="text/javascript" src="plugins/js/jquery.rollGallery_yeso.js"></script>
<script type="text/javascript" src="plugins/echarts/echarts.js"></script>
<script type="text/javascript" src="plugins/echarts/map/china.js"></script>
<script type="text/javascript" src="plugins/js/jquery.fullPage.min.js"></script>
<script type="text/javascript" src="js/hua/scroll.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.min.js"></script>
    
<!--视频页面需引进的js-->
<script type="text/javascript" src="js/video.min.js"></script>

    <script type="text/javascript" src="plugins/video/modernizr.js"></script>
    <script type="text/javascript" src="plugins/video/jquery.flexslider.js"></script>
    <script type="text/javascript" src="plugins/video/shCore.js"></script>
    <script type="text/javascript" src="plugins/video/shBrushXml.js"></script>
    <script type="text/javascript" src="plugins/video/shBrushJScript.js"></script>
    <script type="text/javascript" src="plugins/video/jquery.easing.js"></script>
    <script type="text/javascript" src="plugins/video/jquery.mousewheel.js"></script>
    <script type="text/javascript" src="plugins/video/video.js"></script>
    
    
    <script type="text/javascript" src="js/controller.js"></script>
    <script type="text/javascript" src="plugins/js/BigScreen01.js"></script>
	<script type="text/javascript" src="plugins/js/BigScreen02.js"></script>
    <script type="text/javascript" src="js/BigScreen04.js"></script>
    <script type="text/javascript" src="js/BigScreen05.js"></script>
    <script src="js/jqbar.js" type="text/javascript"></script>




<script>
_V_.options.flash.swf = "css/video-js.swf";

$(function(){
	
	/**
	*	屏幕切换
	*/
    $('#sucaihuo').fullpage({
        /*sectionsColor : ['#1bbc9b', '#4BBFC3', '#7BAABE', '#f90'],*/
		anchors: ['page1', 'page2', 'page3', 'page4', 'page5'],
		menu: '#menu',
		continuousVertical: true,
		scrollingSpeed:0,
		easing:'swing',
		afterLoad: function(anchorLink, index) { //第二屏（当前屏）视屏播放

//          console.log(index);
          if (index == 5&&action.action_type == 0) {
              var _RanNum=Math.floor(Math.random()*5);
        	  var _RanImg=$('section.slider ol.flex-control-nav li').eq(_RanNum).find('img');
        	  _RanImg.click();
//              playVideo(_RanNum);
document.getElementById("v_"+_RanNum).play();
          } else {
              pauseEvevyVideo();
          }
      }
    });

    /*setInterval(function(){
        $.fn.fullpage.moveSectionDown();
    }, 3000);*/
    
	
    
    
    /**
    *中国总球迷人数
    */
    $('#sub_progress').height(100 - 56.08 + '%');//男球迷占到中国总球迷人数的%
    $('#sub_female').height(100 - 43.92 + '%');//女球迷占到中国总球迷人数的%
    
    
    /**
     *中国球迷关注较多赛事
     */
     var _first_t=$('.progressbar').eq(0).attr('data-perc');
    $('.progressbar').each(function() {
        var t = $(this),
            dataperc = t.attr('data-perc');     
            barperc = dataperc/_first_t;
//        console.log(barperc);
        /* t.find('.bar').animate({
            width: barperc*360
        }, dataperc * 80); */
        t.find('.bar').css('width',barperc*360);
        t.find('.label').append('<div class="perc"></div>');

            var length = t.find('.bar').css('width');
                perc = barperc*100;
//                console.log(perc.toFixed(2));
                labelpos = (parseInt(length));
            t.find('.label').css('left', labelpos);
            t.find('.perc').text(dataperc + '%');
            /* $('.progressbar .bar').css('animation-iteration-count','infinite'); */
    }); 
    	
    /**
     *中国球迷分析
     */
    
    var dom = document.getElementById("container2");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title: {},
        tooltip: {
        	show : false,
            trigger: 'item',
            formatter: ""
        },
        legend: {},
        series: [{
            name: '访问来源',
            type: 'pie',
            radius:['40%', '77%'],
            center: ['50%', '50%'],
            color: ['#5ed1ed', '#cbcbcb', '#ff5a59', '#c89eff', '#57e78a', '#ffb75b'],
            label: {
            show: false,
            normal: {
                position: 'inner',
				textStyle:{
					fontSize:24,
				}
            },
				emphasis:{
					
				}
			},
			data: [{
				value: 386,
				name: '15.1%'
			}, {
				value: 207,
				name: '8.1%'
			}, {
				value: 192,
				name: '7.5%'
			}, {
				value: 366,
				name: '14.3%'
			}, {
				value: 425,
				name: '16.6%'
			}, {
				value: 983,
				name: '38.4%'
			}],
            itemStyle: {
                emphasis: {
                    /* shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)', */
                    borderColor: '#fff',
                    borderWidth: 2
                }
            }

        }]
    };
    app.currentIndex = -1;
    app.timeTicket = setInterval(function() {
        var dataLen = option.series[0].data.length;
        dataLen = 6;
//        console.log(app.currentIndex)
            // 取消之前高亮的图形
        myChart.dispatchAction({
            type: 'downplay',
            seriesIndex: 0,
            dataIndex: app.currentIndex
        });
        app.currentIndex = (app.currentIndex + 1) % dataLen;
        // 高亮当前图形
        myChart.dispatchAction({
            type: 'highlight',
            seriesIndex: 0,
            dataIndex: app.currentIndex
        });
        // 显示 tooltip
        myChart.dispatchAction({
            type: 'showTip',
            seriesIndex: 0,
            dataIndex: app.currentIndex
        });
    }, 1000);
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    } 
    
});

</script>

    <script type="text/javascript">
        var test = null;
        $(document).ready(function() {
            $('.list_lh li:even').addClass('lieven');
        })
        $(function() {
            $("div.list_lh").myScroll({
                speed: 40, //数值越大，速度越慢
                rowHeight: 550 //li的高度
            });
            $(".conmmentlist").myScroll({
                speed: 40, //数值越大，速度越慢
                rowHeight: 100 //li的高度
            });
        });
    </script>
    
    <script type="text/javascript">
        $(function() {
     
            $("#container2").css("background", "url('images/test.png')")
        });
    </script>
    
	<script type="text/javascript" src="plugins/echarts/hotmap.js"></script>







<a href="http://www.sucaihuo.com/" style="display: none;">sucaihuo</a>
<a href="http://www.sucaihuo.com/77.html" style="display: none;">jQuery全屏滚动插件fullPage.js</a>

</body>
</html>