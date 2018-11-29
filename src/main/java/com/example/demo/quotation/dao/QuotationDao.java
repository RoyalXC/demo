package com.example.demo.quotation.dao;

import com.example.demo.common.spi.po.entity.QuotationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述:
 *
 * @author 薛行晨(RoyalXC)
 * @date 2018/11/29 10:47
 */
@Repository
public interface QuotationDao {
    List<QuotationEntity> selectAll();
}
