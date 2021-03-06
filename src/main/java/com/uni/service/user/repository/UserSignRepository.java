package com.uni.service.user.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.uni.framework.crud.base.BaseRepository;

import com.uni.service.user.entity.UserSign;

/**
 * repository for UserSign generated by jpa-codegen
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-10-04.
 */
@RepositoryRestResource(path = "userSign", itemResourceRel = "resource", collectionResourceRel = "resources")
public interface UserSignRepository extends BaseRepository<UserSign, Long> {


}
