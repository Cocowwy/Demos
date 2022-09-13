package cn.cocowwy.graphqlspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraphqlSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlSpringApplication.class, args);
    }

}
//
//
//@Service
//class Test implements ApplicationRunner {
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//
//        HttpGraphQlClient graphqlClient = HttpGraphQlClient.builder(WebClient.create("localhost:8080/")).build();
//
//        String document = "{" +
//                "  project(slug:\"spring-framework\") {" +
//                "   name" +
//                "   releases {" +
//                "     version" +
//                "   }" +
//                "  }" +
//                "}";
//
//        Mono<Project> projectMono = graphqlClient.document(document)
//                .retrieve("project")
//                .toEntity(Project.class);
//
//
//        projectMono.subscribe(System.out::println);
//    }
//}