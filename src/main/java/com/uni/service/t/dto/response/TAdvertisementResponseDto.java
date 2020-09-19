package com.uni.service.t.dto.response;

import org.springframework.hateoas.server.core.Relation;

import com.uni.framework.crud.base.dto.BaseResponseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.time.LocalDateTime;

/**
 *  TAdvertisement generated by auto
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-08-26.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "resources")
public class TAdvertisementResponseDto extends BaseResponseDto {

    /**
     *
     */
    private static final long serialVersionUID = -1L;

    /**
     * TODO 字段信息描述
     */
    private Integer advertisementId;

    /**
     * TODO 字段信息描述
     */
    private String userId;

    /**
     * TODO 字段信息描述
     */
    private String creatorId;

    /**
     * TODO 字段信息描述
     */
    private String advertisementImg;

    /**
     * TODO 字段信息描述
     */
    private String advertisementUrl;

    /**
     * TODO 字段信息描述
     */
    private Integer clicksCount;

    /**
     * TODO 字段信息描述
     */
    private LocalDateTime createTime;

    /**
     * TODO 字段信息描述
     */
    private LocalDateTime updateTime;

}