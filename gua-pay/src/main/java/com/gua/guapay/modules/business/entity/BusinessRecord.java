package com.gua.guapay.modules.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-23
 */
@TableName("business_record")
public class BusinessRecord extends Model<BusinessRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 支出支付账号
     */
    private String expensePaynum;

    /**
     * 接受支付账号
     */
    private String acceptPaynum;

    /**
     * 交易金额
     */
    private BigDecimal businessMoney;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpensePaynum() {
        return expensePaynum;
    }

    public void setExpensePaynum(String expensePaynum) {
        this.expensePaynum = expensePaynum;
    }

    public String getAcceptPaynum() {
        return acceptPaynum;
    }

    public void setAcceptPaynum(String acceptPaynum) {
        this.acceptPaynum = acceptPaynum;
    }

    public BigDecimal getBusinessMoney() {
        return businessMoney;
    }

    public void setBusinessMoney(BigDecimal businessMoney) {
        this.businessMoney = businessMoney;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public static final String ID = "id";

    public static final String EXPENSE_PAYNUM = "expense_paynum";

    public static final String ACCEPT_PAYNUM = "accept_paynum";

    public static final String BUSINESS_MONEY = "business_money";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BusinessRecord{" +
        "id=" + id +
        ", expensePaynum=" + expensePaynum +
        ", acceptPaynum=" + acceptPaynum +
        ", businessMoney=" + businessMoney +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
