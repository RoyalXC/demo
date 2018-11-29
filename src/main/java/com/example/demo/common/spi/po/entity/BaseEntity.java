package com.example.demo.common.spi.po.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述:
 *
 * @author 薛行晨(RoyalXC)
 * @date 2018/11/29 11:06
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -4508550774691259111L;
    private Boolean isValid;
    private Date cTime;
    private Date mTime;
    private String remark;

    public BaseEntity() {
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }
}
