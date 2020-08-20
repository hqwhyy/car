package com.example.car.action;

import com.example.car.dto.ProductDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.ProductWithBLOBs;
import com.example.car.service.ProductService;
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
public class ProductAction {

    @Autowired
    private ProductService ProductService;

    @RequestMapping("/sys/product/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDTO queryDTO){
        return ProductService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/product/save")
    @ResponseBody
    public R insertTag(@RequestBody ProductDTO productDTO){
        return ProductService.insert(productDTO);
    }

    @RequestMapping("/sys/product/del")
    @ResponseBody
    public R deleTagList(@RequestBody List<Long> ids){
        return ProductService.deleProductList(ids);
    }

    @RequestMapping("/sys/product/info/{id}")
    @ResponseBody
    public R tagInfo(@PathVariable("id") Integer id){
        return ProductService.findProductById(id);
    }

    @RequestMapping("/sys/product/update")
    @ResponseBody
    public R updateTag(@RequestBody ProductWithBLOBs productWithBLOBs){
        return ProductService.updateProduct(productWithBLOBs);
    }
}
