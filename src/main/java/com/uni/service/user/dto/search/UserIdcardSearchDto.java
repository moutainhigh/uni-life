package com.uni.service.user.dto.search;

import java.util.Map;

import com.uni.framework.crud.base.dto.BaseSearchDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  UserIdcard Dto generated by auto
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-09-17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserIdcardSearchDto extends BaseSearchDto{


    /**
     * TODO 字段信息描述
     */
    private String uid;

    /**
     * TODO 字段信息描述
     */
    private String idcard;

    /**
     * TODO 字段信息描述
     */
    private String name;

    /**
     * TODO 字段信息描述
     */
    private Integer age;

    /**
     * TODO 字段信息描述
     */
    private Integer sex;

    /**
     * TODO 字段信息描述
     */
    private String birthday;

    /**
     * TODO 字段信息描述
     */
    private String nation;

    /**
     * TODO 字段信息描述
     */
    private String domicile;

    /**
     * TODO 字段信息描述
     */
    private String signOrg;

    /**
     * 实现父类的查询条件过滤
     *
     */
    @Override
    public void buildSearchParams(Map<String, Object> map) {
        super.putNoNull("EQ_uid", this.uid, map);
        super.putNoNull("EQ_idcard", this.idcard, map);
        super.putNoNull("EQ_name", this.name, map);
        super.putNoNull("EQ_age", this.age, map);
        super.putNoNull("EQ_sex", this.sex, map);
        super.putNoNull("EQ_birthday", this.birthday, map);
        super.putNoNull("EQ_nation", this.nation, map);
        super.putNoNull("EQ_domicile", this.domicile, map);
        super.putNoNull("EQ_signOrg", this.signOrg, map);
    }

}