package cn.cocowwy.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class TestService {
    @SentinelResource(value = "sayHello", blockHandler = "blockHandler")
    public String sayHello(String name) {
        return "Hello, " + name;
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String blockHandler(String name, BlockException ex) {
        throw new RuntimeException("exceptionHandler name:" + name);
    }
}
