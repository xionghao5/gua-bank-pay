package com.gua.guapay.modules.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gua.guapay.modules.business.entity.TransferRecord;
import com.gua.guapay.modules.business.pojo.qo.PageQo;
import com.gua.guapay.modules.business.pojo.qo.TransferQo;
import com.gua.guapay.modules.business.pojo.vo.PayVo;
import com.gua.guapay.modules.business.service.ITransferRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/transferRecord")
public class TransferRecordController {

    @Autowired
    private ITransferRecordService transferRecordService;

    @PostMapping("/transferIn")
    public PayVo transferIn(@RequestBody TransferQo transferQo){
        return transferRecordService.transferIn(transferQo);
    }

    @PostMapping("/takeOut")
    public PayVo takeOut(@RequestBody TransferQo transferQo){
        return transferRecordService.takeOut(transferQo);
    }


    @PostMapping("/pageTransferRecord")
    public IPage<TransferRecord> pageTransferRecord(@RequestBody PageQo pageQo){
        IPage<TransferRecord> page = new Page<>(pageQo.getPageNo(),pageQo.getPageSize());
        return transferRecordService.pageTransferRecord(page);
    }
}

