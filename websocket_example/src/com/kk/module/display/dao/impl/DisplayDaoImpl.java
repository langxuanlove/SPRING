package com.gnet.module.display.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gnet.module.display.dao.IDisplayDao;
import com.gnet.socket.SocketController;
import com.google.common.eventbus.EventBus;

@Repository(value="displayDao")
public class DisplayDaoImpl implements IDisplayDao{

	private static final Logger logger=LoggerFactory.getLogger(DisplayDaoImpl.class);
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Override
	public String getProClubInfo(String key, String columnName, int pageIndex,
			int pageSize) {
		StringBuilder sql=new StringBuilder().append("select * from club where ");
		if(!StringUtils.isEmpty(columnName)){
			sql.append(columnName).append(" like '%").append(key).append("%' ").
				append(" limit ").
				append(pageIndex).
				append(",").
				append(pageSize);
		}
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{});
		return JSON.toJSONString(list);
	}
	@Override
	public String getProTeamInfo(String key, String columnName, int pageIndex,
			int pageSize) {
		StringBuilder sql=new StringBuilder().append("select * from prof_team  where ");
		if(!StringUtils.isEmpty(columnName)){
			sql.append(columnName).append(" like '%").append(key).append("%' ").
				append(" limit ").
				append(pageIndex).
				append(",").
				append(pageSize);
		}
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{});
		return JSON.toJSONString(list);
	}
	@Override
	public String getProPlayerInfo(String key, String columnName,
			int pageIndex, int pageSize) {
		StringBuilder sql=new StringBuilder().append("select * from prof_player  where ");
			sql.append(" name ").append(" like '%%' and point !='' ").
				append("  order by point limit ").
				append(pageIndex).
				append(",").
				append(pageSize);
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{});
		return JSON.toJSONString(list);
	}
	@Override
	public String getClubRank(String clubName, String assoName,int pageIndex, int pageSize) {
		StringBuilder sql=new StringBuilder().append("select * from club   ");
		if(!StringUtils.isEmpty(clubName)){
			sql.append(" where name like '%").append(clubName).append("%' ").
				append(" order by rank  limit ").
				append(pageIndex).
				append(",").
				append(pageSize);
		}else{
			sql.append("where (game !='' AND asso_name LIKE '%"+"中超"+"%') or (game !='' AND asso_name LIKE '%"+"中甲"+"%') OR mark ='1' or mark='0' ").
			append(" order by rank ").
			append(" limit ").
			append(pageIndex).
			append(",").
			append(pageSize);
		}
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{});
		System.out.println(list);
		return JSON.toJSONString(list);
	}
	@Override
	public String getRefereeInfo(String keyName, int pageIndex, int pageSize) {
		StringBuilder sql=new StringBuilder().append("select * from referee   ");
		if(!StringUtils.isEmpty(keyName)){
			sql.append(" where name like '%").append(keyName).append("%' ").
				append(" order by rank  limit ").
				append(pageIndex).
				append(",").
				append(pageSize);
		}else{
			sql.append(" limit ").
			append(pageIndex).
			append(",").
			append(pageSize);
		}
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{});
		System.out.println(list);
		return JSON.toJSONString(list);
	}
	@Override
	public String getPunishInfo(String provinceName, int pageIndex, int pageSize) {
		StringBuilder sql=new StringBuilder().append("select * from gather   ");
		if(!StringUtils.isEmpty(provinceName)){
			sql.append(" where province like '%").append(provinceName).append("%' ").
				append(" limit ").
				append(pageIndex).
				append(",").
				append(pageSize);
		}else{
			sql.append(" limit ").
			append(pageIndex).
			append(",").
			append(pageSize);
		}
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{});
		System.out.println(list);
		return JSON.toJSONString(list);
	}
	
	
	@Override
	public String getWorldRankInfo(String countryName, int pageIndex,
			int pageSize) {
		StringBuilder sql=new StringBuilder().append("select * from lists   ");
		if(!StringUtils.isEmpty(countryName)){
			sql.append(" where country like '%").append(countryName).append("%' ").
				append(" limit ").
				append(pageIndex).
				append(",").
				append(pageSize);
		}else{
			sql.append(" limit ").
			append(pageIndex).
			append(",").
			append(pageSize);
		}
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{});
		System.out.println(list);
		return JSON.toJSONString(list);
	}
	@Override
	public String getChinaInfo() {
		StringBuilder sql=new StringBuilder().append("select lists.*,(SELECT COUNT(*) FROM club) AS clubTotal ,(SELECT SUM(gather.punish_number) FROM gather) AS punishTotal from lists  where country like '%中超%' or mark ='1' ");
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{});
		System.out.println(list);
		return JSON.toJSONString(list);
	}
	@Override
	public String getRegionSoilBased() {
		StringBuilder sql=new StringBuilder().append("SELECT temp1.province,temp1.pingying, A,C,F ,(CASE  WHEN T<0 then 0  ELSE T END )AS T ,K FROM (  select  temp.pingying,temp.province,81*A as A ,B as C ,(5*C+5*F) AS F , ROUND((((4000/31/20)*(20-E)-8*D)),2) as T, ROUND(((81*A+B+5*C-13*D+5*D+(4000/31/20)*(20-E)+5*F)/10),2) AS K from (SELECT province ,pingying,(select COUNT(*) FROM club WHERE province= gather.province) as A ,	gather.referee_number as B,gather.top_referee_number AS C , gather.punish_number AS D, (SELECT COUNT(*) FROM prof_team WHERE city =gather.province) AS F, 6 AS E FROM gather) AS temp ) AS temp1 ORDER BY temp1.pingying;");
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{});
		System.out.println(list);
		return JSON.toJSONString(list);
	}
	@Override
	public String getNewsBaseInfo(String key,int pageIndex,int pageSize) {
		StringBuilder sql=new StringBuilder().append("select * from news  where type=?");
		sql.append(" limit ").
			append(pageIndex).
			append(",").
			append(pageSize);
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{key});
		System.out.println(list);
		return JSON.toJSONString(list);
	}
	@Override
	public String getNewsDetail(String newsId) {
		StringBuilder sql=new StringBuilder().append("select * from news  where news_ID= ?");
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{newsId});
		System.out.println(list);
		return JSON.toJSONString(list);
	}
	@Override
	public String getCommentByNewsId(String newsId) {
		StringBuilder sql=new StringBuilder().append("select * from comment  where news_ID= ?");
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{newsId});
		System.out.println(list);
		return JSON.toJSONString(list);
	}
	@Override
	public void insertNewsInfo(String str) {
		// 如果新闻不存在则插入,否则进行每条新闻的评论数量对比,(同时要通知前端,新闻数量有变化的接口.)
		JSONObject json=JSONObject.parseObject(str);
		StringBuilder sql=new StringBuilder().append("insert into news (news_ID,province,quantity,type) values(?,?,?,0) ");
		int res=jdbcTemplate.update(sql.toString(),
			new Object[]{json.getString("FM_NEWS_ID"),
						 json.getString("_fm_news__FM_NEWS_AREA_"),
						 json.getString("FM_NEWS_COMTNUM")});
		System.out.println("插入返回值:"+res);
	}
	
	@Override
	public void insertComments(String str) {
		JSONObject json=JSONObject.parseObject(str);
		StringBuilder sqlQuery=new StringBuilder().append("select * from comment where commentId=?");
		List<Map<String, Object>> queryList=jdbcTemplate.queryForList(sqlQuery.toString(),new Object[]{json.getString("CommentId")});
		int res=0;
		if(queryList.size()>0){
			StringBuilder sql=new StringBuilder().append("update  comment set user_ID=?,user_name=?,news_ID=?,time=?,comment_diension=?,pic=?  where commentId=?");
			res=jdbcTemplate.update(sql.toString(),
					new Object[]{json.getString("UserId"),
								 json.getString("UserName"),
								 json.getString("BusinessId"),
								 json.getString("CREATEDATE"),
								 json.getString("Content"),
								 json.getString("UserPhoto"),
								 json.getString("CommentId")});
			logger.info("update");
		}else{
			StringBuilder sql=new StringBuilder().append("insert into comment (commentId,user_ID,user_name,news_ID,time,comment_diension,pic) values(?,?,?,?,?,?,?) ");
			// 未完成
			res=jdbcTemplate.update(sql.toString(),
				new Object[]{json.getString("CommentId"),
							 json.getString("UserId"),
							 json.getString("UserName"),
							 json.getString("BusinessId"),
							 json.getString("CREATEDATE"),
							 json.getString("Content"),
							 json.getString("UserPhoto")});
			logger.info("insert");
		}
		System.out.println("评论插入返回值:"+res);
		if(res>0){
			EventBus eventBus = new EventBus("notice");
			SocketController listener = new SocketController();
	        eventBus.register(listener);
	        String msg="{\"flag_type\":\"1\",\"msg_body\":{\"new_id\":\""+json.getString("BusinessId")+"\"}}";
	        eventBus.post(msg);
		}
	}
	
	@Override
	public String getLastestComments(String newsId) {
		StringBuilder sql=new StringBuilder().append("select *,date_format(time,'%Y-%m-%d %H:%i:%S') as TM from comment where news_ID = ?  order by time limit 1");
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{newsId});
		System.out.println(list);
		return JSON.toJSONString(list);
	}
	@Override
	public void updateNewsInfo(String str) {
		JSONObject json=JSONObject.parseObject(str);
		StringBuilder sql=new StringBuilder().append("update  news set quantity=? where news_ID=? ");
		int res=jdbcTemplate.update(sql.toString(),
				new Object[]{json.getString("FM_NEWS_COMTNUM"),
			                 json.getString("FM_NEWS_ID")});
		logger.info("update {}",res);
	}
	
	@Override
	public String getVideoInfo(String videoId,int pageIndex,int pageSize) {
		StringBuilder sql=new StringBuilder().append("select * from video   ");
		List<Map<String, Object>> list=null;
		if(!StringUtils.isEmpty(videoId)){
			sql.append(" where video_ID=? ").
				append(" limit ").
				append(pageIndex).
				append(",").
				append(pageSize);
			list=jdbcTemplate.queryForList(sql.toString(), new Object[]{videoId});
		}else{
			sql.append(" limit ").
				append(pageIndex).
				append(",").
				append(pageSize);
			list=jdbcTemplate.queryForList(sql.toString(), new Object[]{});
		}
		System.out.println(list);
		return JSON.toJSONString(list);
	}
	
	@Override
	public String updatePageInfo(String pageNum, String actionType,
			String subPageFlag, String subPageNum) {
		StringBuilder sql=new StringBuilder().append("update action_page set page_num=?, action_type=? ,sub_page_num=? ,sub_page_flag=?,mark=? where id=? ");
		int res=jdbcTemplate.update(sql.toString(),
				new Object[]{pageNum,actionType,subPageNum,subPageFlag,System.currentTimeMillis(),"000000000"});
		logger.info("update {}",res);
		if(res>0){
			// 更新成功后通知前端
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list = jdbcTemplate.queryForList("select * from action_page where id='000000000'",new Object[] {});
			EventBus eventBus = new EventBus("notice");
			SocketController listener = new SocketController();
	        eventBus.register(listener);
	        String msg="{\"flag_type\":\"0\",\"msg_body\":"+JSON.toJSON(list.get(0)).toString()+"}";
	        eventBus.post(msg);
	        logger.info("传给前台的数据为:{}",msg);
			return "success";
		}else{
			return "fail";
		}
	}
	
	@Override
	public String selectPageInfo() {
		StringBuilder sql=new StringBuilder().append("select * from action_page where id='000000000'");
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{});
		return JSON.toJSONString(list);
	}
	@Override
	public String getHotInformation() {
		StringBuilder sql=new StringBuilder().append("select * from sevengames  order by game_ID");
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{});
		List<Map<String, Object>> res=new ArrayList<Map<String, Object>>();
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> object=new HashMap<String, Object>();
				object.put("game_ID", list.get(i).get("game_ID"));
				object.put("game_name", list.get(i).get("game_name"));
				object.put("game_introduction", list.get(i).get("game_introduction"));
				object.put("people_num", list.get(i).get("people_num"));
				object.put("team_num", list.get(i).get("team_num"));
				/*StringBuilder querySql=new StringBuilder().append("select * from sevengamesnews where game_ID=?   ");
				System.out.println(list.get(i).get("game_ID"));
				listSql=jdbcTemplate.queryForList(querySql.toString(), new Object[]{list.get(i).get("game_ID")});
				System.out.println(listSql.size());
				System.out.println(JSON.toJSONString(listSql,SerializerFeature.DisableCircularReferenceDetect));
				if(listSql.size()>0){
					object.put("game_news_array", listSql);
				}else{
					object.put("game_news_array","[]");
				}*/
				res.add(object);
			}
		}
		return JSON.toJSONString(res);
	}
	@Override
	public String getHotInformationNews(String game_ID) {
		StringBuilder sql=new StringBuilder().append("select * from sevengamesnews where game_ID=?");
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString(), new Object[]{game_ID});
		return JSON.toJSONString(list);
	}
}
