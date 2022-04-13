package cn.cocowwy.graphqljava;

import org.mountcloud.graphql.GraphqlClient;
import org.mountcloud.graphql.request.query.DefaultGraphqlQuery;
import org.mountcloud.graphql.request.query.GraphqlQuery;
import org.mountcloud.graphql.request.result.ResultAttributtes;
import org.mountcloud.graphql.response.GraphqlResponse;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class GraphqlJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlJavaApplication.class, args);
    }
}

/**
 * Http发送方式 --> CV 自Postman
 *
 * POST /graphql HTTP/1.1
 * Host: localhost:8080
 * Content-Type: application/json
 * Content-Length: 129
 *
 * {"query":"{\r\n    bookById(id:\"book-1\"){\r\n        id\r\n        name\r\n        pageCount\r\n    }\r\n}\r\n","variables":{}}
 *
 */
@Component
class TestClient implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 测试地址
        String serverUrl = "http://localhost:8080/graphql";
        GraphqlClient graphqlClient = GraphqlClient.buildGraphqlClient(serverUrl);
        Map<String, String> httpHeaders = new HashMap<>();
        httpHeaders.put("content-type", "application/json");
        graphqlClient.setHttpHeaders(httpHeaders);
        GraphqlQuery query = new DefaultGraphqlQuery("bookById");
        // id = book-1
        query.addParameter("id", "book-1");
        query.addResultAttributes("name");

        // 同层 author 添加子属性
        ResultAttributtes authorAttributte = new ResultAttributtes("author");
        authorAttributte.addResultAttributes("firstName");
        authorAttributte.addResultAttributes("lastName");

        query.addResultAttributes(authorAttributte);
        try {
            //执行query
            GraphqlResponse response = graphqlClient.doQuery(query);
            //获取数据，数据为map类型
            Map result = response.getData();
            System.out.println(result.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}