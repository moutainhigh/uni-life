package com.uni.service.user.dto.response;

import org.springframework.hateoas.server.core.Relation;

import com.uni.framework.crud.base.dto.BaseResponseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *  UserOrg generated by auto
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-09-17.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "resources")
public class UserOrgResponseDto extends BaseResponseDto {

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
    private String groupId;

    /**
     * TODO 字段信息描述
     */
    private String postId;

    /**
     * TODO 字段信息描述
     */
    private String groupName;

    /**
     * TODO 字段信息描述
     */
    private String postName;

}
