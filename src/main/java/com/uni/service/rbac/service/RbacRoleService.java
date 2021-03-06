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
import com.uni.service.rbac.entity.RbacRole;
import com.uni.service.rbac.dto.request.RbacRoleRequestDto;
import com.uni.service.rbac.dto.response.RbacRoleResponseDto;
import com.uni.service.rbac.dto.search.RbacRoleSearchDto;

import lombok.extern.slf4j.Slf4j;

/**
 * service for RbacRole generated by jpa-codegen
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-10-04.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class RbacRoleService extends BaseService<RbacRole, Long> {

    //@Autowired
    //private RbacRoleRepository rbacRoleRepository;

    /**
     * 创建实体
     *
     * @param rbacRoleRequestDto 表单
     * @return 实体对象
     */
    @Transactional(readOnly = false)
    public RbacRoleResponseDto create(RbacRoleRequestDto rbacRoleRequestDto) {
        RbacRole rbacRole = new RbacRole();
        super.mapper(rbacRole, rbacRoleRequestDto);
        rbacRole = super.save(rbacRole);
        return super.mapperByClass(rbacRole, RbacRoleResponseDto.class);
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
    public RbacRoleResponseDto update(Long id, RbacRoleRequestDto rbacRoleRequestDto) {
        RbacRole rbacRole = super.findById(id).get();
        super.mapper(rbacRole, rbacRoleRequestDto);
        rbacRole = super.save(rbacRole);
        return super.mapperByClass(rbacRole, RbacRoleResponseDto.class);
    }

    /**
     * 获取一个实体对象
     *
     * @param id 实体id
     * @return 实体对象
     */
    public RbacRoleResponseDto details(Long id) {
        RbacRole rbacRole = super.findById(id).get();
        return super.mapperByClass(rbacRole, RbacRoleResponseDto.class);
    }

    /**
     * 分页列表
     *
     * @param searchParams 搜索参数
     * @param pageInfo     分页信息
     * @return 分页列表
     */
    public Page<RbacRoleResponseDto> getPageList(Map<String, Object> searchParams, PageInfo pageInfo) {
        BaseSearchDto searchDto = super.conver(searchParams, RbacRoleSearchDto.class);
        Map<String, Object> searchMap = searchDto.getSearchParams();
        log.debug("RbacRole的分页搜索的条件是={},排序的字段为={}", searchMap, pageInfo.getSortType());
        Page<RbacRole> page = super.findPageBySort(searchMap, pageInfo.getNumber(), pageInfo.getSize(), Direction.DESC,
                pageInfo.getSortType().split(","));
        Page<RbacRoleResponseDto> respPage = new PageImpl<>(super.mapperList(page.getContent(), RbacRoleResponseDto.class),
                page.getPageable(), page.getTotalElements());
        return respPage;
    }

    /**
     * 根据搜索条件搜索所有符合条件的信息列表
     *
     * @param searchParams 搜索参数
     * @return 信息列表
     */
    public List<RbacRoleResponseDto> findByParams(Map<String, Object> searchParams, String sortTypes) {
        BaseSearchDto searchDto = super.conver(searchParams, RbacRoleSearchDto.class);
        log.debug("RbacRole的不分页搜索的参数是={}", searchDto);
        List<RbacRole> list = super.findAllBySort(searchDto.getSearchParams(), Direction.DESC, sortTypes.split(","));
        return super.mapperList(list, RbacRoleResponseDto.class);
    }

}
