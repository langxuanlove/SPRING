/**
 * 版权声明： 版权所有 违者必究 2012
 * 日    期：12-6-8
 */
package com.keytec.activemq.rop;

import java.util.Date;

import com.keytec.activemq.utils.DateUtils;
import com.rop.request.RopConverter;

/**
 * @author : chenxh(quickselect@163.com)
 * @date: 14-3-18
 */
public class DateConverter implements RopConverter<String,Date> {


    public Date convert(String s) {
        return DateUtils.parseDate(s,DateUtils.SHOW_DATETIME_FORMAT);
    }


    public String unconvert(Date date) {
        return DateUtils.format(date,DateUtils.SHOW_DATETIME_FORMAT);
    }


    public Class<String> getSourceClass() {
        return String.class;
    }


    public Class<Date> getTargetClass() {
        return Date.class;
    }
}
