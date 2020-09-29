package com.example.car.action;

import com.example.car.dto.ArticleDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.Article;
import com.example.car.service.ArticleService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * 资讯管理
 *
 * @author qwhe
 * @date 2020/09/29
 */
@Controller
public class ArticleAction {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/sys/article/list")
    @ResponseBody
    public DataGridResult showArticle(QueryDTO queryDTO){
        return articleService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/article/save")
    @ResponseBody
    public R insert(@RequestBody ArticleDTO articleDTO){
        return articleService.insert(articleDTO);
    }

    @RequestMapping("/sys/article/del")
    @ResponseBody
    public R deleArticleList(@RequestBody List<Long> ids){
        return articleService.deleArticleList(ids);
    }

    @RequestMapping("/sys/article/info//{id}")
    @ResponseBody
    public R ArticleInfo(@PathVariable("id") Long id){
        return articleService.findArticleById(id);
    }

    @RequestMapping("/sys/article/update")
    @ResponseBody
    public R updateArticle(@RequestBody Article article){
        return articleService.updateArticle(article);
    }
}
