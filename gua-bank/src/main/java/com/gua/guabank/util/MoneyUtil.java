package com.gua.guabank.util;

import java.math.BigDecimal;

public class MoneyUtil {
    private MoneyUtil(){

    }

    /**
     * 余额不足
     * @return
     */
    public static boolean notEnoughMoney(BigDecimal nowMoney,BigDecimal withdrwMoney){
        return nowMoney.compareTo(withdrwMoney) == -1;

    }
}
