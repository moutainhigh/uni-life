package com.uni.service.rbac.dto.search;

import java.util.Map;

import com.uni.framework.crud.base.dto.BaseSearchDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  RbacPost Dto generated by auto
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-09-17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RbacPostSearchDto extends BaseSearchDto{


    /**
     * TODO 字段信息描述
     */
    private String postPid;

    /**
     * TODO 字段信息描述
     */
    private String postName;

    /**
     * TODO 字段信息描述
     */
    private String postCode;

    /**
     * TODO 字段信息描述
     */
    private String postIcon;

    /**
     * TODO 字段信息描述
     */
    private String salary;

    /**
     * 实现父类的查询条件过滤
     *
     */
    @Override
    public void buildSearchParams(Map<String, Object> map) {
        super.putNoNull("EQ_postPid", this.postPid, map);
        super.putNoNull("EQ_postName", this.postName, map);
        super.putNoNull("EQ_postCode", this.postCode, map);
        super.putNoNull("EQ_postIcon", this.postIcon, map);
        super.putNoNull("EQ_salary", this.salary, map);
    }

}
