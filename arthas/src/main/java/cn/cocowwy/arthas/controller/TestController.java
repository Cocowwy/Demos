package cn.cocowwy.arthas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * cd /Users/cocowwy/Desktop/space/idea-space/Demos/arthas/src/main/resources/static
 * 启动arthas： java -jar arthas-boot.jar
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@RestController
public class TestController {
    private boolean cycle = true;

    /**
     * curl localhost:8080/test
     * @return
     */
    @GetMapping("/test")
    public String test() {
        Thread.currentThread().setName("test" + System.currentTimeMillis());
        int i = 1;
        while (cycle) {
            i++;
        }
        return "test";
    }

    /**
     * curl localhost:8080/label/false
     * @param data
     * @return
     */
    @GetMapping("/label/{data}")
    public String out(@PathVariable("data") Boolean data) {
        cycle = data;
        return "test";
    }

    /**
     * curl localhost:8080/gc
     * @return
     */
    @GetMapping("/gc")
    public String gc() {
        System.gc();
        return "OK";
    }
}
