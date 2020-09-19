package com.uni.service.t.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.uni.framework.crud.base.BaseRepository;

import com.uni.service.t.entity.TAdvertisement;

/**
 * repository for TAdvertisement generated by jpa-codegen
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-08-26.
 */
@RepositoryRestResource(path = "tAdvertisement", itemResourceRel = "resource", collectionResourceRel = "resources")
public interface TAdvertisementRepository extends BaseRepository<TAdvertisement, Long> {


}
