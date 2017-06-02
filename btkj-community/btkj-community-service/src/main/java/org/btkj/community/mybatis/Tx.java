package org.btkj.community.mybatis;

import javax.annotation.Resource;

import org.btkj.community.redis.CommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tx")
public class Tx {
	
	@Resource
	private CommentMapper commentMapper;

	@Transactional
	public void storeComments(int articleId) {
		commentMapper.storeComments(articleId);
	}
}
