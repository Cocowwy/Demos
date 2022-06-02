package cn.cocowwy.beautifulcode.subscribe.client;

import cn.cocowwy.beautifulcode.subscribe.domain.rq.MemberListRequest;
import cn.cocowwy.common.dto.Result;

import java.util.List;

/**
 * 假设当前接口为对外的RPC接口（如fegin的接口定义），通过订阅以及扩展字段，来简化IO的传输数据量
 * @author cocowwy.cn
 * @create 2022-06-06-11:27
 */

public interface MemberClient {
    Result<List<?>> list(MemberListRequest memberListRequest);
}
