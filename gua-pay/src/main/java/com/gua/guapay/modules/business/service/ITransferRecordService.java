package com.gua.guapay.modules.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gua.guapay.modules.business.entity.TransferRecord;
import com.gua.guapay.modules.business.pojo.qo.TransferQo;
import com.gua.guapay.modules.business.pojo.vo.PayVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-23
 */
public interface ITransferRecordService extends IService<TransferRecord> {

    PayVo transferIn(TransferQo transferQo);

    PayVo takeOut(TransferQo transferQo);

    IPage<TransferRecord> pageTransferRecord(IPage<TransferRecord> page);
}
