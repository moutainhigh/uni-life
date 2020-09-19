package com.uni.service.use.biz;

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
import com.uni.service.use.dto.request.UseRorgRequestDto;
import com.uni.service.use.dto.response.UseRorgResponseDto;
import com.uni.service.use.service.UseRorgService;

/**
 * controller for UseRorg
 * 这是类的注释
 *
 * @author lait
 * Created On 2020-08-26.
 */
@Service
public class UseRorgBiz {

    @Autowired
    private UseRorgService useRorgService;

    /**
     * 新增
     *
     * @param useRorgRequestDto
     * @return
     */
    public ResponseEntity<?> create(UseRorgRequestDto useRorgRequestDto) {
        UseRorgResponseDto useRorgResp = useRorgService.create(useRorgRequestDto);
        return super.doResource(useRorgResp, this.getClass());
    }

    /**
     * 根据主键删除，支持批量主键删除
     *
     * @param ids
     * @return
     */
    public ResponseDto<?> delete(@PathVariable List<Long> ids) {
        useRorgService.deleteByIds(ids);
        return ResponseDto.success(null);
    }

    /**
     * 更新
     *
     * @param UseRorgRequestDto
     * @param id
     * @return
     */
    public ResponseEntity<?> update(Long id,@RequestBody UseRorgRequestDto useRorgRequestDto) {
        UseRorgResponseDto useRorgResp = useRorgService.update(id,useRorgRequestDto);
        return super.doResource(useRorgResp, this.getClass());
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    public ResponseEntity<?> details( Long id) {
        UseRorgResponseDto useRorgResp = useRorgService.details(id);
        return super.doResource(useRorgResp, this.getClass());
    }

    public HttpEntity<PagedModel<?>> getPageData(
            int pageNumber,
            int pageSize,
            String sortTypes,
            ServletRequest request) {
        // 获取搜索参数
        Map<String, Object> searchParams = super.getSearchParamStartWith(request, IConstants.EMPTY_SEARCH_PREFIX);
        PageInfo pageInfo = new PageInfo(pageNumber, pageSize, sortTypes);
        Page<UseRorgResponseDto> page = useRorgService.getPageList(searchParams, pageInfo);
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
        List<UseRorgResponseDto> dataList = useRorgService.findByParams(searchParams,sortTypes);
        return super.doListResources(dataList, this.getClass());
    }
}
