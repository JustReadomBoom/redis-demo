package com.redis.service.global_exception;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * @Author: zqz
 * @Description:
 * @Date: Created in 14:40 2021/1/28
 */
public class TestOrder {

    @Length(min = 8, max = 32, message = "订单号长度8-32个字符")
    @NotBlank(message = "订单号不能为空")
    private String orderNo;

    @Length(min = 12, max = 64, message = "订单名称长度12-64个字符")
    @NotBlank(message = "订单名称不能为空")
    private String orderName;

    @NotNull(message = "订单金额不能为空")
    private BigDecimal orderAmt;

    @Pattern(regexp = "^1[3|4|5|8][0-9]\\d{8}$", message = "手机号码格式不正确")
    @NotBlank(message = "手机号不能为空")
    private String mobileNo;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "TestOrder{" +
                "orderNo='" + orderNo + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderAmt=" + orderAmt +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}
