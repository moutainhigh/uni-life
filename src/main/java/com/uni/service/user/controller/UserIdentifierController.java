package com.uni.service.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uni.framework.crud.base.BaseEndpoint;
import com.uni.framework.crud.base.dto.ResponseDto;
import com.uni.framework.crud.base.utils.IConstants;
import com.uni.framework.crud.base.utils.PageInfo;
import com.uni.service.user.dto.request.UserIdentifierRequestDto;
import com.uni.service.user.dto.response.UserIdentifierResponseDto;
import com.uni.service.user.service.UserIdentifierService;

/**
 * controller for UserIdentifier
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-10-04.
 */
@RestController
@RequestMapping("/userIdentifier")
public class UserIdentifierController extends BaseEndpoint {

    @Autowired
    private UserIdentifierService userIdentifierService;

    /**
     * 新增
     *
     * @param userIdentifierRequestDto
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody UserIdentifierRequestDto userIdentifierRequestDto) {
        UserIdentifierResponseDto userIdentifierResp = userIdentifierService.create(userIdentifierRequestDto);
        return super.doResource(userIdentifierResp, this.getClass());
    }

    /**
     * 根据主键删除，支持批量主键删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public ResponseDto<?> delete(@PathVariable List<Long> ids) {
        userIdentifierService.deleteByIds(ids);
        return ResponseDto.success(null);
    }

    /**
     * 更新
     *
     * @param UserIdentifierRequestDto
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody UserIdentifierRequestDto userIdentifierRequestDto) {
        UserIdentifierResponseDto userIdentifierResp = userIdentifierService.update(id,userIdentifierRequestDto);
        return super.doResource(userIdentifierResp, this.getClass());
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {
        UserIdentifierResponseDto userIdentifierResp = userIdentifierService.details(id);
        return super.doResource(userIdentifierResp, this.getClass());
    }

    @Override
    @GetMapping("")
    public HttpEntity<PagedModel<?>> getPageData(
            @RequestParam(value = IConstants.DEFAULT_PAGE_NUM_FIELD, defaultValue = IConstants.DEFAULT_PAGE_NUM_VAL) int pageNumber,
            @RequestParam(value = IConstants.DEFAULT_PAGE_SIZE_FIELD, defaultValue = IConstants.DEFAULT_PAGE_SIZE_VAL) int pageSize,
            @RequestParam(value = IConstants.DEFAULT_SORT_TYPES_FIELD, defaultValue = IConstants.DEFAULT_SORT_TYPE_VAL) String sortTypes,
            ServletRequest request) {
        // 获取搜索参数
        Map<String, Object> searchParams = super.getSearchParamStartWith(request, IConstants.EMPTY_SEARCH_PREFIX);
        PageInfo pageInfo = new PageInfo(pageNumber, pageSize, sortTypes);
        Page<UserIdentifierResponseDto> page = userIdentifierService.getPageList(searchParams, pageInfo);
        return super.doPage(pageNumber, pageSize, sortTypes, request, this.getClass(), page);
    }

    /**
     * 条件搜索，返回不分页的列表
     *
     * @param request
     * @return
     */
    @GetMapping("/find/params")
    public ResponseEntity<?> findByParams(
            @RequestParam(value = IConstants.DEFAULT_SORT_TYPES_FIELD, defaultValue = IConstants.DEFAULT_SORT_TYPE_VAL) String sortTypes,
            ServletRequest request) {
        // 1.获取搜索参数
        Map<String, Object> searchParams = super.getSearchParamStartWith(request, IConstants.EMPTY_SEARCH_PREFIX);
        // 2.获取数据
        List<UserIdentifierResponseDto> dataList = userIdentifierService.findByParams(searchParams,sortTypes);
        return super.doListResources(dataList, this.getClass());
    }
}
