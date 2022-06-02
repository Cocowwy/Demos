package cn.cocowwy.beautifulcode.subscribe;

import cn.cocowwy.beautifulcode.subscribe.client.MemberClient;
import cn.cocowwy.beautifulcode.subscribe.domain.rq.MemberListRequest;
import cn.cocowwy.beautifulcode.subscribe.enums.MemberDtoEnum;
import cn.cocowwy.common.dto.Result;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-18:06
 */
@Service
public class SubscribeRunner implements ApplicationRunner {
    @Autowired
    private MemberClient memberClient;

    @Override
    public void run(ApplicationArguments args) {
        MemberDtoEnum type = MemberDtoEnum.MEMBER_V3_DTO;
        MemberListRequest request = new MemberListRequest();
        request.setReturnType(type);
        request.setMemberNos(Arrays.asList("1", "2", "3", "4", "5", "6", "10", "128"));
        Result<List<?>> results = memberClient.list(request);
        BeanUtil.copyToList(results.getData(), type.getClazz())
                .forEach(System.out::println);
    }
}
