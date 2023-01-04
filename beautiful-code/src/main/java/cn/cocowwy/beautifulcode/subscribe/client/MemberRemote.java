package cn.cocowwy.beautifulcode.subscribe.client;

import cn.cocowwy.beautifulcode.subscribe.domain.Member;
import cn.cocowwy.beautifulcode.subscribe.domain.rq.MemberListRequest;
import cn.cocowwy.common.dto.Result;
import cn.hutool.core.bean.BeanUtil;
import org.jeasy.random.EasyRandom;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-12:23
 */
@Service
public class MemberRemote implements MemberClient {

    // mock data
    private static final Map<String, Member> DATAS = new HashMap<>(150);

    @PostConstruct
    void initData() {
        EasyRandom easyRandom = new EasyRandom();
        for (int i = 0; i < 150; i++) {
            Member member = easyRandom.nextObject(Member.class);
            member.setMemberNo(String.valueOf(i));
            DATAS.put(String.valueOf(i), member);
        }
    }

    @Override
    public Result<List<?>> list(MemberListRequest request) {
        if (request.getAll()) {
            return Result.success(BeanUtil.copyToList(DATAS.values(), request.getReturnType().getClazz(), null));
        }

        return Result.success(request.getMemberNos().stream()
                .map(no -> BeanUtil.copyProperties(DATAS.get(no), request.getReturnType().getClazz()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }
}
