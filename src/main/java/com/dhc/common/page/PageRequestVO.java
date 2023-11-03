package com.dhc.common.page;

import com.dhc.common.filter.BaseFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @Author donghongchen
 * @create 2023/10/17 11:10
 * @Description:
 */
@Data
public class PageRequestVO {

    @Schema(title = "长度", example = "0")
    private Integer length;

    @Schema(title = "页数", example = "1")
    private Integer page;

    @Schema(title = "是否计算长度", description = "为true则page>1时也返回总长度")
    private Boolean countTotal;

    @Schema(title = "请求时间戳", description = "请求第一页时返回，后续page>1时需要回传，防止时间逆序查询时新增数据导致分页数据重复")
    private Long timestamp;

    @Schema(title = "数据总量", description = "请求第一页时返回，可通过countTotal参数使page>1时也回传该值")
    private Long total;

    @Schema(title = "分页最大长度", description = "超出报异常", hidden = true)
    private int pageMaxLength = 500;

    public <F extends BaseFilter<T>, T, VO> PageVO<VO> pageable(F filter,
                                                                Function<F, List<T>> query,
                                                                Function<List<T>, List<VO>> mapper) {
        configureFilter(filter);
        List<T> models = query.apply(filter);
        return createResponse(models, mapper);

    }

    private <T, VO> PageVO<VO> createResponse(List<T> models, Function<List<T>, List<VO>> mapper) {
        List<VO> vos;
        if (!CollectionUtils.isEmpty(models)) {
            vos = mapper.apply(models);
        } else {
            vos = Collections.emptyList();
        }

        int page = getRealPage();
        int length = getRealLength();

        PageVO<VO> pageVO = new PageVO<>();
        pageVO.setLength(length);
        pageVO.setPage(page);
        pageVO.setList(vos);
        pageVO.setTotal(this.getTotal());
        pageVO.setTimestamp(page <= 1 ? Long.valueOf(System.currentTimeMillis()) : this.getTimestamp());

        return pageVO;
    }

    public <F extends BaseFilter<T>, T> void configureFilter(BaseFilter<T> filter) {
        int length = getRealLength();

        if (length > pageMaxLength) {
            throw new IllegalArgumentException("分页长度不能超过" + pageMaxLength);
        }

        int page = getRealPage();
        int offset = length * (page - 1);

        filter.setLength(length);
        filter.setPage(page);
        filter.setOffset(offset);
        if (Boolean.TRUE.equals(this.getCountTotal())) {
            filter.setTotalHolder(this::setTotal);
        }

    }

    private int getRealPage() {
        return this.getPage() == null || this.getPage() <= 0 ? 1 : this.getPage();
    }

    private int getRealLength() {
        return this.getLength() != null ? this.getLength() : 20;
    }

}
