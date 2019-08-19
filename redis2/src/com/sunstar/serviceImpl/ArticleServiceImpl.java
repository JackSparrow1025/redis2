package com.sunstar.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunstar.mapper.ArticleMapper;
import com.sunstar.pojo.Article;
import com.sunstar.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public List<Article> search() {
		return articleMapper.search();
	}

	@Override
	public void deleteById(String aid) {
		articleMapper.deleteById(aid);
	}

	
}
