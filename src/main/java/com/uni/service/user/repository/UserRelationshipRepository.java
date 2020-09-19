package com.uni.service.user.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.uni.framework.crud.base.BaseRepository;

import com.uni.service.user.entity.UserRelationship;

/**
 * repository for UserRelationship generated by jpa-codegen
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-09-17.
 */
@RepositoryRestResource(path = "userRelationship", itemResourceRel = "resource", collectionResourceRel = "resources")
public interface UserRelationshipRepository extends BaseRepository<UserRelationship, Long> {


}
