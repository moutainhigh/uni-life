package com.uni.service.rbac.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uni.framework.crud.base.dto.BaseSearchDto;
import com.uni.framework.crud.base.BaseService;
import com.uni.framework.crud.base.utils.PageInfo;
import com.uni.service.rbac.entity.RbacRolePermission;
import com.uni.service.rbac.dto.request.RbacRolePermissionRequestDto;
import com.uni.service.rbac.dto.response.RbacRolePermissionResponseDto;
import com.uni.service.rbac.dto.search.RbacRolePermissionSearchDto;

import lombok.extern.slf4j.Slf4j;

/**
 * service for RbacRolePermission generated by jpa-codegen
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-09-17.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class RbacRolePermissionService extends BaseService<RbacRolePermission, Long> {

    //@Autowired
    //private RbacRolePermissionRepository rbacRolePermissionRepository;

    /**
     * 创建实体
     *
     * @param rbacRolePermissionRequestDto 表单
     * @return 实体对象
     */
    @Transactional(readOnly = false)
    public RbacRolePermissionResponseDto create(RbacRolePermissionRequestDto rbacRolePermissionRequestDto) {
        RbacRolePermission rbacRolePermission = new RbacRolePermission();
        super.mapper(rbacRolePermission, rbacRolePermissionRequestDto);
        rbacRolePermission = super.save(rbacRolePermission);
        return super.mapperByClass(rbacRolePermission, RbacRolePermissionResponseDto.class);
    }

    /**
     * 删除实体
     * @param id 实体id
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        super.findById(id).ifPresent(t -> {
            t.setIsActive(Boolean.FALSE);
            super.save(t);
        });
    }

    /**
     * 根据主键集合批量删除
     *
     * @param ids
     */
    @Transactional(readOnly = false)
    public void deleteByIds(List<Long> ids) {
        super.deleteAllById(ids);
    }

    /**
     * 更新实体
     *
     * @param dto 表单
     * @param id      实体id
     * @return 实体对象
     */
    @Transactional(readOnly = false)
    public RbacRolePermissionResponseDto update(Long id, RbacRolePermissionRequestDto rbacRolePermissionRequestDto) {
        RbacRolePermission rbacRolePermission = super.findById(id).get();
        super.mapper(rbacRolePermission, rbacRolePermissionRequestDto);
        rbacRolePermission = super.save(rbacRolePermission);
        return super.mapperByClass(rbacRolePermission, RbacRolePermissionResponseDto.class);
    }

    /**
     * 获取一个实体对象
     *
     * @param id 实体id
     * @return 实体对象
     */
    public RbacRolePermissionResponseDto details(Long id) {
        RbacRolePermission rbacRolePermission = super.findById(id).get();
        return super.mapperByClass(rbacRolePermission, RbacRolePermissionResponseDto.class);
    }

    /**
     * 分页列表
     *
     * @param searchParams 搜索参数
     * @param pageInfo     分页信息
     * @return 分页列表
     */
    public Page<RbacRolePermissionResponseDto> getPageList(Map<String, Object> searchParams, PageInfo pageInfo) {
        BaseSearchDto searchDto = super.conver(searchParams, RbacRolePermissionSearchDto.class);
        Map<String, Object> searchMap = searchDto.getSearchParams();
        log.debug("RbacRolePermission的分页搜索的条件是={},排序的字段为={}", searchMap, pageInfo.getSortType());
        Page<RbacRolePermission> page = super.findPageBySort(searchMap, pageInfo.getNumber(), pageInfo.getSize(), Direction.DESC,
                pageInfo.getSortType().split(","));
        Page<RbacRolePermissionResponseDto> respPage = new PageImpl<>(super.mapperList(page.getContent(), RbacRolePermissionResponseDto.class),
                page.getPageable(), page.getTotalElements());
        return respPage;
    }

    /**
     * 根据搜索条件搜索所有符合条件的信息列表
     *
     * @param searchParams 搜索参数
     * @return 信息列表
     */
    public List<RbacRolePermissionResponseDto> findByParams(Map<String, Object> searchParams, String sortTypes) {
        BaseSearchDto searchDto = super.conver(searchParams, RbacRolePermissionSearchDto.class);
        log.debug("RbacRolePermission的不分页搜索的参数是={}", searchDto);
        List<RbacRolePermission> list = super.findAllBySort(searchDto.getSearchParams(), Direction.DESC, sortTypes.split(","));
        return super.mapperList(list, RbacRolePermissionResponseDto.class);
    }

}
