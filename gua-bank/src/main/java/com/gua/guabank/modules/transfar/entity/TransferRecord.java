package com.gua.guabank.modules.transfar.entity;

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
 * @since 2021-05-22
 */
@TableName("transfer_record")
public class TransferRecord extends Model<TransferRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 转账卡id
     */
    private Long transferCardId;

    /**
     * 接受卡id
     */
    private Long acceptCardId;

    /**
     * 转账金额
     */
    private BigDecimal transferMoney;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransferCardId() {
        return transferCardId;
    }

    public void setTransferCardId(Long transferCardId) {
        this.transferCardId = transferCardId;
    }

    public Long getAcceptCardId() {
        return acceptCardId;
    }

    public void setAcceptCardId(Long acceptCardId) {
        this.acceptCardId = acceptCardId;
    }

    public BigDecimal getTransferMoney() {
        return transferMoney;
    }

    public void setTransferMoney(BigDecimal transferMoney) {
        this.transferMoney = transferMoney;
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

    public static final String TRANSFER_CARD_ID = "transfer_card_id";

    public static final String ACCEPT_CARD_ID = "accept_card_id";

    public static final String TRANSFER_MONEY = "transfer_money";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TransferRecord{" +
        "id=" + id +
        ", transferCardId=" + transferCardId +
        ", acceptCardId=" + acceptCardId +
        ", transferMoney=" + transferMoney +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
