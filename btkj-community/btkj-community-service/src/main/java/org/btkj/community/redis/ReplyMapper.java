package org.btkj.community.redis;

import java.util.List;

import org.btkj.community.mybatis.dao.ReplyDao;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Reply;
import org.btkj.pojo.model.Pager;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;
import org.rapid.util.common.message.Result;

public class ReplyMapper extends RedisProtostuffDBMapper<Integer, Reply, ReplyDao> {

	public ReplyMapper() {
		super(BtkjTables.REPLY, "hash:db:reply");
	}
	
	public Result<Pager<Reply>> replies(int quizId, int page, int pageSize) {
		int total = dao.total(quizId);
		if (0 == total)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		pageSize = Math.max(1, pageSize);    // 至少显示一条数据
		int mod = total % pageSize;
		total = 0 == mod ? total / pageSize : (total / pageSize) + 1;
		page = Math.max(1, page);
		page = Math.min(total, page);
		int start = (page - 1) * pageSize;
		List<Reply> list = dao.paging(quizId, start, pageSize);
		return Result.result(new Pager<Reply>(total, list));
	}
}
