package com.example.car.action;

import com.example.car.dto.QueryDTO;
import com.example.car.dto.TagDTO;
import com.example.car.pojo.Tag;
import com.example.car.service.TagService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class TagAction {

    @Autowired
    private TagService tagService;

    @RequestMapping("/sys/tag/list")
    @ResponseBody
    public DataGridResult showTag(QueryDTO queryDTO){
        return tagService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/tag/save")
    @ResponseBody
    public R insertTag(@RequestBody TagDTO tagDTO){
        return tagService.insert(tagDTO);
    }

    @RequestMapping("/sys/tag/del")
    @ResponseBody
    public R deleTagList(@RequestBody List<Long> ids){
        return tagService.deleTagList(ids);
    }

    @RequestMapping("/sys/tag/info/{id}")
    @ResponseBody
    public R tagInfo(@PathVariable("id") Integer id){
        return tagService.findTagById(id);
    }

    @RequestMapping("/sys/tag/update")
    @ResponseBody
    public R updateTag(@RequestBody Tag tag){
        return tagService.updateTag(tag);
    }
}
