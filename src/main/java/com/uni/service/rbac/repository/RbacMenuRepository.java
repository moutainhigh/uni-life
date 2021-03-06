package com.uni.service.rbac.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.uni.framework.crud.base.BaseRepository;

import com.uni.service.rbac.entity.RbacMenu;

/**
 * repository for RbacMenu generated by jpa-codegen
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-10-04.
 */
@RepositoryRestResource(path = "rbacMenu", itemResourceRel = "resource", collectionResourceRel = "resources")
public interface RbacMenuRepository extends BaseRepository<RbacMenu, Long> {


}
