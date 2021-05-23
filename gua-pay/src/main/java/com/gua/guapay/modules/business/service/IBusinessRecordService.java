package com.gua.guapay.modules.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gua.guapay.modules.business.entity.BusinessRecord;
import com.gua.guapay.modules.business.pojo.qo.BusinessQo;
import com.gua.guapay.modules.business.pojo.vo.BusinessVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-23
 */
public interface IBusinessRecordService extends IService<BusinessRecord> {

    BusinessVo business(BusinessQo businessQo);

    IPage<BusinessRecord> pageBusinessRecord(IPage<BusinessRecord> page);
}
