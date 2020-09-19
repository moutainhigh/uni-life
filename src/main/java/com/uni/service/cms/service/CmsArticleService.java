package com.uni.service.cms.service;

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
import com.uni.service.cms.entity.CmsArticle;
import com.uni.service.cms.dto.request.CmsArticleRequestDto;
import com.uni.service.cms.dto.response.CmsArticleResponseDto;
import com.uni.service.cms.dto.search.CmsArticleSearchDto;

import lombok.extern.slf4j.Slf4j;

/**
 * service for CmsArticle generated by jpa-codegen
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-08-26.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class CmsArticleService extends BaseService<CmsArticle, Long> {

    //@Autowired
    //private CmsArticleRepository cmsArticleRepository;

    /**
     * 创建实体
     *
     * @param cmsArticleRequestDto 表单
     * @return 实体对象
     */
    @Transactional(readOnly = false)
    public CmsArticleResponseDto create(CmsArticleRequestDto cmsArticleRequestDto) {
        CmsArticle cmsArticle = new CmsArticle();
        super.mapper(cmsArticle, cmsArticleRequestDto);
        cmsArticle = super.save(cmsArticle);
        return super.mapperByClass(cmsArticle, CmsArticleResponseDto.class);
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
    public void deleteByIds(List<Integer> ids) {
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
    public CmsArticleResponseDto update(Long id, CmsArticleRequestDto cmsArticleRequestDto) {
        CmsArticle cmsArticle = super.findById(id).get();
        super.mapper(cmsArticle, cmsArticleRequestDto);
        cmsArticle = super.save(cmsArticle);
        return super.mapperByClass(cmsArticle, CmsArticleResponseDto.class);
    }

    /**
     * 获取一个实体对象
     *
     * @param id 实体id
     * @return 实体对象
     */
    public CmsArticleResponseDto details(Long id) {
        CmsArticle cmsArticle = super.findById(id).get();
        return super.mapperByClass(cmsArticle, CmsArticleResponseDto.class);
    }

    /**
     * 分页列表
     *
     * @param searchParams 搜索参数
     * @param pageInfo     分页信息
     * @return 分页列表
     */
    public Page<CmsArticleResponseDto> getPageList(Map<String, Object> searchParams, PageInfo pageInfo) {
        BaseSearchDto searchDto = super.conver(searchParams, CmsArticleSearchDto.class);
        Map<String, Object> searchMap = searchDto.getSearchParams();
        log.debug("CmsArticle的分页搜索的条件是={},排序的字段为={}", searchMap, pageInfo.getSortType());
        Page<CmsArticle> page = super.findPageBySort(searchMap, pageInfo.getNumber(), pageInfo.getSize(), Direction.DESC,
                pageInfo.getSortType().split(","));
        Page<CmsArticleResponseDto> respPage = new PageImpl<>(super.mapperList(page.getContent(), CmsArticleResponseDto.class),
                page.getPageable(), page.getTotalElements());
        return respPage;
    }

    /**
     * 根据搜索条件搜索所有符合条件的信息列表
     *
     * @param searchParams 搜索参数
     * @return 信息列表
     */
    public List<CmsArticleResponseDto> findByParams(Map<String, Object> searchParams, String sortTypes) {
        BaseSearchDto searchDto = super.conver(searchParams, CmsArticleSearchDto.class);
        log.debug("CmsArticle的不分页搜索的参数是={}", searchDto);
        List<CmsArticle> list = super.findAllBySort(searchDto.getSearchParams(), Direction.DESC, sortTypes.split(","));
        return super.mapperList(list, CmsArticleResponseDto.class);
    }

}
