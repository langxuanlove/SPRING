package com.gnet.module.display.service;

public interface IDisplayService {
	/**
	 * 
	 * getProClubInfo:(获取职业足球俱乐部信息表). <br/>
	 *
	 * @author Jikey
	 * @param key
	 * @param columnName
	 * @param beginIndex
	 * @param pageSize
	 * @return
	 */
	public String getProClubInfo(String key,String columnName,int pageIndex,int pageSize);
	
	
	/**
	 * 
	 * getProTeamInfo:(获取职业球队赛事信息). <br/>
	 *
	 * @author Jikey
	 * @param key
	 * @param columnName
	 * @param beginIndex
	 * @param pageSize
	 * @return
	 */
	public String getProTeamInfo(String key,String columnName,int pageIndex,int pageSize);

	
	/**
	 * 
	 * getProPlayerInfo:(获取职业球员个人信息). <br/>
	 *
	 * @author Jikey
	 * @param key
	 * @param columnName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public String getProPlayerInfo(String key,String columnName,int pageIndex,int pageSize);
	
	
	/**
	 * 
	 * getClubRank:(获取俱乐部排名). <br/>
	 *
	 * @author Jikey
	 * @param clubName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public String getClubRank(String clubName,String assoName,int pageIndex,int pageSize);
	
	/**
	 * 
	 * getRefereeInfo:(获取所有裁判的基本信息). <br/>
	 *
	 * @author Jikey
	 * @param keyName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public String getRefereeInfo(String keyName,int pageIndex,int pageSize);
	
	/**
	 * 
	 * getPunishInfo:(获取各个省的处罚次数). <br/>
	 *
	 * @author Jikey
	 * @param provinceName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public String getPunishInfo(String provinceName,int pageIndex,int pageSize);
	/**
	 * 
	 * getWorldRankInfo:(世界国家上座率排名). <br/>
	 *
	 * @author Jikey
	 * @param countryName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	
	public String getWorldRankInfo(String countryName,int pageIndex,int pageSize);
	
	/**
	 * 
	 * getChinaInfo:(获取中国的世界排名,俱乐部个数,处罚总数,人数). <br/>
	 *
	 * @author Jikey
	 * @return
	 */
	public String getChinaInfo();
	
	/**
	 * 
	 * getRegionSoilBased:(获得地域土壤指标). <br/>
	 * @author Jikey
	 * @return
	 */
	public String getRegionSoilBased();
	
	/**
	 * 
	 * getNewsBaseInfo:(这里用一句话描述这个方法的作用). <br/>
	 *
	 * @author Jikey
	 * @param key
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public String getNewsBaseInfo(String key,int pageIndex,int pageSize);
	
	/**
	 * 
	 * getNewsDetail:(新闻详细信息). <br/>
	 *
	 * @author Jikey
	 * @param newsId
	 * @return
	 */
	public String getNewsDetail(String newsId);
	
	/**
	 * 
	 * getCommentByNewsId:(根据新闻ID获取新闻的评论详细信息). <br/>
	 *
	 * @author Jikey
	 * @param newsId
	 * @return
	 */
	public String getCommentByNewsId(String newsId);
	
	/**
	 * 
	 * getVideoInfo:(获取视频媒体信息). <br/>
	 *
	 * @author Jikey
	 * @param vedioId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	 
	public String getVideoInfo(String videoId,int pageIndex,int pageSize);
	
	/**
	 * 
	 * updatePageInfo:(更新页面操作接口). <br/>
	 *
	 * @author Jikey
	 * @param pageNum
	 * @param actionType
	 * @param subPageFlag
	 * @param subPageNum
	 * @return
	 */
	public String updatePageInfo(String pageNum,String actionType,String subPageFlag,String subPageNum );
	
	/**
	 * 
	 * selectPageInfo:(查询当前页是第几页的信息). <br/>
	 *
	 * @author Jikey
	 * @return
	 */
	public String selectPageInfo();
	
	/**
	 * 
	 * getHotInformation:(获取热力图足球新闻). <br/>
	 *
	 * @author Jikey
	 * @return
	 */
	
	public String getHotInformation();
	/**
	 * 
	 * getHotInformation:(获取热力图足球新闻内容). <br/>
	 *
	 * @author Lizhuo
	 * @return
	 */
	
	public String getHotInformationNews(String game_ID);
}
