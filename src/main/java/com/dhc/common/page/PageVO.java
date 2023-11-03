package com.dhc.common.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author donghongchen
 * @create 2023/10/17 14:11
 * @Description:
 */
@Data
public class PageVO<VO> {

    @Schema(title = "分页长度")
    private Integer length;

    @Schema(title = "当前页数")
    private Integer page;

    @Schema(title = "总数量", description = "page=1时生成，否则返回客户端上传的值")
    private Long total;

    @Schema(title = "返回值数据")
    private List<VO> list;

    @Schema(title = "请求时间戳", description = "page=1时生成")
    private Long timestamp;

}
