package com.sunstar.mapper;

import java.util.List;

import com.sunstar.pojo.Article;

public interface ArticleMapper {

	List<Article> search();

	void deleteById(String aid);

}
