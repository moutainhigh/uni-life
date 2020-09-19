package com.uni.service.user.dto.search;

import java.util.Map;

import com.uni.framework.crud.base.dto.BaseSearchDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  UserSign Dto generated by auto
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-09-17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserSignSearchDto extends BaseSearchDto{


    /**
     * TODO 字段信息描述
     */
    private String uid;

    /**
     * TODO 字段信息描述
     */
    private String nick;

    /**
     * TODO 字段信息描述
     */
    private String icon;

    /**
     * TODO 字段信息描述
     */
    private String source;

    /**
     * 实现父类的查询条件过滤
     *
     */
    @Override
    public void buildSearchParams(Map<String, Object> map) {
        super.putNoNull("EQ_uid", this.uid, map);
        super.putNoNull("EQ_nick", this.nick, map);
        super.putNoNull("EQ_icon", this.icon, map);
        super.putNoNull("EQ_source", this.source, map);
    }

}