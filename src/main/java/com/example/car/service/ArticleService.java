package com.example.car.service;

import com.example.car.dto.ArticleDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.ActivityWithBLOBs;
import com.example.car.pojo.Article;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;

import java.util.List;

public interface ArticleService {

    public DataGridResult findByPage(QueryDTO queryDTO);

    public R insert(ArticleDTO articleDTO);

    public R deleArticleList(List<Long> ids);

    public R findArticleById(Long id);

    public R updateArticle(Article article);
}
