package cn.cocowwy.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.4.jar
 */
@SpringBootApplication
public class SentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
    }
}


@Service
class TestService {
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}

@RestController
class TestController {
    @Autowired
    private TestService service;

    /**
     * fallback管理的是java运行异常。blockHandler管sentinel的控制台违规配置。
     * 若blockHandler和fallback都进行了配置，则被限流降级而抛出BlockException时只会进入blockHandler处理逻辑
     *
     * 如果 处理逻辑在其他的类  则需要使用static并且指定类名
     *
     * @return
     */
    @GetMapping(value = "/hello/{name}")
    @SentinelResource(value = "apiHello", blockHandler = "exceptionHandler", fallback = "helloFallback")
    public String apiHello(@PathVariable String name) {
        return service.sayHello(name);
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public static String helloFallback(String s) {
        System.out.println("helloFallback");
        return String.format("Halooooo %d", s);
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public static String exceptionHandler(String s, BlockException ex) {
        System.out.println("exceptionHandler");
        // Do some log here.
        ex.printStackTrace();
        return "Oops, error occurred at " + s;
    }
}