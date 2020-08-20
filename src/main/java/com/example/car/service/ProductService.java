package com.example.car.service;

import com.example.car.dto.ProductDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.ProductWithBLOBs;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;

import java.util.List;

public interface ProductService {

    public DataGridResult findByPage(QueryDTO queryDTO);

    public R insert(ProductDTO productDTO);

    public R deleProductList(List<Long> ids);

    public R findProductById(Integer id);

    public R updateProduct(ProductWithBLOBs productWithBLOBs);

}
