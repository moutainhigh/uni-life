package com.uni.service.rbac.dto.response;

import org.springframework.hateoas.server.core.Relation;

import com.uni.framework.crud.base.dto.BaseResponseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *  RbacCategory generated by auto
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-10-04.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "resources")
public class RbacCategoryResponseDto extends BaseResponseDto {

    private static final long serialVersionUID = -1L;

    /**
     * TODO 字段信息描述
     */
    private String pid;

    /**
     * TODO 字段信息描述
     */
    private String categoryName;

    /**
     * TODO 字段信息描述
     */
    private Integer sorted;

}
