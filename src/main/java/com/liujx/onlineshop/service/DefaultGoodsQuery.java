package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.Good;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lenovo
 */
@Service
public class DefaultGoodsQuery extends AbstractGoodsQuery {
    @Override
    protected List<Good> sort(List<Good> goods) {
        goods.sort((good1, good2) -> {
           return (int) (good2.getClick() - good1.getClick());
        });
        return goods;
    }
}
