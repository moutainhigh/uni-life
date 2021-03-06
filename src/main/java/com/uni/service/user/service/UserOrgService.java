package com.uni.service.user.service;

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
import com.uni.service.user.entity.UserOrg;
import com.uni.service.user.dto.request.UserOrgRequestDto;
import com.uni.service.user.dto.response.UserOrgResponseDto;
import com.uni.service.user.dto.search.UserOrgSearchDto;

import lombok.extern.slf4j.Slf4j;

/**
 * service for UserOrg generated by jpa-codegen
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-10-04.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class UserOrgService extends BaseService<UserOrg, Long> {

    //@Autowired
    //private UserOrgRepository userOrgRepository;

    /**
     * 创建实体
     *
     * @param userOrgRequestDto 表单
     * @return 实体对象
     */
    @Transactional(readOnly = false)
    public UserOrgResponseDto create(UserOrgRequestDto userOrgRequestDto) {
        UserOrg userOrg = new UserOrg();
        super.mapper(userOrg, userOrgRequestDto);
        userOrg = super.save(userOrg);
        return super.mapperByClass(userOrg, UserOrgResponseDto.class);
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
    public UserOrgResponseDto update(Long id, UserOrgRequestDto userOrgRequestDto) {
        UserOrg userOrg = super.findById(id).get();
        super.mapper(userOrg, userOrgRequestDto);
        userOrg = super.save(userOrg);
        return super.mapperByClass(userOrg, UserOrgResponseDto.class);
    }

    /**
     * 获取一个实体对象
     *
     * @param id 实体id
     * @return 实体对象
     */
    public UserOrgResponseDto details(Long id) {
        UserOrg userOrg = super.findById(id).get();
        return super.mapperByClass(userOrg, UserOrgResponseDto.class);
    }

    /**
     * 分页列表
     *
     * @param searchParams 搜索参数
     * @param pageInfo     分页信息
     * @return 分页列表
     */
    public Page<UserOrgResponseDto> getPageList(Map<String, Object> searchParams, PageInfo pageInfo) {
        BaseSearchDto searchDto = super.conver(searchParams, UserOrgSearchDto.class);
        Map<String, Object> searchMap = searchDto.getSearchParams();
        log.debug("UserOrg的分页搜索的条件是={},排序的字段为={}", searchMap, pageInfo.getSortType());
        Page<UserOrg> page = super.findPageBySort(searchMap, pageInfo.getNumber(), pageInfo.getSize(), Direction.DESC,
                pageInfo.getSortType().split(","));
        Page<UserOrgResponseDto> respPage = new PageImpl<>(super.mapperList(page.getContent(), UserOrgResponseDto.class),
                page.getPageable(), page.getTotalElements());
        return respPage;
    }

    /**
     * 根据搜索条件搜索所有符合条件的信息列表
     *
     * @param searchParams 搜索参数
     * @return 信息列表
     */
    public List<UserOrgResponseDto> findByParams(Map<String, Object> searchParams, String sortTypes) {
        BaseSearchDto searchDto = super.conver(searchParams, UserOrgSearchDto.class);
        log.debug("UserOrg的不分页搜索的参数是={}", searchDto);
        List<UserOrg> list = super.findAllBySort(searchDto.getSearchParams(), Direction.DESC, sortTypes.split(","));
        return super.mapperList(list, UserOrgResponseDto.class);
    }

}
