package com.example.car.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.car.dao.ProductMapper;
import com.example.car.dto.ProductDTO;
import com.example.car.dto.QueryDTO;
import com.example.car.pojo.ProductExample;
import com.example.car.pojo.ProductWithBLOBs;
import com.example.car.service.ProductService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(queryDTO.getSearch())){
            criteria.andNameLike("%"+queryDTO.getSearch()+"%");
        }
        if(!StringUtils.isEmpty(queryDTO.getSort())){
            example.setOrderByClause("id"+queryDTO.getOrder());
        }
        List<ProductWithBLOBs> productWithBLOBs = productMapper.selectByExampleWithBLOBs(example);
        PageInfo<ProductWithBLOBs> productWithBLOBsPageInfo = new PageInfo<>(productWithBLOBs);
        DataGridResult result = new DataGridResult();
        result.setTotal(productWithBLOBsPageInfo.getTotal());
        result.setRows(productWithBLOBs);
        return result;
    }

    @Override
    public R insert(ProductDTO productCarDTO) {
        ProductWithBLOBs productWithBLOBs = new ProductWithBLOBs();
        productWithBLOBs.setName(productCarDTO.getName());
        productWithBLOBs.setService(productCarDTO.getService());
        int i = productMapper.insert(productWithBLOBs);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("添加失败");
        }
    }

    @Override
    public R deleProductList(List<Long> ids) {
        int i = productMapper.deleProductCarList(ids);
        if(i>0) {
            return R.ok();
        }else {
            return R.error("删除失败");
        }
    }

    @Override
    public R findProductById(Integer id) {
        ProductWithBLOBs productWithBLOBs = productMapper.selectByPrimaryKey(id);
        return R.ok().put("product",productWithBLOBs);
    }

    @Override
    public R updateProduct(ProductWithBLOBs productWithBLOBs) {
        int i = productMapper.updateByPrimaryKeyWithBLOBs(productWithBLOBs);
        return i>0?R.ok():R.error("修改失败");
    }
}
