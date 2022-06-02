package cn.cocowwy.beautifulcode.subscribe.client;

import cn.cocowwy.common.dto.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-12:23
 */
@Service
public class MemberRemote implements MemberClient {

    @Override
    public Result<List<?>> findByList() {
        return null;
    }
}
