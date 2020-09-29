package com.example.car.action;

import com.example.car.dto.ActivityDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.ActivityWithBLOBs;
import com.example.car.service.ActivityService;
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
 * 活动管理
 *
 * @author qwhe
 * @date 2020/09/29
 */
@Controller
public class ActivityAction {

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/sys/activity/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDTO queryDTO){
        return activityService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/activity/save")
    @ResponseBody
    public R insert(@RequestBody ActivityDTO activityDTO){
        return activityService.insert(activityDTO);
    }

    @RequestMapping("/sys/activity/del")
    @ResponseBody
    public R deleActivityList(@RequestBody List<Long> ids){
        return activityService.deleActivityList(ids);
    }

    @RequestMapping("/sys/activity/info/{id}")
    @ResponseBody
    public R activityInfo(@PathVariable("id") Long id){
        return activityService.findActivityById(id);
    }

    @RequestMapping("/sys/activity/update")
    @ResponseBody
    public R updateActivity(@RequestBody ActivityWithBLOBs activity){
        return activityService.updateActivity(activity);
    }
}
