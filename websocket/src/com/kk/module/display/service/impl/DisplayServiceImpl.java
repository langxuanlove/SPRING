package com.gnet.module.display.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gnet.module.display.dao.IDisplayDao;
import com.gnet.module.display.service.IDisplayService;

@Service(value="displayService")
public class DisplayServiceImpl implements IDisplayService{

	private final static Logger logger=LoggerFactory.getLogger(DisplayServiceImpl.class);
	@Resource(name="displayDao")
	private IDisplayDao displayDao;
	@Override
	public String getProClubInfo(String key, String columnName, int beginIndex,
			int pageSize) {
		return displayDao.getProClubInfo(key, columnName, beginIndex, pageSize);
	}
	@Override
	public String getProTeamInfo(String key, String columnName, int beginIndex,
			int pageSize) {
		return displayDao.getProTeamInfo(key, columnName, beginIndex, pageSize);
	}
	@Override
	public String getProPlayerInfo(String key, String columnName,
			int pageIndex, int pageSize) {
		return displayDao.getProPlayerInfo(key, columnName, pageIndex, pageSize);
	}
	@Override
	public String getClubRank(String clubName, String assoName, int pageIndex, int pageSize) {
		return displayDao.getClubRank(clubName,assoName, pageIndex, pageSize);
	}
	@Override
	public String getRefereeInfo(String keyName, int pageIndex, int pageSize) {
		return displayDao.getRefereeInfo(keyName, pageIndex, pageSize);
	}
	@Override
	public String getPunishInfo(String provinceName, int pageIndex, int pageSize) {
		return displayDao.getPunishInfo(provinceName, pageIndex, pageSize);
	}
	@Override
	public String getWorldRankInfo(String countryName, int pageIndex,
			int pageSize) {
		return displayDao.getWorldRankInfo(countryName, pageIndex, pageSize);
	}
	@Override
	public String getChinaInfo() {
		return displayDao.getChinaInfo();
	}
	@Override
	public String getRegionSoilBased() {
		return displayDao.getRegionSoilBased();
	}
	@Override
	public String getNewsBaseInfo(String key,int pageIndex,int pageSize) {
		return displayDao.getNewsBaseInfo(key,pageIndex,pageSize);
	}
	@Override
	public String getNewsDetail(String newsId) {
		return  displayDao.getNewsDetail(newsId);
	}
	@Override
	public String getCommentByNewsId(String newsId) {
		return displayDao.getCommentByNewsId(newsId);
	}
	@Override
	public String getVideoInfo(String videoId,int pageIndex,int pageSize) {
		return displayDao.getVideoInfo(videoId,pageIndex,pageSize);
	}
	@Override
	public String updatePageInfo(String pageNum, String actionType,
			String subPageFlag, String subPageNum) {
		return displayDao.updatePageInfo(pageNum, actionType, subPageFlag, subPageNum);
	}
	@Override
	public String selectPageInfo() {
		return displayDao.selectPageInfo();
	}
	@Override
	public String getHotInformation() {
		return displayDao.getHotInformation();
	}
	@Override
	public String getHotInformationNews(String game_ID) {
		return displayDao.getHotInformationNews(game_ID);
	}
	
	
}
