package com.gnet.module.display.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.catalina.core.ApplicationContext;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gnet.module.display.service.IDisplayService;
import com.gnet.module.display.task.TaskReqCujuNews;
import com.gnet.utils.CxfUtil;
import com.gnet.utils.PropertiesUtil;



@Controller
@RequestMapping("/displayController")
public class DisplayController {
	private final String UTF_8="UTF-8";
	private final static Logger logger=LoggerFactory.getLogger(DisplayController.class);
	@Resource(name="displayService")
	private IDisplayService displayService;
	private String CU_JU_URL=PropertiesUtil.getKeyValue("CU_JU_URL");
	/**
	 * 
	 * getClubInfo:(按条件获取职业足球俱乐部信息,有分页功能). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getClubInfo",produces = "application/json; charset=utf-8",method=RequestMethod.GET)
	public void getClubInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		String key=request.getParameter("key");
		String columnName=request.getParameter("columnName");
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		logger.info("key:{},columnName:{},pageIndex:{},pageSize:{}",key,columnName,pageIndex,pageSize);
		System.out.println(key+";"+columnName+";"+pageIndex+";"+pageSize);
		String msg=displayService.getProClubInfo(key, columnName, pageIndex, pageSize);
		out.print(msg);
	}
	
	
	/**
	 * 
	 * getClubInfo:(按条件获取职业球队赛事表,有分页功能). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getProTeamInfo",method={RequestMethod.POST,RequestMethod.GET})
	public void getProTeamInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		String key=request.getParameter("key");
		String columnName=request.getParameter("columnName");
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		logger.info("key:{},columnName:{},pageIndex:{},pageSize:{}",key,columnName,pageIndex,pageSize);
		System.out.println(key+";"+columnName+";"+pageIndex+";"+pageSize);
		String msg=displayService.getProTeamInfo(key, columnName, pageIndex, pageSize);
		out.print(msg);
	}
	/**
	 * 
	 * getProPlayerInfo:(获取职业球员的个人信息). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getProPlayerInfo",method={RequestMethod.POST,RequestMethod.GET})
	public void getProPlayerInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		logger.info("pageIndex:{},pageSize:{}",pageIndex,pageSize);
		System.out.println(pageIndex+";"+pageSize);
		String msg=displayService.getProPlayerInfo("", "", pageIndex, pageSize);
		out.print(msg);
	}
	
	
	/**
	 * 
	 * getClubRank:(获取俱乐部排名). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getClubRank",method={RequestMethod.POST,RequestMethod.GET})
	public void getClubRank(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		String clubName=request.getParameter("clubName");
		// 赛事名称
		String assoName=request.getParameter("assoName");
		System.out.println("...."+clubName);
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		logger.info("columnName:{},pageIndex:{},pageSize:{}",clubName,pageIndex,pageSize);
		System.out.println(clubName+";"+pageIndex+";"+pageSize);
		String msg=displayService.getClubRank(clubName,assoName, pageIndex, pageSize);
		out.print(JSON.toJSON(msg));
	}
	
	/**
	 * 
	 * getRefereeInfo:(获取所有的裁判基本信息). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response 
	 * @throws Exception
	 */
	@RequestMapping(value="/getRefereeInfo",method={RequestMethod.POST,RequestMethod.GET})
	public void getRefereeInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		// 根据关键字查询裁判信息. 此参数可以为空,前端不比传入.
		String keyName=request.getParameter("keyName");
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		logger.info("keyName:{},pageIndex:{},pageSize:{}",keyName,pageIndex,pageSize);
		String msg=displayService.getRefereeInfo(keyName, pageIndex, pageSize);
		out.print(JSON.toJSON(msg));
	}
	/**
	 * 
	 * getPunishInfo:(获取各个省的处罚数量). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getPunishInfo",method={RequestMethod.POST,RequestMethod.GET})
	public void getPunishInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		String provinceName=request.getParameter("provinceName");
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		logger.info("provinceName:{},pageIndex:{},pageSize:{}",provinceName,pageIndex,pageSize);
		String msg=displayService.getPunishInfo(provinceName, pageIndex, pageSize);
		out.print(JSON.toJSON(msg));
	}
	
	/**
	 * 
	 * getWorldRankInfo:(世界上座率排名). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getWorldRankInfo",method={RequestMethod.POST,RequestMethod.GET})
	public void getWorldRankInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		// 国家名称
		String countryName=request.getParameter("countryName");
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		logger.info("countryName:{},pageIndex:{},pageSize:{}",countryName,pageIndex,pageSize);
		String msg=displayService.getWorldRankInfo(countryName, pageIndex, pageSize);
		out.print(JSON.toJSON(msg));
	}
	/**
	 * 
	 * getChinaInfo:(获取中国的世界排名,俱乐部个数,处罚总数,人数). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getChinaInfo",method={RequestMethod.POST,RequestMethod.GET})
	public void getChinaInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		// 国家名称
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		logger.info("pageIndex:{},pageSize:{}",pageIndex,pageSize);
		String msg=displayService.getChinaInfo();
		out.print(JSON.toJSON(msg));
	}
	
	
	
	/**
	 * 
	 * getChinaInfo:(获得地域土壤指标). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getRegionSoilBased",method={RequestMethod.POST,RequestMethod.GET})
	public void getRegionSoilBased(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		// 国家名称
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		logger.info("pageIndex:{},pageSize:{}",pageIndex,pageSize);
		String msg=displayService.getRegionSoilBased();
		out.print(JSON.toJSON(msg));
	}
	
	
	/**
	 * 
	 * getNewsBaseInfo:(获取所有地区的新闻消息). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getNewsBaseInfo",method={RequestMethod.POST,RequestMethod.GET})
	public void getNewsBaseInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		// 国家名称
		String key=request.getParameter("type");
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		logger.info("pageIndex:{},pageSize:{}",pageIndex,pageSize);
		String msg=displayService.getNewsBaseInfo(key,pageIndex,pageSize);
		out.print(JSON.toJSON(msg));
	}
	
	/**
	 * 
	 * getNewsDetail:(根据新闻ID获取新闻详细信息). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getNewsDetail",method={RequestMethod.POST,RequestMethod.GET})
	public void getNewsDetail(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		// 新闻ID
		String newsId=request.getParameter("newsId");
		// type:0 调用足球接口 ,type:1调用本地数据接口
		String type=request.getParameter("type");
		logger.info("newsId:{}",newsId);
		String msg="";
		if("0".equals(type)){
			String res=CxfUtil.callService(CU_JU_URL, "GetNewsDetails", new Object[]{newsId});
			msg=TaskReqCujuNews.getCuJuResult(res);
		}else{
			msg=displayService.getNewsDetail(newsId);
		}
		out.print(JSON.toJSON(msg));
	}
	
	/**
	 * 
	 * getCommentByNewsId:(根据新闻ID获取新闻的所有评论信息). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getCommentByNewsId",method={RequestMethod.POST,RequestMethod.GET})
	public void getCommentByNewsId(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		// 新闻ID
		String newsId=request.getParameter("newsId");
		logger.info("newsId:{}",newsId);
		String msg=displayService.getCommentByNewsId(newsId);
		out.print(JSON.toJSON(msg));
	}
	
	/**
	 * 
	 * getVedioInfo:(获取足球视频信息). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getVideoInfo",method={RequestMethod.POST,RequestMethod.GET})
	public void getVideoInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		// 新闻ID
		String videoId=request.getParameter("videoId");
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		logger.info("videoId:{}",videoId);
		String msg=displayService.getVideoInfo(videoId,pageIndex,pageSize);
		out.print(JSON.toJSON(msg));
	}
	@RequestMapping(value="/index",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		String videoListStr = displayService.getVideoInfo("",0,99);
		List<Map<String, Object>> list = JSON.parseObject(videoListStr, List.class); 
		
		request.setAttribute("list", list);
		
		return new ModelAndView("Big_Screen");
		
	}
	
	/**
	 * 
	 * updatePageInfo:(更新页面操作接口). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/updatePageInfo",method={RequestMethod.POST,RequestMethod.GET})
	public void updatePageInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		// 新闻ID
		String pageNum=request.getParameter("pageNum");
		// 自动0、手动1、手机端基本传来就是手动值为1
		String actionType=request.getParameter("actionType");
		// 是否存在子菜单
		String subPageFlag=request.getParameter("subPageFlag");
		// 子栏目页数
		String subPageNum=request.getParameter("subPageNum");
		logger.info("pageNum:{},actionType:{},subPageFlag:{},subPageNum:{}",pageNum,actionType,subPageFlag,subPageNum);
		String msg=displayService.updatePageInfo(pageNum,actionType,subPageFlag,subPageNum);
		out.print(JSON.toJSON(msg));
	}
	
	/**
	 * 
	 * selectPageInfo:(查询当前页是第几页的信息). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/selectPageInfo",method={RequestMethod.POST,RequestMethod.GET})
	public void selectPageInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		String msg=displayService.selectPageInfo();
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.16:8888");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST");
		// 请求withCredentials为true时,则必须加载以下参数.
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Methods, Access-Control-Max-Age, X-Auth-Token, Content-Type, Accept");
		out.print(JSON.toJSON(msg));
	}
	
	/**
	 * 
	 * getHotInformation:(获取热力图足球新闻). <br/>
	 *
	 * @author Jikey
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getHotInformation",method={RequestMethod.POST,RequestMethod.GET})
	public void getHotInformation(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		String msg=displayService.getHotInformation();
		out.print(msg);
	}
	
	/**
	 * 
	 * getHotInformation:(获取热力图足球新闻内容). <br/>
	 *
	 * @author Lizhuo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getHotInformationNews",method={RequestMethod.POST,RequestMethod.GET})
	public void getHotInformationNews(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding(UTF_8);
		response.setCharacterEncoding(UTF_8);
		PrintWriter out=response.getWriter();
		String game_ID = request.getParameter("game_ID");
		String msg=displayService.getHotInformationNews(game_ID);
		out.print(msg);
	}
	
	public String check(String str){
		return "OK";
	} 
}
