package cn.cocowwy.spring.webflux;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@RestController
public class HelloController {

    @GetMapping("/monoHello")
    public Mono<String> monoHello() throws InterruptedException {
        long start = System.currentTimeMillis();
        Mono<String> hello = Mono.fromSupplier(() -> {
            try {
                return getHelloStr();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("接口耗时：" + (System.currentTimeMillis() - start));
        return hello;
    }

    private String getHelloStr() throws InterruptedException {
        Thread.sleep(3000L);
        return "hello webflux";
    }

    @GetMapping(value = "/flux",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        Flux<String> flux = Flux.fromArray(new String[]{"小黑","小胖","小六","一鑫"}).map(s -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "二班：" + s;
        });
        return flux;
    }
}
