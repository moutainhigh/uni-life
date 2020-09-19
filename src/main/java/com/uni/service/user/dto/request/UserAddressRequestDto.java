package com.uni.service.user.dto.request;

import com.uni.framework.crud.base.dto.BaseRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *  UserAddress Dto generated by auto
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-09-17.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class UserAddressRequestDto extends BaseRequestDto {

    /**
     *
     */
    private static final long serialVersionUID = -1L;

    /**
     * TODO 字段信息描述
     */
    private String uid;

    /**
     * TODO 字段信息描述
     */
    private String province;

    /**
     * TODO 字段信息描述
     */
    private String city;

    /**
     * TODO 字段信息描述
     */
    private String district;

    /**
     * TODO 字段信息描述
     */
    private String street;

    /**
     * TODO 字段信息描述
     */
    private Integer typ;

    /**
     * TODO 字段信息描述
     */
    private Integer indx;

    /**
     * TODO 字段信息描述
     */
    private String contacts;

    /**
     * TODO 字段信息描述
     */
    private String phoneNum;

}
