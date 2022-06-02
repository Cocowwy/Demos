package cn.cocowwy.beautifulcode.subscribe.domain.rq;

import cn.cocowwy.beautifulcode.subscribe.enums.MemberDtoEnum;
import lombok.Data;

import java.util.List;

/**
 * 自定义请求参数，可扩展
 * @author cocowwy.cn
 * @create 2022-06-06-17:35
 */
@Data
public class MemberListRequest {
    /**
     * 是否返回全部，默认false，此时返回所有memberNos指定的数据，
     * 为true时则返回所有数据
     */
    private Boolean all = Boolean.FALSE;
    /**
     * 查询的memberNos集合
     */
    private List<String> memberNos;
    /**
     * 返回值类型
     * {@link MemberDtoEnum}
     */
    private MemberDtoEnum returnType;
}
