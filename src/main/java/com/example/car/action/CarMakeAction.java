package com.example.car.action;

import com.example.car.dto.CarMakeDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.CarMake;
import com.example.car.service.CarMakeService;
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
 * 品牌管理
 *
 * @author qwhe
 * @date 2020/09/29
 */
@Controller
public class CarMakeAction {

    @Autowired
    private CarMakeService carMakeService;

    @RequestMapping("/sys/make/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDTO queryDTO){
        return carMakeService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/make/save")
    @ResponseBody
    public R insert(@RequestBody CarMakeDTO carMakeDTO){
        return carMakeService.insert(carMakeDTO);
    }

    @RequestMapping("/sys/make/del")
    @ResponseBody
    public R deleCarMakeList(@RequestBody List<Long> ids){
        return carMakeService.deleCarMakeList(ids);
    }

    @RequestMapping("/sys/make/info/{id}")
    @ResponseBody
    public R CarMakeInfo(@PathVariable("id") Integer id){
        return carMakeService.findCarMakeById(id);
    }

    @RequestMapping("/sys/make/update")
    @ResponseBody
    public R updateCarMake(@RequestBody CarMake carMake){
        return carMakeService.updateCarMake(carMake);
    }
}
