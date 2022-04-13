package cn.cocowwy.graphqljava;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * @author cocowwy.cn
 * @create 2022-04-04-15:04
 */

@Component
public class GraphQLProvider {
    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    private GraphQL graphQL;

    /**
     * 向容器中注入 GraphQL 的 Bean
     * 就像启动了一个GraphQL服务 来接受请求一样
     * @return
     */
    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    /**
     * 根据 .graphqls 初始化 GraphQL
     * @throws IOException
     */
    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    /**
     * 根据 .graphqls的信息  构建 GraphQL
     * @param sdl .graphqls文件的内容
     * @return
     */
    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        /**
         * 构建一些映射之类的吧
         */
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    /**
     *      详细相关格式描述参考该文档
     *      https://graphql.cn/learn/schema/
     *
     *      schema.graphqls
     *
     *      type Query {
     *          bookById(id: ID): Book
     *       }
     *
     *      type Book {
     *         id: ID
     *         name: String
     *         pageCount: Int
     *         author: Author
     *       }
     *
     *      type Author {
     *         id: ID
     *         firstName: String
     *         lastName: String
     *       }
     *
     * @return
     */
    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher()))
                .type(newTypeWiring("Book")
                        .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()))
                .build();
    }
}



