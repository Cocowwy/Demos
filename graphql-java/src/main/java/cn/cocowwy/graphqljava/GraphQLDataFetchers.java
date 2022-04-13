package cn.cocowwy.graphqljava;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 提取器
 *
 * 对于 GraphQL Java 服务器来说，最重要的概念可能是 DataFetcher：DataFetcher 在执行查询时获取一个字段的数据。
 * 它为在查询中遇到的每个字段调用适当的 DataFetcher。 DataFetcher 是一个具有单个方法的接口,
 * 架构中的每个字段都有一个与之关联的 DataFetcher。如果您没有为特定字段指定任何 DataFetcher，则使用默认的 PropertyDataFetcher
 *
 * 里面主要定义一些
 * 提取方法
 * 返回  DataFetcher 函数式接口
 * 实现 根据 dataFetchingEnvironment 返回一个实际对象的操作
 *
 * @author cocowwy.cn
 * @create 2022-04-04-15:05
 */
@Component
public class GraphQLDataFetchers {

    private static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "book-1",
                    "name", "Harry Potter and the Philosopher's Stone",
                    "pageCount", "223",
                    "authorId", "author-1"),
            ImmutableMap.of("id", "book-2",
                    "name", "Moby Dick",
                    "pageCount", "635",
                    "authorId", "author-2"),
            ImmutableMap.of("id", "book-3",
                    "name", "Interview with the vampire",
                    "pageCount", "371",
                    "authorId", "author-3")
    );

    private static List<Map<String, String>> authors = Arrays.asList(
            ImmutableMap.of("id", "author-1",
                    "firstName", "Joanne",
                    "lastName", "Rowling"),
            ImmutableMap.of("id", "author-2",
                    "firstName", "Herman",
                    "lastName", "Melville"),
            ImmutableMap.of("id", "author-3",
                    "firstName", "Anne",
                    "lastName", "Rice")
    );

    /**
     * 返回一个根据参数获取数据的函数
     * @return
     */
    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books
                    .stream()
                    .filter(book -> book.get("id").equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            /**
             * 获取 父字段的提取结果
             *
             * GraphQL 中每个字段的 DataFetcher 以自上而下的方式调用，父级的结果是子级 DataFetcherEnvironment 的源属性
             */
            Map<String,String> book = dataFetchingEnvironment.getSource();
            String authorId = book.get("authorId");
            return authors
                    .stream()
                    .filter(author -> author.get("id").equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }
}
