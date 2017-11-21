package com.gnet.module.display.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gnet.module.display.dao.IDisplayDao;
import com.gnet.utils.CxfUtil;
import com.gnet.utils.PropertiesUtil;

@Service(value="taskReqCujuNews")
public class TaskReqCujuNews extends QuartzJobBean{

	@Resource(name="displayDao")
	private IDisplayDao displayDao;
	private static final Logger logger = LoggerFactory.getLogger(TaskReqCujuNews.class);
	private String CU_JU_URL=PropertiesUtil.getKeyValue("CU_JU_URL");
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		logger.info("开始请求蹴鞠查询新闻数据！");
		JobDataMap map = arg0.getMergedJobDataMap();
		int test = Integer.parseInt(map.get("test").toString());
		System.out.println(test);
		displayDao = (IDisplayDao) map.get("displayDao");
		try {
			String res=CxfUtil.callService(CU_JU_URL, "GetRecommendNews", new Object[]{});
			JSONArray array=JSONArray.parseArray(getCuJuResult(res));
			if(array.size()>0){
				for (int i = 0; i < array.size(); i++) {
					String newsId=JSONObject.parseObject(array.getString(i)).getString("FM_NEWS_ID");
					String daoRes=displayDao.getNewsDetail(newsId);
					JSONArray daoArray=JSONArray.parseArray(daoRes);
					if(daoArray.size()>0){
						JSONObject jsonObject=JSONObject.parseObject(daoArray.getString(0));
						if(jsonObject.getInteger("quantity")!=JSONObject.parseObject(array.getString(i)).getInteger("FM_NEWS_COMTNUM")){
							// 更新评论,前台只要有变化就更新div区域
							String resComment=getAllCommentsByNewsId(newsId);
							// 查询当前新闻ID评论库中的最新时间为多少,进行比对,然后拿过来的数据比对本地数据库的最新时间,如果时间大于本地时间则进行插入.
							// 蹴鞠评论数据
							JSONArray arrayComment=JSONArray.parseArray(resComment);
							String dbComment=displayDao.getLastestComments(newsId);
							// db评论数据
							JSONArray arrayDbComment=JSONArray.parseArray(dbComment);
							// 如果评论为空则插入
							if(arrayDbComment.size()>0){
								// db评论时间
								String dbTime = JSONObject.parseObject(arrayDbComment.getString(0)).getString("TM");
								// 轮询比较评论时间
								for (int j = 0; j < arrayComment.size(); j++) {
									// ssssss此处需要改为蹴鞠的时间参数sssss
									if(compareDate(JSONObject.parseObject(arrayComment.getString(j)).getString("CREATEDATE"),dbTime)>0){
										logger.info("数据库有评论数据,插入评论");
										displayDao.insertComments(arrayComment.getString(j));
									}
								}
							// 否则匹配对比时间
							}else{
								logger.info("数据库没有评论数据,插入评论");
								for (int j = 0; j < arrayComment.size(); j++) {
									displayDao.insertComments(arrayComment.getString(j));
								}
							}
							// 更新新闻中评论的数量
							displayDao.updateNewsInfo(array.getString(i));
						}
						
					}else{
						// 添加新闻
						displayDao.insertNewsInfo(array.getString(i));
						// 还需要添加评论
						if(JSONArray.parseArray(getAllCommentsByNewsId(newsId)).size()>0)
						displayDao.insertComments(JSONArray.parseArray(getAllCommentsByNewsId(newsId)).getString(0));
					}
					
				}
			}
			logger.info("查询结束...{}",res);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static int compareDate(String date1,String date2) throws Exception{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateTime1 = dateFormat.parse(date1);
		Date dateTime2 = dateFormat.parse(date2);
		return dateTime1.compareTo(dateTime2);
	}
	
	// 返回蹴鞠指定新闻的所有评论
	public String getAllCommentsByNewsId(String newsId){
		String resComments= CxfUtil.callService(CU_JU_URL, "GetNewsComments", new Object[]{newsId,1,1000});
		return getCuJuResult(resComments);
	}
	/**
	 * 
	 * getCuJuResult:(简化数据格式). <br/>
	 *
	 * @author Jikey
	 * @param str
	 * @return
	 */
	public static String getCuJuResult(String str){
		JSONObject json=JSONObject.parseObject(str);
		return JSONObject.parseObject(json.getString("Data")).getString("table0");
	} 
	
}
