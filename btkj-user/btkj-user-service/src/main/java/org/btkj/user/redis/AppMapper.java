package org.btkj.user.redis;

import java.util.List;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.info.AppListInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.AppSearcher;
import org.btkj.user.mybatis.dao.AppDao;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;
import org.rapid.util.common.message.Result;

public class AppMapper extends RedisProtostuffDBMapper<Integer, App, AppDao> {
	
	private static final String APP_DATA				= "hash:db:app";					

	public AppMapper() {
		super(BtkjTables.APP, APP_DATA);
	}
	
	/**
	 * 分页获取App用户
	 * 
	 * @param pager
	 * @param tid
	 */
	@SuppressWarnings("unchecked")
	public Result<Pager<AppListInfo>> appList(AppSearcher searcher) {
		int total = dao.searchCount(searcher);
		if (0 == total)
			return Result.result(Pager.EMPLTY);
		searcher.calculate(total);
		List<AppListInfo> apps = dao.search(searcher);
		Pager<AppListInfo> pager = new Pager<AppListInfo>(searcher.getTotal(), apps);
		return Result.result(pager);
	}
}
