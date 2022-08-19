package cn.cocowwy.arthas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@RestController
public class TestController {
    private volatile boolean cycle = true;

    @GetMapping("/test")
    public String test() {
        Thread.currentThread().setName("test" + System.currentTimeMillis());
        int i = 1;
        while (cycle) {
            i++;
        }
        return "test";
    }

    @GetMapping("/label/{data}")
    public String out(@PathVariable("data")Boolean data) {
        cycle = data;
        return "test";
    }
}
