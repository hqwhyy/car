package com.example.car.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.car.dao.ArticleMapper;
import com.example.car.dto.ArticleDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.Article;
import com.example.car.pojo.ArticleExample;
import com.example.car.service.ArticleService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.DateUtils;
import com.example.car.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        List<ArticleDTO> list = new ArrayList<>();
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(queryDTO.getSearch())) {
            criteria.andTitleLike("%" + queryDTO.getSearch() + "%");
        }
        if(!StringUtils.isEmpty(queryDTO.getSort())){
            example.setOrderByClause("id"+queryDTO.getOrder());
        }
        List<Article> articles1 = articleMapper.selectByExampleWithBLOBs(example);
        for (Article article:articles1) {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setTitle(article.getTitle());
            articleDTO.setSource(article.getSource());
            articleDTO.setSummary(article.getSummary());
            articleDTO.setCoverPic(article.getCoverPic());
            articleDTO.setAuthor(article.getAuthor());
            String s = DateUtils.longToStr(article.getCreateTime());
            articleDTO.setCreateTime(s);
            articleDTO.setTags(article.getTags());
            Byte allowUp = article.getAllowUp();
            if(allowUp.intValue()==1) {
                articleDTO.setAllowUp(true);
            }else {
                articleDTO.setAllowUp(false);
            }
            if(article.getAllowFav().intValue()==1) {
                articleDTO.setAllowFav(true);
            }else {
                articleDTO.setAllowFav(false);
            }
            articleDTO.setContent(article.getContent());
            list.add(articleDTO);
        }
        PageInfo<Article> articlePageInfo = new PageInfo<>(articles1);
        DataGridResult result = new DataGridResult();
        result.setTotal(articlePageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public R insert(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setSource(articleDTO.getSource());
        article.setSummary(articleDTO.getSummary());
        article.setCoverPic(articleDTO.getCoverPic());
        article.setAuthor(articleDTO.getAuthor());
        Long time = null;
        try {
            time = DateUtils.strToLong(articleDTO.getCreateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        article.setCreateTime(time);
        article.setTags(articleDTO.getTags());
        Boolean allowUp = articleDTO.getAllowUp();
        if(allowUp) {
            article.setAllowUp((byte)1);
        }else {
            article.setAllowUp((byte)0);
        }
        Boolean allowFav = articleDTO.getAllowFav();
        if(allowFav) {
            article.setAllowFav((byte)1);
        }else {
            article.setAllowFav((byte)0);
        }
        article.setContent(articleDTO.getContent());
        int i = articleMapper.insert(article);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("添加失败");
        }
    }

    @Override
    public R deleArticleList(List<Long> ids) {
        int i = articleMapper.deleArticleList(ids);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("删除失败");
        }
    }

    @Override
    public R findArticleById(Long id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        return R.ok().put("activity",article);
    }

    @Override
    public R updateArticle(Article article) {
        int i = articleMapper.updateByPrimaryKeySelective(article);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("修改失败");
        }
    }
}
