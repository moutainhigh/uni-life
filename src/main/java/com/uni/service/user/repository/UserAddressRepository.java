package com.uni.service.user.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.uni.framework.crud.base.BaseRepository;

import com.uni.service.user.entity.UserAddress;

/**
 * repository for UserAddress generated by jpa-codegen
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-09-17.
 */
@RepositoryRestResource(path = "userAddress", itemResourceRel = "resource", collectionResourceRel = "resources")
public interface UserAddressRepository extends BaseRepository<UserAddress, Long> {


}
