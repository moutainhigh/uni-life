package com.uni.service.rbac.biz;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.uni.framework.crud.base.BaseEndpoint;
import com.uni.framework.crud.base.dto.ResponseDto;
import com.uni.framework.crud.base.utils.IConstants;
import com.uni.framework.crud.base.utils.PageInfo;
import com.uni.service.rbac.dto.request.RbacResourceRequestDto;
import com.uni.service.rbac.dto.response.RbacResourceResponseDto;
import com.uni.service.rbac.service.RbacResourceService;

/**
 * controller for RbacResource
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-09-17.
 */
@Service
public class RbacResourceBiz {

    @Autowired
    private RbacResourceService rbacResourceService;

    /**
     * 新增
     *
     * @param rbacResourceRequestDto
     * @return
     */
    public ResponseEntity<?> create(RbacResourceRequestDto rbacResourceRequestDto) {
        RbacResourceResponseDto rbacResourceResp = rbacResourceService.create(rbacResourceRequestDto);
        return super.doResource(rbacResourceResp, this.getClass());
    }

    /**
     * 根据主键删除，支持批量主键删除
     *
     * @param ids
     * @return
     */
    public ResponseDto<?> delete(@PathVariable List<Long> ids) {
        rbacResourceService.deleteByIds(ids);
        return ResponseDto.success(null);
    }

    /**
     * 更新
     *
     * @param RbacResourceRequestDto
     * @param id
     * @return
     */
    public ResponseEntity<?> update(Long id,@RequestBody RbacResourceRequestDto rbacResourceRequestDto) {
        RbacResourceResponseDto rbacResourceResp = rbacResourceService.update(id,rbacResourceRequestDto);
        return super.doResource(rbacResourceResp, this.getClass());
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    public ResponseEntity<?> details( Long id) {
        RbacResourceResponseDto rbacResourceResp = rbacResourceService.details(id);
        return super.doResource(rbacResourceResp, this.getClass());
    }

    public HttpEntity<PagedModel<?>> getPageData(
            int pageNumber,
            int pageSize,
            String sortTypes,
            ServletRequest request) {
        // 获取搜索参数
        Map<String, Object> searchParams = super.getSearchParamStartWith(request, IConstants.EMPTY_SEARCH_PREFIX);
        PageInfo pageInfo = new PageInfo(pageNumber, pageSize, sortTypes);
        Page<RbacResourceResponseDto> page = rbacResourceService.getPageList(searchParams, pageInfo);
        return super.doPage(pageNumber, pageSize, sortTypes, request, this.getClass(), page);
    }

    /**
     * 条件搜索，返回不分页的列表
     *
     * @param request
     * @return
     */
    public ResponseEntity<?> findByParams(
            String sortTypes,
            ServletRequest request) {
        // 1.获取搜索参数
        Map<String, Object> searchParams = super.getSearchParamStartWith(request, IConstants.EMPTY_SEARCH_PREFIX);
        // 2.获取数据
        List<RbacResourceResponseDto> dataList = rbacResourceService.findByParams(searchParams,sortTypes);
        return super.doListResources(dataList, this.getClass());
    }
}
