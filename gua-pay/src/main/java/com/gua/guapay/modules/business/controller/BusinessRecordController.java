package com.gua.guapay.modules.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gua.guapay.modules.business.entity.BusinessRecord;
import com.gua.guapay.modules.business.pojo.qo.BusinessQo;
import com.gua.guapay.modules.business.pojo.qo.PageQo;
import com.gua.guapay.modules.business.pojo.vo.BusinessVo;
import com.gua.guapay.modules.business.service.IBusinessRecordService;
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
@RequestMapping("/businessRecord")
public class BusinessRecordController {

    @Autowired
    private IBusinessRecordService businessRecordService;

    @PostMapping("/business")
    public BusinessVo business(@RequestBody BusinessQo businessQo){
        return businessRecordService.business(businessQo);
    }

    @PostMapping("/pageBusinessRecord")
    public IPage<BusinessRecord> pageBusinessRecord(@RequestBody PageQo pageQo){
        IPage<BusinessRecord> page = new Page<>(pageQo.getPageNo(),pageQo.getPageSize());
        return businessRecordService.pageBusinessRecord(page);
    }




}

