package cn.cocowwy.spring.restendpoints;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class RestTemplateDemo {
    private static final RestTemplate restTemplate = new RestTemplate();

    public void restTemplateDemo() {
        // 从可变参数设置url
        String result1 = restTemplate.getForObject(
                "https://example.com/hotels/{hotel}/bookings/{booking}", String.class, "42", "21");

        // 从map中获取参数设置url
        Map<String, String> vars = Collections.singletonMap("hotel", "42");
        String result2 = restTemplate.getForObject(
                "https://example.com/hotels/{hotel}/rooms/{hotel}", String.class, vars);

        // 设置请求头 获取请求头等
        String uriTemplate = "https://example.com/hotels/{hotel}";
        URI uri = UriComponentsBuilder.fromUriString(uriTemplate).build(42);
        RequestEntity<Void> requestEntity = RequestEntity.get(uri)
                .header("MyRequestHeader", "MyValue")
                .build();
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
        String responseHeader = response.getHeaders().getFirst("MyResponseHeader");
        String body = response.getBody();
    }
}
