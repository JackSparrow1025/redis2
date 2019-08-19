package com.sunstar.service;

import java.util.List;

import com.sunstar.pojo.Article;

public interface ArticleService {

	List<Article> search();

	void deleteById(String aid);

}
