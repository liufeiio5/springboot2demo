package com.qgwy.alpha_web_manager.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qgwy.alpha_web_manager.annotation.DataSource;
import com.qgwy.alpha_web_manager.bean.ProductItem;
import com.qgwy.alpha_web_manager.mapper.ProductItemMapper;
import com.qgwy.alpha_web_manager.service.ProductItemService;
import com.qgwy.alpha_web_manager.vo.ItemListVo;
import com.qgwy.alpha_web_manager.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class ProductItemServiceImpl extends ServiceImpl<ProductItemMapper,ProductItem> implements ProductItemService {

    @Autowired
    private ProductItemMapper productItemMapper;

    @Override
    @DataSource(name="second")
    public Page<ProductDetailVo> getProductDetail(Page<ProductDetailVo> page, Integer marketId, Integer categoryId){
        return page.setRecords(this.productItemMapper.getProductDetails(page,marketId,categoryId));
    }


    @Override
    @DataSource(name = "second")
    public IPage<ProductItem> getProductItemList(Page<ProductItem> page, Integer marketId){
        //条件构造器，单表查询数据
        QueryWrapper<ProductItem> queryWrapper = new QueryWrapper<ProductItem>()
                .eq("market_id",marketId)
                .select("product_id","product_name","product_cost_price","product_price","create_date");
        return this.productItemMapper.selectPage(page,queryWrapper);

    }

    @Override
    public IPage<ProductItem> getItemList(Page page){
        //查询构造器，将数据查询出来
        QueryWrapper<ProductItem> queryWrapper = new QueryWrapper<>();
        //分页器，传入分页条件page，需要分页的数据，根据分页条件对传如的参数进行分页
        IPage<ProductItem> itemIPage = productItemMapper.selectPage(page,queryWrapper);
        //返回分页后的数据
        return itemIPage;
    }

    /**
     * mybatis-plus自带的单表分页方法
     * @param offset 分页条件
     * @param marketId 超市Id
     * @param categoryId 分类ID
     * @return
     */
    @Override
    public Page<ItemListVo> getItemListVo(Integer offset,Integer pageNumber, Integer marketId, Integer categoryId){

        //分页构造器，传如分页条件：page--当前第几页，size--每一页的数据量
        Page<ItemListVo> page = new Page<>(offset,pageNumber);
        //mapper接口方法查询数据,传入一个分页构造器，查询的时候mybatis-plus会帮我们将分页的条件SQL语句进行拼接
        List<ItemListVo> listVos = productItemMapper.getItemListVo(page,marketId,categoryId);
        //将查询到的数据set到Page对象里面，获得数据的总条数，页数，当前第几页
        page.setRecords(listVos);
        //将查询到的数据set到分页器进行分页
        return page;
    }

    /**
     * 查询product表查询所需字段的信息
     * @param currPage 当前第几页
     * @param size 每一页数据大小
     * @param categoryId 查询条件：分类Id
     * @return
     */
    @Override
    public IPage<ProductItem> getProductItemInfo(Integer currPage,Integer size,Integer categoryId){
        //分页构造器，传入分页参数
        Page page = new Page(currPage,size);
        //条件构造查询
        QueryWrapper<ProductItem> queryWrapper = new QueryWrapper<ProductItem>()
                .eq("category_id",categoryId) //查询条件
                .select("product_id","product_name","category_id","origin","sp1");//查询返回的字段
        return productItemMapper.selectPage(page,queryWrapper);
    }

}
